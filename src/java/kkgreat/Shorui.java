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
@Table(name = "shorui")
@XmlRootElement
@NamedQueries({
    /* 未使用
    @NamedQuery(name = "Shorui.findAll", query = "SELECT s FROM Shorui s"),
    @NamedQuery(name = "Shorui.findById", query = "SELECT s FROM Shorui s WHERE s.id = :id"),
    @NamedQuery(name = "Shorui.findByShorui", query = "SELECT s FROM Shorui s WHERE s.shorui = :shorui")
    */
})
public class Shorui implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 64)
    @Column(name = "shorui")
    private String shorui;
    @Transient
    private Long okng;

    public Shorui() {
    }

    public Shorui(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShorui() {
        return shorui;
    }

    public void setShorui(String shorui) {
        this.shorui = shorui;
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
        if (!(object instanceof Shorui)) {
            return false;
        }
        Shorui other = (Shorui) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kkgreat.Shorui[ id=" + id + " ]";
    }

    /**
     * @return the okng
     */
    public Long getOkng() {
        return okng;
    }

    /**
     * @param okng the okng to set
     */
    public void setOkng(Long okng) {
        this.okng = okng;
    }

}
