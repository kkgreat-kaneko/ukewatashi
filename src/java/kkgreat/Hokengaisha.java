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
@Table(name = "hokengaisha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hokengaisha.getTantouList", query = "SELECT h FROM Hokengaisha h WHERE h.hokengaisha = :hokengaisha"),
    @NamedQuery(name = "Hokengaisha.getSeihoList", query = "SELECT distinct h.seiho FROM Hokengaisha h WHERE h.hokengaisha = :hokengaisha"),
    @NamedQuery(name = "Hokengaisha.findAll", query = "SELECT h FROM Hokengaisha h"),
    @NamedQuery(name = "Hokengaisha.findLikeUserId", query = "SELECT h FROM Hokengaisha h WHERE h.userId like :userId"),
    @NamedQuery(name = "Hokengaisha.findLikeKakuninsha", query = "SELECT h FROM Hokengaisha h WHERE h.kakuninsha like :kakuninsha"),
    @NamedQuery(name = "Hokengaisha.findLikeUserIDAndKakuninsha", query = "SELECT h FROM Hokengaisha h WHERE h.userId like :userId AND h.kakuninsha like :kakuninsha"),
    /*未使用
    @NamedQuery(name = "Hokengaisha.findByUserId", query = "SELECT h FROM Hokengaisha h WHERE h.userId = :userId"),
    @NamedQuery(name = "Hokengaisha.findByPassword", query = "SELECT h FROM Hokengaisha h WHERE h.password = :password"),
    @NamedQuery(name = "Hokengaisha.findByPasswordSetdate", query = "SELECT h FROM Hokengaisha h WHERE h.passwordSetdate = :passwordSetdate"),
    @NamedQuery(name = "Hokengaisha.findByHokengaisha", query = "SELECT h FROM Hokengaisha h WHERE h.hokengaisha = :hokengaisha"),
    @NamedQuery(name = "Hokengaisha.findBySeiho", query = "SELECT h FROM Hokengaisha h WHERE h.seiho = :seiho"),
    @NamedQuery(name = "Hokengaisha.findByKakuninsha", query = "SELECT h FROM Hokengaisha h WHERE h.kakuninsha = :kakuninsha"),
    @NamedQuery(name = "Hokengaisha.findByYomi", query = "SELECT h FROM Hokengaisha h WHERE h.yomi = :yomi"),
    @NamedQuery(name = "Hokengaisha.findByBusho", query = "SELECT h FROM Hokengaisha h WHERE h.busho = :busho"),
    @NamedQuery(name = "Hokengaisha.findByYakushoku", query = "SELECT h FROM Hokengaisha h WHERE h.yakushoku = :yakushoku"),
    @NamedQuery(name = "Hokengaisha.findByDenwa", query = "SELECT h FROM Hokengaisha h WHERE h.denwa = :denwa"),
    @NamedQuery(name = "Hokengaisha.findByFax", query = "SELECT h FROM Hokengaisha h WHERE h.fax = :fax"),
    @NamedQuery(name = "Hokengaisha.findByEMail", query = "SELECT h FROM Hokengaisha h WHERE h.eMail = :eMail")
    */
})
public class Hokengaisha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "user_id")
    private String userId;
    @Size(max = 16)
    @Column(name = "password")
    private String password;
    @Size(max = 20)
    @Column(name = "password_setdate")
    private String passwordSetdate;
    @Size(max = 128)
    @Column(name = "hokengaisha")
    private String hokengaisha;
    @Size(max = 128)
    @Column(name = "seiho")
    private String seiho;
    @Size(max = 64)
    @Column(name = "kakuninsha")
    private String kakuninsha;
    @Size(max = 64)
    @Column(name = "yomi")
    private String yomi;
    @Size(max = 128)
    @Column(name = "busho")
    private String busho;
    @Size(max = 64)
    @Column(name = "yakushoku")
    private String yakushoku;
    @Size(max = 32)
    @Column(name = "denwa")
    private String denwa;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="電話/FAXの形式が無効です。xxx-xxx-xxxxの形式にしてください")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 32)
    @Column(name = "fax")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="無効な電子メール")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 64)
    @Column(name = "e_mail")
    private String eMail;

    public Hokengaisha() {
    }

    public Hokengaisha(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSetdate() {
        return passwordSetdate;
    }

    public void setPasswordSetdate(String passwordSetdate) {
        this.passwordSetdate = passwordSetdate;
    }

    public String getHokengaisha() {
        return hokengaisha;
    }

    public void setHokengaisha(String hokengaisha) {
        this.hokengaisha = hokengaisha;
    }

    public String getSeiho() {
        return seiho;
    }

    public void setSeiho(String seiho) {
        this.seiho = seiho;
    }

    public String getKakuninsha() {
        return kakuninsha;
    }

    public void setKakuninsha(String kakuninsha) {
        this.kakuninsha = kakuninsha;
    }

    public String getYomi() {
        return yomi;
    }

    public void setYomi(String yomi) {
        this.yomi = yomi;
    }

    public String getBusho() {
        return busho;
    }

    public void setBusho(String busho) {
        this.busho = busho;
    }

    public String getYakushoku() {
        return yakushoku;
    }

    public void setYakushoku(String yakushoku) {
        this.yakushoku = yakushoku;
    }

    public String getDenwa() {
        return denwa;
    }

    public void setDenwa(String denwa) {
        this.denwa = denwa;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hokengaisha)) {
            return false;
        }
        Hokengaisha other = (Hokengaisha) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kkgreat.Hokengaisha[ userId=" + userId + " ]";
    }
    
}
