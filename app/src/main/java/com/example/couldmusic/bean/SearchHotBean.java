package com.example.couldmusic.bean;

import java.util.List;

public class SearchHotBean {

    private List<Hot> hots;

    public List<Hot> getHots() {
        return hots;
    }

    public void setHots(List<Hot> hots) {
        this.hots = hots;
    }

    public static class Hot{

        /**"first": "Newboy",
         "second": 1,
         "third": null,
         "iconType": 1
         */

        private String first;
        private int second;
        private Object third;
        private int iconType;

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public Object getThird() {
            return third;
        }

        public void setThird(Object third) {
            this.third = third;
        }

        public int getIconType() {
            return iconType;
        }

        public void setIconType(int iconType) {
            this.iconType = iconType;
        }
    }
}
