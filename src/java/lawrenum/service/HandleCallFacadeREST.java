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
import javax.ws.rs.core.MediaType;
import lawrenum.HandleCall;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("handlecall")
public class HandleCallFacadeREST extends AbstractFacade<HandleCall> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public HandleCallFacadeREST() {
        super(HandleCall.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public HandleCall findById(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<HandleCall> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
