package upm.gidea.rest;

import java.util.List;
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
        logic.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, User entity) {
        logic.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        logic.remove(logic.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Integer id) {
        return logic.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<User> findAll() {
        return logic.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return logic.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(logic.count());
    }
    
}
