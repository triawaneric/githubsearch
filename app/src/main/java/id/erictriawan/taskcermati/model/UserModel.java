package id.erictriawan.taskcermati.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erictriawan on 10/20/17.
 */

public class UserModel {


    @SerializedName("total_count")
    String mTotalCount;
    @SerializedName("incomplete_results")
    String mIncompletedResults;
    @SerializedName("items")
    List<Items> mItems;


    //get


    public String getmTotalCount() {
        return mTotalCount;
    }

    public List<Items> getmItems() {
        return mItems;
    }

    public String getmIncompletedResults() {
        return mIncompletedResults;
    }

    //set


    public void setmIncompletedResults(String mIncompletedResults) {
        this.mIncompletedResults = mIncompletedResults;
    }

    public void setmTotalCount(String mTotalCount) {
        this.mTotalCount = mTotalCount;
    }

    public void setmItems(List<Items> mItems) {
        this.mItems = mItems;
    }

    //class

    public class Items{

        @SerializedName("login")
        String mLogin;
        @SerializedName("id")
        String mId;
        @SerializedName("avatar_url")
        String mAvatarUrl;
        @SerializedName("gravatar_id")
        String mGravatarId;
        @SerializedName("url")
        String mUrl;
        @SerializedName("html_url")
        String mHtmlUrl;
        @SerializedName("followers_url")
        String mFollowersUrl;
        @SerializedName("following_url")
        String mFollowingUrl;
        @SerializedName("gists_url")
        String mGitsUrl;
        @SerializedName("starred_url")
        String mStarredUrl;
        @SerializedName("subscriptions_url")
        String mSubUrl;
        @SerializedName("organizations_url")
        String mOrgUrl;
        @SerializedName("repos_url")
        String mRepoUrl;
        @SerializedName("events_url")
        String mEventUrl;
        @SerializedName("received_events_url")
        String mReceivedEventsUrl;
        @SerializedName("type")
        String mType;
        @SerializedName("site_admin")
        String mSiteAdmin;
        @SerializedName("score")
        String mScore;


        //get


        public String getmGravatarId() {
            return mGravatarId;
        }

        public String getmId() {
            return mId;
        }

        public String getmSiteAdmin() {
            return mSiteAdmin;
        }

        public String getmAvatarUrl() {
            return mAvatarUrl;
        }

        public String getmEventUrl() {
            return mEventUrl;
        }

        public String getmFollowersUrl() {
            return mFollowersUrl;
        }

        public String getmFollowingUrl() {
            return mFollowingUrl;
        }

        public String getmGitsUrl() {
            return mGitsUrl;
        }

        public String getmHtmlUrl() {
            return mHtmlUrl;
        }

        public String getmLogin() {
            return mLogin;
        }

        public String getmOrgUrl() {
            return mOrgUrl;
        }

        public String getmReceivedEventsUrl() {
            return mReceivedEventsUrl;
        }

        public String getmRepoUrl() {
            return mRepoUrl;
        }

        public String getmScore() {
            return mScore;
        }

        public String getmStarredUrl() {
            return mStarredUrl;
        }

        public String getmSubUrl() {
            return mSubUrl;
        }

        public String getmType() {
            return mType;
        }

        public String getmUrl() {
            return mUrl;
        }

        //set


        public void setmAvatarUrl(String mAvatarUrl) {
            this.mAvatarUrl = mAvatarUrl;
        }

        public void setmEventUrl(String mEventUrl) {
            this.mEventUrl = mEventUrl;
        }

        public void setmFollowersUrl(String mFollowersUrl) {
            this.mFollowersUrl = mFollowersUrl;
        }

        public void setmFollowingUrl(String mFollowingUrl) {
            this.mFollowingUrl = mFollowingUrl;
        }

        public void setmGitsUrl(String mGitsUrl) {
            this.mGitsUrl = mGitsUrl;
        }



        public void setmHtmlUrl(String mHtmlUrl) {
            this.mHtmlUrl = mHtmlUrl;
        }


        public void setmLogin(String mLogin) {
            this.mLogin = mLogin;
        }

        public void setmOrgUrl(String mOrgUrl) {
            this.mOrgUrl = mOrgUrl;
        }

        public void setmReceivedEventsUrl(String mReceivedEventsUrl) {
            this.mReceivedEventsUrl = mReceivedEventsUrl;
        }

        public void setmRepoUrl(String mRepoUrl) {
            this.mRepoUrl = mRepoUrl;
        }

        public void setmScore(String mScore) {
            this.mScore = mScore;
        }

        public void setmGravatarId(String mGravatarId) {
            this.mGravatarId = mGravatarId;
        }

        public void setmId(String mId) {
            this.mId = mId;
        }

        public void setmSiteAdmin(String mSiteAdmin) {
            this.mSiteAdmin = mSiteAdmin;
        }

        public void setmStarredUrl(String mStarredUrl) {
            this.mStarredUrl = mStarredUrl;
        }

        public void setmSubUrl(String mSubUrl) {
            this.mSubUrl = mSubUrl;
        }

        public void setmType(String mType) {
            this.mType = mType;
        }

        public void setmUrl(String mUrl) {
            this.mUrl = mUrl;
        }
    }


}
