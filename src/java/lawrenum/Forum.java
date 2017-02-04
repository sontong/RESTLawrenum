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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son Tong
 */
@Entity
@Table(name = "forum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Forum.findAll", query = "SELECT f FROM Forum f"),
    @NamedQuery(name = "Forum.findByIdforum", query = "SELECT f FROM Forum f WHERE f.idforum = :idforum"),
    @NamedQuery(name = "Forum.findByName", query = "SELECT f FROM Forum f WHERE f.name = :name")})
public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idforum")
    private Integer idforum;
    @Size(max = 32)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;

    public Forum() {
    }

    public Forum(Integer idforum) {
        this.idforum = idforum;
    }

    public Integer getIdforum() {
        return idforum;
    }

    public void setIdforum(Integer idforum) {
        this.idforum = idforum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idforum != null ? idforum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forum)) {
            return false;
        }
        Forum other = (Forum) object;
        if ((this.idforum == null && other.idforum != null) || (this.idforum != null && !this.idforum.equals(other.idforum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lawrenum.Forum[ idforum=" + idforum + " ]";
    }
    
}
