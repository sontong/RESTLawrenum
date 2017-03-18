/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum.service;

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
import lawrenum.Vote;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("vote")
public class VoteFacadeREST extends AbstractFacade<Vote> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public VoteFacadeREST() {
        super(Vote.class);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Vote findByIdvote(@PathParam("id") Integer id) {
        return super.find(id);
    } 
    
    @GET
    @Produces({"text/plain"})
    public int findId(@QueryParam("idpost") int idpost, @QueryParam("iduser") int iduser) {
        String query = "SELECT p From Vote p WHERE p.idpost ="+idpost+" AND p.iduser="+iduser;
        Vote v = null;
        try{
            v = (Vote) em.createQuery(query).getSingleResult();
        } catch(Exception ex){
            return 0;
        }        
        return 1;
    }    
    
    @POST
    @Consumes({"application/json"})
    public String createVote(Vote entity) {   
        // Persist the vote into the Vote table
        super.create(entity);
        em.flush();

//        String query = "UPDATE Post p SET p.upvote = p.upvote + 1 WHERE p.idpost = " + entity.getIdpost();
//        int i = em.createQuery(query).executeUpdate();
        
        String q2 = "UPDATE HandlePost p SET p.upvote = p.upvote + 1 WHERE p.idpost = " + entity.getIdpost();
        int ii = em.createQuery(q2).executeUpdate();

        return entity.getIdvote().toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Vote entity) {
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
