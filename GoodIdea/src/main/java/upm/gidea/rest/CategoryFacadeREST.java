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
import upm.gidea.entities.Category;
import upm.gidea.logic.CategoryLogicService;
import upm.gidea.web.CategoryWeb;

/**
 *
 * @author elts
 */
@Stateless
@Path("category")
public class CategoryFacadeREST {

    @EJB
    CategoryLogicService logic;

    /**
     *
     * @param entity
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(CategoryWeb entity) {
        try {
            logic.validateData(entity);
            logic.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param entity
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(CategoryWeb entity) {
        try {
            logic.editWeb(entity);
        } catch (Exception ex) {
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Category find(@PathParam("id") Integer id) {
        try {
            return logic.find(id);
        } catch (Exception ex) {
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new Category(); //TODO
        }
    }

    @GET

    @Produces({"application/xml", "application/json"})
    public List<CategoryWeb> findAll() {
        try {
            return logic.findAllWeb();
        } catch (Exception ex) {
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>(); //TODO
        }
    }
    /*
     * Is necessary for Category????
     */

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Category> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return logic.findRange(new int[]{from, to});
        } catch (Exception ex) {
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>(); //TODO            
        }
    }

    /**
     *
     * @return
     */
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        try {
            return String.valueOf(logic.count());
        } catch (Exception ex) {
            Logger.getLogger(CategoryFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return ""; //TODO
        }
    }
}
