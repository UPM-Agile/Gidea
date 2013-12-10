package upm.gidea.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import upm.gidea.entities.User;
import upm.gidea.logic.UserLogicService;
import upm.gidea.web.UserWeb;

/**
 *
 * @author elts
 */
@Stateless
@Path("user")
public class UserFacadeREST {

    @EJB
    UserLogicService logic;

    /**
     *
     * @param entity
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(UserWeb entity) {
        try {
            logic.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param entity
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(UserWeb entity) {
        try {
            logic.editToWeb(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     */
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

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return logic.findRange(new int[]{from, to});
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>(); //TODO
        }
    }

    /**
     *
     * @return
     */
    @GET

    @Produces({"application/xml", "application/json"})
    public List<UserWeb> findAll() {
        try {
            return logic.findAllWeb();
        } catch (Exception ex) {
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>(); //TODO
        }
    }

    @GET
    @Path(value = "count")
    @Produces(value = "text/plain")
    @Asynchronous
    public void countREST(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doCountREST());
    }

    private String doCountREST() {
        try {
            return String.valueOf(logic.count());
        } catch (Exception ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return ""; //TODO
        }
    }

    @GET
    @Path("check/u={email}/p={password}")
    @Produces({"application/xml", "application/json"})
    public UserWeb validateLogin(@PathParam("email") String user, @PathParam("password") String password) {
        return logic.validateLogin(user, password);
    }
}
