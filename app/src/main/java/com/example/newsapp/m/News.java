
package com.example.newsapp.m;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("articles")
    private List<Article> mArticles= new ArrayList<>();
    @SerializedName("status")
    private String mStatus="";
    @SerializedName("totalResults")
    private Long mTotalResults=0L;

    public List<Article> getArticles() {
        return mArticles;
    }

    public void setArticles(List<Article> articles) {
        mArticles = articles;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
