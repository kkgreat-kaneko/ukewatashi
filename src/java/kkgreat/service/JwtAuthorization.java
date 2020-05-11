/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import javax.ejb.Stateless;
/**
 *
 * @author great_kaneko
 */
@Stateless
public class JwtAuthorization {
    public String issuToken() {
        String myToken = "";
        String claim = "";
        //System.out.println("jwt issuToken");
        try {
            Date expireTime = new Date();
            //expireTime.setTime(expireTime.getTime() + 600000l);//ミリ単位　10分プラス
            expireTime.setTime(expireTime.getTime() + 86400000l);//ミリ単位　24プラス時間
                    
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                .withIssuer("greatauth0")
                .withExpiresAt(expireTime) //有効期限登録
                .withClaim("sub", "kaneko") //calaim subに情報登録 UserIDとか
                .sign(algorithm);
            myToken = token;
        
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            System.out.println("error jwt");
        }
        //System.out.println("mytoken=>" + myToken);
        return myToken;
    }
    
    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("greatauth0")
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
            //System.out.println(jwt.getIssuer());
            //System.out.println(jwt.getType());
            //System.out.println(jwt.getHeaderClaim("owner"));
            //System.out.println(jwt.getSubject());
            //System.out.println(jwt.getExpiresAt().toString());
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            //System.out.println(exception.getMessage());//期限切れ表示
            return false;
        }
        
    }
}
