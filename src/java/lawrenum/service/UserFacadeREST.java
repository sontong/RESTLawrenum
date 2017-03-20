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
import javax.ws.rs.QueryParam;
import lawrenum.User;

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
        return em.createNamedQuery("User.findAll", User.class).getResultList();
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