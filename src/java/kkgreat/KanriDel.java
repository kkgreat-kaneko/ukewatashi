/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author great_kaneko
 */
@Entity
@Table(name = "kanri_del")
@XmlRootElement
@NamedQueries({
    
    /*
    @NamedQuery(name = "KanriDel.findAll", query = "SELECT k FROM KanriDel k"),
    @NamedQuery(name = "KanriDel.findById", query = "SELECT k FROM KanriDel k WHERE k.id = :id"),
    @NamedQuery(name = "KanriDel.findByStatus", query = "SELECT k FROM KanriDel k WHERE k.status = :status"),
    @NamedQuery(name = "KanriDel.findByStatusApp", query = "SELECT k FROM KanriDel k WHERE k.statusApp = :statusApp"),
    @NamedQuery(name = "KanriDel.findByDlvry", query = "SELECT k FROM KanriDel k WHERE k.dlvry = :dlvry"),
    @NamedQuery(name = "KanriDel.findByHokengaisha", query = "SELECT k FROM KanriDel k WHERE k.hokengaisha = :hokengaisha"),
    @NamedQuery(name = "KanriDel.findByHokenTantou", query = "SELECT k FROM KanriDel k WHERE k.hokenTantou = :hokenTantou"),
    @NamedQuery(name = "KanriDel.findBySeihobun", query = "SELECT k FROM KanriDel k WHERE k.seihobun = :seihobun"),
    @NamedQuery(name = "KanriDel.findBySeiho", query = "SELECT k FROM KanriDel k WHERE k.seiho = :seiho"),
    @NamedQuery(name = "KanriDel.findByShoukenbango", query = "SELECT k FROM KanriDel k WHERE k.shoukenbango = :shoukenbango"),
    @NamedQuery(name = "KanriDel.findByKeiyakusha", query = "SELECT k FROM KanriDel k WHERE k.keiyakusha = :keiyakusha"),
    @NamedQuery(name = "KanriDel.findByKubun", query = "SELECT k FROM KanriDel k WHERE k.kubun = :kubun"),
    @NamedQuery(name = "KanriDel.findByOkng", query = "SELECT k FROM KanriDel k WHERE k.okng = :okng"),
    @NamedQuery(name = "KanriDel.findByShoruiUmu", query = "SELECT k FROM KanriDel k WHERE k.shoruiUmu = :shoruiUmu"),
    @NamedQuery(name = "KanriDel.findByShorui1", query = "SELECT k FROM KanriDel k WHERE k.shorui1 = :shorui1"),
    @NamedQuery(name = "KanriDel.findByOkng1", query = "SELECT k FROM KanriDel k WHERE k.okng1 = :okng1"),
    @NamedQuery(name = "KanriDel.findByShorui2", query = "SELECT k FROM KanriDel k WHERE k.shorui2 = :shorui2"),
    @NamedQuery(name = "KanriDel.findByOkng2", query = "SELECT k FROM KanriDel k WHERE k.okng2 = :okng2"),
    @NamedQuery(name = "KanriDel.findByShorui3", query = "SELECT k FROM KanriDel k WHERE k.shorui3 = :shorui3"),
    @NamedQuery(name = "KanriDel.findByOkng3", query = "SELECT k FROM KanriDel k WHERE k.okng3 = :okng3"),
    @NamedQuery(name = "KanriDel.findByShorui4", query = "SELECT k FROM KanriDel k WHERE k.shorui4 = :shorui4"),
    @NamedQuery(name = "KanriDel.findByOkng4", query = "SELECT k FROM KanriDel k WHERE k.okng4 = :okng4"),
    @NamedQuery(name = "KanriDel.findByShorui5", query = "SELECT k FROM KanriDel k WHERE k.shorui5 = :shorui5"),
    @NamedQuery(name = "KanriDel.findByOkng5", query = "SELECT k FROM KanriDel k WHERE k.okng5 = :okng5"),
    @NamedQuery(name = "KanriDel.findByShorui6", query = "SELECT k FROM KanriDel k WHERE k.shorui6 = :shorui6"),
    @NamedQuery(name = "KanriDel.findByOkng6", query = "SELECT k FROM KanriDel k WHERE k.okng6 = :okng6"),
    @NamedQuery(name = "KanriDel.findByShorui7", query = "SELECT k FROM KanriDel k WHERE k.shorui7 = :shorui7"),
    @NamedQuery(name = "KanriDel.findByOkng7", query = "SELECT k FROM KanriDel k WHERE k.okng7 = :okng7"),
    @NamedQuery(name = "KanriDel.findByShorui8", query = "SELECT k FROM KanriDel k WHERE k.shorui8 = :shorui8"),
    @NamedQuery(name = "KanriDel.findByOkng8", query = "SELECT k FROM KanriDel k WHERE k.okng8 = :okng8"),
    @NamedQuery(name = "KanriDel.findByShorui9", query = "SELECT k FROM KanriDel k WHERE k.shorui9 = :shorui9"),
    @NamedQuery(name = "KanriDel.findByOkng9", query = "SELECT k FROM KanriDel k WHERE k.okng9 = :okng9"),
    @NamedQuery(name = "KanriDel.findByShorui10", query = "SELECT k FROM KanriDel k WHERE k.shorui10 = :shorui10"),
    @NamedQuery(name = "KanriDel.findByOkng10", query = "SELECT k FROM KanriDel k WHERE k.okng10 = :okng10"),
    @NamedQuery(name = "KanriDel.findByOkShoruiIchiran", query = "SELECT k FROM KanriDel k WHERE k.okShoruiIchiran = :okShoruiIchiran"),
    @NamedQuery(name = "KanriDel.findByFubiShoruiIchiran", query = "SELECT k FROM KanriDel k WHERE k.fubiShoruiIchiran = :fubiShoruiIchiran"),
    @NamedQuery(name = "KanriDel.findByShoruiMaisu", query = "SELECT k FROM KanriDel k WHERE k.shoruiMaisu = :shoruiMaisu"),
    @NamedQuery(name = "KanriDel.findByBikou", query = "SELECT k FROM KanriDel k WHERE k.bikou = :bikou"),
    @NamedQuery(name = "KanriDel.findByHokenBikou", query = "SELECT k FROM KanriDel k WHERE k.hokenBikou = :hokenBikou"),
    @NamedQuery(name = "KanriDel.findByTantoushaUserId", query = "SELECT k FROM KanriDel k WHERE k.tantoushaUserId = :tantoushaUserId"),
    @NamedQuery(name = "KanriDel.findByTantousha", query = "SELECT k FROM KanriDel k WHERE k.tantousha = :tantousha"),
    @NamedQuery(name = "KanriDel.findByTantoushaKaisha", query = "SELECT k FROM KanriDel k WHERE k.tantoushaKaisha = :tantoushaKaisha"),
    @NamedQuery(name = "KanriDel.findByTantoushaTeam", query = "SELECT k FROM KanriDel k WHERE k.tantoushaTeam = :tantoushaTeam"),
    @NamedQuery(name = "KanriDel.findByShinseishaUserId", query = "SELECT k FROM KanriDel k WHERE k.shinseishaUserId = :shinseishaUserId"),
    @NamedQuery(name = "KanriDel.findByShinseisha", query = "SELECT k FROM KanriDel k WHERE k.shinseisha = :shinseisha"),
    @NamedQuery(name = "KanriDel.findByShinseishaKaisha", query = "SELECT k FROM KanriDel k WHERE k.shinseishaKaisha = :shinseishaKaisha"),
    @NamedQuery(name = "KanriDel.findByShinseishaTeam", query = "SELECT k FROM KanriDel k WHERE k.shinseishaTeam = :shinseishaTeam"),
    @NamedQuery(name = "KanriDel.findByKakuninsha", query = "SELECT k FROM KanriDel k WHERE k.kakuninsha = :kakuninsha"),
    @NamedQuery(name = "KanriDel.findBySakuseibi", query = "SELECT k FROM KanriDel k WHERE k.sakuseibi = :sakuseibi"),
    @NamedQuery(name = "KanriDel.findBySaishuHenshubi", query = "SELECT k FROM KanriDel k WHERE k.saishuHenshubi = :saishuHenshubi"),
    @NamedQuery(name = "KanriDel.findByKakuninbi", query = "SELECT k FROM KanriDel k WHERE k.kakuninbi = :kakuninbi"),
    @NamedQuery(name = "KanriDel.findBySaishuKakuninbi", query = "SELECT k FROM KanriDel k WHERE k.saishuKakuninbi = :saishuKakuninbi"),
    @NamedQuery(name = "KanriDel.findBySakujyoriyu", query = "SELECT k FROM KanriDel k WHERE k.sakujyoriyu = :sakujyoriyu"),
    @NamedQuery(name = "KanriDel.findBySakujyosha", query = "SELECT k FROM KanriDel k WHERE k.sakujyosha = :sakujyosha"),
    @NamedQuery(name = "KanriDel.findByShouninsha", query = "SELECT k FROM KanriDel k WHERE k.shouninsha = :shouninsha"),
    @NamedQuery(name = "KanriDel.findByShouninbi", query = "SELECT k FROM KanriDel k WHERE k.shouninbi = :shouninbi"),
    @NamedQuery(name = "KanriDel.findByMishouninsha", query = "SELECT k FROM KanriDel k WHERE k.mishouninsha = :mishouninsha"),
    @NamedQuery(name = "KanriDel.findByMishouninbi", query = "SELECT k FROM KanriDel k WHERE k.mishouninbi = :mishouninbi")
    */
})
public class KanriDel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private long status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_app")
    private long statusApp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "dlvry")
    private String dlvry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "hokengaisha")
    private String hokengaisha;
    @Size(max = 64)
    @Column(name = "hoken_tantou")
    private String hokenTantou;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seihobun")
    private long seihobun;
    @Size(max = 128)
    @Column(name = "seiho")
    private String seiho;
    @Size(max = 32)
    @Column(name = "shoukenbango")
    private String shoukenbango;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "keiyakusha")
    private String keiyakusha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "kubun")
    private String kubun;
    @Column(name = "okng")
    private Long okng;
    @Column(name = "shorui_umu")
    private Long shoruiUmu;
    @Size(max = 32)
    @Column(name = "shorui1")
    private String shorui1;
    @Column(name = "okng1")
    private Long okng1;
    @Size(max = 32)
    @Column(name = "shorui2")
    private String shorui2;
    @Column(name = "okng2")
    private Long okng2;
    @Size(max = 32)
    @Column(name = "shorui3")
    private String shorui3;
    @Column(name = "okng3")
    private Long okng3;
    @Size(max = 32)
    @Column(name = "shorui4")
    private String shorui4;
    @Column(name = "okng4")
    private Long okng4;
    @Size(max = 32)
    @Column(name = "shorui5")
    private String shorui5;
    @Column(name = "okng5")
    private Long okng5;
    @Size(max = 32)
    @Column(name = "shorui6")
    private String shorui6;
    @Column(name = "okng6")
    private Long okng6;
    @Size(max = 32)
    @Column(name = "shorui7")
    private String shorui7;
    @Column(name = "okng7")
    private Long okng7;
    @Size(max = 32)
    @Column(name = "shorui8")
    private String shorui8;
    @Column(name = "okng8")
    private Long okng8;
    @Size(max = 32)
    @Column(name = "shorui9")
    private String shorui9;
    @Column(name = "okng9")
    private Long okng9;
    @Size(max = 32)
    @Column(name = "shorui10")
    private String shorui10;
    @Column(name = "okng10")
    private Long okng10;
    @Size(max = 320)
    @Column(name = "ok_shorui_ichiran")
    private String okShoruiIchiran;
    @Size(max = 320)
    @Column(name = "fubi_shorui_ichiran")
    private String fubiShoruiIchiran;
    @Column(name = "shorui_maisu")
    private Long shoruiMaisu;
    @Size(max = 256)
    @Column(name = "bikou")
    private String bikou;
    @Size(max = 256)
    @Column(name = "hoken_bikou")
    private String hokenBikou;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "tantousha_user_id")
    private String tantoushaUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "tantousha")
    private String tantousha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "tantousha_kaisha")
    private String tantoushaKaisha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "tantousha_team")
    private String tantoushaTeam;
    @Size(max = 64)
    @Column(name = "shinseisha_user_id")
    private String shinseishaUserId;
    @Size(max = 64)
    @Column(name = "shinseisha")
    private String shinseisha;
    @Size(max = 64)
    @Column(name = "shinseisha_kaisha")
    private String shinseishaKaisha;
    @Size(max = 64)
    @Column(name = "shinseisha_team")
    private String shinseishaTeam;
    @Size(max = 64)
    @Column(name = "kakuninsha")
    private String kakuninsha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sakuseibi")
    private String sakuseibi;
    @Size(max = 20)
    @Column(name = "saishu_henshubi")
    private String saishuHenshubi;
    @Size(max = 20)
    @Column(name = "kakuninbi")
    private String kakuninbi;
    @Size(max = 20)
    @Column(name = "saishu_kakuninbi")
    private String saishuKakuninbi;
    @Size(max = 128)
    @Column(name = "sakujyoriyu")
    private String sakujyoriyu;
    @Size(max = 64)
    @Column(name = "sakujyosha")
    private String sakujyosha;
    @Size(max = 64)
    @Column(name = "shouninsha")
    private String shouninsha;
    @Size(max = 20)
    @Column(name = "shouninbi")
    private String shouninbi;
    @Size(max = 64)
    @Column(name = "mishouninsha")
    private String mishouninsha;
    @Size(max = 20)
    @Column(name = "mishouninbi")
    private String mishouninbi;
    @Transient
    private Integer limit;
    @Transient
    private String userId;
    @Transient
    private Integer kengen;
    @Transient
    private String[] sKaisha;
    @Transient
    private Integer beforeId;
    @Transient
    private boolean sBusho;

    public KanriDel() {
    }

    public KanriDel(Long id) {
        this.id = id;
    }

    public KanriDel(Long id, long status, long statusApp, String dlvry, String hokengaisha, long seihobun, String keiyakusha, String kubun, String tantoushaUserId, String tantousha, String tantoushaKaisha, String tantoushaTeam, String sakuseibi) {
        this.id = id;
        this.status = status;
        this.statusApp = statusApp;
        this.dlvry = dlvry;
        this.hokengaisha = hokengaisha;
        this.seihobun = seihobun;
        this.keiyakusha = keiyakusha;
        this.kubun = kubun;
        this.tantoushaUserId = tantoushaUserId;
        this.tantousha = tantousha;
        this.tantoushaKaisha = tantoushaKaisha;
        this.tantoushaTeam = tantoushaTeam;
        this.sakuseibi = sakuseibi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getStatusApp() {
        return statusApp;
    }

    public void setStatusApp(long statusApp) {
        this.statusApp = statusApp;
    }

    public String getDlvry() {
        return dlvry;
    }

    public void setDlvry(String dlvry) {
        this.dlvry = dlvry;
    }

    public String getHokengaisha() {
        return hokengaisha;
    }

    public void setHokengaisha(String hokengaisha) {
        this.hokengaisha = hokengaisha;
    }

    public String getHokenTantou() {
        return hokenTantou;
    }

    public void setHokenTantou(String hokenTantou) {
        this.hokenTantou = hokenTantou;
    }

    public long getSeihobun() {
        return seihobun;
    }

    public void setSeihobun(long seihobun) {
        this.seihobun = seihobun;
    }

    public String getSeiho() {
        return seiho;
    }

    public void setSeiho(String seiho) {
        this.seiho = seiho;
    }

    public String getShoukenbango() {
        return shoukenbango;
    }

    public void setShoukenbango(String shoukenbango) {
        this.shoukenbango = shoukenbango;
    }

    public String getKeiyakusha() {
        return keiyakusha;
    }

    public void setKeiyakusha(String keiyakusha) {
        this.keiyakusha = keiyakusha;
    }

    public String getKubun() {
        return kubun;
    }

    public void setKubun(String kubun) {
        this.kubun = kubun;
    }

    public Long getOkng() {
        return okng;
    }

    public void setOkng(Long okng) {
        this.okng = okng;
    }

    public Long getShoruiUmu() {
        return shoruiUmu;
    }

    public void setShoruiUmu(Long shoruiUmu) {
        this.shoruiUmu = shoruiUmu;
    }

    public String getShorui1() {
        return shorui1;
    }

    public void setShorui1(String shorui1) {
        this.shorui1 = shorui1;
    }

    public Long getOkng1() {
        return okng1;
    }

    public void setOkng1(Long okng1) {
        this.okng1 = okng1;
    }

    public String getShorui2() {
        return shorui2;
    }

    public void setShorui2(String shorui2) {
        this.shorui2 = shorui2;
    }

    public Long getOkng2() {
        return okng2;
    }

    public void setOkng2(Long okng2) {
        this.okng2 = okng2;
    }

    public String getShorui3() {
        return shorui3;
    }

    public void setShorui3(String shorui3) {
        this.shorui3 = shorui3;
    }

    public Long getOkng3() {
        return okng3;
    }

    public void setOkng3(Long okng3) {
        this.okng3 = okng3;
    }

    public String getShorui4() {
        return shorui4;
    }

    public void setShorui4(String shorui4) {
        this.shorui4 = shorui4;
    }

    public Long getOkng4() {
        return okng4;
    }

    public void setOkng4(Long okng4) {
        this.okng4 = okng4;
    }

    public String getShorui5() {
        return shorui5;
    }

    public void setShorui5(String shorui5) {
        this.shorui5 = shorui5;
    }

    public Long getOkng5() {
        return okng5;
    }

    public void setOkng5(Long okng5) {
        this.okng5 = okng5;
    }

    public String getShorui6() {
        return shorui6;
    }

    public void setShorui6(String shorui6) {
        this.shorui6 = shorui6;
    }

    public Long getOkng6() {
        return okng6;
    }

    public void setOkng6(Long okng6) {
        this.okng6 = okng6;
    }

    public String getShorui7() {
        return shorui7;
    }

    public void setShorui7(String shorui7) {
        this.shorui7 = shorui7;
    }

    public Long getOkng7() {
        return okng7;
    }

    public void setOkng7(Long okng7) {
        this.okng7 = okng7;
    }

    public String getShorui8() {
        return shorui8;
    }

    public void setShorui8(String shorui8) {
        this.shorui8 = shorui8;
    }

    public Long getOkng8() {
        return okng8;
    }

    public void setOkng8(Long okng8) {
        this.okng8 = okng8;
    }

    public String getShorui9() {
        return shorui9;
    }

    public void setShorui9(String shorui9) {
        this.shorui9 = shorui9;
    }

    public Long getOkng9() {
        return okng9;
    }

    public void setOkng9(Long okng9) {
        this.okng9 = okng9;
    }

    public String getShorui10() {
        return shorui10;
    }

    public void setShorui10(String shorui10) {
        this.shorui10 = shorui10;
    }

    public Long getOkng10() {
        return okng10;
    }

    public void setOkng10(Long okng10) {
        this.okng10 = okng10;
    }

    public String getOkShoruiIchiran() {
        return okShoruiIchiran;
    }

    public void setOkShoruiIchiran(String okShoruiIchiran) {
        this.okShoruiIchiran = okShoruiIchiran;
    }

    public String getFubiShoruiIchiran() {
        return fubiShoruiIchiran;
    }

    public void setFubiShoruiIchiran(String fubiShoruiIchiran) {
        this.fubiShoruiIchiran = fubiShoruiIchiran;
    }

    public Long getShoruiMaisu() {
        return shoruiMaisu;
    }

    public void setShoruiMaisu(Long shoruiMaisu) {
        this.shoruiMaisu = shoruiMaisu;
    }

    public String getBikou() {
        return bikou;
    }

    public void setBikou(String bikou) {
        this.bikou = bikou;
    }

    public String getHokenBikou() {
        return hokenBikou;
    }

    public void setHokenBikou(String hokenBikou) {
        this.hokenBikou = hokenBikou;
    }

    public String getTantoushaUserId() {
        return tantoushaUserId;
    }

    public void setTantoushaUserId(String tantoushaUserId) {
        this.tantoushaUserId = tantoushaUserId;
    }

    public String getTantousha() {
        return tantousha;
    }

    public void setTantousha(String tantousha) {
        this.tantousha = tantousha;
    }

    public String getTantoushaKaisha() {
        return tantoushaKaisha;
    }

    public void setTantoushaKaisha(String tantoushaKaisha) {
        this.tantoushaKaisha = tantoushaKaisha;
    }

    public String getTantoushaTeam() {
        return tantoushaTeam;
    }

    public void setTantoushaTeam(String tantoushaTeam) {
        this.tantoushaTeam = tantoushaTeam;
    }

    public String getShinseishaUserId() {
        return shinseishaUserId;
    }

    public void setShinseishaUserId(String shinseishaUserId) {
        this.shinseishaUserId = shinseishaUserId;
    }

    public String getShinseisha() {
        return shinseisha;
    }

    public void setShinseisha(String shinseisha) {
        this.shinseisha = shinseisha;
    }

    public String getShinseishaKaisha() {
        return shinseishaKaisha;
    }

    public void setShinseishaKaisha(String shinseishaKaisha) {
        this.shinseishaKaisha = shinseishaKaisha;
    }

    public String getShinseishaTeam() {
        return shinseishaTeam;
    }

    public void setShinseishaTeam(String shinseishaTeam) {
        this.shinseishaTeam = shinseishaTeam;
    }

    public String getKakuninsha() {
        return kakuninsha;
    }

    public void setKakuninsha(String kakuninsha) {
        this.kakuninsha = kakuninsha;
    }

    public String getSakuseibi() {
        return sakuseibi;
    }

    public void setSakuseibi(String sakuseibi) {
        this.sakuseibi = sakuseibi;
    }

    public String getSaishuHenshubi() {
        return saishuHenshubi;
    }

    public void setSaishuHenshubi(String saishuHenshubi) {
        this.saishuHenshubi = saishuHenshubi;
    }

    public String getKakuninbi() {
        return kakuninbi;
    }

    public void setKakuninbi(String kakuninbi) {
        this.kakuninbi = kakuninbi;
    }

    public String getSaishuKakuninbi() {
        return saishuKakuninbi;
    }

    public void setSaishuKakuninbi(String saishuKakuninbi) {
        this.saishuKakuninbi = saishuKakuninbi;
    }

    public String getSakujyoriyu() {
        return sakujyoriyu;
    }

    public void setSakujyoriyu(String sakujyoriyu) {
        this.sakujyoriyu = sakujyoriyu;
    }

    public String getSakujyosha() {
        return sakujyosha;
    }

    public void setSakujyosha(String sakujyosha) {
        this.sakujyosha = sakujyosha;
    }

    public String getShouninsha() {
        return shouninsha;
    }

    public void setShouninsha(String shouninsha) {
        this.shouninsha = shouninsha;
    }

    public String getShouninbi() {
        return shouninbi;
    }

    public void setShouninbi(String shouninbi) {
        this.shouninbi = shouninbi;
    }

    public String getMishouninsha() {
        return mishouninsha;
    }

    public void setMishouninsha(String mishouninsha) {
        this.mishouninsha = mishouninsha;
    }

    public String getMishouninbi() {
        return mishouninbi;
    }

    public void setMishouninbi(String mishouninbi) {
        this.mishouninbi = mishouninbi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KanriDel)) {
            return false;
        }
        KanriDel other = (KanriDel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kkgreat.KanriDel[ id=" + id + " ]";
    }

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the kengen
     */
    public Integer getKengen() {
        return kengen;
    }

    /**
     * @param kengen the kengen to set
     */
    public void setKengen(Integer kengen) {
        this.kengen = kengen;
    }

    /**
     * @return the sKaisha
     */
    public String[] getsKaisha() {
        return sKaisha;
    }

    /**
     * @param sKaisha the sKaisha to set
     */
    public void setsKaisha(String[] sKaisha) {
        this.sKaisha = sKaisha;
    }

    /**
     * @return the beforeId
     */
    public Integer getBeforeId() {
        return beforeId;
    }

    /**
     * @param beforeId the beforeId to set
     */
    public void setBeforeId(Integer beforeId) {
        this.beforeId = beforeId;
    }

    /**
     * @return the sBusho
     */
    public boolean issBusho() {
        return sBusho;
    }

    /**
     * @param sBusho the sBusho to set
     */
    public void setsBusho(boolean sBusho) {
        this.sBusho = sBusho;
    }
    
}
