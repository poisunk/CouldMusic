package com.example.couldmusic.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SongsDetailBean {

    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public static class Song{
        /**"name": "海阔天空",
         "id": 347230,
         "pst": 0,
         "t": 0,
         "ar": [
         {
         "id": 11127,
         "name": "Beyond",
         "tns": [],
         "alias": []
         }
         ],
         "alia": [],
         "pop": 100,
         "st": 0,
         "rt": "600902000004240302",
         "fee": 1,
         "v": 106,
         "crbt": null,
         "cf": "",
         "al": {
         "id": 34209,
         "name": "海阔天空",
         "picUrl": "https://p2.music.126.net/8y8KJC1eCSO_vUKf2MyZwA==/109951165796899183.jpg",
         "tns": [],
         "pic_str": "109951165796899183",
         "pic": 109951165796899180
         },
         "dt": 326000,
         "h": {
         "br": 320000,
         "fid": 0,
         "size": 13042459,
         "vd": 0
         },
         "m": {
         "br": 192000,
         "fid": 0,
         "size": 7825492,
         "vd": 2310
         },
         "l": {
         "br": 128000,
         "fid": 0,
         "size": 5217009,
         "vd": 1324
         },
         "a": null,
         "cd": "1",
         "no": 1,
         "rtUrl": null,
         "ftype": 0,
         "rtUrls": [],
         "djId": 0,
         "copyright": 1,
         "s_id": 0,
         "mark": 8192,
         "originCoverType": 0,
         "originSongSimpleData": null,
         "tagPicList": null,
         "resourceState": true,
         "version": 106,
         "songJumpInfo": null,
         "entertainmentTags": null,
         "single": 0,
         "noCopyrightRcmd": null,
         "rtype": 0,
         "rurl": null,
         "mst": 9,
         "cp": 7002,
         "mv": 376199,
         "publishTime": 746812800000,
         "tns": [
         "Boundless Oceans, Vast Skies"
         ]*/

        private String name;
        private long id;
        private int pst;
        private int t;
        private List<Ar> ar;
        private List<Object> alia;
        private int pop;
        private int st;
        private String rt;
        private int fee;
        private int v;
        private Object crbt;
        private String cf;
        private Al al;
        private long dt;
        private H h;
        private M m;
        private L l;
        private Object a;
        private String cd;
        private int no;
        private String rtUrl;
        private int ftype;
        private List<Object> rtUrls;
        private int djId;
        private int copyright;
        private int s_id;
        private int originCoverType;
        private Object originSongSimpleData;
        private Object tagPicList;
        private boolean resourceState;
        private int version;
        private Object songJumpInfo;
        private Object entertainmentTags;
        private int single;
        private Object noCopyrightRcmd;
        private int rtype;
        private Object rurl;
        private int mst;
        private int cp;
        private int mv;
        private long publishTime;
        private List<String> tns;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getPst() {
            return pst;
        }

        public void setPst(int pst) {
            this.pst = pst;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public List<Ar> getAr() {
            return ar;
        }

        public void setAr(List<Ar> ar) {
            this.ar = ar;
        }

        public List<Object> getAlia() {
            return alia;
        }

        public void setAlia(List<Object> alia) {
            this.alia = alia;
        }

        public int getPop() {
            return pop;
        }

        public void setPop(int pop) {
            this.pop = pop;
        }

        public int getSt() {
            return st;
        }

        public void setSt(int st) {
            this.st = st;
        }

        public String getRt() {
            return rt;
        }

        public void setRt(String rt) {
            this.rt = rt;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public Object getCrbt() {
            return crbt;
        }

        public void setCrbt(Object crbt) {
            this.crbt = crbt;
        }

        public String getCf() {
            return cf;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }

        public Al getAl() {
            return al;
        }

        public void setAl(Al al) {
            this.al = al;
        }

        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        public H getH() {
            return h;
        }

        public void setH(H h) {
            this.h = h;
        }

        public M getM() {
            return m;
        }

        public void setM(M m) {
            this.m = m;
        }

        public L getL() {
            return l;
        }

        public void setL(L l) {
            this.l = l;
        }

        public Object getA() {
            return a;
        }

        public void setA(Object a) {
            this.a = a;
        }

        public String getCd() {
            return cd;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getRtUrl() {
            return rtUrl;
        }

        public void setRtUrl(String rtUrl) {
            this.rtUrl = rtUrl;
        }

        public int getFtype() {
            return ftype;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }

        public List<Object> getRtUrls() {
            return rtUrls;
        }

        public void setRtUrls(List<Object> rtUrls) {
            this.rtUrls = rtUrls;
        }

        public int getDjId() {
            return djId;
        }

        public void setDjId(int djId) {
            this.djId = djId;
        }

        public int getCopyright() {
            return copyright;
        }

        public void setCopyright(int copyright) {
            this.copyright = copyright;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }


        public int getOriginCoverType() {
            return originCoverType;
        }

        public void setOriginCoverType(int originCoverType) {
            this.originCoverType = originCoverType;
        }

        public Object getOriginSongSimpleData() {
            return originSongSimpleData;
        }

        public void setOriginSongSimpleData(Object originSongSimpleData) {
            this.originSongSimpleData = originSongSimpleData;
        }

        public Object getTagPicList() {
            return tagPicList;
        }

        public void setTagPicList(Object tagPicList) {
            this.tagPicList = tagPicList;
        }

        public boolean isResourceState() {
            return resourceState;
        }

        public void setResourceState(boolean resourceState) {
            this.resourceState = resourceState;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getSongJumpInfo() {
            return songJumpInfo;
        }

        public void setSongJumpInfo(Object songJumpInfo) {
            this.songJumpInfo = songJumpInfo;
        }

        public Object getEntertainmentTags() {
            return entertainmentTags;
        }

        public void setEntertainmentTags(Object entertainmentTags) {
            this.entertainmentTags = entertainmentTags;
        }

        public int getSingle() {
            return single;
        }

        public void setSingle(int single) {
            this.single = single;
        }

        public Object getNoCopyrightRcmd() {
            return noCopyrightRcmd;
        }

        public void setNoCopyrightRcmd(Object noCopyrightRcmd) {
            this.noCopyrightRcmd = noCopyrightRcmd;
        }

        public int getRtype() {
            return rtype;
        }

        public void setRtype(int rtype) {
            this.rtype = rtype;
        }

        public Object getRurl() {
            return rurl;
        }

        public void setRurl(Object rurl) {
            this.rurl = rurl;
        }

        public int getMst() {
            return mst;
        }

        public void setMst(int mst) {
            this.mst = mst;
        }

        public int getCp() {
            return cp;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }

        public int getMv() {
            return mv;
        }

        public void setMv(int mv) {
            this.mv = mv;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public List<String> getTns() {
            return tns;
        }

        public void setTns(List<String> tns) {
            this.tns = tns;
        }

        public static class Ar{
                    /**"id": 11127,
                     "name": "Beyond",
                     "tns": [],
                     "alias": []*/
                    private  long id;
                    private String name;
                    private List<Object> tns;
                    private List<Object> alias;

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

            public List<Object> getTns() {
                return tns;
            }

            public void setTns(List<Object> tns) {
                this.tns = tns;
            }

            public List<Object> getAlias() {
                return alias;
            }

            public void setAlias(List<Object> alias) {
                this.alias = alias;
            }
        }

        public static class Al{
            /**"id": 34209,
             "name": "海阔天空",
             "picUrl": "https://p2.music.126.net/8y8KJC1eCSO_vUKf2MyZwA==/109951165796899183.jpg",
             "tns": [],
             "pic_str": "109951165796899183",
             "pic": 109951165796899180
             */

            private long id;
            private String name;
            private String picUrl;
            private List<Object> tns;
            private String  pic_str;
            private long pic;

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

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public List<Object> getTns() {
                return tns;
            }

            public void setTns(List<Object> tns) {
                this.tns = tns;
            }

            public String getPic_str() {
                return pic_str;
            }

            public void setPic_str(String pic_str) {
                this.pic_str = pic_str;
            }

            public long getPic() {
                return pic;
            }

            public void setPic(long pic) {
                this.pic = pic;
            }
        }

        public static class H{
            /**"br": 320000,
             "fid": 0,
             "size": 13042459,
             "vd": 0
             */

            private long br;
            private long fid;
            private long size;


            public long getBr() {
                return br;
            }

            public void setBr(long br) {
                this.br = br;
            }

            public long getFid() {
                return fid;
            }

            public void setFid(long fid) {
                this.fid = fid;
            }

            public long getSize() {
                return size;
            }

            public void setSize(long size) {
                this.size = size;
            }


        }

        public static class M{
            private long br;
            private long fid;
            private long size;


            public long getBr() {
                return br;
            }

            public void setBr(long br) {
                this.br = br;
            }

            public long getFid() {
                return fid;
            }

            public void setFid(long fid) {
                this.fid = fid;
            }

            public long getSize() {
                return size;
            }

            public void setSize(long size) {
                this.size = size;
            }


        }

        public static class L{
            private long br;
            private long fid;
            private long size;


            public long getBr() {
                return br;
            }

            public void setBr(long br) {
                this.br = br;
            }

            public long getFid() {
                return fid;
            }

            public void setFid(long fid) {
                this.fid = fid;
            }

            public long getSize() {
                return size;
            }

            public void setSize(long size) {
                this.size = size;
            }

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Song song = (Song) o;
            return id == song.id && name.equals(song.name);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongsDetailBean that = (SongsDetailBean) o;

        if(that.getSongs().size()!=this.getSongs().size())return false;
        for(int i=0;i<that.getSongs().size();i++){
            if(that.getSongs().get(i).getId()!=this.getSongs().get(i).getId()){
                return false;
            }
        }
        return true;
    }

}
