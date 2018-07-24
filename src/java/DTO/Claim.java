/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Chathuranga
 */
import java.util.Date;

public class Claim {
    
    private String id;
    private String mem_id;
    private Date date;
    private String rationale;
    private String status;
    private float amount;
   
    
    public Claim(){}
    public Claim(String status,float balance,int max_claim){
        
    }
    public Claim(String id,String mem_id, Date date, String rationale, String status, float amount){
        this.id = id;
        this.mem_id = mem_id;
        this.date = date;
        this.rationale = rationale;
        this.status = status;
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the mem_id
     */
    public String getMem_id() {
        return mem_id;
    }

    /**
     * @param mem_id the mem_id to set
     */
    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the rationale
     */
    public String getRationale() {
        return rationale;
    }

    /**
     * @param rationale the rationale to set
     */
    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    /**
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

}
