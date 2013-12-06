package upm.gidea.logic;

import upm.gidea.persistence.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import upm.gidea.entities.User;

/**
 *
 * @author elts
 */
@Stateless
public class UserLogicService extends AbstractFacade<User> {

    @PersistenceContext(unitName = "upm.gidea_war_1.0-SNAPSHOT_PU")
    private EntityManager em;

    public UserLogicService() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public User getUserByEmail(String username) {
        return em.createNamedQuery("User.findByEmail",User.class).setParameter("email", username).getSingleResult();
    }
    
    
    
    
}
