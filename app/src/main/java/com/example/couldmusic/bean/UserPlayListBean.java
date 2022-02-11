package com.example.couldmusic.bean;

import java.util.List;

public class UserPlayListBean {

    /**"version": "0",
     "more": false,
     "playlist": [
     {
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
     },
     "artists": null,
     "tracks": null,
     "updateFrequency": null,
     "backgroundCoverId": 0,
     "backgroundCoverUrl": null,
     "titleImage": 0,
     "titleImageUrl": null,
     "englishTitle": null,
     "opRecommend": false,
     "recommendInfo": null,
     "subscribedCount": 4,
     "cloudTrackCount": 1,
     "userId": 32953014,
     "totalDuration": 0,
     "coverImgId": 109951166270468850,
     "privacy": 0,
     "trackUpdateTime": 1644471453690,
     "trackCount": 979,
     "updateTime": 1644418976941,
     "commentThreadId": "A_PL_0_24381616",
     "coverImgUrl": "https://p1.music.126.net/H2v1v3rOHuHRO4PwT_yulw==/109951166270468848.jpg",
     "specialType": 5,
     "anonimous": false,
     "createTime": 1407747901072,
     "highQuality": false,
     "newImported": false,
     "trackNumberUpdateTime": 1644418976941,
     "playCount": 16256,
     "adType": 0,
     "description": "描述",
     "tags": [
     "学习"
     ],
     "ordered": true,
     "status": 0,
     "name": "binaryify喜欢的音乐",
     "id": 24381616,
     "coverImgId_str": "109951166270468848",
     "sharedUsers": null,
     "shareStatus": null
     },
     }*/
   private String version;
   private boolean more;
   private List<PlayListDetailBean> playlist;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public List<PlayListDetailBean> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlayListDetailBean> playlist) {
        this.playlist = playlist;
    }
}
