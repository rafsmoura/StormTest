package br.com.stormtest.models;

/**
 * Created by root on 04/04/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TagName);
    }

    public Tag() {
    }

    protected Tag(Parcel in) {
        this.TagName = in.readString();
    }

    public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
}
