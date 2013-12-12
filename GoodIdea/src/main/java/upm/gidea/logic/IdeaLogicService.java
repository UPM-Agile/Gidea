package upm.gidea.logic;

import java.text.SimpleDateFormat;
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
import upm.gidea.web.IdeaWeb;
import upm.gidea.entities.Category;
import upm.gidea.entities.User;

@Stateless
public class IdeaLogicService extends AbstractFacade<Idea> {

    @PersistenceContext(unitName = "upm.gidea_war_1.0-SNAPSHOT_PU")
    private EntityManager em;

    @EJB
    UserLogicService userLogicService;

    @EJB
    CategoryLogicService categoryLogicService;

    public IdeaLogicService() {
        super(Idea.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Service that allows the creation of an idea by an entrepreneur
     *
     * @param ideaWeb
     * @throws java.lang.Exception
     */
    public void create(IdeaWeb ideaWeb) throws Exception {
        Idea idea = this.toEntity(ideaWeb);
        idea.setStatus(IdeaStatus.CREATED);
        idea.setCreationDate(new Date());
        super.create(idea);
    }

    /**
     * Lists the ideas created by this user
     *
     * @param username
     * @return
     */
    public List<IdeaWeb> viewOwnIdeas(String username) throws Exception {
        List<Idea> listIdea = userLogicService.getUserByEmail(username).getIdeas();
        List<IdeaWeb> listIdeaWeb = new ArrayList<>();
        for (Idea idea : listIdea) {
            listIdeaWeb.add(this.toWeb(idea));
        }
        return listIdeaWeb;
    }

    /**
     * Performs a request for approval for a created idea.
     *
     * @param ideaId
     */
    public void askForIdeaPublishing(Integer ideaId) throws Exception {

    }

    /**
     * Changes the idea's status to published
     *
     * @param ideaId
     */
    public void publishIdea(Integer ideaId) throws Exception {

    }

    public void editWeb(IdeaWeb idea) throws Exception {
        super.edit(toEntity(idea));
    }

    public void removeWeb(IdeaWeb idea) throws Exception {
        super.remove(toEntity(idea));
    }

    public IdeaWeb findWeb(Integer ideaID) throws Exception {
        return toWeb(find(ideaID));
    }
    
    public List<IdeaWeb> findAllWeb() throws Exception {
        return toWeb(findAll());
    }
    
    public List<IdeaWeb> findRangeWeb(int[] args) throws Exception {
        return toWeb(findRange(args));
    }

        
    public void validateData(IdeaWeb idea) throws Exception {
        // validate inputs
        if (idea.getTitle() == null || idea.getTitle().isEmpty()) {
            throw new BusinessException("Title is empty");
        }
        if (idea.getDescription() == null || idea.getDescription().isEmpty()) {
            throw new BusinessException("Please add a short description");
        }
        if (idea.getDescription().length() > 1000) {
            throw new BusinessException("The short description is too long");
        }
        if (idea.getCategory() == null) {
            throw new BusinessException("Please choose a category");
        }
    }

    /*
     * Convert an Idea object to Idea Web Object
     */
    private Idea toEntity(IdeaWeb obj) throws Exception {
        /*
         *Creating object Idea
         */
        Idea i = new Idea();

        if (obj.getId() != null && !"".equals(obj.getId().trim())) {
            Integer id = Integer.parseInt(obj.getId());
            i.setId(id);
        }

        /*
         *Obtaining object User and Category
         */
        String category = obj.getCategory();
        if (category != null) {
            Category cat = categoryLogicService.findByName(category);
            i.setCategory(cat);
        }
        User u = userLogicService.getUserByEmail(obj.getOwner());

        i.setOwner(u);
        i.setDescription(obj.getDescription());
        i.setTitle(obj.getTitle());
        String status = obj.getStatus();
        if (status != null) {
            i.setStatus(IdeaStatus.valueOf(status));
        }
        Long date = Long.getLong(obj.getPublishingDate());
        if (date != null) {
//            i.setPublishingDate(SimpleDateFormat.getInstance().parse(date));
           i.setPublishingDate(new Date(date));
        }
        date = Long.getLong(obj.getEditionDate());
        if (date != null) {
//            i.setEditionDate(SimpleDateFormat.getInstance().parse(date));
            i.setEditionDate(new Date(date));
        }
        date = Long.getLong(obj.getRejectionDate());
        if (date != null) {
//            i.setRejectionDate(SimpleDateFormat.getInstance().parse(date));
            i.setRejectionDate(new Date(date));
        }
        
        date = Long.getLong(obj.getCreationDate());
        if (date != null) {
//            i.setCreationDate(SimpleDateFormat.getInstance().parse(date));
             i.setCreationDate(new Date(date));
        }
        return i;
    }

    /*
     * Convert an Idea object to Idea Web Object
     */
    private IdeaWeb toWeb(Idea obj) {
        IdeaWeb w = new IdeaWeb();
        w.setId("" + obj.getId());
        w.setCategory(obj.getCategory().getName());
        w.setOwner(obj.getOwner().getEmail());
        w.setTitle(obj.getTitle());
        w.setDescription(obj.getDescription());
        
         String status = obj.getStatus().toString();
        if (status != null) {
            w.setStatus(obj.getStatus().toString());
        }
        if (obj.getPublishingDate() != null) {
            w.setPublishingDate("" + obj.getPublishingDate().getTime());
        }
        if (obj.getEditionDate() != null) {
            w.setEditionDate(""+ obj.getEditionDate().getTime());
        }
        if (obj.getRejectionDate()!= null) {
            w.setRejectionDate("" + obj.getRejectionDate().getTime());
        }

        if (obj.getCreationDate()!= null) {
            w.setCreationDate(""+obj.getCreationDate().getTime());
        }
         
        return w;
    }

    /**
     * Convert entity list to web objects
     *
     * @param all
     * @return
     */
    private List<IdeaWeb> toWeb(List<Idea> all) {
        List<IdeaWeb> result = new ArrayList<>();
        for (Idea idea : all) {
            result.add(toWeb(idea));
        }
        return result;
    }
}
