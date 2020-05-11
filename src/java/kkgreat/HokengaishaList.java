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
@Table(name = "hokengaisha_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HokengaishaList.findAll", query = "SELECT h FROM HokengaishaList h"),
    @NamedQuery(name = "HokengaishaList.findById", query = "SELECT h FROM HokengaishaList h WHERE h.id = :id"),
    @NamedQuery(name = "HokengaishaList.findByHokengaisha", query = "SELECT h FROM HokengaishaList h WHERE h.hokengaisha = :hokengaisha")})
public class HokengaishaList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 128)
    @Column(name = "hokengaisha")
    private String hokengaisha;

    public HokengaishaList() {
    }

    public HokengaishaList(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHokengaisha() {
        return hokengaisha;
    }

    public void setHokengaisha(String hokengaisha) {
        this.hokengaisha = hokengaisha;
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
        if (!(object instanceof HokengaishaList)) {
            return false;
        }
        HokengaishaList other = (HokengaishaList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kkgreat.HokengaishaList[ id=" + id + " ]";
    }
    
}
