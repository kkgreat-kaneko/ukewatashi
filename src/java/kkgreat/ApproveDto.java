/*
 * 書類データ承認/承認戻し用DTOクラス
 */
package kkgreat;

import java.io.Serializable;

/**
 *
 * @author great_kaneko
 */
public class ApproveDto implements Serializable{
    private boolean setApprove;         // 承認:TRUE・承認戻し:FALSE 判定用
    private Long[] kanriIds;            // 承認未承認書類データID配列
    private String shouninsha;          // 承認者
    private String shouninbi;           // 承認日
    private String mishouninsha;        // 未承認者
    private String mishouninbi;         // 未承認日

    /**
     * @return the setApprove
     */
    public boolean isSetApprove() {
        return setApprove;
    }

    /**
     * @param setApprove the setApprove to set
     */
    public void setSetApprove(boolean setApprove) {
        this.setApprove = setApprove;
    }

    /**
     * @return the kanriIds
     */
    public Long[] getKanriIds() {
        return kanriIds;
    }

    /**
     * @param kanriIds the kanriIds to set
     */
    public void setKanriIds(Long[] kanriIds) {
        this.kanriIds = kanriIds;
    }

    /**
     * @return the shouninsha
     */
    public String getShouninsha() {
        return shouninsha;
    }

    /**
     * @param shouninsha the shouninsha to set
     */
    public void setShouninsha(String shouninsha) {
        this.shouninsha = shouninsha;
    }

    /**
     * @return the shouninbi
     */
    public String getShouninbi() {
        return shouninbi;
    }

    /**
     * @param shouninbi the shouninbi to set
     */
    public void setShouninbi(String shouninbi) {
        this.shouninbi = shouninbi;
    }

    /**
     * @return the mishouninsha
     */
    public String getMishouninsha() {
        return mishouninsha;
    }

    /**
     * @param mishouninsha the mishouninsha to set
     */
    public void setMishouninsha(String mishouninsha) {
        this.mishouninsha = mishouninsha;
    }

    /**
     * @return the mishouninbi
     */
    public String getMishouninbi() {
        return mishouninbi;
    }

    /**
     * @param mishouninbi the mishouninbi to set
     */
    public void setMishouninbi(String mishouninbi) {
        this.mishouninbi = mishouninbi;
    }

    
    
}
