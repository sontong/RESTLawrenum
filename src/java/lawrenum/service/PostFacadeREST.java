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
import lawrenum.HandlePost;
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
    @Path("getAll")
    @Produces({"application/json"})
    public List<Post> getAllPostByIdForum(@QueryParam("idforum") int idforum) {        
        String query = "SELECT p FROM Post p WHERE p.idforum = "+idforum+
                " ORDER BY CAST(p.time AS DATE) DESC, p.upvote DESC, p.time DESC";
        return em.createQuery(query).getResultList();
    }
    
    @GET
    @Path("deletePost")
    public void deletePost(@QueryParam("idpost") Integer idpost) {
        super.remove(super.find(idpost));

        String query = "DELETE FROM Vote p WHERE p.idpost =" + idpost;
        int i = em.createQuery(query).executeUpdate();

        String queryReport = "DELETE FROM Report p WHERE p.idtarget =" + idpost;
        int r = em.createQuery(queryReport).executeUpdate();
        
        String queryComment = "DELETE FROM Comment c WHERE c.idpost =" + idpost;
        int cm = em.createQuery(queryComment).executeUpdate();
    }          
    
    @POST
    @Consumes({"application/json"})
    public String createPost(Post entity) {
        entity.setTime(Calendar.getInstance().getTime());
        entity.setUpvote(0);
        super.create(entity);
        em.flush();
        return entity.getIdpost().toString();
    }
    
    @POST
    @Path("edit")
    @Consumes("application/json")
    public String editPost(Post entity) {                   
        
        String q2 = "UPDATE HandlePost p "
                  + "SET "
                  + "p.idforum = " + entity.getIdforum() 
                  + ", p.title = " + entity.getTitle()
                  + ", p.content = " + entity.getContent()
                  + ", p.tag = " + entity.getTag()
                  + ", p.time = " + Calendar.getInstance().getTime()
                  + " WHERE p.idpost = " + entity.getIdpost();
        int ii = em.createQuery(q2).executeUpdate();

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