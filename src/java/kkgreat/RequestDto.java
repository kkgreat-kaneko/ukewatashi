/*
 * エンティティ以外で使用する汎用リクエスト応答クラス
 * 型別に配列型パラメーターを用意
 */
package kkgreat;

import java.io.Serializable;


public class RequestDto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long[] paramLongs;
    private String[] paramStrings;

    /**
     * @return the paramLongs
     */
    public Long[] getParamLongs() {
        return paramLongs;
    }

    /**
     * @param paramLongs the paramLongs to set
     */
    public void setParamLongs(Long[] paramLongs) {
        this.paramLongs = paramLongs;
    }

    /**
     * @return the paramStrings
     */
    public String[] getParamStrings() {
        return paramStrings;
    }

    /**
     * @param paramStrings the paramStrings to set
     */
    public void setParamStrings(String[] paramStrings) {
        this.paramStrings = paramStrings;
    }
    
    
}
