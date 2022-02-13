package com.example.couldmusic.bean;

import java.util.List;

public class SearchPlaylistBean {

    private result result;

    public result getResult() {
        return result;
    }

    public void setResult(result result) {
        this.result = result;
    }

    public static class result{

        private List<PlayListDetailBean> playlists;

        public List<PlayListDetailBean> getPlaylists() {
            return playlists;
        }

        public void setPlaylists(List<PlayListDetailBean> playlists) {
            this.playlists = playlists;
        }
    }
}

