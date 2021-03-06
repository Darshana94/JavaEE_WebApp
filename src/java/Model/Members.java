package Model;
// Generated Jul 23, 2018 6:33:59 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Members generated by hbm2java
 */
public class Members  implements java.io.Serializable {


     private String id;
     private String name;
     private String address;
     private Date dob;
     private Date dor;
     private String status;
     private float balance;

    public Members() {
    }

	
    public Members(String id, String name, String address, String status, float balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.balance = balance;
    }
    public Members(String id, String name, String address, Date dob, Date dor, String status, float balance) {
       this.id = id;
       this.name = name;
       this.address = address;
       this.dob = dob;
       this.dor = dor;
       this.status = status;
       this.balance = balance;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getDob() {
        return this.dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public Date getDor() {
        return this.dor;
    }
    
    public void setDor(Date dor) {
        this.dor = dor;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public float getBalance() {
        return this.balance;
    }
    
    public void setBalance(float balance) {
        this.balance = balance;
    }




}


