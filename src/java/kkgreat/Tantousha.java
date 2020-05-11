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
@Table(name = "tantousha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tantousha.findByKaisha", query = "SELECT t FROM Tantousha t WHERE t.kaisha = :kaisha")
    /*
    @NamedQuery(name = "Tantousha.findAll", query = "SELECT t FROM Tantousha t"),
    @NamedQuery(name = "Tantousha.findByUserId", query = "SELECT t FROM Tantousha t WHERE t.userId = :userId"),
    @NamedQuery(name = "Tantousha.findByPassword", query = "SELECT t FROM Tantousha t WHERE t.password = :password"),
    @NamedQuery(name = "Tantousha.findByPasswordSetdate", query = "SELECT t FROM Tantousha t WHERE t.passwordSetdate = :passwordSetdate"),
    @NamedQuery(name = "Tantousha.findByKengen", query = "SELECT t FROM Tantousha t WHERE t.kengen = :kengen"),
    @NamedQuery(name = "Tantousha.findByShimei", query = "SELECT t FROM Tantousha t WHERE t.shimei = :shimei"),
    @NamedQuery(name = "Tantousha.findByBusho", query = "SELECT t FROM Tantousha t WHERE t.busho = :busho"),
    @NamedQuery(name = "Tantousha.findByKubun", query = "SELECT t FROM Tantousha t WHERE t.kubun = :kubun"),
    @NamedQuery(name = "Tantousha.findByPrinter", query = "SELECT t FROM Tantousha t WHERE t.printer = :printer"),
    @NamedQuery(name = "Tantousha.findByDenwa", query = "SELECT t FROM Tantousha t WHERE t.denwa = :denwa"),
    @NamedQuery(name = "Tantousha.findByFax", query = "SELECT t FROM Tantousha t WHERE t.fax = :fax"),
    @NamedQuery(name = "Tantousha.findByEMail", query = "SELECT t FROM Tantousha t WHERE t.eMail = :eMail"),
    @NamedQuery(name = "Tantousha.findByHokengaishaOrder", query = "SELECT t FROM Tantousha t WHERE t.hokengaishaOrder = :hokengaishaOrder"),
    @NamedQuery(name = "Tantousha.findByKubunOrder", query = "SELECT t FROM Tantousha t WHERE t.kubunOrder = :kubunOrder"),
    @NamedQuery(name = "Tantousha.findByShoruiOrder", query = "SELECT t FROM Tantousha t WHERE t.shoruiOrder = :shoruiOrder")
    */
})
    
public class Tantousha implements Serializable {
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
    @Column(name = "kengen")
    private Long kengen;
    @Size(max = 64)
    @Column(name = "shimei")
    private String shimei;
    @Size(max = 128)
    @Column(name = "kaisha")
    private String kaisha;
    @Size(max = 128)
    @Column(name = "busho")
    private String busho;
    @Size(max = 64)
    @Column(name = "kubun")
    private String kubun;
    @Size(max = 32)
    @Column(name = "printer")
    private String printer;
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
    @Size(max = 256)
    @Column(name = "hokengaisha_order")
    private String hokengaishaOrder;
    @Size(max = 256)
    @Column(name = "kubun_order")
    private String kubunOrder;
    @Size(max = 256)
    @Column(name = "shorui_order")
    private String shoruiOrder;

    public Tantousha() {
    }

    public Tantousha(String userId) {
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

    public Long getKengen() {
        return kengen;
    }

    public void setKengen(Long kengen) {
        this.kengen = kengen;
    }

    public String getShimei() {
        return shimei;
    }

    public void setShimei(String shimei) {
        this.shimei = shimei;
    }

    public String getKaisha() {
        return kaisha;
    }

    public void setKaisha(String kaisha) {
        this.kaisha = kaisha;
    }

    public String getBusho() {
        return busho;
    }

    public void setBusho(String busho) {
        this.busho = busho;
    }

    public String getKubun() {
        return kubun;
    }

    public void setKubun(String kubun) {
        this.kubun = kubun;
    }

    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
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

    public String getHokengaishaOrder() {
        return hokengaishaOrder;
    }

    public void setHokengaishaOrder(String hokengaishaOrder) {
        this.hokengaishaOrder = hokengaishaOrder;
    }

    public String getKubunOrder() {
        return kubunOrder;
    }

    public void setKubunOrder(String kubunOrder) {
        this.kubunOrder = kubunOrder;
    }

    public String getShoruiOrder() {
        return shoruiOrder;
    }

    public void setShoruiOrder(String shoruiOrder) {
        this.shoruiOrder = shoruiOrder;
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
        if (!(object instanceof Tantousha)) {
            return false;
        }
        Tantousha other = (Tantousha) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kkgreat.Tantousha[ userId=" + userId + " ]";
    }
    
}
