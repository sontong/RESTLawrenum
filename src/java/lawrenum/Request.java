/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son Tong
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
    @NamedQuery(name = "Request.findByIdrequest", query = "SELECT r FROM Request r WHERE r.idrequest = :idrequest"),
    @NamedQuery(name = "Request.findByIduser", query = "SELECT r FROM Request r WHERE r.iduser = :iduser"),
    @NamedQuery(name = "Request.findByRequestType", query = "SELECT r FROM Request r WHERE r.requestType = :requestType"),
    @NamedQuery(name = "Request.findByTime", query = "SELECT r FROM Request r WHERE r.time = :time")})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrequest")
    private Integer idrequest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private int iduser;
    @Column(name = "request_type")
    private Integer requestType;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Request() {
    }

    public Request(Integer idrequest) {
        this.idrequest = idrequest;
    }

    public Request(Integer idrequest, int iduser) {
        this.idrequest = idrequest;
        this.iduser = iduser;
    }

    public Integer getIdrequest() {
        return idrequest;
    }

    public void setIdrequest(Integer idrequest) {
        this.idrequest = idrequest;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrequest != null ? idrequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.idrequest == null && other.idrequest != null) || (this.idrequest != null && !this.idrequest.equals(other.idrequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lawrenum.Request[ idrequest=" + idrequest + " ]";
    }
    
}
