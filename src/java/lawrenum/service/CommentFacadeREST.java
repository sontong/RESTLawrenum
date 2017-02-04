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
import lawrenum.Comment;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("comment")
public class CommentFacadeREST extends AbstractFacade<Comment> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public CommentFacadeREST() {
        super(Comment.class);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Comment find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("idpost")
    @Produces({"application/json"})
    public List<Comment> findByIdforum(@QueryParam("idpost") int idpost) {
        return em.createNamedQuery("Comment.findByIdpost", Comment.class).setParameter("idpost", idpost).getResultList();
    }
        
    @POST
    @Consumes({"application/json"})
    public String createComment(Comment entity) {
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
        return entity.getIdcomment().toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Comment entity) {
        super.edit(entity);
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
