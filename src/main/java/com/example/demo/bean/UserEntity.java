/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.data.UserRole;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

/**
 * [OVERVIEW] User Entity.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new
*/
@Entity
@Table(name = "User")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "user_id")
    @SerializedName("user_id")
    @JsonProperty("user_id")
    private Integer userId;
    
    @JsonProperty("username")
    @Column(name = "username")
    private String username;
    
    @JsonProperty("password")
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @JsonProperty("email")
    @Column(name = "email")
    private String email;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @JsonProperty("dob")
    @Column(name = "dob")
    private Date dob;

    @JsonProperty("address")
    @Column(name = "address")
    private String address;

    @JsonProperty("role")
    @Column(name = "role")
    private Integer role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }
    
    @JsonGetter("dob")
    public String getDobValue() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateValue = df.format(dob);
        return dateValue;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getRole() {
        return UserRole.parse(this.role);
    }
    
    @JsonGetter("role")
    public Integer getRoleValue() {
    	return this.role;
    }
    
    public void setRole(UserRole role) {
    	this.role = role.value();
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UserEntity() {
        super();
    }

    public UserEntity(Integer userId, String username, String password, String email, String phone, Date dob, String address, Integer role) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.role = role;
    }
    

}
