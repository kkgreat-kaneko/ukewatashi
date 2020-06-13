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
    
    /*
    * 担当者ID検索(1件)
    *
    */
    @TokenSecurity
    @GET
    @Path("getbyid/{id}")
    @Produces({"application/json"})
    public Hokengaisha getById(@PathParam("id") String id) {
        return super.find(id);
    }
    
    /*
    * 保険会社担当者登録
    *
    */
    @TokenSecurity
    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Hokengaisha createHokengaisha(Hokengaisha entity) {
        super.create(entity);
        return super.find(entity.getUserId());
    }
    
    /*
    * 保険会社担当者更新
    *
    */
    @TokenSecurity
    @POST
    @Path("update")
    @Consumes({"application/json"})
    public Hokengaisha update(Hokengaisha entity) {
        super.edit(entity);
        return super.find(entity.getUserId());
    }
    
    /*
    * 保険会社担当者削除
    *
    */
    @TokenSecurity
    @DELETE
    @Path("delete/{id}")
    @Produces("text/plain")
    public Integer remove(@PathParam("id") String id) {
        super.remove(super.find(id));
        return 1;
    }
    
    /*
    *  保険会社担当者全件
    */
    @TokenSecurity
    @GET
    @Path("hokentantouall")
    @Produces({"application/json"})
    public List<Hokengaisha> getHokenTantouAll() {
        TypedQuery<Hokengaisha> q = getEntityManager().createNamedQuery("Hokengaisha.findAll", Hokengaisha.class);
        return q.getResultList();
    }
    
    /*
    * 検索 ユーザーID部分一致
    *
    */
    @TokenSecurity
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @Path("findlikeuserid")
    public List<Hokengaisha> findLikeUserId(Hokengaisha entity) {
        TypedQuery<Hokengaisha> q = getEntityManager().createNamedQuery("Hokengaisha.findLikeUserId", Hokengaisha.class);
        StringBuffer param = new StringBuffer();
        param.append("%");
        param.append(entity.getUserId());
        param.append("%");
        q.setParameter("userId", param.toString());
        return q.getResultList();
    }
    
    /*
    * 検索 氏名(確認者)部分一致
    *
    */
    @TokenSecurity
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @Path("findlikekakuninsha")
    public List<Hokengaisha> findLikeShimei(Hokengaisha entity) {
        TypedQuery<Hokengaisha> q = getEntityManager().createNamedQuery("Hokengaisha.findLikeKakuninsha", Hokengaisha.class);
        StringBuffer param = new StringBuffer();
        param.append("%");
        param.append(entity.getKakuninsha());
        param.append("%");
        q.setParameter("kakuninsha", param.toString());
        return q.getResultList();
    }
    
    /*
    * 検索 ユーザーID氏名部分一致
    *
    */
    @TokenSecurity
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @Path("findlikeuseridkakuninsha")
    public List<Hokengaisha> findLikeUserIdAndShimei(Hokengaisha entity) {
        TypedQuery<Hokengaisha> q = getEntityManager().createNamedQuery("Tantousha.findLikeUserIDAndKakuninsha", Hokengaisha.class);
        StringBuffer param1 = new StringBuffer();
        param1.append("%");
        param1.append(entity.getUserId());
        param1.append("%");
        q.setParameter("userId", param1.toString());
        StringBuffer param2 = new StringBuffer();
        param2.append("%");
        param2.append(entity.getKakuninsha());
        param2.append("%");
        q.setParameter("shimei", param2.toString());
        return q.getResultList();
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
