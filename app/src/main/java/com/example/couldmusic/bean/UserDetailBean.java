package com.example.couldmusic.bean;

public class UserDetailBean {
    /**"level": 10,
     "listenSongs": 29118,
     "userPoint": {
     "userId": 32953014,
     "balance": 95,
     "updateTime": 1644492803360,
     "version": 10,
     "status": 1,
     "blockBalance": 0
     },
     "mobileSign": false,
     "pcSign": false,
     "profile": {
     "privacyItemUnlimit": {
     "area": true,
     "college": true,
     "age": true,
     "villageAge": true
     },
     "avatarDetail": null,
     "accountStatus": 0,
     "province": 440000,
     "createTime": 1407747900967,
     "birthday": 768931200000,
     "gender": 1,
     "nickname": "binaryify",
     "avatarImgId": 109951165601796690,
     "mutual": false,
     "djStatus": 0,
     "avatarUrl": "http://p1.music.126.net/axewGX7u9P9Iuqjep-3mmQ==/109951165601796681.jpg",
     "backgroundImgId": 109951163792144620,
     "backgroundUrl": "http://p1.music.126.net/WLTBvNL_l9ZKlslFwaCM9Q==/109951163792144631.jpg",
     "city": 440300,
     "vipType": 11,
     "followed": false,
     "remarkName": null,
     "userType": 0,
     "authStatus": 0,
     "detailDescription": "",
     "experts": {},
     "expertTags": null,
     "defaultAvatar": false,
     "backgroundImgIdStr": "109951163792144631",
     "avatarImgIdStr": "109951165601796681",
     "description": "",
     "userId": 32953014,
     "signature": "深圳市爱猫人士",
     "authority": 0,
     "followeds": 51,
     "follows": 16,
     "blacklist": false,
     "eventCount": 17,
     "allSubscribedCount": 0,
     "playlistBeSubscribedCount": 4,
     "avatarImgId_str": "109951165601796681",
     "followTime": null,
     "followMe": false,
     "artistIdentity": [],
     "cCount": 0,
     "inBlacklist": false,
     "sDJPCount": 0,
     "playlistCount": 20,
     "sCount": 0,
     "newFollows": 16
     },
     "peopleCanSeeMyPlayRecord": false,
     "bindings": [
     {
     "expiresIn": 2147483647,
     "refreshTime": 1592285666,
     "bindingTime": 1426295169224,
     "tokenJsonStr": null,
     "expired": false,
     "url": "",
     "userId": 32953014,
     "id": 28098251,
     "type": 1
     },
     {
     "expiresIn": 2628968,
     "refreshTime": 1507142393,
     "bindingTime": 1407747883151,
     "tokenJsonStr": null,
     "expired": true,
     "url": "http://weibo.com/u/5144142752",
     "userId": 32953014,
     "id": 18574366,
     "type": 2
     }
     ],*/

    private int level;
    private int listenSongs;
    private Profile profile;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getListenSongs() {
        return listenSongs;
    }

    public void setListenSongs(int listenSongs) {
        this.listenSongs = listenSongs;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public static class Profile{
        private boolean followed;
        private long userId;
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

        private PrivacyItemUnlimit privacyItemUnlimit;

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
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

        public PrivacyItemUnlimit getPrivacyItemUnlimit() {
            return privacyItemUnlimit;
        }

        public void setPrivacyItemUnlimit(PrivacyItemUnlimit privacyItemUnlimit) {
            this.privacyItemUnlimit = privacyItemUnlimit;
        }

        public static class PrivacyItemUnlimit{
            private boolean area;
            private boolean college;
            private boolean age;
            private boolean villageAge;

            public boolean isArea() {
                return area;
            }

            public void setArea(boolean area) {
                this.area = area;
            }

            public boolean isCollege() {
                return college;
            }

            public void setCollege(boolean college) {
                this.college = college;
            }

            public boolean isAge() {
                return age;
            }

            public void setAge(boolean age) {
                this.age = age;
            }

            public boolean isVillageAge() {
                return villageAge;
            }

            public void setVillageAge(boolean villageAge) {
                this.villageAge = villageAge;
            }
        }
    }


}
