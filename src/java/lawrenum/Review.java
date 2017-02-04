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
@Table(name = "review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByIdreview", query = "SELECT r FROM Review r WHERE r.idreview = :idreview"),
    @NamedQuery(name = "Review.findByIduser", query = "SELECT r FROM Review r WHERE r.iduser = :iduser"),
    @NamedQuery(name = "Review.findByIdevent", query = "SELECT r FROM Review r WHERE r.idevent = :idevent"),
    @NamedQuery(name = "Review.findByTime", query = "SELECT r FROM Review r WHERE r.time = :time")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreview")
    private Integer idreview;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private int iduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idevent")
    private int idevent;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Review() {
    }

    public Review(Integer idreview) {
        this.idreview = idreview;
    }

    public Review(Integer idreview, int iduser, int idevent) {
        this.idreview = idreview;
        this.iduser = iduser;
        this.idevent = idevent;
    }

    public Integer getIdreview() {
        return idreview;
    }

    public void setIdreview(Integer idreview) {
        this.idreview = idreview;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
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
        hash += (idreview != null ? idreview.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.idreview == null && other.idreview != null) || (this.idreview != null && !this.idreview.equals(other.idreview))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lawrenum.Review[ idreview=" + idreview + " ]";
    }
    
}
