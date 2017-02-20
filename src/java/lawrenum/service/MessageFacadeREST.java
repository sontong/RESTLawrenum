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
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
import lawrenum.Message;
import lawrenum.Post;
import lawrenum.Report;
import lawrenum.Request;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("message")
public class MessageFacadeREST extends AbstractFacade<Message> {    
    private String reportApproved = "One of your recent report has been approved.";    
    private String request = "Your request for a new sub forum: \n";
    private String requestAccepted = "has been approved.";                
    private String requestDeclined = "has been declined for this reason: \n";   
    private String post = "Your post: \n";
    private String postDeleted = "has been deleted for this reason: \n";
    
    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public MessageFacadeREST() {
        super(Message.class);
    }
    
    @GET
    @Path("{id}")
    @Produces({"text/plain"})
    public String hasSeenAll(@PathParam("id") int iduser) {        
        String query = "SELECT m FROM Message m WHERE m.iduser = "+iduser+" and m.seen = "+0;                
        Message m = null;                
        try {
            m = (Message) em.createQuery(query).getSingleResult();
            return "1"; 
        } catch (NonUniqueResultException ex) {
            return "1";
        } catch (NoResultException ex){
            return "0";
        }
    }

    @GET
    @Path("iduser")
    @Produces({"application/json"})
    public List<Message> findByIdsuer(@QueryParam("iduser") int iduser) {                
        String query = "SELECT p FROM Message p WHERE p.iduser = "+iduser+
                " ORDER BY p.time DESC";        
        
        String update = "UPDATE Message p SET p.seen = 1 WHERE p.iduser = "+iduser;
        int i = em.createQuery(update).executeUpdate();
        
        return em.createQuery(query).getResultList();
    }
    
    @GET
    @Path("reportApproved")
    public void sendReportApprovedMessage(@QueryParam("idreport") int idreport) {        
        // reportApproved                
        Message toReporter = new Message();
        Report r = null;
        try {
            r = em.createNamedQuery("Report.findByIdreport", Report.class).setParameter("idreport", idreport).getSingleResult();
        } catch (Exception ex) {
        }
        toReporter.setIduser(r.getIduser());
        toReporter.setSeen(0);
        toReporter.setTime(Calendar.getInstance().getTime());
        toReporter.setContent(reportApproved);

        super.create(toReporter);
        System.out.println("toReporter: iduser =" + r.getIduser());

        // postDeleted        
        Message toPoster = new Message();
        Post p = null;
        try {
            p = em.createNamedQuery("Post.findByIdpost", Post.class).setParameter("idpost", r.getIdtarget()).getSingleResult();
        } catch (Exception ex) {
        }
        toPoster.setIduser(p.getIduser());
        toPoster.setSeen(0);
        toPoster.setTime(Calendar.getInstance().getTime());
        toPoster.setContent(post + p.getContent() + "\n" + postDeleted + r.getContent());

        super.create(toPoster);
        em.flush();        
    }
    
    @GET
    @Path("requestAccepted")
    public void sendRequestMessage(@QueryParam("idrequest") int idrequest) {        
        Message toRequester = new Message();
        
        Request r = null;
        try {
            r = em.createNamedQuery("Request.findByIdrequest", Request.class).setParameter("idrequest", idrequest).getSingleResult();
        } catch (Exception ex) {
        }
        
        toRequester.setIduser(r.getIduser());
        toRequester.setSeen(0);
        toRequester.setTime(Calendar.getInstance().getTime());
        toRequester.setContent(request + r.getTitle() + "\n" + requestAccepted);

        super.create(toRequester);
        em.flush();
    }

    @GET
    @Path("requestDeclined")
    public void sendRequestDeclined(@QueryParam("idrequest") int idrequest, @QueryParam("reason") String reason) {        
        Message toRequester = new Message();
        
        Request r = null;
        try {
            r = em.createNamedQuery("Request.findByIdrequest", Request.class).setParameter("idrequest", idrequest).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        toRequester.setIduser(r.getIduser());
        toRequester.setSeen(0);
        toRequester.setTime(Calendar.getInstance().getTime());
        toRequester.setContent(request + r.getTitle() + "\n" + requestDeclined + reason);

        super.create(toRequester);
        em.flush();
    }
    
    @POST
    @Consumes({"application/json"})
    public String createMessage(Message entity) {
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
        return entity.getIdmessage().toString();
    }
    
    public void newMessage(Message entity){
        super.create(entity);
        em.flush();
    }
    
    public String getPostContent(int idpost){
        String query = "SELECT p.title, p.content FROM Post p WHERE p.idpost="+idpost;
        String title = (String) em.createQuery(query).getParameterValue("title");
        String content = (String) em.createQuery(query).getParameterValue("content");
        return title + "\n" + content + "\n";
    }    

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Message entity) {
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
