/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lawrenum.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Son Tong
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(lawrenum.service.CommentFacadeREST.class);
        resources.add(lawrenum.service.EventFacadeREST.class);
        resources.add(lawrenum.service.ForumFacadeREST.class);
        resources.add(lawrenum.service.HandleCommentFacadeREST.class);
        resources.add(lawrenum.service.HandlePostFacadeREST.class);
        resources.add(lawrenum.service.MembershipFacadeREST.class);
        resources.add(lawrenum.service.MessageFacadeREST.class);
        resources.add(lawrenum.service.PostFacadeREST.class);
        resources.add(lawrenum.service.ReportFacadeREST.class);
        resources.add(lawrenum.service.RequestFacadeREST.class);
        resources.add(lawrenum.service.ReviewFacadeREST.class);
        resources.add(lawrenum.service.UserFacadeREST.class);
        resources.add(lawrenum.service.VoteFacadeREST.class);
    }
    
}
