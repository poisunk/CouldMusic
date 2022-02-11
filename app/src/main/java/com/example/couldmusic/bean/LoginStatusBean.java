package com.example.couldmusic.bean;

public class LoginStatusBean {

    /**"data": {
     "code": 200,
     "account": {
     "id": 559535337,
     "userName": "1_********355",
     "type": 1,
     "status": 0,
     "whitelistAuthority": 0,
     "createTime": 1501997558332,
     "tokenVersion": 0,
     "ban": 0,
     "baoyueVersion": 0,
     "donateVersion": 0,
     "vipType": 11,
     "anonimousUser": false,
     "paidFee": false
     },
     "profile": {
     "userId": 559535337,
     "userType": 0,
     "nickname": "将材",
     "avatarImgId": 109951165229695940,
     "avatarUrl": "http://p1.music.126.net/gdeCW6ZAUpV6qDfhyTh3LA==/109951165229695932.jpg",
     "backgroundImgId": 109951166680328180,
     "backgroundUrl": "http://p1.music.126.net/Jz5XvKXTJlH6_gdLmu-9ig==/109951166680328177.jpg",
     "signature": "",
     "createTime": 1501997642125,
     "userName": "1_********355",
     "accountType": 1,
     "shortUserName": "********355",
     "birthday": 1056619003752,
     "authority": 0,
     "gender": 1,
     "accountStatus": 0,
     "province": 140000,
     "city": 140100,
     "authStatus": 0,
     "description": null,
     "detailDescription": null,
     "defaultAvatar": false,
     "expertTags": null,
     "experts": null,
     "djStatus": 0,
     "locationStatus": 10,
     "vipType": 11,
     "followed": false,
     "mutual": false,
     "authenticated": false,
     "lastLoginTime": 1644492623983,
     "lastLoginIP": "162.14.68.47",
     "remarkName": null,
     "viptypeVersion": 1643580901394,
     "authenticationTypes": 0,
     "avatarDetail": null,
     "anchor": false
     }
     }*/

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{


        private int code;

        private Account account;

        private Profile profile;



        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

        public static class Account{
            private long id;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }
        }

        public static class Profile{
            private long userId;

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }
        }
    }
}
