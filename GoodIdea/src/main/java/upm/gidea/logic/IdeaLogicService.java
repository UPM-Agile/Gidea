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
    public void createIdea(String title, String description, Integer userId, Integer categoryID ) throws Exception
    {
        // validate inputs
        if (title == null||title.isEmpty()) {
            throw new Exception("Title is empty");   
        }
        if (description.length() > 1000 ){
            throw new Exception("The short description is too long");
        }
        if(description == null||description.isEmpty()){
            throw new Exception("Please add a short description");
        } else {
        }
        if(categoryID == null){
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
}
