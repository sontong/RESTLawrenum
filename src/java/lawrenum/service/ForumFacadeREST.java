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
import lawrenum.Forum;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("forum")
public class ForumFacadeREST extends AbstractFacade<Forum> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public ForumFacadeREST() {
        super(Forum.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Forum find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Override
    @Produces({"application/json"})
    public List<Forum> findAll() {
        return super.findAll();
    }   
    
    @POST
    @Consumes({"application/json"})
    public String createForum(Forum entity) {        
        super.create(entity);
        em.flush();
        return entity.getIdforum().toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Forum entity) {
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
