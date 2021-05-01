package com.example.bulletin_test.ui.writingContent;

import java.util.ArrayList;
import java.util.Date;

public class FreePostInfo {
    private String title;
    private String content;
    private String publisher;
    private Date createdAt;

    public FreePostInfo(String title, String content, String publisher, Date createdAt){
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.createdAt = createdAt;
    }

    public String getTitle(){ return this.title;}
    public void setTitle(String title){this.title = title;}
    public String getContent(){ return  this.content;}
    public void setContent(String content){this.content = content;}
    public String getPublisher(){return this.publisher;}
    public void setPublisher(String publisher){this.publisher = publisher;}
    public Date getCreatedAt(){return this.createdAt;}
    public void setCreatedAt(Date publisher){this.createdAt = createdAt;}

}
