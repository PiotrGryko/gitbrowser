package testexample.piotr.com.gitbrowser.app.model;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import testexample.piotr.com.gitbrowser.R;

/**
 * Created by piotr on 11/05/17.
 */
public class ModelUser extends BaseObservable implements Serializable {

    @SerializedName("login")
    public String login;
    @SerializedName("id")
    int id;
    @SerializedName("avatar_url")
    String imageUrl;
    @SerializedName("gravatar_id")
    String gravatar_id;
    @SerializedName("url")
    String url;
    @SerializedName("type")
    String type;
    @SerializedName("site_admin")
    boolean site_admin;
    @SerializedName("name")
    String name;
    @SerializedName("company")
    String company;
    @SerializedName("blog")
    String blog;
    @SerializedName("location")
    String location;
    @SerializedName("email")
    String email;
    @SerializedName("hirable")
    String hireable;
    @SerializedName("bio")
    String bio;
    @SerializedName("public_repos")
    int public_repos;
    @SerializedName("public_gists")
    int public_gists;
    @SerializedName("followers")
    int followers;
    @SerializedName("following")
    int following;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;


    public String getImageUrl() {return imageUrl;}
    public String getUrl() {return url;}
    public String getName() {return name;}
    public String getLocation() {return location;}
    public String getType() {return type;}
    public String getEmail() {return email;}
    public String getBlog() {return blog;}
    public int getPublicRepos() {return public_repos;}
    public int getGists() {return public_gists;}
    public int getFollowers() {return followers;}
    public int getFollowing() {return following;}
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(view);
    }
}
