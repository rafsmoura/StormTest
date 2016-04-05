package br.com.stormtest.models;

/**
 * Created by root on 04/04/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Tag {

    @SerializedName("TagName")
    @Expose
    private String TagName;

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "TagName='" + TagName + '\'' +
                '}';
    }
}
