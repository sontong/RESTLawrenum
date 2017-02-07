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
import lawrenum.Post;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("post")
public class PostFacadeREST extends AbstractFacade<Post>{

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public PostFacadeREST() {
        super(Post.class);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Post findByIdpost(@PathParam("id") Integer id) {
        return super.find(id);
    }       
    
//    @GET
//    @Path("idforum")
//    @Produces({"application/json"})
//    public List<Post> findByIdforum(@QueryParam("idforum") int idforum) {
//        return em.createNamedQuery("Post.findByIdforum", Post.class).setParameter("idforum", idforum).getResultList();
//    }
    
    @GET
    @Path("idforum")
    @Produces({"application/json"})
    public List<Post> findByIdforum(@QueryParam("idforum") int idforum) {
        //return em.createNamedQuery("Post.findByIdforum", Post.class).setParameter("idforum", idforum).getResultList();
        String query = "SELECT p FROM Post p WHERE p.idforum = "+idforum+" ORDER BY p.sticky DESC";
        return em.createQuery(query).getResultList();
    }
    
    @GET
    @Path("tag")
    @Produces({"application/json"})
    public List<Post> findByTag(@QueryParam("tag") String tag) {                    
        String query = "SELECT p FROM Post p WHERE p.tag LIKE '%"+tag+"%'";
        return em.createQuery(query).getResultList();        
    }
    
    @POST
    @Consumes({"application/json"})
    public String createPost(Post entity) {
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
        return entity.getIdpost().toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Post entity) {
        super.edit(entity);
    }
    
    @PUT
    @Path("sticky/{id}")     
    @Consumes({"application/json"})
    public void makeSticky(@PathParam("id") Integer id) {
        String query = "UPDATE Post p SET p.sticky = 1 WHERE p.idpost = "+id;
        em.createQuery(query).executeUpdate();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
