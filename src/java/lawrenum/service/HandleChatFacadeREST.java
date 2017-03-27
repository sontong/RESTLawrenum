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
import lawrenum.HandleChat;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("handlechat")
public class HandleChatFacadeREST extends AbstractFacade<HandleChat> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public HandleChatFacadeREST() {
        super(HandleChat.class);
    }

    @GET
    @Produces({"application/json"})
    public List<HandleChat> findMessage(@QueryParam("idsender") int idsender,@QueryParam("idreceiver") int idreceiver) {        
        String query = "SELECT c FROM HandleChat c WHERE (c.idsender = "+idsender+
                " AND c.idreceiver = "+idreceiver+") OR (c.idsender = "+idreceiver+
                " AND c.idreceiver = "+idsender+") ";
        
        return em.createQuery(query).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
