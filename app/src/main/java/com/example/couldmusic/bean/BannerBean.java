package com.example.couldmusic.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BannerBean implements Serializable {


    private List<BannersBean> banners;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean{
        /**{
         "adLocation": null,
         "monitorImpress": null,
         "bannerId": "1643360946324189",
         "extMonitor": null,
         "pid": null,
         "pic": "http://p1.music.126.net/aL3fgOoNHnIP9Si33losYA==/109951166994524103.jpg",
         "program": null,
         "video": null,
         "adurlV2": null,
         "adDispatchJson": null,
         "dynamicVideoData": null,
         "monitorType": null,
         "adid": null,
         "titleColor": "red",
         "requestId": "",
         "exclusive": false,
         "scm": "1.music-homepage.homepage_banner_force.banner.4283979.-746760618.null",
         "event": null,
         "alg": null,
         "song": null,
         "targetId": 139476594,
         "showAdTag": true,
         "adSource": null,
         "showContext": null,
         "targetType": 10,
         "typeTitle": "新碟首发",
         "url": null,
         "encodeId": "139476594",
         "extMonitorInfo": null,
         "monitorClick": null,
         "monitorImpressList": null,
         "monitorBlackList": null,
         "monitorClickList": null
         },
         */

        private Object adLocation;
        private Object monitorImpress;
        private String bannerId;
        private Object extMonitor;
        private Object pid;
        private String pic;
        private Object program;
        private Object video;
        private Object adurlV2;
        private Object adDispatchJson;
        private Object dynamicVideoData;
        private Object monitorType;
        private Object adid;
        private String titleColor;
        private String requestId;
        private boolean exclusive;
        private String scm;
        private Object event;
        private Object alg;
        private Object song;
        private long targetId;
        private boolean showAdTag;
        private Object adSource;
        private Object showContext;
        private int targetType;
        private String typeTitle;
        private Object url;
        private String encodeId;
        private Object extMonitorInfo;
        private Object monitorClick;
        private Object monitorImpressList;
        private Object monitorBlackList;
        private Object monitorClickList;


        public Object getAdLocation() {
            return adLocation;
        }

        public void setAdLocation(Object adLocation) {
            this.adLocation = adLocation;
        }

        public Object getMonitorImpress() {
            return monitorImpress;
        }

        public void setMonitorImpress(Object monitorImpress) {
            this.monitorImpress = monitorImpress;
        }

        public String getBannerId() {
            return bannerId;
        }

        public void setBannerId(String bannerId) {
            this.bannerId = bannerId;
        }

        public Object getExtMonitor() {
            return extMonitor;
        }

        public void setExtMonitor(Object extMonitor) {
            this.extMonitor = extMonitor;
        }

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
            this.pid = pid;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public Object getProgram() {
            return program;
        }

        public void setProgram(Object program) {
            this.program = program;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }

        public Object getAdurlV2() {
            return adurlV2;
        }

        public void setAdurlV2(Object adurlV2) {
            this.adurlV2 = adurlV2;
        }

        public Object getAdDispatchJson() {
            return adDispatchJson;
        }

        public void setAdDispatchJson(Object adDispatchJson) {
            this.adDispatchJson = adDispatchJson;
        }

        public Object getDynamicVideoData() {
            return dynamicVideoData;
        }

        public void setDynamicVideoData(Object dynamicVideoData) {
            this.dynamicVideoData = dynamicVideoData;
        }

        public Object getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(Object monitorType) {
            this.monitorType = monitorType;
        }

        public Object getAdid() {
            return adid;
        }

        public void setAdid(Object adid) {
            this.adid = adid;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public boolean isExclusive() {
            return exclusive;
        }

        public void setExclusive(boolean exclusive) {
            this.exclusive = exclusive;
        }

        public String getScm() {
            return scm;
        }

        public void setScm(String scm) {
            this.scm = scm;
        }

        public Object getEvent() {
            return event;
        }

        public void setEvent(Object event) {
            this.event = event;
        }

        public Object getAlg() {
            return alg;
        }

        public void setAlg(Object alg) {
            this.alg = alg;
        }

        public Object getSong() {
            return song;
        }

        public void setSong(Object song) {
            this.song = song;
        }

        public long getTargetId() {
            return targetId;
        }

        public void setTargetId(long targetId) {
            this.targetId = targetId;
        }

        public boolean isShowAdTag() {
            return showAdTag;
        }

        public void setShowAdTag(boolean showAdTag) {
            this.showAdTag = showAdTag;
        }

        public Object getAdSource() {
            return adSource;
        }

        public void setAdSource(Object adSource) {
            this.adSource = adSource;
        }

        public Object getShowContext() {
            return showContext;
        }

        public void setShowContext(Object showContext) {
            this.showContext = showContext;
        }

        public int getTargetType() {
            return targetType;
        }

        public void setTargetType(int targetType) {
            this.targetType = targetType;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public String getEncodeId() {
            return encodeId;
        }

        public void setEncodeId(String encodeId) {
            this.encodeId = encodeId;
        }

        public Object getExtMonitorInfo() {
            return extMonitorInfo;
        }

        public void setExtMonitorInfo(Object extMonitorInfo) {
            this.extMonitorInfo = extMonitorInfo;
        }

        public Object getMonitorClick() {
            return monitorClick;
        }

        public void setMonitorClick(Object monitorClick) {
            this.monitorClick = monitorClick;
        }

        public Object getMonitorImpressList() {
            return monitorImpressList;
        }

        public void setMonitorImpressList(Object monitorImpressList) {
            this.monitorImpressList = monitorImpressList;
        }

        public Object getMonitorBlackList() {
            return monitorBlackList;
        }

        public void setMonitorBlackList(Object monitorBlackList) {
            this.monitorBlackList = monitorBlackList;
        }

        public Object getMonitorClickList() {
            return monitorClickList;
        }

        public void setMonitorClickList(Object monitorClickList) {
            this.monitorClickList = monitorClickList;
        }
    }
}
