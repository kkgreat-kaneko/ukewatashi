/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkgreat;

/**
 *
 * @author great_kaneko
 */
public class Const {

    public static final String JLX_HOKEN = "株式会社JALUX保険部";
    public static final String JLX_HS_HOKEN = "株式会社JALUX保険サービス";
    // 確認書印刷用会社名
    public static final String PRT_JLX_HOKEN = "JALUX保険部";
    public static final String PRT_JLX_HS_HOKEN = "JALUX保険サービス";
        
    // Status ButtonValue
    public static final String STATUS_ALL   = "1";
    public static final String STATUS_DLVRY = "2";
    public static final String STATUS_OK    = "3";
    public static final String STATUS_NOT   = "4";
    public static final String STATUS_NG    = "5";
    
    // 承認Status絞込み選択状態
    public static final String STATUS_APP_NOT = "1";
    public static final String STATUS_APP_OK  = "2";
    public static final String STATUS_APP_ALL = "3";
    
    // 承認ステータスID 未承認：0/ 承認済み：10
    public static final Integer STATUS_NOT_APPROVE = 0;
    public static final Integer STATUS_APPROVE = 10;
    
    // 受渡方法
    public static final String DLVRY_HANDING = "受渡";
    public static final String DLVRY_MAIL = "郵送";
    
    // 印刷JasperReportsファイルパス
    //public static final String JASPER_PATH_JLX_CHECKSHEET = "/Users/great_kaneko/NetBeansProjects/Ukewatashi/web/japsper/JLX_report.jasper";
    //public static final String PDF_OUTPUT_PATH_JLX_CHECKSHEET = "/Users/great_kaneko/NetBeansProjects/pdf/ukewatashi_testA4.pdf";
    // 開発検証機用共通パス設定/opt以下パスにファイル配置
    // チェックシート印刷用jasperファイル
    public static final String JASPER_PATH_JLX_CHECKSHEET = "/opt/ukewatashi/jasper/JLX_report.jasper";
    public static final String PDF_OUTPUT_PATH_JLX_CHECKSHEET = "/opt/ukewatashi/pdf/JLX_CHECKSHEET.pdf";
    // 確認書印刷用jasperファイル
    public static final String JASPER_PATH_JLX_HOKEN_CONFIRM = "/opt/ukewatashi/jasper/hoken_report.jasper";
    public static final String PDF_OUTPUT_PATH_JLX_HOKEN_CONFIRM = "/opt/ukewatashi/pdf/hoken_CHECKSHEET.pdf";
    
    
}
