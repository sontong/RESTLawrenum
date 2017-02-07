/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum.service;

import java.util.Calendar;
import java.util.List;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import lawrenum.HandlePost;
import lawrenum.Post;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("handlepost")
public class HandlePostFacadeREST extends AbstractFacade<HandlePost> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public HandlePostFacadeREST() {
        super(HandlePost.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public HandlePost findByIdpost(@PathParam("id") Integer id) {
        return super.find(id);
    }       
    
    @GET
    @Path("idforum")
    @Produces({"application/json"})
    public List<HandlePost> findByIdforum(@QueryParam("idforum") int idforum) {
        return em.createNamedQuery("HandlePost.findByIdforum", HandlePost.class).setParameter("idforum", idforum).getResultList();
    }
    
    @GET
    @Path("tag")
    @Produces({"application/json"})
    public List<HandlePost> findByTag(@QueryParam("tag") String tag) {                    
        String query = "SELECT p FROM HandlePost p WHERE p.tag LIKE '%"+tag+"%'";
        return em.createQuery(query).getResultList();        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
