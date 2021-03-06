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
import upm.gidea.logic.IdeaLogicService;
import upm.gidea.web.IdeaWeb;

/**
 *
 * @author elts
 */
@Stateless
@Path("idea")
public class IdeaFacadeREST {

    @EJB
    private IdeaLogicService logic;
    
    /**
     *
     * @param entity
     * @throws Exception
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(IdeaWeb entity) throws Exception {
        try {
            logic.validateData(entity);
            logic.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param username
     * @return
     */
    @GET
    @Path("own")
    @Produces({"application/xml", "application/json"})
    public List<IdeaWeb> findOwnIdeas(@PathParam("username") String username) {
        try {
            return logic.viewOwnIdeas(username);
        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();//TODO
        }
    }

    public void askForIdeaPublishing(Integer ideaId) {
        try {
            logic.askForIdeaPublishing(ideaId);
        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Changes the idea's status to published
     *
     * @param ideaId
     * @throws java.lang.Exception
     */
    public void publishIdea(Integer ideaId) throws Exception {
        try {
            logic.publishIdea(ideaId);
        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param ideaId
     * @param entity
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer ideaId, IdeaWeb entity) {
        try {
            logic.editWeb(entity);
        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IdeaFacadeREST.class
                    .getName()).log(Level.SEVERE, null, ex);
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
    public IdeaWeb find(@PathParam("id") Integer id) {
        try {
            return logic.findWeb(id);

        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new IdeaWeb(); //TODO
        }
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public List<IdeaWeb> findAll() throws Exception {
        try {
            return logic.findAllWeb();

        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();//TODO
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
    public List<IdeaWeb> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return logic.findRangeWeb(new int[]{from, to});

        } catch (Exception ex) {
            Logger.getLogger(IdeaFacadeREST.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();       //TODO
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
            Logger.getLogger(IdeaFacadeREST.class.getName()).log(Level.SEVERE, null, ex);

            return ""; //TODO
        }
    }
}
