
package com.spitslide.recommendationapp.tastedive;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar_ {

    @SerializedName("Info")
    @Expose
    private List<Info> info = null;
    @SerializedName("Results")
    @Expose
    private List<Result> results = null;

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
