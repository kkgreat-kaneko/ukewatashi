/*
 * 書類リストサービス
 * 
 * 
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
import kkgreat.Shorui;
import kkgreat.Kankyou;


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
    
    /*
    * 書類検索
    *
    */
    @TokenSecurity
    @GET
    @Path("getbyshorui/{name}")
    @Produces({"application/json"})
    public List<Shorui> getByShorui(@PathParam("name") String name) {
        TypedQuery<Shorui> q = getEntityManager().createNamedQuery("Shorui.findByShorui", Shorui.class);
        q.setParameter("shorui", name);
        return q.getResultList();
    }
    
    /*
    * 書類登録
    *
    */
    @TokenSecurity
    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Shorui createShorui(Shorui entity) {
        /*
        *   idの割り当ては、Kankyouテーブルshorui_maxより取得し、ShoruiにInsert時に同時に更新している
        */
        Kankyou kankyou = getEntityManager().find(Kankyou.class, 1L);
        Long maxId = kankyou.getShoruiMax();
        maxId += 1L;
        entity.setId(maxId);
        super.create(entity);
        super.flush();                            //継承クラスにflushメソッドを追加、entityManagerに即時insert発行
        kankyou.setShoruiMax(maxId);              //Kankyoテーブルshorui_max値を更新
        getEntityManager().merge(kankyou);
        super.flush();
        return super.find(entity.getId());
    }
    
    /*
    * 書類更新
    *
    */
    @TokenSecurity
    @POST
    @Path("update")
    @Consumes({"application/json"})
    public Shorui update(Shorui entity) {
        super.edit(entity);
        return super.find(entity.getId());
    }
    
    /*
    * 書類削除
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
