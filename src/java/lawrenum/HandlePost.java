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
@Table(name = "handle_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HandlePost.findAll", query = "SELECT h FROM HandlePost h"),
    @NamedQuery(name = "HandlePost.findByIdpost", query = "SELECT h FROM HandlePost h WHERE h.idpost = :idpost"),
    @NamedQuery(name = "HandlePost.findByIdforum", query = "SELECT h FROM HandlePost h WHERE h.idforum = :idforum"),
    @NamedQuery(name = "HandlePost.findByIduser", query = "SELECT h FROM HandlePost h WHERE h.iduser = :iduser"),
    @NamedQuery(name = "HandlePost.findByFullname", query = "SELECT h FROM HandlePost h WHERE h.fullname = :fullname"),
    @NamedQuery(name = "HandlePost.findByTitle", query = "SELECT h FROM HandlePost h WHERE h.title = :title"),
    @NamedQuery(name = "HandlePost.findByTag", query = "SELECT h FROM HandlePost h WHERE h.tag = :tag"),
    @NamedQuery(name = "HandlePost.findByTime", query = "SELECT h FROM HandlePost h WHERE h.time = :time"),
    @NamedQuery(name = "HandlePost.findBySticky", query = "SELECT h FROM HandlePost h WHERE h.sticky = :sticky"),
    @NamedQuery(name = "HandlePost.findByUpvote", query = "SELECT h FROM HandlePost h WHERE h.upvote = :upvote")})
public class HandlePost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpost")
    private Integer idpost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idforum")
    private int idforum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private int iduser;
    @Size(max = 45)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 256)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Size(max = 256)
    @Column(name = "tag")
    private String tag;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sticky")
    private long sticky;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upvote")
    private long upvote;

    public HandlePost() {
    }

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    public int getIdforum() {
        return idforum;
    }

    public void setIdforum(int idforum) {
        this.idforum = idforum;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getSticky() {
        return sticky;
    }

    public void setSticky(long sticky) {
        this.sticky = sticky;
    }

    public long getUpvote() {
        return upvote;
    }

    public void setUpvote(long upvote) {
        this.upvote = upvote;
    }
    
}
