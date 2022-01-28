package com.example.couldmusic.bean;

import java.util.List;

public class LoginBean {

    /**
    "loginType": 1,
    "code": 200,
     */
    private int loginType;
    private int code;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**"account": {
     "id": 559535337,
     "userName": "1_17183517355",
     "type": 1,
     "status": 0,
     "whitelistAuthority": 0,
     "createTime": 1501997558332,
     "salt": "[B@1a428149",
     "tokenVersion": 0,
     "ban": 0,
     "baoyueVersion": 0,
     "donateVersion": 0,
     "vipType": 11,
     "viptypeVersion": 1640647329593,
     "anonimousUser": false,
     "uninitialized": false
     },
     */

    private AccountBean account;

    public class AccountBean{
        private long id;
        private String userName;
        private int type;
        private int status;
        private int whitelistAuthority;
        private long createTime;
        private String salt;
        private int tokenVersion;
        private int ban;
        private int baoyueVersion;
        private int donateVersion;
        private int vipType;
        private int viptypeVersion;
        private boolean anonimousUser;
        private boolean uninitialized;

        public boolean isUninitialized() {
            return uninitialized;
        }

        public void setUninitialized(boolean uninitialized) {
            this.uninitialized = uninitialized;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getWhitelistAuthority() {
            return whitelistAuthority;
        }

        public void setWhitelistAuthority(int whitelistAuthority) {
            this.whitelistAuthority = whitelistAuthority;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public int getTokenVersion() {
            return tokenVersion;
        }

        public void setTokenVersion(int tokenVersion) {
            this.tokenVersion = tokenVersion;
        }

        public int getBan() {
            return ban;
        }

        public void setBan(int ban) {
            this.ban = ban;
        }

        public int getBaoyueVersion() {
            return baoyueVersion;
        }

        public void setBaoyueVersion(int baoyueVersion) {
            this.baoyueVersion = baoyueVersion;
        }

        public int getDonateVersion() {
            return donateVersion;
        }

        public void setDonateVersion(int donateVersion) {
            this.donateVersion = donateVersion;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public int getViptypeVersion() {
            return viptypeVersion;
        }

        public void setViptypeVersion(int viptypeVersion) {
            this.viptypeVersion = viptypeVersion;
        }

        public boolean isAnonimousUser() {
            return anonimousUser;
        }

        public void setAnonimousUser(boolean anonimousUser) {
            this.anonimousUser = anonimousUser;
        }
    }

    /**"profile": {
     "followed": false,
     "backgroundUrl": "https://p3.music.126.net/Jz5XvKXTJlH6_gdLmu-9ig==/109951166680328177.jpg",
     "detailDescription": "",
     "avatarImgIdStr": "109951165229695932",
     "backgroundImgIdStr": "109951166680328177",
     "userId": 559535337,
     "userType": 0,
     "accountStatus": 0,
     "vipType": 11,
     "gender": 1,
     "avatarImgId": 109951165229695940,
     "nickname": "将材",
     "backgroundImgId": 109951166680328180,
     "birthday": 1056619003752,
     "city": 140100,
     "avatarUrl": "https://p4.music.126.net/gdeCW6ZAUpV6qDfhyTh3LA==/109951165229695932.jpg",
     "defaultAvatar": false,
     "province": 140000,
     "expertTags": null,
     "experts": {},
     "mutual": false,
     "remarkName": null,
     "authStatus": 0,
     "djStatus": 0,
     "description": "",
     "signature": "",
     "authority": 0,
     "avatarImgId_str": "109951165229695932",
     "followeds": 1,
     "follows": 1,
     "eventCount": 1,
     "avatarDetail": null,
     "playlistCount": 11,
     "playlistBeSubscribedCount": 1
     },*/
    private ProfileBean profile;
    public class ProfileBean{
        private String detailDescription;
        private boolean followed;
        private int userId;
        private boolean defaultAvatar;
        private String avatarUrl;
        private String nickname;
        private long birthday;
        private long avatarImgId;
        private int province;
        private int accountStatus;
        private int vipType;
        private int gender;
        private int djStatus;
        private String avatarImgIdStr;
        private String backgroundImgIdStr;
        private ExpertsBean experts;
        private boolean mutual;
        private Object remarkName;
        private Object expertTags;
        private int authStatus;
        private long backgroundImgId;
        private int userType;
        private int city;
        private String signature;
        private int authority;
        private String description;
        private String backgroundUrl;
        private String avatarImgId_str;
        private int followeds;
        private int follows;
        private int eventCount;
        private int playlistCount;
        private int playlistBeSubscribedCount;

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public boolean isDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(int djStatus) {
            this.djStatus = djStatus;
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

        public ExpertsBean getExperts() {
            return experts;
        }

        public void setExperts(ExpertsBean experts) {
            this.experts = experts;
        }

        public boolean isMutual() {
            return mutual;
        }

        public void setMutual(boolean mutual) {
            this.mutual = mutual;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getAvatarImgId_str() {
            return avatarImgId_str;
        }

        public void setAvatarImgId_str(String avatarImgId_str) {
            this.avatarImgId_str = avatarImgId_str;
        }

        public int getFolloweds() {
            return followeds;
        }

        public void setFolloweds(int followeds) {
            this.followeds = followeds;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getEventCount() {
            return eventCount;
        }

        public void setEventCount(int eventCount) {
            this.eventCount = eventCount;
        }

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public int getPlaylistBeSubscribedCount() {
            return playlistBeSubscribedCount;
        }

        public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
            this.playlistBeSubscribedCount = playlistBeSubscribedCount;
        }

        public class ExpertsBean {
        }
    }

    /**"bindings": [
     {
     "userId": 559535337,
     "url": "",
     "expired": false,
     "bindingTime": 1501997586814,
     "tokenJsonStr": "{\"countrycode\":\"\",\"cellphone\":\"17183517355\",\"hasPassword\":true}",
     "expiresIn": 2147483647,
     "refreshTime": 1501997586,
     "id": 3191434185,
     "type": 1
     },
     {
     "userId": 559535337,
     "url": "",
     "expired": true,
     "bindingTime": 1501997558336,
     "tokenJsonStr": "{\"access_token\":\"FAB16879D8A42F309B24424B417FEC0F\",\"openid\":\"B88F39A356F3C171A5DC0C6AEE1A3243\",\"query_authority_cost\":685,\"nickname\":\"@贤言\",\"partnerType\":\"0\",\"expires_in\":7776000,\"login_cost\":455,\"authority_cost\":2883}",
     "expiresIn": 7776000,
     "refreshTime": 1577544347,
     "id": 3191434186,
     "type": 5
     }
     ],*/

     private List<BindingsBean> bindings;
     public class BindingsBean{

        private int refreshTime;
        private String url;
        private int userId;
        private String tokenJsonStr;
        private long id;
        private int type;
        private int expiresIn;
        private long bindingTime;
        private boolean expired;

        public int getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(int refreshTime) {
            this.refreshTime = refreshTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTokenJsonStr() {
            return tokenJsonStr;
        }

        public void setTokenJsonStr(String tokenJsonStr) {
            this.tokenJsonStr = tokenJsonStr;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public long getBindingTime() {
            return bindingTime;
        }

        public void setBindingTime(long bindingTime) {
            this.bindingTime = bindingTime;
        }

        public boolean isExpired() {
            return expired;
        }

        public void setExpired(boolean expired) {
            this.expired = expired;
        }
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public List<BindingsBean> getBindings() {
        return bindings;
    }

    public void setBindings(List<BindingsBean> bindings) {
        this.bindings = bindings;
    }
}