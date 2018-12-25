package com.example.filenber.goog;

/**
 * Created by filenber on 07/12/2018.
 */

public class channel_list_items {
    int communityid;
    String communityname;
    String uniquecommunityname;
    String creator_name;
    String communityImage;
    String Date;
    String Time;
    String Join_num;
    String status;


    public channel_list_items(int communityid , String communityname, String uniquecommunityname, String creator_name, String communityImage, String date, String time, String join_num,String status) {
       this.communityid = communityid;
        this.communityname = communityname;
        this.uniquecommunityname = uniquecommunityname;
        this.creator_name = creator_name;
        this.communityImage = communityImage;
       this. Date = date;
       this. Time = time;
      this.Join_num = join_num;
      this.status =status;
    }
    public int getCommunityid() {
        return communityid;
    }

    public void setCommunityid(int communityid) {
        this.communityid = communityid;
    }

    public String getCommunityname() {
        return communityname;
    }

    public void setCommunityname(String communityname) {
        this.communityname = communityname;
    }

    public String getUniquecommunityname() {
        return uniquecommunityname;
    }

    public void setUniquecommunityname(String uniquecommunityname) {
        this.uniquecommunityname = uniquecommunityname;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getCommunityImage() {
        return communityImage;
    }

    public void setCommunityImage(String communityImage) {
        this.communityImage = communityImage;
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

    public String getJoin_num() {
        return Join_num;
    }

    public void setJoin_num(String join_num) {
        Join_num = join_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
