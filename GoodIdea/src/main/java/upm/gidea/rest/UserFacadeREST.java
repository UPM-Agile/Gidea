package upm.gidea.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import upm.gidea.entities.User;
import upm.gidea.logic.UserLogicService;

/**
 *
 * @author elts
 */
@Stateless
@Path("user")
public class UserFacadeREST
{ 
    @EJB
    UserLogicService logic;
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(User entity) {
        try {
            logic.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, User entity) {
        try {
            logic.edit(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            logic.remove(logic.find(id));
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Integer id) {
        try {
            return logic.find(id);
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new User();
        }
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        try {
            return logic.findAll();
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<User>(); //TODO
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return logic.findRange(new int[]{from, to});
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<User>(); //TODO
        }
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        try {
            return String.valueOf(logic.count());
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return ""; //TODO
        }
    }
    
}
