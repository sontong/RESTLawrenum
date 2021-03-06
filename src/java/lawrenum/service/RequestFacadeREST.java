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
import lawrenum.Request;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("request")
public class RequestFacadeREST extends AbstractFacade<Request> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public RequestFacadeREST() {
        super(Request.class);
    }    

    @GET    
    @Override
    @Produces({"application/json"})
    public List<Request> findAll() {
        String query = "SELECT p FROM Request p ORDER BY p.time DESC";       
        return em.createQuery(query).getResultList();                
    }
    
    @GET
    @Path("{id}")
    public void deleteRequest(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }    
    
    @POST
    @Consumes({"application/json"})
    public String createPost(Request entity) {
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
        return entity.getIdrequest().toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Request entity) {
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
