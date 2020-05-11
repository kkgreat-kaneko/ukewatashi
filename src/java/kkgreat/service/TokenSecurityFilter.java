/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author great_kaneko
 */
@TokenSecurity
@Provider
public class TokenSecurityFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @EJB
    JwtAuthorization jwtauth;
    private static final String AUTHENTICATE_SCHEME = "Bearer";
    
    @Override
    public void filter(ContainerRequestContext requestContext) {
        //リクエストヘッダー情報取得
        String authrizationHeader = 
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        //Authorizationヘッダー検証
        if (!isTokenBasedAuthentication(authrizationHeader)) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build());
            return;
        }
        
        //Authorizationヘッダー：Bearer "token" <----Bearer+スペースを取り除いたtokenを取得       
        String token = authrizationHeader
                .substring(AUTHENTICATE_SCHEME.length()).trim();
        
        //EJB JwtAuthorizationクラスで認証処理
        if (!jwtauth.verifyToken(token)) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build());
            return;
        }
    }

    
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
    }
    
    
    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        //Authorizationヘッダー情報を検証
        //"Bearer "で始まるAuthorizationを許可
        return authorizationHeader != null &&
                authorizationHeader.toLowerCase()
                        .startsWith(AUTHENTICATE_SCHEME.toLowerCase() + " ");
    }
}
