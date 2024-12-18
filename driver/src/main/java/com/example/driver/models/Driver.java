package com.example.driver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Driver {
    @Id
    private String email;
    private String drivername;
    private String mobile;
    private String gender;
    private String age;
    private String address;
    private String password;

    public Driver() {
        super();
    }

    public Driver(String email, String drivername, String mobile, String gender, String age, String address,
            String password) {
        super();
        this.email = email;
        this.drivername = drivername;
        this.mobile = mobile;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    // public void setEmailVerified(boolean res) {
    // this.emailVerified = res;
    // }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
