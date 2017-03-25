/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son Tong
 */
@Entity
@Table(name = "calling_session")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallingSession.findAll", query = "SELECT c FROM CallingSession c"),
    @NamedQuery(name = "CallingSession.findByIdcall", query = "SELECT c FROM CallingSession c WHERE c.idcall = :idcall"),
    @NamedQuery(name = "CallingSession.findByIdnetwork", query = "SELECT c FROM CallingSession c WHERE c.idnetwork = :idnetwork"),
    @NamedQuery(name = "CallingSession.findByIdcaller", query = "SELECT c FROM CallingSession c WHERE c.idcaller = :idcaller"),
    @NamedQuery(name = "CallingSession.findByIdreceiver", query = "SELECT c FROM CallingSession c WHERE c.idreceiver = :idreceiver"),
    @NamedQuery(name = "CallingSession.findByStartTime", query = "SELECT c FROM CallingSession c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "CallingSession.findByStartInCallTime", query = "SELECT c FROM CallingSession c WHERE c.startInCallTime = :startInCallTime"),
    @NamedQuery(name = "CallingSession.findByIsOver", query = "SELECT c FROM CallingSession c WHERE c.isOver = :isOver")})
public class CallingSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcall")
    private Integer idcall;
    @Column(name = "idnetwork")
    private BigInteger idnetwork;
    @Column(name = "idcaller")
    private Integer idcaller;
    @Column(name = "idreceiver")
    private Integer idreceiver;
    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "startInCallTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startInCallTime;
    @Column(name = "isOver")
    private Integer isOver;

    public CallingSession() {
    }

    public CallingSession(Integer idcall) {
        this.idcall = idcall;
    }

    public Integer getIdcall() {
        return idcall;
    }

    public void setIdcall(Integer idcall) {
        this.idcall = idcall;
    }

    public BigInteger getIdnetwork() {
        return idnetwork;
    }

    public void setIdnetwork(BigInteger idnetwork) {
        this.idnetwork = idnetwork;
    }

    public Integer getIdcaller() {
        return idcaller;
    }

    public void setIdcaller(Integer idcaller) {
        this.idcaller = idcaller;
    }

    public Integer getIdreceiver() {
        return idreceiver;
    }

    public void setIdreceiver(Integer idreceiver) {
        this.idreceiver = idreceiver;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartInCallTime() {
        return startInCallTime;
    }

    public void setStartInCallTime(Date startInCallTime) {
        this.startInCallTime = startInCallTime;
    }

    public Integer getIsOver() {
        return isOver;
    }

    public void setIsOver(Integer isOver) {
        this.isOver = isOver;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcall != null ? idcall.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallingSession)) {
            return false;
        }
        CallingSession other = (CallingSession) object;
        if ((this.idcall == null && other.idcall != null) || (this.idcall != null && !this.idcall.equals(other.idcall))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lawrenum.CallingSession[ idcall=" + idcall + " ]";
    }
    
}
