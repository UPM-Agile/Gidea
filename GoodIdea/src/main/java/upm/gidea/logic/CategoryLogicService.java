package upm.gidea.logic;

import java.util.ArrayList;
import upm.gidea.persistence.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import upm.gidea.entities.Category;
import upm.gidea.exceptions.BusinessException;
import upm.gidea.web.CategoryWeb;

/**
 *
 * @author elts
 */
@Stateless
public class CategoryLogicService extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "upm.gidea_war_1.0-SNAPSHOT_PU")
    private EntityManager em;

    public CategoryLogicService() {
        super(Category.class);
    }

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Category getDefaultCategory() throws Exception {
        Category c = super.find(1);
        if (c == null) {
            c = new Category();
            c.setName("General");
            super.create(c);
        }
        return c;
    }

    public Category findByName(String name) throws Exception {
        return em.createNamedQuery("Category.findByName", Category.class).setParameter("name", name).getSingleResult();

    }
    
    
    
     public void create(CategoryWeb catWeb) throws Exception {
        Category cat = this.toEntity(catWeb);
        super.create(cat);
    }

   


    public void editWeb(CategoryWeb cat) throws Exception {
        super.edit(toEntity(cat));
    }

    public void removeWeb(CategoryWeb cat) throws Exception {
        super.remove(toEntity(cat));
    }

    public CategoryWeb findWeb(Integer ideaID) throws Exception {
        return toWeb(find(ideaID));
    }
    
    public List<CategoryWeb> findAllWeb() throws Exception {
        return toWeb(findAll());
    }
    
           
    public void validateData(CategoryWeb cat) throws Exception {
        // validate inputs
        if (cat.getName() == null || cat.getName().isEmpty()) {
            throw new BusinessException("Title is empty");
        }
       
    }

    /*
     * Convert a CategoryWeb object to Category Object
     */
    private Category toEntity(CategoryWeb obj) throws Exception {
        /*
         *Creating object Idea
         */
        Category cat = new Category();

        if (obj.getId() != null && !"".equals(obj.getId().trim())) {
            Integer id = Integer.parseInt(obj.getId());
            cat.setId(id);
        }

       cat.setName(obj.getName());
       
        return cat;
    }

    /*
     * Convert a Category object to Category Web Object
     */
    private CategoryWeb toWeb(Category obj) {
        CategoryWeb w = new CategoryWeb();
        w.setId("" + obj.getId());
        w.setName(obj.getName());
        //TODO COMPLETE
        return w;
    }

    /**
     * Convert entity list to web objects
     *
     * @param all
     * @return
     */
    private List<CategoryWeb> toWeb(List<Category> all) {
        List<CategoryWeb> result = new ArrayList<>();
        for (Category cat : all) {
            result.add(toWeb(cat));
        }
        return result;
    }
}
