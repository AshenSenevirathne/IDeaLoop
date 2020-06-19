package com.idealoop.busseek.model;

public class Passenger {
    String fname;
    String lname;
    String address;
    String contactno;
    String NIC;
    String mainbusroute;
    String passengerID;
    String CustomerType;
    String imgurl;
    String email;
    String Password;

    public Passenger(){}
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



    public Passenger(String imgurl,String fname, String lname, String NIC, String address, String contactno, String email, String mainbusroute, String passengerID, String customerType, String Password) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.contactno = contactno;
        this.NIC = NIC;
        this.mainbusroute = mainbusroute;
        this.passengerID = passengerID;
        CustomerType = customerType;
        this.imgurl = imgurl;
        this.email = email;
        this.Password = Password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getMainbusroute() {
        return mainbusroute;
    }

    public void setMainbusroute(String mainbusroute) {
        this.mainbusroute = mainbusroute;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
