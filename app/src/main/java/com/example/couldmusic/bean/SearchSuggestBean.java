package com.example.couldmusic.bean;

import android.widget.ListView;

import java.util.List;

public class SearchSuggestBean {

    /**"result": {
     "allMatch": [
     {
     "keyword": "海阔天空",
     "type": 1,
     "alg": "alg_psug_new",
     "lastKeyword": "",
     "feature": "MSwyLDMsODswLDAsMDswYmU4OGFlZDk0MzI3NjkxODFmYmE4ZmMzZWIwMjE2ZA"
     },
     {
     "keyword": "海阔天空黄家驹",
     "type": 1,
     "alg": "alg_psug_new",
     "lastKeyword": "",
     "feature": "ODswLDAsMDswYmU4OGFlZDk0MzI3NjkxODFmYmE4ZmMzZWIwMjE2ZA"
     },
     {
     "keyword": "海阔天空 易烊千玺",
     "type": 1,
     "alg": "alg_psug_new",
     "lastKeyword": "",
     "feature": "NCw4OzAsMCwwOzBiZTg4YWVkOTQzMjc2OTE4MWZiYThmYzNlYjAyMTZk"
     },
     {
     "keyword": "海阔天空dj",
     "type": 1,
     "alg": "alg_psug_new",
     "lastKeyword": "",
     "feature": "ODswLDAsMDswYmU4OGFlZDk0MzI3NjkxODFmYmE4ZmMzZWIwMjE2ZA"
     },
     {
     "keyword": "海阔天空伴奏",
     "type": 1,
     "alg": "alg_psug_new",
     "lastKeyword": "",
     "feature": "ODswLDAsMDswYmU4OGFlZDk0MzI3NjkxODFmYmE4ZmMzZWIwMjE2ZA"
     },
     {
     "keyword": "海阔天空信乐团",
     "type": 1,
     "alg": "alg_psug_new",
     "lastKeyword": "",
     "feature": "Myw4OzAsMCwwOzBiZTg4YWVkOTQzMjc2OTE4MWZiYThmYzNlYjAyMTZk"
     }
     ]
     },
     "code": 200
     */

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result{

        private List<AllMatch> allMatch;

        public List<AllMatch> getAllMatch() {
            return allMatch;
        }

        public void setAllMatch(List<AllMatch> allMatch) {
            this.allMatch = allMatch;
        }

        public static class AllMatch{

            /**{
             "keyword": "海阔天空伴奏",
             "type": 1,
             "alg": "alg_psug_new",
             "lastKeyword": "",
             "feature": "ODswLDAsMDswYmU4OGFlZDk0MzI3NjkxODFmYmE4ZmMzZWIwMjE2ZA"
             },*/

            private String keyword;
            private int type;
            private String alg;
            private String lastKeyword;
            private String feature;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAlg() {
                return alg;
            }

            public void setAlg(String alg) {
                this.alg = alg;
            }

            public String getLastKeyword() {
                return lastKeyword;
            }

            public void setLastKeyword(String lastKeyword) {
                this.lastKeyword = lastKeyword;
            }

            public String getFeature() {
                return feature;
            }

            public void setFeature(String feature) {
                this.feature = feature;
            }
        }

    }
}
