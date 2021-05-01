package com.example.bulletin_test.ui.writingContent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class RecipePostInfo implements Serializable {
    private String titleImage;
    private String title;
    private ArrayList<String> content;
    private String publisher;
    private Date createdAt;
    private long recom;

    public RecipePostInfo(String titleImage, String title, ArrayList<String> content, String publisher, Date createdAt , long recom){
        this.titleImage = titleImage;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.createdAt = createdAt;
        this.recom = recom;
    }


    public String getTitleImage(){ return this.titleImage;}
    public void setTitleImage(String titleImage){this.titleImage = titleImage;}
    public String getTitle(){ return this.title;}
    public void setTitle(String title){this.title = title;}
    public ArrayList<String> getContent(){ return  this.content;}
    public void setContent(ArrayList<String> content){this.content = content;}
    public String getPublisher(){return this.publisher;}
    public void setPublisher(String publisher){this.publisher = publisher;}
    public Date getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(Date publisher){this.createdAt = createdAt;}
    public long getRecom(){ return this.recom;}
    public void setRecom(long recom){this.recom = recom;}
}
