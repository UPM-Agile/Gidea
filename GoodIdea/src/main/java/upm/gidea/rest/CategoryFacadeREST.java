package upm.gidea.rest;

import upm.gidea.persistence.AbstractFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import upm.gidea.entities.Category;
import upm.gidea.logic.CategoryLogicService;

/**
 *
 * @author elts
 */
@Stateless
@Path("category")
public class CategoryFacadeREST {

    @EJB
    CategoryLogicService logic;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Category entity) {
        logic.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Category entity) {
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
    public Category find(@PathParam("id") Integer id) {
        return logic.find(id);
    }

    @GET

    @Produces({"application/xml", "application/json"})
    public List<Category> findAll() {
        return logic.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Category> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return logic.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(logic.count());
    }
}
