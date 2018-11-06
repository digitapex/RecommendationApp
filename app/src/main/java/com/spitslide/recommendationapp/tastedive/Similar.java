
package com.spitslide.recommendationapp.tastedive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    @SerializedName("Similar")
    @Expose
    private Similar_ similar;

    public Similar_ getSimilar() {
        return similar;
    }

    public void setSimilar(Similar_ similar) {
        this.similar = similar;
    }

}
