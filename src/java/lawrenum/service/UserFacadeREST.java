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
import lawrenum.User;
import lawrenum.HandleCall;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }
    
    @GET
    @Path("getAllUsers")
    @Produces({"application/json"})
    public List<User> getAllUsers(){
        return em.createNamedQuery("User.findByType", User.class).setParameter("type", 1).getResultList();                
    }
    
    @GET
    @Path("search")
    @Produces({"application/json"})
    public List<User> searchByName(@QueryParam("fullname") String fullname){        
        String query = "SELECT u FROM User u WHERE u.fullname LIKE '%"+fullname+"%'";
        return em.createQuery(query).getResultList();
    }
    
    @GET
    @Produces({"application/json"})
    public User checkUser(@QueryParam("user") String user, @QueryParam("password") String password) {
        User u = null;
        try {
            u = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", user).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
        if (u.getPassword().equals(password)) {
//            return u.getIduser().toString();
            return super.find(u.getIduser());
        } else {
            return null;
        }
    }

    @GET
    @Path("id")
    @Produces({"text/plain"})
    public String findId(@QueryParam("user") String user) {
        User u = null;
        try {
            u = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", user).getSingleResult();
        } catch (Exception ex) {
            return "0";
        }
        return u.getIduser().toString();
    }
    
    @GET
    @Path("avatar")
    public void uploadAvatar(@QueryParam("iduser") int iduser, @QueryParam("url") String url){
        String query = "UPDATE User u SET u.avatar = '"+url+"' WHERE u.iduser = "+iduser;
        int i = em.createQuery(query).executeUpdate();
    }
    
    @GET
    @Path("ping")
    public void pingServer(@QueryParam("iduser") int iduser){        
        try{
            User u = em.createNamedQuery("User.findByIduser", User.class).setParameter("iduser", iduser).getSingleResult();
            u.setLastOnline(Calendar.getInstance().getTime());
            super.edit(u);
        } catch(Exception ex){            
        }
    }
            
    @GET
    @Path("updateStatus")
    public void updateStatus(){
        List<User> userList = em.createNamedQuery("User.findAll", User.class).getResultList();        
        long msDiff;                
        
        for (User u : userList) {
            msDiff = Calendar.getInstance().getTime().getTime() - u.getLastOnline().getTime();   
            // If difference between the last time an user ping the server is bigger than 10 seconds,
            // then his status is changed to offline 
            if(msDiff > 1000*10){
                u.setStatus(0);
                super.edit(u);
            } else {
                u.setStatus(1);
                super.edit(u);
            }               
        }
    }
    
    @GET
    @Path("beingCalled")
    public int beingCalled(@QueryParam("iduser") int iduser){                       
        User u = null;
        try {
            u = em.createNamedQuery("User.findByIduser", User.class).setParameter("iduser", iduser).getSingleResult();
            if (u.getIsbeingcalled() == 1) {
                String query = "SELECT c FROM CallingSession c WHERE c.isOver = 0 AND (c.idreceiver =" + iduser
                        + " OR c.idcaller = " + iduser + ")";
                try {
                    List<CallingSession> callList = em.createQuery(query).getResultList();
                    for (CallingSession c : callList) {
                        // Check if the call is over
                        long msDiff = Calendar.getInstance().getTime().getTime() - c.getStartTime().getTime();
                        if (msDiff > 1000 * 45) {
                            String callOver = "UPDATE CallingSession c SET c.isOver = 1 WHERE c.idcall="+c.getIdcall();
                            int i = em.createQuery(callOver).executeUpdate();
                            
                            String changeBeingCalled = "UPDATE User u SET u.isbeingcalled = 0 "
                                    + "WHERE u.iduser IN ("+c.getIdcaller()+","+c.getIdreceiver()+")";
                            int ii = em.createQuery(changeBeingCalled).executeUpdate();
                        } else if (c.getIdcaller() == iduser) {
                            return -1;
                        } else {
                            return c.getIdcall();
                        }
                    }
                } catch (Exception ex) {                    
                }
            }
        } catch (Exception ex) {            
        }
        String changeBeingCalled = "UPDATE User u SET u.isbeingcalled = 0 "
                                    + "WHERE u.iduser="+u.getIduser();
        int i = em.createQuery(changeBeingCalled).executeUpdate();
        return 0;
    }
    
//    @GET
//    @Path("exp")
//    public void exp(){
//        List<User> userList = em.createNamedQuery("User.findAll", User.class).getResultList();        
//        long msDiff;                
//        
//        for (User u : userList) {
//            u.setLastOnline(Calendar.getInstance().getTime());
//            super.edit(u);
//        }
//    }
    
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public User createUser(User entity) {
        // First check that no user with this name is already in the system
        User other = null;
        try {
            other = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", entity.getUsername()).getSingleResult();
        } catch (Exception ex) {
           
        }
        if(other != null)
            return null;
                
        super.create(entity);
        em.flush();
        return entity;
//        return entity.getIduser().toString();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, User entity) {
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