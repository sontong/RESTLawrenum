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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son Tong
 */
@Entity
@Table(name = "membership")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m"),
    @NamedQuery(name = "Membership.findByIdmembership", query = "SELECT m FROM Membership m WHERE m.idmembership = :idmembership"),
    @NamedQuery(name = "Membership.findByIduser", query = "SELECT m FROM Membership m WHERE m.iduser = :iduser"),
    @NamedQuery(name = "Membership.findByIdforum", query = "SELECT m FROM Membership m WHERE m.idforum = :idforum")})
public class Membership implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmembership")
    private Integer idmembership;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private int iduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idforum")
    private int idforum;

    public Membership() {
    }

    public Membership(Integer idmembership) {
        this.idmembership = idmembership;
    }

    public Membership(Integer idmembership, int iduser, int idforum) {
        this.idmembership = idmembership;
        this.iduser = iduser;
        this.idforum = idforum;
    }

    public Integer getIdmembership() {
        return idmembership;
    }

    public void setIdmembership(Integer idmembership) {
        this.idmembership = idmembership;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdforum() {
        return idforum;
    }

    public void setIdforum(int idforum) {
        this.idforum = idforum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmembership != null ? idmembership.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.idmembership == null && other.idmembership != null) || (this.idmembership != null && !this.idmembership.equals(other.idmembership))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lawrenum.Membership[ idmembership=" + idmembership + " ]";
    }
    
}
