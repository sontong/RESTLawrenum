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
    
    @GET
    public int getCallStatus(@QueryParam("idcall") int idcall){        
        String query =  "SELECT c FROM CallingSession c WHERE c.idcall="+idcall+
                        " AND c.startInCallTime IS NOT NULL";
        try{
            em.createQuery(query).getSingleResult();
            return 1;
        } catch(Exception ex){
            return 0;
        }
    }
    
    @GET
    @Path("acceptCall")
    public void acceptCall(@QueryParam("idcall") int idcall){
        CallingSession c = null;
        try{
            c = em.createNamedQuery("CallingSession.findByIdcall", CallingSession.class).setParameter("idcall", idcall).getSingleResult();
        } catch(Exception ex){
        }
                
        c.setStartInCallTime(Calendar.getInstance().getTime());
        super.edit(c);
        
//        String startCall = "UPDATE CallingSession c SET c.time = " + Calendar.getInstance().getTime()
//                + " WHERE c.idcall="+idcall;
//        int i = em.createQuery(startCall).executeUpdate();
        
//        String changeUserStatus = "UPDATE User u SET u.isbeingcalled = 1 WHERE u.iduser = "+c.getIdreceiver();
//        int ii = em.createQuery(changeUserStatus).executeUpdate();
    }
    
    @GET
    @Path("endCall")
    public void endCall(@QueryParam("idcall") int idcall){
        CallingSession c = null;
        try{
            c = em.createNamedQuery("CallingSession.findByIdcall", CallingSession.class).setParameter("idcall", idcall).getSingleResult();
            c.setIsOver(1);
            super.edit(c);
        } catch(Exception ex){
        }
        
        String endCall = "UPDATE User u SET u.isbeingcalled = 0 WHERE u.iduser IN ("+c.getIdcaller()+","+c.getIdreceiver()+")";
        int i = em.createQuery(endCall).executeUpdate();
    }
    
    @GET 
    @Path("stopCall")
    public void stopCall(@QueryParam("idcall") int idcall){
        CallingSession c = null;
        try{
            c = em.createNamedQuery("CallingSession.findByIdcall", CallingSession.class).setParameter("idcall", idcall).getSingleResult();
        } catch(Exception ex){
        }
        
        String endCall = "UPDATE User u SET u.isbeingcalled = 0 WHERE u.iduser = "+c.getIdcaller();
        int i = em.createQuery(endCall).executeUpdate();
    }
    
    @POST
    @Consumes({"application/json"})
    public int createCall(CallingSession entity) {                  
        super.create(entity);
        entity.setIsOver(0);
        entity.setStartTime(Calendar.getInstance().getTime());
        em.flush();
        
        String query = "UPDATE User u SET u.isbeingcalled = 1 WHERE u.iduser IN ("+entity.getIdcaller()+","+entity.getIdreceiver()+")";
        int i = em.createQuery(query).executeUpdate();
        
        return entity.getIdcall();
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