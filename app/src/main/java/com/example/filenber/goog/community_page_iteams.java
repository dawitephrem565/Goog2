package com.example.filenber.goog;

/**
 * Created by filenber on 03/12/2018.
 */

public class community_page_iteams

{
    int id;
    String username;
    String userprofileimage;
    String userworkarea;
    String userpostmsg;
    String userimgpost;
   String  clapstatus;
    String numberofclap;
    String numberofcomment;
    String Date;
    String Time;



    public community_page_iteams(int id, String username, String userprofileimage, String userworkarea, String userpostmsg, String userimgpost, String clapstatus, String numberofclap, String numberofcomment, String date, String time) {
        this.id = id;
        this.username = username;
        this.userprofileimage = userprofileimage;
        this.userworkarea = userworkarea;
        this.userpostmsg = userpostmsg;

        this.userimgpost = userimgpost;
        this.clapstatus = clapstatus;
        this.numberofclap = numberofclap;
        this.numberofcomment = numberofcomment;
        Date = date;
        Time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserprofileimage() {
        return userprofileimage;
    }

    public void setUserprofileimage(String userprofileimage) {
        this.userprofileimage = userprofileimage;
    }

    public String getUserworkarea() {
        return userworkarea;
    }

    public void setUserworkarea(String userworkarea) {
        this.userworkarea = userworkarea;
    }

    public String getUserpostmsg() {
        return userpostmsg;
    }

    public void setUserpostmsg(String userpostmsg) {
        this.userpostmsg = userpostmsg;
    }

    public String getUserimgpost() {
        return userimgpost;
    }

    public void setUserimgpost(String userimgpost) {
        this.userimgpost = userimgpost;
    }
    public String getClapstatus() {
        return clapstatus;
    }

    public void setClapstatus(String clapstatus) {
        this.clapstatus = clapstatus;
    }

    public String getNumberofclap() {
        return numberofclap;
    }

    public void setNumberofclap(String numberofclap) {
        this.numberofclap = numberofclap;
    }

    public String getNumberofcomment() {
        return numberofcomment;
    }

    public void setNumberofcomment(String numberofcomment) {
        this.numberofcomment = numberofcomment;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
