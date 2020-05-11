/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat.utility;

/**
 *
 * @author great_kaneko
 */
public class Utility {
    
    public static String sqlStringFormat(String str) {
        StringBuffer sb = new StringBuffer("'");
        sb.append(str);
        return sb.append("'")
                .toString();
    }
}
