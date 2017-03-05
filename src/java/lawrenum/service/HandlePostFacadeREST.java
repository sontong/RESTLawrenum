/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import lawrenum.HandlePost;

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
    @Produces({"text/plain"})
    public String findByIdpost(@PathParam("id") Integer id) {
        HandlePost u = em.createNamedQuery("HandlePost.findByIdpost", HandlePost.class).setParameter("idpost", id).getSingleResult();
        return u.getContent();
    }    
    
    @GET
    @Path("idforum")
    @Produces({"application/json"})
    public List<HandlePost> findByIdforum(@QueryParam("idforum") int idforum) {        
        String query = "SELECT p FROM HandlePost p WHERE p.idforum = "+idforum+
                " ORDER BY p.sticky DESC, CAST(p.time AS DATE) DESC, p.upvote DESC, p.time DESC";
        return em.createQuery(query).getResultList();
    }
    
    @GET
    @Path("tag")
    @Produces({"application/json"})
    public List<HandlePost> findByTag(@QueryParam("tag") String tag) {                    
        String query = "SELECT p FROM HandlePost p WHERE p.tag LIKE '%"+tag+"%'" +
                " ORDER BY p.sticky DESC, CAST(p.time AS DATE) DESC, p.upvote DESC, p.time DESC";
        return em.createQuery(query).getResultList();        
    }    
    
    @GET
    @Path("iduser")
    @Produces({"application/json"})
    public List<HandlePost> findByIduser(@QueryParam("iduser") int iduser) {        
        String query = "SELECT p FROM HandlePost p WHERE p.iduser = "+iduser+
                " ORDER BY p.sticky DESC, CAST(p.time AS DATE) DESC, p.upvote DESC, p.time DESC";
        return em.createQuery(query).getResultList();
    }
            
                     
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
