/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
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
import kkgreat.Tantousha;
import kkgreat.Token;

/**
 *
 * @author great_kaneko
 */
@Stateless
@Path("tantousha")
public class TantoushaFacadeREST extends AbstractFacade<Tantousha> {
    @PersistenceContext(unitName = "UkewatashiPU")
    private EntityManager em;
    
    @EJB
    JwtAuthorization jwtauth;

    public TantoushaFacadeREST() {
        super(Tantousha.class);
    }
    
    /*
    * ログイン処理時使用
    * アクセスセキュリティ制限無し
    * ログイン成功時、アクセス許可トークンを発行、以後トークンチェックよりアクセスを許可 
    */
    @POST
    @Consumes({"application/json"})
    @Path("auth")
    public Token auth(Tantousha authTantousha) {
        if (Objects.nonNull(authTantousha.getUserId()) ) {
            Tantousha tantousha = super.find(authTantousha.getUserId());
            if (Objects.nonNull(tantousha)) {
                if ( tantousha.getPassword().equals(authTantousha.getPassword()) ) {
                    Token token = new Token();
                    token.setToken(jwtauth.issuToken());
                    //System.out.println("test token");
                    //token.setUserId(tantousha.getUserId());
                    //token.setKengen(tantousha.getKengen());
                    token.setTantousha(tantousha);
                    return token;
                }
            }
        }
        System.out.println("login fail");
        return null;
    }
    
    /*
    * 全検索用
    */
    @TokenSecurity
    @GET
    @Override
    @Produces({"application/json"})
    public List<Tantousha> findAll() {
        return super.findAll();
    }
    
    /*
    * 会社別社員検索
    * 申請者変更時に会社別社員一覧用
    */
    @TokenSecurity
    @GET
    @Produces({"application/json"})
    @Path("bykaisha/{kaisha}")
    public List<Tantousha> getListByKaisha(@PathParam("kaisha") String kaisha) {
        TypedQuery<Tantousha> q = getEntityManager().createNamedQuery("Tantousha.findByKaisha", Tantousha.class);
        q.setParameter("kaisha", kaisha);
        return q.getResultList();
    }
    
    /*
    * 担当者ID検索(1件)
    *
    */
    @TokenSecurity
    @GET
    @Path("getbyid/{id}")
    @Produces({"application/json"})
    public Tantousha getById(@PathParam("id") String id) {
        return super.find(id);
    }
    
    /*
    * 担当者更新
    *
    */
    @TokenSecurity
    @POST
    @Path("update")
    @Consumes({"application/json"})
    public Tantousha update(Tantousha entity) {
        super.edit(entity);
        return super.find(entity.getUserId());
    }
    
    
    
    //テスト確認用フリーアクセス可能 削除予定
    /*
    @POST
    @Consumes({"application/json"})
    @Path("authold")
    public Tantousha authOld(Tantousha authTantousha) {
        if (Objects.nonNull(authTantousha.getUserId()) ) {
            Tantousha tantousha = super.find(authTantousha.getUserId());
            if (Objects.nonNull(tantousha)) {
                if ( tantousha.getPassword().equals(authTantousha.getPassword()) ) {
                    return tantousha;
                }
            }
        }
        return null;
    }
    */
    /*---------------------------------------------------------------------------------------
    *   未使用
    *
    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Tantousha entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Tantousha entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Tantousha find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Tantousha> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
