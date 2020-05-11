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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author great_kaneko
 */
@Entity
@Table(name = "kankyou")
@XmlRootElement
@NamedQueries({
    /*
    @NamedQuery(name = "Kankyou.findAll", query = "SELECT k FROM Kankyou k"),
    @NamedQuery(name = "Kankyou.findById", query = "SELECT k FROM Kankyou k WHERE k.id = :id"),
    @NamedQuery(name = "Kankyou.findByKanriMax", query = "SELECT k FROM Kankyou k WHERE k.kanriMax = :kanriMax"),
    @NamedQuery(name = "Kankyou.findByDbPath", query = "SELECT k FROM Kankyou k WHERE k.dbPath = :dbPath"),
    @NamedQuery(name = "Kankyou.findByTantoushaJasperPath", query = "SELECT k FROM Kankyou k WHERE k.tantoushaJasperPath = :tantoushaJasperPath"),
    @NamedQuery(name = "Kankyou.findByHokengaishaJasperPath", query = "SELECT k FROM Kankyou k WHERE k.hokengaishaJasperPath = :hokengaishaJasperPath"),
    @NamedQuery(name = "Kankyou.findByKubunMax", query = "SELECT k FROM Kankyou k WHERE k.kubunMax = :kubunMax"),
    @NamedQuery(name = "Kankyou.findByShoruiMax", query = "SELECT k FROM Kankyou k WHERE k.shoruiMax = :shoruiMax"),
    @NamedQuery(name = "Kankyou.findByHokengaishaMax", query = "SELECT k FROM Kankyou k WHERE k.hokengaishaMax = :hokengaishaMax"),
    @NamedQuery(name = "Kankyou.findByPrintUse", query = "SELECT k FROM Kankyou k WHERE k.printUse = :printUse"),
    @NamedQuery(name = "Kankyou.findByHokenPrintUse", query = "SELECT k FROM Kankyou k WHERE k.hokenPrintUse = :hokenPrintUse"),
    @NamedQuery(name = "Kankyou.findByInternalFilePath", query = "SELECT k FROM Kankyou k WHERE k.internalFilePath = :internalFilePath"),
    @NamedQuery(name = "Kankyou.findByExternalFilePath", query = "SELECT k FROM Kankyou k WHERE k.externalFilePath = :externalFilePath")
    */
})
public class Kankyou implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kanri_max")
    private long kanriMax;
    @Size(max = 256)
    @Column(name = "db_path")
    private String dbPath;
    @Size(max = 256)
    @Column(name = "tantousha_jasper_path")
    private String tantoushaJasperPath;
    @Size(max = 256)
    @Column(name = "hokengaisha_jasper_path")
    private String hokengaishaJasperPath;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kubun_max")
    private long kubunMax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shorui_max")
    private long shoruiMax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hokengaisha_max")
    private long hokengaishaMax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "print_use")
    private long printUse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hoken_print_use")
    private long hokenPrintUse;
    @Size(max = 256)
    @Column(name = "internal_file_path")
    private String internalFilePath;
    @Size(max = 256)
    @Column(name = "external_file_path")
    private String externalFilePath;

    public Kankyou() {
    }

    public Kankyou(Long id) {
        this.id = id;
    }

    public Kankyou(Long id, long kanriMax, long kubunMax, long shoruiMax, long hokengaishaMax, long printUse, long hokenPrintUse) {
        this.id = id;
        this.kanriMax = kanriMax;
        this.kubunMax = kubunMax;
        this.shoruiMax = shoruiMax;
        this.hokengaishaMax = hokengaishaMax;
        this.printUse = printUse;
        this.hokenPrintUse = hokenPrintUse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getKanriMax() {
        return kanriMax;
    }

    public void setKanriMax(long kanriMax) {
        this.kanriMax = kanriMax;
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public String getTantoushaJasperPath() {
        return tantoushaJasperPath;
    }

    public void setTantoushaJasperPath(String tantoushaJasperPath) {
        this.tantoushaJasperPath = tantoushaJasperPath;
    }

    public String getHokengaishaJasperPath() {
        return hokengaishaJasperPath;
    }

    public void setHokengaishaJasperPath(String hokengaishaJasperPath) {
        this.hokengaishaJasperPath = hokengaishaJasperPath;
    }

    public long getKubunMax() {
        return kubunMax;
    }

    public void setKubunMax(long kubunMax) {
        this.kubunMax = kubunMax;
    }

    public long getShoruiMax() {
        return shoruiMax;
    }

    public void setShoruiMax(long shoruiMax) {
        this.shoruiMax = shoruiMax;
    }

    public long getHokengaishaMax() {
        return hokengaishaMax;
    }

    public void setHokengaishaMax(long hokengaishaMax) {
        this.hokengaishaMax = hokengaishaMax;
    }

    public long getPrintUse() {
        return printUse;
    }

    public void setPrintUse(long printUse) {
        this.printUse = printUse;
    }

    public long getHokenPrintUse() {
        return hokenPrintUse;
    }

    public void setHokenPrintUse(long hokenPrintUse) {
        this.hokenPrintUse = hokenPrintUse;
    }

    public String getInternalFilePath() {
        return internalFilePath;
    }

    public void setInternalFilePath(String internalFilePath) {
        this.internalFilePath = internalFilePath;
    }

    public String getExternalFilePath() {
        return externalFilePath;
    }

    public void setExternalFilePath(String externalFilePath) {
        this.externalFilePath = externalFilePath;
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
        if (!(object instanceof Kankyou)) {
            return false;
        }
        Kankyou other = (Kankyou) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kkgreat.Kankyou[ id=" + id + " ]";
    }
    
}
