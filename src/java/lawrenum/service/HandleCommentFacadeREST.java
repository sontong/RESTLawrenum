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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import lawrenum.HandleComment;
import lawrenum.HandlePost;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("handlecomment")
public class HandleCommentFacadeREST extends AbstractFacade<HandleComment> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public HandleCommentFacadeREST() {
        super(HandleComment.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public HandleComment findByIdpost(@PathParam("id") Integer id) {
        return super.find(id);
    }       
    
    @GET
    @Path("idpost")
    @Produces({"application/json"})
    public List<HandleComment> findByIdpost(@QueryParam("idpost") int idpost) {
        return em.createNamedQuery("HandleComment.findByIdpost", HandleComment.class).setParameter("idpost", idpost).getResultList();
    }
    
    @GET
    @Path("tag")
    @Produces({"application/json"})
    public List<HandleComment> findByTag(@QueryParam("tag") String tag) {                    
        String query = "SELECT p FROM HandleComment p WHERE p.tag LIKE '%"+tag+"%'";
        return em.createQuery(query).getResultList();        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
