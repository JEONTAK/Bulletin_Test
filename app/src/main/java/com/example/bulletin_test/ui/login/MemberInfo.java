package com.example.bulletin_test.ui.login;

//회원 정보 저장

public class MemberInfo {

    private String name;
    private String phoneNumber;
    private String Date;
    private String adress;

    public MemberInfo(String name, String phoneNumber, String adress, String Date)
    {
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.Date=Date;
        this.adress=adress;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public String getDate(){
        return this.Date;
    }
    public void setDate(String Date){
        this.Date=Date;
    }

    public String getAdress(){
        return this.adress;
    }
    public void setAdress(String adress){
        this.adress=adress;
    }




}
