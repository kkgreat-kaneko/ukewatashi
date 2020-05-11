/*
 * アクセストークン DTOクラス
 */
package kkgreat;


public class Token {
    private String token;
    private Tantousha tantousha;
    /*
    private String userId;
    private long kengen;
    */

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the tantousha
     */
    public Tantousha getTantousha() {
        return tantousha;
    }

    /**
     * @param tantousha the tantousha to set
     */
    public void setTantousha(Tantousha tantousha) {
        this.tantousha = tantousha;
    }

}
