package com.example.bulletin_test.ui.login;

//회원 정보 저장

public class MemberInfo {

    private String name;
    private String phoneNumber;
    private String Date;
    private String adress;
    private String userId;

    public MemberInfo(String name, String phoneNumber, String adress, String Date, String userId)
    {
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.Date=Date;
        this.adress=adress;
        this.userId=userId;
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

    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }




}
