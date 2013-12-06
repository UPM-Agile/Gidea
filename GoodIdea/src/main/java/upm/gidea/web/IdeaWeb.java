package upm.gidea.web;

import javax.xml.bind.annotation.XmlRootElement;
import upm.gidea.entities.Idea;

/**
 *
 * @author user
 */
@XmlRootElement
public class IdeaWeb {

    private String id;

    private String title;

    private String description;

    private String rejectionDate;

    private String owner;

    private String category;

    private String status;

    private String creationDate;

    private String publishingDate;

    private String editionDate;

    public IdeaWeb() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRejectionDate() {
        return rejectionDate;
    }

    public void setRejectionDate(String rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(String editionDate) {
        this.editionDate = editionDate;
    }
}
