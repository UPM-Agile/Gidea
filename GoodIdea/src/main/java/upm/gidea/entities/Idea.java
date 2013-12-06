package upm.gidea.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import upm.gidea.constants.IdeaStatus;
import upm.gidea.web.IdeaWeb;

/**
 * Business Idea
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Idea.findAll", query = "SELECT i FROM Idea i"),
    @NamedQuery(name = "Idea.findById", query = "SELECT i FROM Idea i WHERE i.id = :id"),
    @NamedQuery(name = "Idea.findByTitle", query = "SELECT i FROM Idea i WHERE i.title like '%:title%'"),
    @NamedQuery(name = "Idea.findByDescription", query = "SELECT i FROM Idea i WHERE i.description like '%:description%'"),
    @NamedQuery(name = "Idea.findByOwnerId", query = "SELECT i FROM Idea i JOIN i.owner o WHERE o.id = :ownerId")})
@XmlRootElement
public class Idea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 200)
    @Column(name = "title", nullable = false)
    private String title;
    
    @Size(max = 1000)
    @Column(name = "description", nullable = false)
    private String description;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rejectionDate")
    private Date rejectionDate;    
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private User owner;
    
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ID")
    private Category category;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IdeaStatus status;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    private Date creationDate;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publishingDate")
    private Date publishingDate;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "editionDate")
    private Date editionDate;
    
    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idea)) {
            return false;
        }
        Idea other = (Idea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }    

    public Date getRejectionDate() {
        return rejectionDate;
    }

    public void setRejectionDate(Date rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    public IdeaStatus getStatus() {
        return status;
    }

    public void setStatus(IdeaStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Date getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(Date editionDate) {
        this.editionDate = editionDate;
    }

    @Override
    public String toString() {
        return "entities.Idea[ id=" + id + " ]";
    }


}
