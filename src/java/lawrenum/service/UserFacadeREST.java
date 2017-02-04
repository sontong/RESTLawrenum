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
import lawrenum.Post;
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
    @Produces({"text/plain"})
    public String checkUser(@QueryParam("user") String user, @QueryParam("password") String password) {
        User u = null;
        try {
            u = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", user).getSingleResult();
        } catch (Exception ex) {
            return "0";
        }
        if (u.getPassword().equals(password)) {
//            return u.getIduser().toString();
            return u.toJSONString();
        } else {
            return "0";
        }
    }

    @GET
    @Path("id")
    @Produces({"text/plain"})
    public String findId(@QueryParam("user") String user) {
        User u = null;
        try {
            u = em.createNamedQuery("User.findByName", User.class).setParameter("username", user).getSingleResult();
        } catch (Exception ex) {
            return "0";
        }
        return u.getIduser().toString();
    }
    
    @POST
    @Consumes({"application/json"})
    public String createUser(User entity) {
        // First check that no user with this name is already in the system
        User other = null;
        try {
            other = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", entity.getUsername()).getSingleResult();
        } catch (Exception ex) {
           
        }
        if(other != null)
            return "0";
                
        super.create(entity);
        em.flush();
        return entity.toJSONString();
        // return entity.getIduser().toString();
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
