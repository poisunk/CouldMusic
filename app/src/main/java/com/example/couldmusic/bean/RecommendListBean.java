package com.example.couldmusic.bean;

import java.io.Serializable;
import java.util.List;

public class RecommendListBean implements Serializable {
    private List<Creatives> creatives;

    public static class Creatives implements Serializable{
        /**
         * "creativeType": "list",
         * "creativeId": "3136952023",
         * "action": "orpheus://playlist/3136952023?autoplay=0&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD",
         * "actionType": "orpheus",
         * "uiElement": {
         * "mainTitle": {
         * "title": "今天从《交易》听起|私人雷达"
         * },
         * "subTitle": {},
         * "image": {
         * "imageUrl": "http://p1.music.126.net/U6AtP6yKpMJ7dr2xpDOThA==/109951165498105810.jpg"
         * },
         * "labelTexts": [
         * "流行"
         * ],
         * "rcmdShowType": "DEFAULT"
         * },
         * "resources": [
         * {
         * "uiElement": {
         * "mainTitle": {
         * "title": "今天从《交易》听起|私人雷达"
         * },
         * "subTitle": {},
         * "image": {
         * "imageUrl": "http://p1.music.126.net/U6AtP6yKpMJ7dr2xpDOThA==/109951165498105810.jpg"
         * },
         * "labelTexts": [
         * "流行"
         * ],
         * "rcmdShowType": "DEFAULT"
         * },
         * "resourceType": "list",
         * "resourceId": "3136952023",
         * "resourceUrl": null,
         * "resourceExtInfo": {
         * "playCount": 9938425856,
         * "highQuality": false,
         * "specialType": 100
         * },
         * "action": "orpheus://playlist/3136952023?autoplay=0&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD",
         * "actionType": "orpheus",
         * "valid": true,
         * "alg": "official_image_album_sub",
         * "logInfo": "{\"relatedType\":\"ALBUM_LOGO\",\"relatedSong\":\"500427744\",\"relatedArtist\":\"\",\"title\":\"今天从《交易》听起|私人雷达\",\"relatedId\":\"35993583\"}"
         * }
         * ],
         * "alg": "official_image_album_sub",
         * "logInfo": "{\"relatedType\":\"ALBUM_LOGO\",\"relatedSong\":\"500427744\",\"relatedArtist\":\"\",\"title\":\"今天从《交易》听起|私人雷达\",\"relatedId\":\"35993583\"}",
         * "position": 0
         */

        private String creativeType;
        private String creativeId;
        private String action;
        private String actionType;

        private UiElement uiElement;

        private List<Resources> resources;

        private String alg;
        private String logInfo;
        private int position;

        public String getCreativeType() {
            return creativeType;
        }

        public void setCreativeType(String creativeType) {
            this.creativeType = creativeType;
        }

        public String getCreativeId() {
            return creativeId;
        }

        public void setCreativeId(String creativeId) {
            this.creativeId = creativeId;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getActionType() {
            return actionType;
        }

        public void setActionType(String actionType) {
            this.actionType = actionType;
        }

        public UiElement getUiElement() {
            return uiElement;
        }

        public void setUiElement(UiElement uiEElement) {
            this.uiElement = uiEElement;
        }

        public List<Resources> getResources() {
            return resources;
        }

        public void setResources(List<Resources> resources) {
            this.resources = resources;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }

        public String getLogInfo() {
            return logInfo;
        }

        public void setLogInfo(String logInfo) {
            this.logInfo = logInfo;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public static class UiElement {
            /**
             * "uiElement": {
             * "mainTitle": {
             * "title": "今天从《交易》听起|私人雷达"
             * },
             * "subTitle": {},
             * "image": {
             * "imageUrl": "http://p1.music.126.net/U6AtP6yKpMJ7dr2xpDOThA==/109951165498105810.jpg"
             * },
             * "labelTexts": [
             * "流行"
             * ],
             * "rcmdShowType": "DEFAULT"
             * },
             */

            private MainTitle mainTitle;

            public static class MainTitle {
                private String title;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            private SubTitle subTitle;

            private static class SubTitle {

            }

            private Image image;

            public static class Image {
                private String imageUrl;

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }
            }

            private List<String> labelTexts;

            private String rcmdShowType;

            public MainTitle getMainTitle() {
                return mainTitle;
            }

            public void setMainTitle(MainTitle mainTitle) {
                this.mainTitle = mainTitle;
            }

            public SubTitle getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(SubTitle subTitle) {
                this.subTitle = subTitle;
            }

            public Image getImage() {
                return image;
            }

            public void setImage(Image image) {
                this.image = image;
            }

            public List<String> getLabelTexts() {
                return labelTexts;
            }

            public void setLabelTexts(List<String> labelTexts) {
                this.labelTexts = labelTexts;
            }

            public String getRcmdShowType() {
                return rcmdShowType;
            }

            public void setRcmdShowType(String rcmdShowType) {
                this.rcmdShowType = rcmdShowType;
            }


        }

        public static class Resources {
            /**
             * "resources": [
             * {
             * "uiElement": {
             * "mainTitle": {
             * "title": "今天从《交易》听起|私人雷达"
             * },
             * "subTitle": {},
             * "image": {
             * "imageUrl": "http://p1.music.126.net/U6AtP6yKpMJ7dr2xpDOThA==/109951165498105810.jpg"
             * },
             * "labelTexts": [
             * "流行"
             * ],
             * "rcmdShowType": "DEFAULT"
             * },
             * "resourceType": "list",
             * "resourceId": "3136952023",
             * "resourceUrl": null,
             * "resourceExtInfo": {
             * "playCount": 9938425856,
             * "highQuality": false,
             * "specialType": 100
             * },
             * "action": "orpheus://playlist/3136952023?autoplay=0&referLog=HOMEPAGE_BLOCK_PLAYLIST_RCMD",
             * "actionType": "orpheus",
             * "valid": true,
             * "alg": "official_image_album_sub",
             * "logInfo": "{\"relatedType\":\"ALBUM_LOGO\",\"relatedSong\":\"500427744\",\"relatedArtist\":\"\",\"title\":\"今天从《交易》听起|私人雷达\",\"relatedId\":\"35993583\"}"
             * }
             * ];
             */

            private UiElement uiElement;
            private String resourceType;
            private String resourceId;
            private Object resourceUrl;

            private ResourceExtInfo resourceExtInfo;

            public static class ResourceExtInfo {
                private long playCount;
                private boolean highQuality;
                private int specialType;

                public long getPlayCount() {
                    return playCount;
                }

                public void setPlayCount(long playCount) {
                    this.playCount = playCount;
                }

                public boolean isHighQuality() {
                    return highQuality;
                }

                public void setHighQuality(boolean highQuality) {
                    this.highQuality = highQuality;
                }

                public int getSpecialType() {
                    return specialType;
                }

                public void setSpecialType(int specialType) {
                    this.specialType = specialType;
                }
            }


            private String action;
            private String actionType;
            private boolean valid;
            private String alg;
            private String logInfo;

            public UiElement getUiElement() {
                return uiElement;
            }

            public void setUiElement(UiElement uiElement) {
                this.uiElement = uiElement;
            }

            public String getResourceType() {
                return resourceType;
            }

            public void setResourceType(String resourceType) {
                this.resourceType = resourceType;
            }

            public String getResourceId() {
                return resourceId;
            }

            public void setResourceId(String resourceId) {
                this.resourceId = resourceId;
            }

            public Object getResourceUrl() {
                return resourceUrl;
            }

            public void setResourceUrl(Object resourceUrl) {
                this.resourceUrl = resourceUrl;
            }

            public ResourceExtInfo getResourceExtInfo() {
                return resourceExtInfo;
            }

            public void setResourceExtInfo(ResourceExtInfo resourceExtInfo) {
                this.resourceExtInfo = resourceExtInfo;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getActionType() {
                return actionType;
            }

            public void setActionType(String actionType) {
                this.actionType = actionType;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public String getAlg() {
                return alg;
            }

            public void setAlg(String alg) {
                this.alg = alg;
            }

            public String getLogInfo() {
                return logInfo;
            }

            public void setLogInfo(String logInfo) {
                this.logInfo = logInfo;
            }
        }

    }

    public List<Creatives> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<Creatives> creatives) {
        this.creatives = creatives;
    }
}
