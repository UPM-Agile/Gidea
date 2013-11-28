package upm.gidea.logic;

import upm.gidea.persistence.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import upm.gidea.entities.Category;

/**
 *
 * @author elts
 */
@Stateless
public class CategoryLogicService extends AbstractFacade<Category> 
{
    @PersistenceContext(unitName = "upm.gidea_war_1.0-SNAPSHOT_PU")
    private EntityManager em;

    public CategoryLogicService() {
        super(Category.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
}
