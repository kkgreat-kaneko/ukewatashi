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
import kkgreat.HokengaishaList;

/**
 *
 * @author great_kaneko
 */
@Stateless
@Path("hokengaishalist")
public class HokengaishaListFacadeREST extends AbstractFacade<HokengaishaList> {
    @PersistenceContext(unitName = "UkewatashiPU")
    private EntityManager em;

    public HokengaishaListFacadeREST() {
        super(HokengaishaList.class);
    }

    @TokenSecurity
    @GET
    @Override
    @Path("alllist")
    @Produces({"application/json"})
    public List<HokengaishaList> findAll() {
        return super.findAll();
    }
    
    /*
    * 以下未使用メソッド
    *
    @TokenSecurity
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(HokengaishaList entity) {
        super.create(entity);
    }
    
    @TokenSecurity
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, HokengaishaList entity) {
        super.edit(entity);
    }

    @TokenSecurity
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @TokenSecurity
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public HokengaishaList find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @TokenSecurity
    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<HokengaishaList> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @TokenSecurity
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
