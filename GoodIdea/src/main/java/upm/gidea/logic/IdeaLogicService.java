package upm.gidea.logic;

import upm.gidea.persistence.AbstractFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import upm.gidea.constants.IdeaStatus;
import upm.gidea.entities.Idea;
import upm.gidea.exceptions.BusinessException;

@Stateless
public class IdeaLogicService  extends AbstractFacade<Idea> 
{    
    @PersistenceContext(unitName = "upm.gidea_war_1.0-SNAPSHOT_PU")
    private EntityManager em;

    @EJB
    UserLogicService userLogicService;
    
    @EJB
    CategoryLogicService categoryLogicService;
    
    public IdeaLogicService() {
        super(Idea.class);
    }

    /**
     * Service that allows the creation of an idea by an entrepreneur
     * @param idea
     * @throws java.lang.Exception
     */
    public void create(String owner, Idea idea) throws Exception
    {
        // validate inputs
        if (idea.getTitle() == null||idea.getTitle().isEmpty()) {
            throw new BusinessException("Title is empty");   
        }
        if(idea.getDescription() == null||idea.getDescription().isEmpty()){
            throw new BusinessException("Please add a short description");
        } 
        if (idea.getDescription().length() > 1000 ){
            throw new BusinessException("The short description is too long");
        }
        if(idea.getCategory() == null){
            throw new BusinessException("Please choose a category");
        }
        
        idea.setOwner(userLogicService.getUserByEmail(owner));
        idea.setCategory(categoryLogicService.getDefaultCategory());
        idea.setCreationDate(new Date());
        idea.setStatus(IdeaStatus.CREATED);
        
        // save in the database
        super.create(idea);    
    }
    
    /**
     * Lists the ideas created by this user
     * @param userId 
     */
    public List<Idea> viewOwnIdeas(String username) {
        return userLogicService.getUserByEmail(username).getIdeas();
    }
    
    /**
     * Performs a request for approval for a created idea.
     * @param ideaId 
     */
    public void askForIdeaPublishing(Integer ideaId) throws Exception
    {
        
    }        
    
    /**
     * Changes the idea's status to published
     * @param ideaId 
     */
    public void publishIdea(Integer ideaId) throws Exception
    {
        
    }

    public void edit(Integer ideaID) throws Exception{
        
    }
    public void remove(Integer ideaID) throws Exception{
        
    }
    public Idea find(Integer ideaID) throws Exception{
        
        return null;
    }
    public List<Idea> findall() throws Exception{
        List<Idea> found = new ArrayList<Idea>();
        return found;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
