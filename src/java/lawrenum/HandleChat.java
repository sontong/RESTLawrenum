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
@Table(name = "handle_chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HandleChat.findAll", query = "SELECT h FROM HandleChat h"),
    @NamedQuery(name = "HandleChat.findByIdchat", query = "SELECT h FROM HandleChat h WHERE h.idchat = :idchat"),
    @NamedQuery(name = "HandleChat.findByIdsender", query = "SELECT h FROM HandleChat h WHERE h.idsender = :idsender"),
    @NamedQuery(name = "HandleChat.findByIdreceiver", query = "SELECT h FROM HandleChat h WHERE h.idreceiver = :idreceiver"),
    @NamedQuery(name = "HandleChat.findByAuthor", query = "SELECT h FROM HandleChat h WHERE h.author = :author"),
    @NamedQuery(name = "HandleChat.findByTime", query = "SELECT h FROM HandleChat h WHERE h.time = :time"),
    @NamedQuery(name = "HandleChat.findByAvatar", query = "SELECT h FROM HandleChat h WHERE h.avatar = :avatar")})
public class HandleChat implements Serializable {

    private static final long serialVersionUID = 1L;    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idchat")
    private int idchat;
    @Column(name = "idsender")
    private Integer idsender;
    @Column(name = "idreceiver")
    private Integer idreceiver;
    @Size(max = 45)
    @Column(name = "author")
    private String author;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Size(max = 128)
    @Column(name = "avatar")
    private String avatar;

    public HandleChat() {
    }

    public int getIdchat() {
        return idchat;
    }

    public void setIdchat(int idchat) {
        this.idchat = idchat;
    }

    public Integer getIdsender() {
        return idsender;
    }

    public void setIdsender(Integer idsender) {
        this.idsender = idsender;
    }

    public Integer getIdreceiver() {
        return idreceiver;
    }

    public void setIdreceiver(Integer idreceiver) {
        this.idreceiver = idreceiver;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
