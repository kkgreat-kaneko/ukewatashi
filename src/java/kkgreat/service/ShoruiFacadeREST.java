/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

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
import kkgreat.Shorui;

/**
 *
 * @author great_kaneko
 */
@Stateless
@Path("shorui")
public class ShoruiFacadeREST extends AbstractFacade<Shorui> {
    @PersistenceContext(unitName = "UkewatashiPU")
    private EntityManager em;

    public ShoruiFacadeREST() {
        super(Shorui.class);
    }

    @TokenSecurity
    @GET
    @Override
    @Path("alllist")
    @Produces({"application/json"})
    public List<Shorui> findAll() {
        return super.findAll();
    }
    
    /*未使用
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Shorui entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Shorui entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Shorui find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Shorui> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    */
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
