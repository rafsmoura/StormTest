package br.com.stormtest.models;

/**
 * Created by root on 04/04/16.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("ContentTitle")
    @Expose
    private String ContentTitle;
    @SerializedName("ContentURL")
    @Expose
    private String ContentURL;
    @SerializedName("ShelfImage")
    @Expose
    private String ShelfImage;
    @SerializedName("Likes")
    @Expose
    private Integer Likes;
    @SerializedName("ViewCount")
    @Expose
    private Integer ViewCount;
    @SerializedName("Type")
    @Expose
    private String Type;
    @SerializedName("Tags")
    @Expose
    private List<Tag> Tags = new ArrayList<Tag>();
    @SerializedName("ShortDescription")
    @Expose
    private String ShortDescription;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("RelatedVideos")
    @Expose
    private List<Object> RelatedVideos = new ArrayList<Object>();
    @SerializedName("User")
    @Expose
    private User User;

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
     * The ContentTitle
     */
    public String getContentTitle() {
        return ContentTitle;
    }

    /**
     *
     * @param ContentTitle
     * The ContentTitle
     */
    public void setContentTitle(String ContentTitle) {
        this.ContentTitle = ContentTitle;
    }

    /**
     *
     * @return
     * The ContentURL
     */
    public String getContentURL() {
        return ContentURL;
    }

    /**
     *
     * @param ContentURL
     * The ContentURL
     */
    public void setContentURL(String ContentURL) {
        this.ContentURL = ContentURL;
    }

    /**
     *
     * @return
     * The ShelfImage
     */
    public String getShelfImage() {
        return ShelfImage;
    }

    /**
     *
     * @param ShelfImage
     * The ShelfImage
     */
    public void setShelfImage(String ShelfImage) {
        this.ShelfImage = ShelfImage;
    }

    /**
     *
     * @return
     * The Likes
     */
    public Integer getLikes() {
        return Likes;
    }

    /**
     *
     * @param Likes
     * The Likes
     */
    public void setLikes(Integer Likes) {
        this.Likes = Likes;
    }

    /**
     *
     * @return
     * The ViewCount
     */
    public Integer getViewCount() {
        return ViewCount;
    }

    /**
     *
     * @param ViewCount
     * The ViewCount
     */
    public void setViewCount(Integer ViewCount) {
        this.ViewCount = ViewCount;
    }

    /**
     *
     * @return
     * The Type
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @param Type
     * The Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     *
     * @return
     * The Tags
     */
    public List<Tag> getTags() {
        return Tags;
    }

    /**
     *
     * @param Tags
     * The Tags
     */
    public void setTags(List<Tag> Tags) {
        this.Tags = Tags;
    }

    /**
     *
     * @return
     * The ShortDescription
     */
    public String getShortDescription() {
        return ShortDescription;
    }

    /**
     *
     * @param ShortDescription
     * The ShortDescription
     */
    public void setShortDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    /**
     *
     * @return
     * The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The RelatedVideos
     */
    public List<Object> getRelatedVideos() {
        return RelatedVideos;
    }

    /**
     *
     * @param RelatedVideos
     * The RelatedVideos
     */
    public void setRelatedVideos(List<Object> RelatedVideos) {
        this.RelatedVideos = RelatedVideos;
    }

    /**
     *
     * @return
     * The User
     */
    public User getUser() {
        return User;
    }

    /**
     *
     * @param User
     * The User
     */
    public void setUser(User User) {
        this.User = User;
    }

    @Override
    public String toString() {
        return "Content{" +
                "Id=" + Id +
                ", ContentTitle='" + ContentTitle + '\'' +
                ", ContentURL='" + ContentURL + '\'' +
                ", ShelfImage='" + ShelfImage + '\'' +
                ", Likes=" + Likes +
                ", ViewCount=" + ViewCount +
                ", Type='" + Type + '\'' +
                ", Tags=" + Tags +
                ", ShortDescription='" + ShortDescription + '\'' +
                ", Description='" + Description + '\'' +
                ", RelatedVideos=" + RelatedVideos +
                ", User=" + User +
                '}';
    }
}