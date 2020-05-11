/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author great_kaneko
 */
@javax.ws.rs.ApplicationPath("ws")
//@javax.ws.rs.ApplicationPath("webresources")
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
        resources.add(kkgreat.service.FubiFacadeREST.class);
        resources.add(kkgreat.service.HokengaishaFacadeREST.class);
        resources.add(kkgreat.service.HokengaishaListFacadeREST.class);
        resources.add(kkgreat.service.KankyouFacadeREST.class);
        resources.add(kkgreat.service.KanriDelFacadeREST.class);
        resources.add(kkgreat.service.KanriFacadeREST.class);
        resources.add(kkgreat.service.KubunFacadeREST.class);
        resources.add(kkgreat.service.NewCrossOriginResourceSharingFilter.class);
        resources.add(kkgreat.service.ShoruiFacadeREST.class);
        resources.add(kkgreat.service.TantoushaFacadeREST.class);
        resources.add(kkgreat.service.TokenSecurityFilter.class);
    }
    
}
