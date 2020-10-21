/*
 * システム定数 Const
 */
package kkgreat;


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
    public static final String STATUS_END   = "6";
    
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
    
    
    // 本番開発検証機用共通パス設定/opt以下パスにファイル配置
    // チェックシート印刷用jasperファイル
    public static final String JASPER_PATH_JLX_CHECKSHEET = "/opt/ukewatashi/jasper/JLX_report_logo.jasper";
    public static final String PDF_OUTPUT_PATH_JLX_CHECKSHEET = "/opt/ukewatashi/pdf/JLX_CHECKSHEET";
    public static final String IMG_PATH_JLX_CHECKSHEET = "/opt/ukewatashi/img/";
    // 確認書印刷用jasperファイル　確認書控え印刷用jasperファイル　出力先パス
    public static final String JASPER_PATH_JLX_HOKEN_CONFIRM = "/opt/ukewatashi/jasper/hoken_report.jasper";
    public static final String JASPER_PATH_JLX_HOKEN_CONFIRM_COPY = "/opt/ukewatashi/jasper/hoken_report_copy.jasper";
    public static final String PDF_OUTPUT_PATH_JLX_HOKEN_CONFIRM = "/opt/ukewatashi/pdf/hoken_CHECKSHEET";
    
    
}
