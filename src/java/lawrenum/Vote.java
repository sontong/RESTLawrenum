/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son Tong
 */
@Entity
@Table(name = "vote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vote.findAll", query = "SELECT v FROM Vote v"),
    @NamedQuery(name = "Vote.findByIdvote", query = "SELECT v FROM Vote v WHERE v.idvote = :idvote"),
    @NamedQuery(name = "Vote.findByIdpost", query = "SELECT v FROM Vote v WHERE v.idpost = :idpost"),
    @NamedQuery(name = "Vote.findByIduser", query = "SELECT v FROM Vote v WHERE v.iduser = :iduser")})
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvote")
    private Integer idvote;
    @Column(name = "idpost")
    private Integer idpost;
    @Column(name = "iduser")
    private Integer iduser;

    public Vote() {
    }

    public Vote(Integer idvote) {
        this.idvote = idvote;
    }

    public Integer getIdvote() {
        return idvote;
    }

    public void setIdvote(Integer idvote) {
        this.idvote = idvote;
    }

    public Integer getIdpost() {
        return idpost;
    }

    public void setIdpost(Integer idpost) {
        this.idpost = idpost;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvote != null ? idvote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.idvote == null && other.idvote != null) || (this.idvote != null && !this.idvote.equals(other.idvote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lawrenum.Vote[ idvote=" + idvote + " ]";
    }
    
}
