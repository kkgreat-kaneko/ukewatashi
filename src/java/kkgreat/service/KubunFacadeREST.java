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
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import kkgreat.Kubun;
import kkgreat.Kankyou;

/**
 *
 * @author great_kaneko
 */
@Stateless
@Path("kubun")
public class KubunFacadeREST extends AbstractFacade<Kubun> {
    @PersistenceContext(unitName = "UkewatashiPU")
    private EntityManager em;

    public KubunFacadeREST() {
        super(Kubun.class);
    }

    @TokenSecurity
    @GET
    @Override
    @Path("alllist")
    @Produces({"application/json"})
    public List<Kubun> findAll() {
        return super.findAll();
    }
    
    /*
    * 区分検索
    *
    */
    @TokenSecurity
    @GET
    @Path("getbykubun/{name}")
    @Produces({"application/json"})
    public List<Kubun> getByKubun(@PathParam("name") String name) {
        TypedQuery<Kubun> q = getEntityManager().createNamedQuery("Kubun.findByKubun", Kubun.class);
        q.setParameter("kubun", name);
        return q.getResultList();
    }
    
    /*
    * 区分登録
    *
    */
    @TokenSecurity
    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Kubun createKubun(Kubun entity) {
        /*
        *   idの割り当ては、Kankyouテーブルkubun_maxより取得し、KubunにInsert時に同時に更新している
        */
        Kankyou kankyou = getEntityManager().find(Kankyou.class, 1L);
        Long maxId = kankyou.getKubunMax();
        maxId += 1L;
        entity.setId(maxId);
        super.create(entity);
        super.flush();                            //継承クラスにflushメソッドを追加、entityManagerに即時insert発行
        kankyou.setKubunMax(maxId);               //Kankyoテーブルkubun_max値を更新
        getEntityManager().merge(kankyou);
        super.flush();
        return super.find(entity.getId());
    }
    
    /*
    * 区分更新
    *
    */
    @TokenSecurity
    @POST
    @Path("update")
    @Consumes({"application/json"})
    public Kubun update(Kubun entity) {
        super.edit(entity);
        return super.find(entity.getId());
    }
    
    /*
    * 区分削除
    *
    */
    @TokenSecurity
    @DELETE
    @Path("delete/{id}")
    @Produces("text/plain")
    public Integer remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
        return 1;
    }
    
    /* 未使用
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Kubun entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Kubun entity) {
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
    public Kubun find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Kubun> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Kubun> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
