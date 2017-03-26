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
import javax.ws.rs.core.MediaType;
import lawrenum.Chat;
/**
 *
 * @author Son Tong
 */
@Stateless
@Path("chat")
public class ChatFacadeREST extends AbstractFacade<Chat> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public ChatFacadeREST() {
        super(Chat.class);
    }   
    
    @GET
    @Produces({"application/json"})
    public List<Chat> getAllChats(){
        return em.createNamedQuery("Chat.findAll", Chat.class).getResultList();                
    }
    
    @GET
    @Path("create")    
    @Consumes({"application/json"})
    public void getCreateChat(@QueryParam("idsender") int idsender, @QueryParam("idreceiver") int idreceiver, @QueryParam("content") String content) {
        Chat entity = new Chat();
        entity.setIdsender(idsender);
        entity.setIdreceiver(idreceiver);
        entity.setContent(content);
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
    }
    
    @POST
    @Consumes({"application/json"})
    public void createChat(Chat entity) {
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/json"})
//    public void edit(@PathParam("id") Integer id, Chat entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
