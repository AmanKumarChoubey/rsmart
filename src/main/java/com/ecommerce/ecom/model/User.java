package com.ecommerce.ecom.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data  // <-- THIS generates all getters & setters automatically
public class User {

    @Transient  //@Transient means it will not be saved in the database.
    private String confirmedPassword;

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;  // primary key like CC1, MM1, SS1, AA1

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // ADMIN, MANAGER, SHOPKEEPER, CUSTOMER
    private String role;

    private String createdBy;
    private LocalDateTime createdOn = LocalDateTime.now();
//    add one more field in database called Address
    private String address;

    public  String getConfirmPassword(){
        return confirmedPassword;
    }

    public void setConfirmPassword(String confirmedPassword){
        this.confirmedPassword=confirmedPassword;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
