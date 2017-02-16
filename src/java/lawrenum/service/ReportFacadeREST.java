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
import lawrenum.Report;

/**
 *
 * @author Son Tong
 */
@Stateless
@Path("report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "RESTLawrenumPU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @GET    
    @Override
    @Produces({"application/json"})
    public List<Report> findAll() {
        String query = "SELECT p FROM Report p ORDER BY p.time DESC";       
        return em.createQuery(query).getResultList();                
    }
    
    @GET
    @Path("{id}")
    public void deleteReport(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }    
            
    @POST
    @Consumes({"application/json"})
    public String createPost(Report entity) {
        entity.setTime(Calendar.getInstance().getTime());
        super.create(entity);
        em.flush();
        return entity.getIdreport().toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Report entity) {
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
