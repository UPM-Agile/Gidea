package upm.gidea.logic;

import upm.gidea.entities.Idea;
import java.util.ArrayList;
import java.util.List;

public class IdeaLogicService 
{    
    /**
     * Service that allows the creation of an idea by an entrepreneur
     * @param title
     * @param description
     * @param userId 
     */
    public void createIdea(String title, String description, Integer userId)
    {
        // validate inputs
        if (title == null) {
            
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
}
