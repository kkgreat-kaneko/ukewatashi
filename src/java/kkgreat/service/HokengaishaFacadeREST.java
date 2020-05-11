/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import kkgreat.Hokengaisha;
import kkgreat.SeihoList;

/**
 *
 * @author great_kaneko
 */
@Stateless
@Path("hokengaisha")
public class HokengaishaFacadeREST extends AbstractFacade<Hokengaisha> {
    @PersistenceContext(unitName = "UkewatashiPU")
    private EntityManager em;

    public HokengaishaFacadeREST() {
        super(Hokengaisha.class);
    }

    @TokenSecurity
    @POST
    @Path("tantoulist")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<Hokengaisha> getTantouList(Hokengaisha entity) {
        TypedQuery<Hokengaisha> q = getEntityManager().createNamedQuery("Hokengaisha.getTantouList", Hokengaisha.class);
        q.setParameter("hokengaisha", entity.getHokengaisha());
        return q.getResultList();
    }
    
    @TokenSecurity
    @POST
    @Path("seiholist")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<SeihoList> getSeihoList(SeihoList seihoList) {
        TypedQuery<String> q = getEntityManager().createNamedQuery("Hokengaisha.getSeihoList", String.class);
        q.setParameter("hokengaisha", seihoList.getHokengaisha());
        //q.setParameter("hokengaisha", "三井住友海上火災保険　株式会社　");
        List<String> result = q.getResultList();
        List<SeihoList> seihoLists = new ArrayList<>();
        for ( String list : result ){
            SeihoList seiholist = new SeihoList();
            seiholist.setSeiho(list);
            seihoLists.add(seiholist);
        }
        return seihoLists;
    }
    
    /*未使用
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Hokengaisha entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Hokengaisha entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Hokengaisha find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Hokengaisha> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Hokengaisha> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
