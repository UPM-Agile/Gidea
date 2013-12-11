package upm.gidea.logic;

import java.util.ArrayList;
import java.util.List;
import upm.gidea.persistence.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import upm.gidea.entities.User;
import upm.gidea.exceptions.BusinessException;
import upm.gidea.web.UserWeb;

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
    
     
     public void create(UserWeb userWeb) throws Exception {
        User us = this.toEntity(userWeb);
        super.create(us);
    }

    /**
     *
     * @param us
     * @throws Exception
     */
    public void editToWeb(UserWeb us) throws Exception {
        super.edit(toEntity(us));
    }

    public void removeWeb(UserWeb us) throws Exception {
        super.remove(toEntity(us));
    }

    public UserWeb findWeb(Integer ideaID) throws Exception {
        return toWeb(find(ideaID));
    }
    
    public List<UserWeb> findAllWeb() throws Exception {
        return toWeb(findAll());
    }
    
           
    public void validateData(UserWeb us) throws Exception {
        // validate inputs
        if (us.getName() == null || us.getName().isEmpty()) {
            throw new BusinessException("Name is empty");
        }
        if (us.getLastname() == null || us.getLastname().isEmpty()) {
            throw new BusinessException("Lastname is empty");
        }
        if (us.getEmail()== null || us.getEmail().isEmpty()) {
            throw new BusinessException("Email is empty");
        }
        if (us.getPassword() == null || us.getPassword().isEmpty()) {
            throw new BusinessException("Password is empty");
        }
       
    }

    /*
     * Convert a CategoryWeb object to Category Object
     */
    private User toEntity(UserWeb obj) throws Exception {
        /*
         *Creating object Idea
         */
        User us = new User();

        if (obj.getId() != null && !"".equals(obj.getId().trim())) {
            Integer id = Integer.parseInt(obj.getId());
            us.setId(id);
        }

       us.setName(obj.getName());
       us.setLastname(obj.getLastname());
       us.setEmail(obj.getEmail());
       us.setPassword(obj.getPassword());
       if (obj.getRole()!= null || !"".equals(obj.getRole().trim())) {
       us.setRole("user");
        }
        return us;
    }

    /*
     * Convert a Category object to Category Web Object
     */
    private UserWeb toWeb(User obj) {
        UserWeb w = new UserWeb();
        w.setId("" + obj.getId());
        w.setName(obj.getName());
        w.setLastname(obj.getLastname());
        w.setEmail(obj.getEmail()); 
        w.setRole(obj.getRole());
        //TODO COMPLETE
        return w;
    }

    /**
     * Convert entity list to web objects
     *
     * @param all
     * @return
     */
    private List<UserWeb> toWeb(List<User> all) {
        List<UserWeb> result = new ArrayList<>();
        for (User us : all) {
            result.add(toWeb(us));
        }
        return result;
    }
    
    
    public UserWeb validateLogin(String user, String password) {
        User us = getUserByEmail(user);
        
        if (us != null) {
            if (us.getPassword().equals(password)) {
                return toWeb(us);
            }
            return null;
            
        }
        return null;
    }
    
}
