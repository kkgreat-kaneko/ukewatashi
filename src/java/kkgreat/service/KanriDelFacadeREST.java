/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

import java.util.List;
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
import kkgreat.Const;
import kkgreat.KanriDel;
import kkgreat.Kankyou;
import kkgreat.utility.Utility;

/**
 *
 * @author great_kaneko
 */
@Stateless
@Path("kanridel")
public class KanriDelFacadeREST extends AbstractFacade<KanriDel> {
    @PersistenceContext(unitName = "UkewatashiPU")
    private EntityManager em;

    public KanriDelFacadeREST() {
        super(KanriDel.class);
    }
    
    
    
    /* 開発テスト------------------------------------------------------------------------------------*/
    @GET
    @Path("test")
    @Produces({"text/plain"})
    public String find() {
        Kankyou kankyou = getEntityManager().find(Kankyou.class, 1L);
        System.out.println(kankyou.getKanriMax());
        return "hello";
    }
    
    @GET
    @Override
    @Path("findall")
    @Produces({"application/xml", "application/json"})
    public List<KanriDel> findAll() {
        return super.findAll();
    }
    /*---------------------------------------------------------------------------------------------*/
    
    /*
    * 削除データ閲覧用　テーブル検索処理
    * Status絞込み、JLX、JLXHS、承認Status絞込み項目選択処理
    * 動的JPQLを作成して実行
    */
    @TokenSecurity
    @POST
    @Path("getlist")
    @Consumes({"application/json"})
    public List<KanriDel> getList(KanriDel entity) {
        String query = "";
        StringBuffer sb = new StringBuffer("select k from KanriDel k");
        
        /* JLX/JLXHSボタンのどちらかが選択されていない時は担当者申請者データのみ
        *  そうでない時は会社内全データ閲覧可能(管理者のみ)
        */
        if ( Objects.isNull(entity.getsKaisha()) && !entity.issBusho() ) {
            sb.append(" where (k.tantoushaUserId = ");
            sb.append(Utility.sqlStringFormat(entity.getUserId()));
            sb.append(" OR k.shinseishaUserId = ");
            sb.append("'");
            sb.append(entity.getUserId());
            sb.append("'");
            sb.append(")");
            //System.out.println(entity.issBusho());
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
        //System.out.println(query);
        TypedQuery<KanriDel> q = getEntityManager().createQuery(query, KanriDel.class);
        q.setFirstResult(0);
        q.setMaxResults(entity.getLimit());
        return q.getResultList();
    }
    
    //--------------------------------------------------------------------------------------------
    //  以下未使用メソッド
    //--------------------------------------------------------------------------------------------
    /*
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(KanriDel entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, KanriDel entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public KanriDel find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<KanriDel> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<KanriDel> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    */

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
