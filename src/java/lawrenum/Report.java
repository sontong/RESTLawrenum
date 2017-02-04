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
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByIdreport", query = "SELECT r FROM Report r WHERE r.idreport = :idreport"),
    @NamedQuery(name = "Report.findByIduser", query = "SELECT r FROM Report r WHERE r.iduser = :iduser"),
    @NamedQuery(name = "Report.findByIdtarget", query = "SELECT r FROM Report r WHERE r.idtarget = :idtarget"),
    @NamedQuery(name = "Report.findByTargetType", query = "SELECT r FROM Report r WHERE r.targetType = :targetType"),
    @NamedQuery(name = "Report.findByTime", query = "SELECT r FROM Report r WHERE r.time = :time")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreport")
    private Integer idreport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private int iduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtarget")
    private int idtarget;
    @Basic(optional = false)
    @NotNull
    @Column(name = "target_type")
    private int targetType;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Report() {
    }

    public Report(Integer idreport) {
        this.idreport = idreport;
    }

    public Report(Integer idreport, int iduser, int idtarget, int targetType) {
        this.idreport = idreport;
        this.iduser = iduser;
        this.idtarget = idtarget;
        this.targetType = targetType;
    }

    public Integer getIdreport() {
        return idreport;
    }

    public void setIdreport(Integer idreport) {
        this.idreport = idreport;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdtarget() {
        return idtarget;
    }

    public void setIdtarget(int idtarget) {
        this.idtarget = idtarget;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
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
        hash += (idreport != null ? idreport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.idreport == null && other.idreport != null) || (this.idreport != null && !this.idreport.equals(other.idreport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lawrenum.Report[ idreport=" + idreport + " ]";
    }
    
}
