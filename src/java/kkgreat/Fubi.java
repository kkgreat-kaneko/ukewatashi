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
@Table(name = "fubi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fubi.findAll", query = "SELECT f FROM Fubi f"),
    @NamedQuery(name = "Fubi.findById", query = "SELECT f FROM Fubi f WHERE f.id = :id"),
    @NamedQuery(name = "Fubi.findByShorui1", query = "SELECT f FROM Fubi f WHERE f.shorui1 = :shorui1"),
    @NamedQuery(name = "Fubi.findByFubi1", query = "SELECT f FROM Fubi f WHERE f.fubi1 = :fubi1"),
    @NamedQuery(name = "Fubi.findByShorui2", query = "SELECT f FROM Fubi f WHERE f.shorui2 = :shorui2"),
    @NamedQuery(name = "Fubi.findByFubi2", query = "SELECT f FROM Fubi f WHERE f.fubi2 = :fubi2"),
    @NamedQuery(name = "Fubi.findByShorui3", query = "SELECT f FROM Fubi f WHERE f.shorui3 = :shorui3"),
    @NamedQuery(name = "Fubi.findByFubi3", query = "SELECT f FROM Fubi f WHERE f.fubi3 = :fubi3"),
    @NamedQuery(name = "Fubi.findByShorui4", query = "SELECT f FROM Fubi f WHERE f.shorui4 = :shorui4"),
    @NamedQuery(name = "Fubi.findByFubi4", query = "SELECT f FROM Fubi f WHERE f.fubi4 = :fubi4"),
    @NamedQuery(name = "Fubi.findByShorui5", query = "SELECT f FROM Fubi f WHERE f.shorui5 = :shorui5"),
    @NamedQuery(name = "Fubi.findByFubi5", query = "SELECT f FROM Fubi f WHERE f.fubi5 = :fubi5"),
    @NamedQuery(name = "Fubi.findByShorui6", query = "SELECT f FROM Fubi f WHERE f.shorui6 = :shorui6"),
    @NamedQuery(name = "Fubi.findByFubi6", query = "SELECT f FROM Fubi f WHERE f.fubi6 = :fubi6"),
    @NamedQuery(name = "Fubi.findByShorui7", query = "SELECT f FROM Fubi f WHERE f.shorui7 = :shorui7"),
    @NamedQuery(name = "Fubi.findByFubi7", query = "SELECT f FROM Fubi f WHERE f.fubi7 = :fubi7"),
    @NamedQuery(name = "Fubi.findByShorui8", query = "SELECT f FROM Fubi f WHERE f.shorui8 = :shorui8"),
    @NamedQuery(name = "Fubi.findByFubi8", query = "SELECT f FROM Fubi f WHERE f.fubi8 = :fubi8"),
    @NamedQuery(name = "Fubi.findByShorui9", query = "SELECT f FROM Fubi f WHERE f.shorui9 = :shorui9"),
    @NamedQuery(name = "Fubi.findByFubi9", query = "SELECT f FROM Fubi f WHERE f.fubi9 = :fubi9"),
    @NamedQuery(name = "Fubi.findByShorui10", query = "SELECT f FROM Fubi f WHERE f.shorui10 = :shorui10"),
    @NamedQuery(name = "Fubi.findByFubi10", query = "SELECT f FROM Fubi f WHERE f.fubi10 = :fubi10")})
public class Fubi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 32)
    @Column(name = "shorui1")
    private String shorui1;
    @Size(max = 128)
    @Column(name = "fubi1")
    private String fubi1;
    @Size(max = 32)
    @Column(name = "shorui2")
    private String shorui2;
    @Size(max = 128)
    @Column(name = "fubi2")
    private String fubi2;
    @Size(max = 32)
    @Column(name = "shorui3")
    private String shorui3;
    @Size(max = 128)
    @Column(name = "fubi3")
    private String fubi3;
    @Size(max = 32)
    @Column(name = "shorui4")
    private String shorui4;
    @Size(max = 128)
    @Column(name = "fubi4")
    private String fubi4;
    @Size(max = 32)
    @Column(name = "shorui5")
    private String shorui5;
    @Size(max = 128)
    @Column(name = "fubi5")
    private String fubi5;
    @Size(max = 32)
    @Column(name = "shorui6")
    private String shorui6;
    @Size(max = 128)
    @Column(name = "fubi6")
    private String fubi6;
    @Size(max = 32)
    @Column(name = "shorui7")
    private String shorui7;
    @Size(max = 128)
    @Column(name = "fubi7")
    private String fubi7;
    @Size(max = 32)
    @Column(name = "shorui8")
    private String shorui8;
    @Size(max = 128)
    @Column(name = "fubi8")
    private String fubi8;
    @Size(max = 32)
    @Column(name = "shorui9")
    private String shorui9;
    @Size(max = 128)
    @Column(name = "fubi9")
    private String fubi9;
    @Size(max = 32)
    @Column(name = "shorui10")
    private String shorui10;
    @Size(max = 128)
    @Column(name = "fubi10")
    private String fubi10;

    public Fubi() {
    }

    public Fubi(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShorui1() {
        return shorui1;
    }

    public void setShorui1(String shorui1) {
        this.shorui1 = shorui1;
    }

    public String getFubi1() {
        return fubi1;
    }

    public void setFubi1(String fubi1) {
        this.fubi1 = fubi1;
    }

    public String getShorui2() {
        return shorui2;
    }

    public void setShorui2(String shorui2) {
        this.shorui2 = shorui2;
    }

    public String getFubi2() {
        return fubi2;
    }

    public void setFubi2(String fubi2) {
        this.fubi2 = fubi2;
    }

    public String getShorui3() {
        return shorui3;
    }

    public void setShorui3(String shorui3) {
        this.shorui3 = shorui3;
    }

    public String getFubi3() {
        return fubi3;
    }

    public void setFubi3(String fubi3) {
        this.fubi3 = fubi3;
    }

    public String getShorui4() {
        return shorui4;
    }

    public void setShorui4(String shorui4) {
        this.shorui4 = shorui4;
    }

    public String getFubi4() {
        return fubi4;
    }

    public void setFubi4(String fubi4) {
        this.fubi4 = fubi4;
    }

    public String getShorui5() {
        return shorui5;
    }

    public void setShorui5(String shorui5) {
        this.shorui5 = shorui5;
    }

    public String getFubi5() {
        return fubi5;
    }

    public void setFubi5(String fubi5) {
        this.fubi5 = fubi5;
    }

    public String getShorui6() {
        return shorui6;
    }

    public void setShorui6(String shorui6) {
        this.shorui6 = shorui6;
    }

    public String getFubi6() {
        return fubi6;
    }

    public void setFubi6(String fubi6) {
        this.fubi6 = fubi6;
    }

    public String getShorui7() {
        return shorui7;
    }

    public void setShorui7(String shorui7) {
        this.shorui7 = shorui7;
    }

    public String getFubi7() {
        return fubi7;
    }

    public void setFubi7(String fubi7) {
        this.fubi7 = fubi7;
    }

    public String getShorui8() {
        return shorui8;
    }

    public void setShorui8(String shorui8) {
        this.shorui8 = shorui8;
    }

    public String getFubi8() {
        return fubi8;
    }

    public void setFubi8(String fubi8) {
        this.fubi8 = fubi8;
    }

    public String getShorui9() {
        return shorui9;
    }

    public void setShorui9(String shorui9) {
        this.shorui9 = shorui9;
    }

    public String getFubi9() {
        return fubi9;
    }

    public void setFubi9(String fubi9) {
        this.fubi9 = fubi9;
    }

    public String getShorui10() {
        return shorui10;
    }

    public void setShorui10(String shorui10) {
        this.shorui10 = shorui10;
    }

    public String getFubi10() {
        return fubi10;
    }

    public void setFubi10(String fubi10) {
        this.fubi10 = fubi10;
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
        if (!(object instanceof Fubi)) {
            return false;
        }
        Fubi other = (Fubi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kkgreat.Fubi[ id=" + id + " ]";
    }
    
}
