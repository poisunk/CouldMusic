package com.example.couldmusic.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class PlayListDetailBean implements Serializable {
    /**"id": 24381616,
     "name": "binaryify喜欢的音乐",
     "coverImgId": 109951165454342880,
     "coverImgUrl": "https://p1.music.126.net/EZC9icQrd8arHI0P9qcu-A==/109951165454342883.jpg",
     "coverImgId_str": "109951165454342883",
     "adType": 0,
     "userId": 32953014,
     "createTime": 1407747901072,
     "status": 0,
     "opRecommend": false,
     "highQuality": false,
     "newImported": false,
     "updateTime": 1643874616995,
     "trackCount": 977,
     "specialType": 5,
     "privacy": 0,
     "trackUpdateTime": 1643880908347,
     "commentThreadId": "A_PL_0_24381616",
     "playCount": 16227,
     "trackNumberUpdateTime": 1643871393532,
     "subscribedCount": 4,
     "cloudTrackCount": 1,
     "ordered": true,
     "description": "描述",
     "tags": [
     "学习"
     ],
     "updateFrequency": null,
     "backgroundCoverId": 0,
     "backgroundCoverUrl": null,
     "titleImage": 0,
     "titleImageUrl": null,
     "englishTitle": null,
     "officialPlaylistType": null,
     "subscribers": [],
     "subscribed": false,
     "creator": {
     "defaultAvatar": false,
     "province": 440000,
     "authStatus": 0,
     "followed": false,
     "avatarUrl": "http://p1.music.126.net/axewGX7u9P9Iuqjep-3mmQ==/109951165601796681.jpg",
     "accountStatus": 0,
     "gender": 1,
     "city": 440300,
     "birthday": 0,
     "userId": 32953014,
     "userType": 0,
     "nickname": "binaryify",
     "signature": "深圳市爱猫人士",
     "description": "",
     "detailDescription": "",
     "avatarImgId": 109951165601796690,
     "backgroundImgId": 109951163792144620,
     "backgroundUrl": "http://p1.music.126.net/WLTBvNL_l9ZKlslFwaCM9Q==/109951163792144631.jpg",
     "authority": 0,
     "mutual": false,
     "expertTags": null,
     "experts": null,
     "djStatus": 0,
     "vipType": 11,
     "remarkName": null,
     "authenticationTypes": 0,
     "avatarDetail": null,
     "avatarImgIdStr": "109951165601796681",
     "backgroundImgIdStr": "109951163792144631",
     "anchor": false,
     "avatarImgId_str": "109951165601796681"
     },*/

    private long id;
    private String name;
    private long coverImgId;
    private String coverImgUrl;
    private String coverImgId_str;
    private int adType;
    private long userId;
    private long createTime;
    private int status;
    private boolean opRecommend;
    private boolean highQuality;
    private boolean newImported;
    private long updateTime;
    private int trackCount;
    private int specialType;
    private int privacy;
    private long trackUpdateTime;
    private String commentThreadId;
    private long playCount;
    private long trackNumberUpdateTime;
    private int subscribedCount;
    private int cloudTrackCount;
    private boolean ordered;
    private String description;
    private List<String> tags;
    private Object updateFrequency;
    private long backgroundCoverId;
    private String backgroundCoverUrl;
    private long titleImage;
    private Object titleImageUrl;
    private Object englishTitle;
    private Object officialPlaylistType;
    private List<Object> subscribers;
    private boolean subscribed;
    private Creator creator;
    private List<TrackIds> trackIds;

    public List<TrackIds> getTrackIds() {
        return trackIds;
    }

    public void setTrackIds(List<TrackIds> trackIds) {
        this.trackIds = trackIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCoverImgId() {
        return coverImgId;
    }

    public void setCoverImgId(long coverImgId) {
        this.coverImgId = coverImgId;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getCoverImgId_str() {
        return coverImgId_str;
    }

    public void setCoverImgId_str(String coverImgId_str) {
        this.coverImgId_str = coverImgId_str;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isOpRecommend() {
        return opRecommend;
    }

    public void setOpRecommend(boolean opRecommend) {
        this.opRecommend = opRecommend;
    }

    public boolean isHighQuality() {
        return highQuality;
    }

    public void setHighQuality(boolean highQuality) {
        this.highQuality = highQuality;
    }

    public boolean isNewImported() {
        return newImported;
    }

    public void setNewImported(boolean newImported) {
        this.newImported = newImported;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public int getSpecialType() {
        return specialType;
    }

    public void setSpecialType(int specialType) {
        this.specialType = specialType;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public long getTrackUpdateTime() {
        return trackUpdateTime;
    }

    public void setTrackUpdateTime(long trackUpdateTime) {
        this.trackUpdateTime = trackUpdateTime;
    }

    public String getCommentThreadId() {
        return commentThreadId;
    }

    public void setCommentThreadId(String commentThreadId) {
        this.commentThreadId = commentThreadId;
    }

    public long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(long playCount) {
        this.playCount = playCount;
    }

    public long getTrackNumberUpdateTime() {
        return trackNumberUpdateTime;
    }

    public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
        this.trackNumberUpdateTime = trackNumberUpdateTime;
    }

    public int getSubscribedCount() {
        return subscribedCount;
    }

    public void setSubscribedCount(int subscribedCount) {
        this.subscribedCount = subscribedCount;
    }

    public int getCloudTrackCount() {
        return cloudTrackCount;
    }

    public void setCloudTrackCount(int cloudTrackCount) {
        this.cloudTrackCount = cloudTrackCount;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(Object updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public long getBackgroundCoverId() {
        return backgroundCoverId;
    }

    public void setBackgroundCoverId(long backgroundCoverId) {
        this.backgroundCoverId = backgroundCoverId;
    }

    public String getBackgroundCoverUrl() {
        return backgroundCoverUrl;
    }

    public void setBackgroundCoverUrl(String backgroundCoverUrl) {
        this.backgroundCoverUrl = backgroundCoverUrl;
    }

    public long getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(long titleImage) {
        this.titleImage = titleImage;
    }

    public Object getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(Object titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public Object getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(Object englishTitle) {
        this.englishTitle = englishTitle;
    }

    public Object getOfficialPlaylistType() {
        return officialPlaylistType;
    }

    public void setOfficialPlaylistType(Object officialPlaylistType) {
        this.officialPlaylistType = officialPlaylistType;
    }

    public List<Object> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Object> subscribers) {
        this.subscribers = subscribers;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public static class Creator{
        private boolean defaultAvatar;
        private long province;
        private int authStatus;
        private boolean followed;
        private String avatarUrl;
        private int accountStatus;
        private int gender;
        private long city;
        private long birthday;
        private long userId;
        private int userType;
        private String nickname;
        private String signature;
        private String description;
        private String detailDescription;
        private long avatarImgId;
        private long backgroundImgId;
        private String backgroundUrl;
        private int authority;
        private boolean mutual;
        private Object expertTags;
        private Object experts;
        private int djStatus;
        private int vipType;
        private Object remarkName;
        private int authenticationTypes;
        private Object avatarDetail;
        private String avatarImgIdStr;
        private String backgroundImgIdStr;
        private boolean anchor;
        private String avatarImgId_str;

        public boolean isDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public long getProvince() {
            return province;
        }

        public void setProvince(long province) {
            this.province = province;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public long getCity() {
            return city;
        }

        public void setCity(long city) {
            this.city = city;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }

        public boolean isMutual() {
            return mutual;
        }

        public void setMutual(boolean mutual) {
            this.mutual = mutual;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public Object getExperts() {
            return experts;
        }

        public void setExperts(Object experts) {
            this.experts = experts;
        }

        public int getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(int djStatus) {
            this.djStatus = djStatus;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public int getAuthenticationTypes() {
            return authenticationTypes;
        }

        public void setAuthenticationTypes(int authenticationTypes) {
            this.authenticationTypes = authenticationTypes;
        }

        public Object getAvatarDetail() {
            return avatarDetail;
        }

        public void setAvatarDetail(Object avatarDetail) {
            this.avatarDetail = avatarDetail;
        }

        public String getAvatarImgIdStr() {
            return avatarImgIdStr;
        }

        public void setAvatarImgIdStr(String avatarImgIdStr) {
            this.avatarImgIdStr = avatarImgIdStr;
        }

        public String getBackgroundImgIdStr() {
            return backgroundImgIdStr;
        }

        public void setBackgroundImgIdStr(String backgroundImgIdStr) {
            this.backgroundImgIdStr = backgroundImgIdStr;
        }

        public boolean isAnchor() {
            return anchor;
        }

        public void setAnchor(boolean anchor) {
            this.anchor = anchor;
        }

        public String getAvatarImgId_str() {
            return avatarImgId_str;
        }

        public void setAvatarImgId_str(String avatarImgId_str) {
            this.avatarImgId_str = avatarImgId_str;
        }
    }

    public static class TrackIds{

        /**"id": 534952158,
         "v": 19,
         "t": 0,
         "at": 1643871393532,
         "alg": null,
         "uid": 32953014,
         "rcmdReason": ""
         */

        private long id;
        private int v;
        private int t;
        private long at;
        private Object alg;
        private long uid;
        private String rcmdReason;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public long getAt() {
            return at;
        }

        public void setAt(long at) {
            this.at = at;
        }

        public Object getAlg() {
            return alg;
        }

        public void setAlg(Object alg) {
            this.alg = alg;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getRcmdReason() {
            return rcmdReason;
        }

        public void setRcmdReason(String rcmdReason) {
            this.rcmdReason = rcmdReason;
        }
    }

}
