package com.example.couldmusic.page.search.contract;

import com.example.couldmusic.bean.SearchHotBean;
import com.example.couldmusic.bean.SearchPlaylistBean;
import com.example.couldmusic.bean.SearchSuggestBean;
import com.example.couldmusic.bean.SearchUsersBean;
import com.example.couldmusic.bean.SongsDetailBean;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public interface SearchContract {
    interface SearchView{
        void onRequestFailed();
    }

    interface SearchHotView extends SearchView{
        void loadHotInfo(SearchHotBean searchHotBean);
    }

    interface SearchResultView extends SearchView{
        void loadResult(Object object);

    }

    interface SearchSuggestView extends SearchView{
        void loadSuggests(SearchSuggestBean searchSuggestBean);
    }

    interface SearchModel{
        interface onFinishedListener{
            void finishHotInfo(SearchHotBean searchHotBean);

            void finishSuggests(SearchSuggestBean searchSuggestBean);

            void finishPlayListResult(SearchPlaylistBean searchPlaylistBean);

            void finishSongsListResult(SongsDetailBean songsDetailBean);

            void finishUserListResult(SearchUsersBean searchUsersBean);

            void onRequestFailed();

        }

        void requestHotInfo(String address,onFinishedListener listener);

        void requestSuggests(String address,onFinishedListener listener);

        void requestPlayListResult(String address,onFinishedListener listener);

        void requestSongsListResult(String address,onFinishedListener listener);

        void requestUserListResult(String address,onFinishedListener listener);
    }

    interface SearchPresenter{
        void loadHotInfo();

        void loadSuggests(String text);

        void loadResult(String keyWord);
    }
}
