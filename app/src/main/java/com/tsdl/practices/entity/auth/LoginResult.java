package com.tsdl.practices.entity.auth;

public class LoginResult {

    /**
     * code : 200
     * msg : null
     * data : {"access_token":"eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX2tleSI6IjMwMDMwNDZiLTRlZGEtNDk3Zi1iN2E0LWYxMjc3Y2Y0YTFiOSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.MLsTVC_NviuJE2L8rpdhiT8Lf0A-uNTlUmInfA4Ijc8yKkixEayajWU8cH4MoMR7iGDl0dCOuj9y2j-24eSz2w","expires_in":720}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * access_token : eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX2tleSI6IjMwMDMwNDZiLTRlZGEtNDk3Zi1iN2E0LWYxMjc3Y2Y0YTFiOSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.MLsTVC_NviuJE2L8rpdhiT8Lf0A-uNTlUmInfA4Ijc8yKkixEayajWU8cH4MoMR7iGDl0dCOuj9y2j-24eSz2w
         * expires_in : 720
         */

        private String access_token;
        private int expires_in;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }
    }

}
