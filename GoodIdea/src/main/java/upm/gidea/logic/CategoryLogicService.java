package upm.gidea.logic;

import upm.gidea.persistence.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import upm.gidea.entities.Category;

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
}
