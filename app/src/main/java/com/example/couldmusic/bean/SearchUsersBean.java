package com.example.couldmusic.bean;

import java.util.List;

public class SearchUsersBean {

    private result result;

    public SearchUsersBean.result getResult() {
        return result;
    }

    public void setResult(SearchUsersBean.result result) {
        this.result = result;
    }

    public static class result{
        private List<UserDetailBean.Profile> userprofiles;

        public List<UserDetailBean.Profile> getUserprofiles() {
            return userprofiles;
        }

        public void setUserprofiles(List<UserDetailBean.Profile> userprofiles) {
            this.userprofiles = userprofiles;
        }
    }
}
