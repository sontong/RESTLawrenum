/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum.service;

import java.math.BigInteger;
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
import lawrenum.CallingSession;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("call")
public class CallingSessionFacadeREST extends AbstractFacade<CallingSession> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public CallingSessionFacadeREST() {
        super(CallingSession.class);
    }
    
    @GET
    @Path("getAll")
    @Produces({"application/json"})
    public List<CallingSession> getAll(){
        return em.createNamedQuery("CallingSession.findAll", CallingSession.class).getResultList();
    }

//    @GET
//    public void createCall(@QueryParam("idcaller") Integer idcaller, @QueryParam("idreceiver") Integer idreceiver){
//        CallingSession newCall = new CallingSession();        
//        newCall.setIdcaller(idcaller);
//        newCall.setIdreceiver(idreceiver);
//        newCall.setTime(Calendar.getInstance().getTime());
//        super.create(newCall);
//        em.flush();
//    }
//            
    
    @POST
    @Consumes({"application/json"})
    public String createCall(CallingSession entity) {   
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();

        return entity.getIdcall().toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, CallingSession entity) {
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
