package upm.gidea.logic;

import upm.gidea.persistence.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import upm.gidea.entities.Idea;

@Stateless
public class IdeaLogicService  extends AbstractFacade<Idea> 
{    
    @PersistenceContext(unitName = "upm.gidea_war_1.0-SNAPSHOT_PU")
    private EntityManager em;

    public IdeaLogicService() {
        super(Idea.class);
    }

    /**
     * Service that allows the creation of an idea by an entrepreneur
     * @param idea
     * @param title
     * @param description
     * @param userId 
     */
    @Override
    public void create(Idea idea) throws Exception
    {
        // validate inputs
        if (idea.getTitle() == null||idea.getTitle().isEmpty()) {
            throw new Exception("Title is empty");   
        }
        if(idea.getDescription() == null||idea.getDescription().isEmpty()){
            throw new Exception("Please add a short description");
        } 
        if (idea.getDescription().length() > 1000 ){
            throw new Exception("The short description is too long");
        }
        if(idea.getCategory() == null){
            throw new Exception("Please choose a category");
        }
        
        
        
        // save in the database
    
    }
    
    /**
     * Lists the ideas created by this user
     * @param userId 
     */
    public List<Idea> viewOwnIdeas(Integer userId)
    {
        List<Idea> list = new ArrayList<Idea>();
        
        return list;
    }
    
    /**
     * Performs a request for approval for a created idea.
     * @param ideaId 
     */
    public void askForIdeaPublishing(Integer ideaId)
    {
        
    }        
    
    /**
     * Changes the idea's status to published
     * @param ideaId 
     */
    public void publishIdea(Integer ideaId)
    {
        
    }
    public void edit(Integer ideaID){
        
    }
    public void remove(Integer ideaID){
        
    }
    public Idea find(Integer ideaID){
        
        return null;
    }
    public List<Idea> findall(String KeyWord){
        List<Idea> found = new ArrayList<Idea>();
        return found;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
