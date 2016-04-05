package br.com.stormtest.models;

/**
 * Created by root on 04/04/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("UserName")
    @Expose
    private String UserName;
    @SerializedName("UserProfilePhoto")
    @Expose
    private String UserProfilePhoto;

    /**
     *
     * @return
     * The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     *
     * @param UserName
     * The UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     *
     * @return
     * The UserProfilePhoto
     */
    public String getUserProfilePhoto() {
        return UserProfilePhoto;
    }

    /**
     *
     * @param UserProfilePhoto
     * The UserProfilePhoto
     */
    public void setUserProfilePhoto(String UserProfilePhoto) {
        this.UserProfilePhoto = UserProfilePhoto;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", UserName='" + UserName + '\'' +
                ", UserProfilePhoto='" + UserProfilePhoto + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.Id);
        dest.writeString(this.UserName);
        dest.writeString(this.UserProfilePhoto);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.Id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.UserName = in.readString();
        this.UserProfilePhoto = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}