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
    public void deleteReport(@PathParam("id") Integer id) {
        super.remove(super.find(id));
        
        String query = "DELETE FROM Vote p WHERE p.idpost =" + id;
        int i = em.createQuery(query).executeUpdate();
        
        String queryReport = "DELETE FROM Report p WHERE p.idtarget =" + id;
        int r = em.createQuery(queryReport).executeUpdate();
    }
  
//    @GET
//    @Path("{id}")
//    public void deleteReport(@QueryParam("idreport") int report, @QueryParam("idpost") int idpost) {
//        super.remove(super.find(idpost));
//
//        String queryVote = "DELETE FROM Vote p WHERE p.idpost =" + idpost;
//        int v = em.createQuery(queryVote).executeUpdate();
//        
//        String queryReport = "DELETE FROM Report p WHERE p.idtarget =" + idpost;
//        int r = em.createQuery(queryReport).executeUpdate();
//    }
    
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
