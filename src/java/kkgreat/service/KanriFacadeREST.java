/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import kkgreat.ApproveDto;
import kkgreat.Const;
import kkgreat.Kankyou;
import kkgreat.Kanri;
import kkgreat.KanriDel;
import kkgreat.utility.Utility;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import kkgreat.DtoKanri;
import net.sf.jasperreports.engine.JRPrintPage;
import sun.awt.DisplayChangedListener;

/**
 *
 * @author great_kaneko
 */
@Stateless
@Path("kanri")
public class KanriFacadeREST extends AbstractFacade<Kanri> {
    @PersistenceContext(unitName = "UkewatashiPU")
    private EntityManager em;

    public KanriFacadeREST() {
        super(Kanri.class);
    }
    
    /*
    * データテーブルリスト用ログイン時初期化
    * Select条件が初期化で決まっているSQL句を実行する
    * KanriEntity名前付きクエリー実行
    */
    @TokenSecurity
    @POST
    @Path("getinitlist")
    @Consumes({"application/json"})
    public List<Kanri> getInitList(Kanri entity) {
        TypedQuery<Kanri> q = getEntityManager().createNamedQuery("Kanri.defaultSelect", Kanri.class);
        q.setParameter("userId", entity.getUserId());
        
        return q.setFirstResult(0)
                .setMaxResults(entity.getLimit())
                .getResultList();
    }
    
    /*
    * データテーブルリスト用検索処理
    * Status絞込み、JLX、JLXHS、承認Status絞込み項目選択処理
    * 動的JPQLを作成して実行
    */
    @TokenSecurity
    @POST
    @Path("getlist")
    @Consumes({"application/json"})
    public List<Kanri> getList(Kanri entity) {
        String query = "";
        StringBuffer sb = new StringBuffer("select k from Kanri k");
        
        /* JLX/JLXHSボタンのどちらかが選択されていない時は担当者申請者データのみ
        *  所属員データが選択されている時は、所属員データ(所属長のみ)
        *  そうでない時は会社内全データ閲覧可能(管理者のみ)
        */
        //if (Objects.isNull(entity.getsKaisha())) {
        if ( Objects.isNull(entity.getsKaisha()) && !entity.issBusho() ) {
            sb.append(" where (k.tantoushaUserId = ");
            sb.append(Utility.sqlStringFormat(entity.getUserId()));
            sb.append(" OR k.shinseishaUserId = ");
            sb.append("'");
            sb.append(entity.getUserId());
            sb.append("'");
            sb.append(")");
        
        } else if (entity.issBusho()) {
            sb.append(" where (k.shinseishaKaisha = ");
            sb.append( Utility.sqlStringFormat(entity.getTantoushaKaisha()) );
            sb.append(" AND k.shinseishaTeam = ");
            sb.append( Utility.sqlStringFormat(entity.getTantoushaTeam()) );
            sb.append(")");
        } else {
            String kaishas[] = entity.getsKaisha();
            switch (kaishas.length) {
                case 1:
                    sb.append(" where k.shinseishaKaisha = ");
                    sb.append( Utility.sqlStringFormat(kaishas[0]) );
                    break;
                default:
                    sb.append(" where (k.shinseishaKaisha = ");
                    sb.append( Utility.sqlStringFormat(kaishas[0]) );
                    sb.append(" OR k.shinseishaKaisha = ");
                    sb.append( Utility.sqlStringFormat(kaishas[1]) );
                    sb.append(")");
                    break;
            }
        }
        
        // 書類Status絞込み
        switch (Long.toString( entity.getStatus() )) {
            case Const.STATUS_ALL:
                sb.append(" AND (k.status BETWEEN -1 AND 3)");
                break;
            case Const.STATUS_DLVRY:
                sb.append(" AND k.status = -1");
                break;
            case Const.STATUS_OK:
                sb.append(" AND (k.status = 1 OR k.status = 2)");
                break;
            case Const.STATUS_NOT:
                sb.append(" AND (k.status = 0 OR k.status = 3)");
                break;
            case Const.STATUS_NG:
                sb.append(" AND k.status = 3");
                break;
        }
        // 承認ステータス絞込み
        switch (Long.toString( entity.getStatusApp() )) {
            case Const.STATUS_APP_NOT:
                sb.append(" AND k.statusApp = 0");
                break;
            case Const.STATUS_APP_OK:
                sb.append(" AND k.statusApp = 10");
                break;
            case Const.STATUS_APP_ALL:
                sb.append(" AND (k.statusApp = 0 OR k.statusApp = 10)");
                break;
        }
        // 管理No以前検索 管理NO絞込み指定時検索しない
        if (Objects.nonNull( entity.getBeforeId() )) {
            sb.append(" AND k.id <= ");
            sb.append(entity.getBeforeId());
        }
        
        sb.append(" ORDER BY k.id DESC");
        query = sb.toString();
        System.out.println(query);
        TypedQuery<Kanri> q = getEntityManager().createQuery(query, Kanri.class);
        q.setFirstResult(0);
        q.setMaxResults(entity.getLimit());
        return q.getResultList();
    }
    
    /*
    * 書類新規登録
    * idはkanriテーブルから取得(kanri_max)、登録完了後、更新する
    */
    @TokenSecurity
    @POST
    @Path("createkanri")
    @Consumes({"application/json"})
    public Kanri createkanri(Kanri entity) {
        /*
        TypedQuery<Long> q = getEntityManager().createNamedQuery("Kanri.maxId", Long.class);
        Long maxId = (Long)q.getSingleResult();
        maxId += 1L;
        System.out.println(maxId);
        */
        
        /*
        *   idの割り当ては、Kankyouテーブルkanri_maxより取得し、KanriにInsert時に同時に更新している
        *   Kanri_delにコピー保存しているので両テーブルでidの重複を防止している
        */
        Kankyou kankyou = getEntityManager().find(Kankyou.class, 1L);
        Long maxId = kankyou.getKanriMax();
        maxId += 1L;
        entity.setId(maxId);
        super.create(entity);
        super.flush();                      //継承クラスにflushメソッドを追加、entityManagerに即時insert発行
        kankyou.setKanriMax(maxId);         //Kankyoテーブルkanri_max値を更新 kanri.idをセット
        getEntityManager().merge(kankyou);
        super.flush();
       
        /* NotNullは空文字''もNG 確認作業として以下
        Kanri kanri = new Kanri();
        kanri.setId(257079L);
        //kanri.setId(maxId);
        //kanri.setStatus(0L);
        kanri.setStatus(entity.getStatus());
        //kanri.setStatusApp(0L);
        kanri.setStatusApp(entity.getStatusApp());
        //kanri.setDlvry("受渡");
        kanri.setDlvry(entity.getDlvry());
        //kanri.setHokengaisha("AFLAC");
        kanri.setHokengaisha(entity.getHokengaisha());
        //kanri.setSeihobun(0L);
        kanri.setSeihobun(entity.getSeihobun());
        //kanri.setKeiyakusha("金子　丘");
        kanri.setKeiyakusha(entity.getKeiyakusha());
        //kanri.setKubun("契約書申込書");
        kanri.setKubun(entity.getKubun());
        //kanri.setTantoushaUserId("kanrisha");
        kanri.setTantoushaUserId(entity.getTantoushaUserId());
        //kanri.setTantousha("管理者ユーザー");
        kanri.setTantousha(entity.getTantousha());
        //kanri.setTantoushaKaisha("JLX");
        kanri.setTantoushaKaisha(entity.getTantoushaKaisha());
        //kanri.setTantoushaTeam("test");
        kanri.setTantoushaTeam(entity.getTantoushaTeam());
        kanri.setSakuseibi("2019/12/18");
        kanri.setKubun(entity.getKubun());
        kanri.setHokenTantou("保険担当oo");
        kanri.setSeiho("生保");
        kanri.setShoukenbango("222222");
        kanri.setShinseisha("管理者ユーザー");
        kanri.setShinseishaKaisha("JLX");
        kanri.setShinseishaUserId("kanrisha");
        */
        //super.create(kanri);
        //super.edit(kanri);
        //super.flush();   
        
        Kanri findKanri = super.find(maxId);
        Long id = findKanri.getId();
        if (Objects.isNull(id)) {
            return null;
        } else {
            return super.find(id);
        }
    }
    
    /*
    * 書類編集更新
    */
    @TokenSecurity
    @POST
    @Path("updatekanri")
    @Consumes({"application/json"})
    public Kanri updateKanri(Kanri entity) {
        super.edit(entity);
        super.flush();                                  //継承クラスにflushメソッドを追加、entityManagerに即時insert発行
        Kanri findKanri = super.find(entity.getId());
        Long id = findKanri.getId();
        if (Objects.isNull(id)) {
            return null;
        } else {
            return super.find(id);
        }
    }
    
    /*
    * 書類削除
    * 削除テーブルに削除データを登録(削除データ閲覧用)
    * EntityManagerのpersistは、
    * KanriDelエンティティを渡すことでKanriDelテーブルへ登録される
    */
    @TokenSecurity
    @POST
    @Path("deletekanri")
    @Produces({"text/plain"})
    @Consumes({"application/json"})
    public Integer removeKanri(Kanri entity) {   
        KanriDel kanriDel = setKanriDel(entity);
        getEntityManager().persist(kanriDel);
        super.remove(entity);
        super.flush();
        
        return 0;
    }
    
    /*
    *   削除用エンティティにデータセット処理
    *
    */
    public KanriDel setKanriDel(Kanri kanri) {
        KanriDel kanriDel = new KanriDel();
        
        kanriDel.setId(kanri.getId());
        kanriDel.setStatus(kanri.getStatus());
        kanriDel.setStatusApp(kanri.getStatusApp());
        kanriDel.setDlvry(kanri.getDlvry());
        kanriDel.setHokengaisha(kanri.getHokengaisha());
        kanriDel.setHokenTantou(kanri.getHokenTantou());
        kanriDel.setSeihobun(kanri.getSeihobun());
        kanriDel.setSeiho(kanri.getSeiho());
        kanriDel.setShoukenbango(kanri.getShoukenbango());
        kanriDel.setKeiyakusha(kanri.getKeiyakusha());
        kanriDel.setKubun(kanri.getKubun());
        kanriDel.setOkng(kanri.getOkng());
        kanriDel.setShoruiUmu(kanri.getShoruiUmu());
        kanriDel.setShorui1(kanri.getShorui1());
        kanriDel.setOkng1(kanri.getOkng1());
        kanriDel.setShorui2(kanri.getShorui2());
        kanriDel.setOkng2(kanri.getOkng2());
        kanriDel.setShorui3(kanri.getShorui3());
        kanriDel.setOkng3(kanri.getOkng3());
        kanriDel.setShorui4(kanri.getShorui4());
        kanriDel.setOkng4(kanri.getOkng4());
        kanriDel.setShorui5(kanri.getShorui5());
        kanriDel.setOkng5(kanri.getOkng5());
        kanriDel.setShorui6(kanri.getShorui6());
        kanriDel.setOkng6(kanri.getOkng6());
        kanriDel.setShorui7(kanri.getShorui7());
        kanriDel.setOkng7(kanri.getOkng7());
        kanriDel.setShorui8(kanri.getShorui8());
        kanriDel.setOkng8(kanri.getOkng8());
        kanriDel.setShorui9(kanri.getShorui9());
        kanriDel.setOkng9(kanri.getOkng9());
        kanriDel.setShorui10(kanri.getShorui10());
        kanriDel.setOkng10(kanri.getOkng10());
        kanriDel.setOkShoruiIchiran(kanri.getOkShoruiIchiran());
        kanriDel.setFubiShoruiIchiran(kanri.getFubiShoruiIchiran());
        kanriDel.setShoruiMaisu(kanri.getShoruiMaisu());
        kanriDel.setBikou(kanri.getBikou());
        kanriDel.setHokenBikou(kanri.getHokenBikou());
        kanriDel.setTantoushaUserId(kanri.getTantoushaUserId());
        kanriDel.setTantousha(kanri.getTantousha());
        kanriDel.setTantoushaKaisha(kanri.getTantoushaKaisha());
        kanriDel.setTantoushaTeam(kanri.getTantoushaTeam());
        kanriDel.setShinseishaUserId(kanri.getShinseishaUserId());
        kanriDel.setShinseisha(kanri.getShinseisha());
        kanriDel.setShinseishaKaisha(kanri.getShinseishaKaisha());
        kanriDel.setShinseishaTeam(kanri.getShinseishaTeam());
        kanriDel.setKakuninsha(kanri.getKakuninsha());
        kanriDel.setSakuseibi(kanri.getSakuseibi());
        kanriDel.setSaishuHenshubi(kanri.getSaishuHenshubi());
        kanriDel.setKakuninbi(kanri.getKakuninbi());
        kanriDel.setSaishuKakuninbi(kanri.getSaishuKakuninbi());
        kanriDel.setSakujyoriyu(kanri.getSakujyoriyu());
        kanriDel.setSakujyosha(kanri.getSakujyosha());
        kanriDel.setShouninsha(kanri.getShouninsha());
        kanriDel.setShouninbi(kanri.getShouninbi());
        kanriDel.setMishouninsha(kanri.getMishouninsha());
        kanriDel.setMishouninbi(kanri.getMishouninbi());
        
        return kanriDel;
    }
    
    /*
    * 一括承認・承認戻し処理
    *　statusApp:0(未承認), 10(承認済)と最終更新日を更新
    * 対象書類ID配列と承認モードフラグをリクエストDTOより受け取り処理する
    */
    @TokenSecurity
    @POST
    @Path("approvekanries")
    @Produces({"text/plain"})
    @Consumes({"application/json"})
    public Integer approveKanries(ApproveDto requestDto) {  
        TypedQuery<Kanri> q;
        // 承認ステータスセット リクエストDTOより承認・承認戻し判別
        if (requestDto.isSetApprove()) {
            q = getEntityManager().createNamedQuery("Kanri.approveKanries", Kanri.class);
            q.setParameter("statusValue", Const.STATUS_APPROVE );
            q.setParameter("shouninsha", requestDto.getShouninsha());
            q.setParameter("shouninbi", requestDto.getShouninbi());
        } else {
            q = getEntityManager().createNamedQuery("Kanri.approveNotKanries", Kanri.class);
            q.setParameter("statusValue", Const.STATUS_NOT_APPROVE );
            q.setParameter("mishouninsha", requestDto.getMishouninsha());
            q.setParameter("mishouninbi", requestDto.getMishouninbi());
        }
        // 承認未承認書類IDセット（配列をリスト化して渡す)
        q.setParameter("ids", Arrays.asList(requestDto.getKanriIds()));
        
        q.executeUpdate();
        
        return 0;
    } 
    
    /*
    *  承認処理　1件更新
    *
    */
    @TokenSecurity
    @POST
    @Path("approvekanri")
    @Produces({"text/plain"})
    @Consumes({"application/json"})
    public Integer approveKanri(Kanri kanri) {  
        super.edit(kanri);
        return 0;
    }
    
    
    /*
    *  JasperReport帳票　PDF出力テスト用
    *　DtoKanriへデータをセット、指定パスの帳票からJasperExportManagerが指定パスにPDFを作成する
    *  java.io.Fileでファイルを読み込み、jax-rs Responseにて添付ファイル形式でPDFデータを送信する
    *  Httpタイプ　application/pdf
    */
    @TokenSecurity
    @GET
    @Path("testpdf")
    @Produces("application/pdf")
    public Response getPdfTest() {
        //jasperファイルと出力先のフォルダを指定。
        String jasperPath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/japsper/JLX_report.jasper";
        //String outputFilePath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/pdf/ukewatashi_testA4.pdf";
        String outputFilePath = "/Users/great_kaneko/NetBeansProjects/pdf/ukewatashi_testA4.pdf";
        
        //Long firstId = 257101L;
        //Kanri fKanri = super.find(firstId);
        Long[] ids = {257090L, 257086L };
        List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids));
        // 名前付きクエリSELECT IN検索
        TypedQuery<Kanri> q;
        q = getEntityManager().createNamedQuery("Kanri.findByIds", Kanri.class);
        q.setParameter("ids", idsList);
        List<Kanri> kanriList = q.getResultList();
        Iterator<Kanri> kanriIterator = kanriList.iterator();
        Kanri fKanri = kanriIterator.next();
        /*
        * パラメータ　ページヘッダー部固定値セット
        * 担当者会社名、入力担当者、受渡方法、保険会社名、保険担当者名
        */
        Map<String,Object> params = setJsperParams(fKanri);
        //フィールドデータセット(ヘッダー以下フィールド繰り返しセット処理)
        List<DtoKanri> flist = setJasperFields(kanriList);
        //帳票フィールドデータソース作成
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(flist);

        try{
            
            //抽象レポートを生成する。
            JasperPrint pdf = JasperFillManager.fillReport(jasperPath, params, ds);
            
            //PDFファイルに出力する。
            JasperExportManager.exportReportToPdfFile(pdf, outputFilePath);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        
    
        File file = new File(outputFilePath);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=ukewatashi_testA4.pdf");
        return response.build();
        
    }
    
    private Map<String,Object> setJsperParams(Kanri kanri) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("tantoushaKaisha", kanri.getTantoushaKaisha());
        params.put("tantousha", kanri.getTantousha());
        params.put("dlvry",kanri.getDlvry());
        params.put("hokengaisha", kanri.getHokengaisha());
        params.put("hokenTantou", kanri.getHokenTantou());
        
        return params;
    }
    
    private List<DtoKanri> setJasperFields(List<Kanri> kanriList) {
        
        List<DtoKanri> list = new ArrayList<DtoKanri>();
        //フィールドデータセット(繰り返し処理)
        kanriList.forEach(kanri->{
            DtoKanri fDto = new DtoKanri();        
            fDto.setId(kanri.getId());
            fDto.setStatus(kanri.getStatus());
            fDto.setKubun(kanri.getKubun());
            fDto.setShoruiMaisu(kanri.getShoruiMaisu());
            fDto.setShinseisha(kanri.getShinseisha());
            fDto.setHokengaisha(kanri.getHokengaisha());
            fDto.setShorui1(kanri.getShorui1());
            fDto.setShorui2(kanri.getShorui2());
            fDto.setShorui3(kanri.getShorui3());
            fDto.setShorui4(kanri.getShorui4());
            fDto.setShorui5(kanri.getShorui5());
            fDto.setShorui6(kanri.getShorui6());
            fDto.setShorui7(kanri.getShorui7());
            fDto.setShorui8(kanri.getShorui8());
            fDto.setShorui9(kanri.getShorui9());
            fDto.setSeiho(kanri.getSeiho());
            fDto.setShoukenbango(kanri.getShoukenbango());
            fDto.setKeiyakusha(kanri.getKeiyakusha());
            fDto.setFubiShoruiIchiran(kanri.getFubiShoruiIchiran());
            fDto.setBikou(kanri.getBikou());
            list.add(fDto);
        });
        
        return list;
    }
    /* vesion2
    @TokenSecurity
    @GET
    @Path("testpdf")
    @Produces("application/pdf")
    public Response getPdf() {
        //jasperファイルと出力先のフォルダを指定。
        String jasperPath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/japsper/Ukewatashi_A4test.jasper";
        //String outputFilePath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/pdf/ukewatashi_testA4.pdf";
        String outputFilePath = "/Users/great_kaneko/NetBeansProjects/pdf/ukewatashi_testA4.pdf";
        
        //印刷対象の受渡書類レコード指定
        Long[] ids = {257078L, 257079L, 257081L};
        List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids)); //配列List化
        //最初の要素(第１帳票)をListから取り出し要素削除
        Iterator<Long> iterator = idsList.iterator();
        Long firstId = iterator.next();
        iterator.remove();
        Kanri fKanri = super.find(firstId);
        DtoKanri fDto = new DtoKanri();
        List<DtoKanri> flist = new ArrayList<DtoKanri>();
        fDto.setId(fKanri.getId());
        fDto.setHokengaisha(fKanri.getHokengaisha());
        fDto.setKeiyakusha(fKanri.getKeiyakusha());    
        flist.add(fDto);
        // 第1帳票を作成後、複数帳票印刷の時、ページ結合する
        try {
            // 1ページ目(単一帳票)帳票データソース作成
            JRBeanCollectionDataSource fDs = new JRBeanCollectionDataSource(flist);
            //パラメータ設定用（今回は使いません。）
            Map<String,Object> fParams = new HashMap<String,Object>();
            //抽象レポートを生成する。
            JasperPrint pdf = JasperFillManager.fillReport(jasperPath, fParams, fDs);
            
            //単複帳票の時、ページ結合化
            idsList.forEach(id->{
                Kanri kanri = super.find(id);
                DtoKanri dto = new DtoKanri();
                List<DtoKanri> vlist = new ArrayList<DtoKanri>();
                dto.setId(kanri.getId());
                dto.setHokengaisha(kanri.getHokengaisha());
                dto.setKeiyakusha(kanri.getKeiyakusha());    
                vlist.add(dto);
                // 帳票データソース作成
                JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(vlist);
                //パラメータ設定用（今回は使いません。）
                Map<String,Object> params = new HashMap<String,Object>();
                try {
                //複数PDF作成　2帳票目以降としてPDFページオブジェクト
                List<JRPrintPage> pages = null;
                //2帳票目以降の抽象レポートを生成する。
                JasperPrint pdf_other = JasperFillManager.fillReport(jasperPath, params, ds);
                //2帳票目以降を最初のPDFへページ結合
                pages = pdf_other.getPages();
                for(JRPrintPage page : pages ){
                   pdf.addPage(page);
                }
                } catch(Exception e){
                    throw new RuntimeException(e);
                }
            });

            //PDFファイルに出力する。
            JasperExportManager.exportReportToPdfFile(pdf, outputFilePath);
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        /*
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(vlist);
        //test line　複数ページ作成　データソースインスタンス別に作成必要
        JRBeanCollectionDataSource ds2 = new JRBeanCollectionDataSource(vlist);
        
        //パラメータ設定用（今回は使いません。）
	Map<String,Object> params = new HashMap<String,Object>();
        //jasperファイルと出力先のフォルダを指定。
        String jasperPath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/japsper/Ukewatashi_A4test.jasper";
        //String outputFilePath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/pdf/ukewatashi_testA4.pdf";
        String outputFilePath = "/Users/great_kaneko/NetBeansProjects/pdf/ukewatashi_testA4.pdf";
        
        try{
            //複数PDF作成　PDFページ結合
            List<JRPrintPage> pages = null;
            
            //抽象レポートを生成する。
            JasperPrint pdf = JasperFillManager.fillReport(jasperPath, params, ds);

            //2つ目PDFデータ作成　作成ページを1つ目に結合
            JasperPrint pdf2 = JasperFillManager.fillReport(jasperPath, params, ds2);
            pages = pdf2.getPages();
            for(JRPrintPage page : pages ){
               pdf.addPage(page);
            }
            
            //PDFファイルに出力する。
            JasperExportManager.exportReportToPdfFile(pdf, outputFilePath);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        */
    /*
        File file = new File(outputFilePath);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=ukewatashi_testA4.pdf");
        return response.build();
        
    }
    */
    
    /* version1
    @TokenSecurity
    @GET
    @Path("testpdf")
    @Produces("application/pdf")
    public Response getPdf() {
        
        List<Kanri> list = super.findAll();
        List<DtoKanri> vlist = new ArrayList<DtoKanri>();
        list.forEach(v->{
            DtoKanri dto = new DtoKanri();
            dto.setId(v.getId());
            dto.setHokengaisha(v.getHokengaisha());
            dto.setKeiyakusha(v.getKeiyakusha());    
            vlist.add(dto);
        });
        vlist.forEach(v->{
            // System.out.println( v.getName());
        });
        
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(vlist);
        //test line　複数ページ作成　データソースインスタンス別に作成必要
        JRBeanCollectionDataSource ds2 = new JRBeanCollectionDataSource(vlist);
        
        //パラメータ設定用（今回は使いません。）
	Map<String,Object> params = new HashMap<String,Object>();
        //jasperファイルと出力先のフォルダを指定。
        String jasperPath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/japsper/Ukewatashi_A4test.jasper";
        //String outputFilePath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/pdf/ukewatashi_testA4.pdf";
        String outputFilePath = "/Users/great_kaneko/NetBeansProjects/pdf/ukewatashi_testA4.pdf";
        
        try{
            
            //複数PDF作成　PDFページ結合
            List<JRPrintPage> pages = null;
            
            //抽象レポートを生成する。
            JasperPrint pdf = JasperFillManager.fillReport(jasperPath, params, ds);

            //2つ目PDFデータ作成　作成ページを1つ目に結合
            JasperPrint pdf2 = JasperFillManager.fillReport(jasperPath, params, ds2);
            pages = pdf2.getPages();
            for(JRPrintPage page : pages ){
               pdf.addPage(page);
            }
            
            //PDFファイルに出力する。
            JasperExportManager.exportReportToPdfFile(pdf, outputFilePath);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        
        File file = new File(outputFilePath);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=ukewatashi_testA4.pdf");
        return response.build();
        
    }
    */
    
    /*
    *   テスト用メソッド　IDをIN指定GET
    */
    @TokenSecurity
    @GET
    @Path("test")
    @Produces({"application/json"})
    public List<Kanri> testGetKanri() {
        Long[] ids = { 257097L, 257090L, 257086L };
        List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids));
        // 名前付きクエリSELECT IN検索
        TypedQuery<Kanri> q;
        q = getEntityManager().createNamedQuery("Kanri.findByIds", Kanri.class);
        q.setParameter("ids", idsList);
        
        return q.getResultList();
    }
    
    /*
    *   　メイン画面チェックシート印刷ボタンより印刷画面表示用に印刷データ検索ソートして結果送信
    *     ポストデータとして、担当者ID・書類IDを受け取る(書類IDは指定なしは1となる 条件指定ID以上)
    */
    @TokenSecurity
    @POST
    @Path("getchecksheet")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public List<Kanri> getCheckSheetKanries(Kanri kanri) {
        TypedQuery<Kanri> q;
        q = getEntityManager().createNamedQuery("Kanri.findCheckSheetKanries", Kanri.class);
        q.setParameter("tantoushaUserId", kanri.getTantoushaUserId());
        q.setParameter("id", kanri.getId());
        List<Kanri> list = q.getResultList();
        
        return list;
    }
    
    /*
    *  チェックシート印刷(全件印刷、個別印刷)
    *　送信した印刷データを全件印刷の場合は、送信と同じデータを受け取り印刷処理
    *　個別印刷は新たにリスト化されたデータを受信して印刷処理
    */
    @TokenSecurity
    @POST
    @Path("printchecksheet")
    @Produces({"application/pdf"})
    @Consumes({"application/json"})
    public Response printCheckSheet(List<Kanri> list) {
        //jasperファイルと出力先のフォルダを指定。環境によって適宜変更 -->const定義に変更予定
        //String jasperPath = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/japsper/JLX_report.jasper";
        //String outputFilePath = "/Users/great_kaneko/NetBeansProjects/pdf/ukewatashi_testA4.pdf";
        String jasperPath = Const.JASPER_PATH_JLX_CHECKSHEET;
        String outputFilePath = Const.PDF_OUTPUT_PATH_JLX_CHECKSHEET;
        
        /*
        *   受信データを帳票出力データとして配列化(同じ処理をAngularでも表示処理時に実行 2次元リスト構造の受渡しできない為)
        *
        */
        List<List<Kanri>> printKanriList = new ArrayList<List<Kanri>>();
        List<Kanri> printKanries = new ArrayList<Kanri>();
        Kanri dtoKanri = null;
        for (Kanri k: list) { 
            if (Objects.isNull(dtoKanri)) {                                 // 初回ループ処理 比較データにセット
                dtoKanri = k;
            }
            
            if (Objects.equals(k.getHokengaisha(),dtoKanri.getHokengaisha())){          // ループ2回目以降　保険会社同じ
                if (Objects.equals(k.getHokenTantou(), dtoKanri.getHokenTantou())) {    // 保険担当同じ
                    if (Objects.equals(k.getDlvry(), dtoKanri.getDlvry())) {            // 受渡方法同じ--->　全一致、同一帳票データ
                        printKanries.add(k);                                            // 同一帳票データのリストに追加
                        dtoKanri = k;                                                   // 次ループの比較用にセット
                    } else {                                                            // 受渡方法異なる
                        printKanriList.add(printKanries);                               // 帳票データリストに異なる前までの同一帳票データリストを追加保持
                        printKanries = new  ArrayList<Kanri>();                         // 新たに同一帳票データリストを作成
                        printKanries.add(k);                                            // 同一帳票データのリストに追加
                        dtoKanri = k;                                                   // 次ループの比較用にセット
                    }
                } else {                                                                // 保険担当者異なる
                    printKanriList.add(printKanries);                                   // 帳票データリストに異なる前までの同一帳票データリストを追加保持
                    printKanries = new  ArrayList<Kanri>();                             // 新たに同一帳票データリストを作成
                    printKanries.add(k);                                                // 同一帳票データのリストに追加
                    dtoKanri = k;                                                       // 次ループの比較用にセット
                }
            } else {                                                                    // 保険会社異なる
                printKanriList.add(printKanries);                                       // 帳票データリストに異なる前までの同一帳票データリストを追加保持
                printKanries = new  ArrayList<Kanri>();                                 // 新たに同一帳票データリストを作成
                printKanries.add(k);                                                    // 同一帳票データのリストに追加
                dtoKanri = k;                                                           // 次ループの比較用にセット
            }       
        }
        if (printKanries.size() != 0) {
            printKanriList.add(printKanries);
        }
        
        /* console debug　*/
        /*
        for(List<Kanri> kList: printKanriList) {
            System.out.println("sheet-------->");
            if (Objects.nonNull(kList)) {
                for (Kanri kk : kList ) {
                    System.out.println(kk.getId() + kk.getHokengaisha() + kk.getHokenTantou() + kk.getDlvry());
                }
            } else {
                System.out.println("is null");
            }
        }
        */
        
        /*帳票作成 概要
        *  ページ結合により複数ある単体帳票を1ファイルにまとめる
        *  1ページ目の帳票を作成後、以後帳票は作成後ページに変換、2ページ以降として順次結合する
        */
        try{
            Iterator<List<Kanri>> ListIterator = printKanriList.iterator();
            List<Kanri> list1 = ListIterator.next();                                //  先頭帳票をリストから取り出し
            ListIterator.remove();                                                  //  先頭帳票をリストから削除
            Kanri fKanri = list1.get(0);                                            //  先頭帳票内印刷データをヘッダー部作成用に取り出し
            /*
            * パラメータ　ページヘッダー部固定値セット
            * 担当者会社名、入力担当者、受渡方法、保険会社名、保険担当者名
            */
            Map<String,Object> params = setJsperParams(fKanri);
            //フィールドデータセット(帳票のヘッダー以下のフィールド部のマッピング用にDtoKanriへセット)
            List<DtoKanri> flist = setJasperFields(list1);
            //1ページ目の帳票フィールドデータソース作成
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(flist);  // 1ページとなる先頭帳票リストから印刷データソース作成
            //1ページ目抽象レポートを生成する。
            JasperPrint pdf = JasperFillManager.fillReport(jasperPath, params, ds);
            
            //複数ページ　PDFページ結合用
            List<JRPrintPage> pages = null;
            //2ページ目以降PDFデータ作成しページとして順次結合していく
            for (List<Kanri> kList: printKanriList) {
                Kanri nKanri = kList.get(0);
                Map<String,Object> nParams = setJsperParams(nKanri);
                //フィールドデータセット(帳票のヘッダー以下のフィールド部のマッピング用にDtoKanriへセット)
                List<DtoKanri> nextList = setJasperFields(kList);
                //2ページ目以降の帳票フィールドデータソース作成
                JRBeanCollectionDataSource ds2 = new JRBeanCollectionDataSource(nextList);
                JasperPrint nextPdf = JasperFillManager.fillReport(jasperPath, nParams, ds2);
                pages = nextPdf.getPages();
                for(JRPrintPage page : pages ){
                   pdf.addPage(page);
                }
            }
            
            //全帳票のページ結合完了 PDFファイルに出力する。
            JasperExportManager.exportReportToPdfFile(pdf, outputFilePath);
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        
        // レスポンスデータ作成 サーバー内に保存したファイル読み込みレスポンス送信
        File file = new File(outputFilePath);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=checkSheet.pdf");
        return response.build();
    }
    
    //----------------------------Integer----------------------------------------------------------------
    //  以下未使用メソッド
    //--------------------------------------------------------------------------------------------
    /*
    @TokenSecurity
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Kanri find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @TokenSecurity
    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Kanri entity) {
        super.create(entity);
    }

    @TokenSecurity
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Kanri entity) {
        super.edit(entity);
    }
    */
    
    
    //@TokenSecurity
    @GET
    @Override
    @Produces({"application/json"})
    public List<Kanri> findAll() {
        return super.findAll();
    }
    
    
    /*
    @TokenSecurity
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Kanri> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @TokenSecurity
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    *
    */

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
