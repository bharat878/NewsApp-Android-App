
package com.example.newsapp.m;

import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    private String mId="";
    @SerializedName("name")
    private String mName="";

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
