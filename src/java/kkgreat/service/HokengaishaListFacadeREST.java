/*
 * 保険会社リストサービスクラス
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
import kkgreat.HokengaishaList;
import kkgreat.Kankyou;


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
    * 保険会社名検索(1件)
    *
    */
    @TokenSecurity
    @GET
    @Path("getbyhokengaishaname/{name}")
    @Produces({"application/json"})
    public List<HokengaishaList> getByHokengaishaName(@PathParam("name") String name) {
        TypedQuery<HokengaishaList> q = getEntityManager().createNamedQuery("HokengaishaList.findByHokengaisha", HokengaishaList.class);
        q.setParameter("hokengaisha", name);
        return q.getResultList();
    }
    
    /*
    * 保険会社登録
    *
    */
    @TokenSecurity
    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public HokengaishaList createHokengaishaList(HokengaishaList entity) {
        /*
        *   idの割り当ては、Kankyouテーブルhokengaisha_maxより取得し、HokengaishaListにInsert時に同時に更新している
        */
        Kankyou kankyou = getEntityManager().find(Kankyou.class, 1L);
        Long maxId = kankyou.getHokengaishaMax();
        maxId += 1L;
        entity.setId(maxId);
        super.create(entity);
        super.flush();                            //継承クラスにflushメソッドを追加、entityManagerに即時insert発行
        kankyou.setHokengaishaMax(maxId);         //Kankyoテーブルhokengaisha_max値を更新
        getEntityManager().merge(kankyou);
        super.flush();
        return super.find(entity.getId());
    }
    
    /*
    * 保険会社更新
    *
    */
    @TokenSecurity
    @POST
    @Path("update")
    @Consumes({"application/json"})
    public HokengaishaList update(HokengaishaList entity) {
        super.edit(entity);
        return super.find(entity.getId());
    }
    
    /*
    * 保険会社削除
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
