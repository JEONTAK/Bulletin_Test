package com.example.bulletin_test.ui.writingContent;

import java.util.ArrayList;
import java.util.Date;

public class PostInfo {
    private String title;
    private ArrayList<String> content;
    private String publisher;
    private Date createdAt;
    private int recom;

    public PostInfo(String title, ArrayList<String> content, String publisher, Date createdAt , int recom){
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.recom = recom;
    }

    public String getTitle(){ return this.title;}
    public void setTitle(String title){this.title = title;}
    public ArrayList<String> getContent(){ return  this.content;}
    public void setContent(ArrayList<String> content){this.content = content;}
    public String getPublisher(){return this.publisher;}
    public void setPublisher(String publisher){this.publisher = publisher;}
    public Date getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(Date publisher){this.createdAt = createdAt;}
    public int getRecom(){ return this.recom;}
    public void setRecom(int recom){this.recom = recom;}
}
