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
@Table(name = "handle_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HandleComment.findAll", query = "SELECT h FROM HandleComment h"),
    @NamedQuery(name = "HandleComment.findByIdcomment", query = "SELECT h FROM HandleComment h WHERE h.idcomment = :idcomment"),
    @NamedQuery(name = "HandleComment.findByIduser", query = "SELECT h FROM HandleComment h WHERE h.iduser = :iduser"),
    @NamedQuery(name = "HandleComment.findByIdpost", query = "SELECT h FROM HandleComment h WHERE h.idpost = :idpost"),
    @NamedQuery(name = "HandleComment.findByTime", query = "SELECT h FROM HandleComment h WHERE h.time = :time"),
    @NamedQuery(name = "HandleComment.findByFullname", query = "SELECT h FROM HandleComment h WHERE h.fullname = :fullname")})
public class HandleComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomment")
    private Integer idcomment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private int iduser;
    @Column(name = "idpost")
    private Integer idpost;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Size(max = 45)
    @Column(name = "fullname")
    private String fullname;

    public HandleComment() {
    }

    public int getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Integer getIdpost() {
        return idpost;
    }

    public void setIdpost(Integer idpost) {
        this.idpost = idpost;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
}
