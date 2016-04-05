package br.com.stormtest.models;

/**
 * Created by root on 04/04/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

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
}