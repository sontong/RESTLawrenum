/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son Tong
 */
@Entity
@Table(name = "handle_call")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HandleCall.findAll", query = "SELECT h FROM HandleCall h"),
    @NamedQuery(name = "HandleCall.findByIdcall", query = "SELECT h FROM HandleCall h WHERE h.idcall = :idcall"),
    @NamedQuery(name = "HandleCall.findByCaller", query = "SELECT h FROM HandleCall h WHERE h.caller = :caller"),
    @NamedQuery(name = "HandleCall.findByIdnetwork", query = "SELECT h FROM HandleCall h WHERE h.idnetwork = :idnetwork"),
    @NamedQuery(name = "HandleCall.findByAvatar", query = "SELECT h FROM HandleCall h WHERE h.avatar = :avatar")})
public class HandleCall implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcall")
    private int idcall;
    @Size(max = 45)
    @Column(name = "caller")
    private String caller;
    @Column(name = "idnetwork")
    private BigInteger idnetwork;
    @Size(max = 128)
    @Column(name = "avatar")
    private String avatar;

    public HandleCall() {
    }

    public int getIdcall() {
        return idcall;
    }

    public void setIdcall(int idcall) {
        this.idcall = idcall;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public BigInteger getIdnetwork() {
        return idnetwork;
    }

    public void setIdnetwork(BigInteger idnetwork) {
        this.idnetwork = idnetwork;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
