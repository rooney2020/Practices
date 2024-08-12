package com.tsdl.practices.entity.auth;

public class CodeResult {

    /**
     * msg : 操作成功
     * img : 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
     * code : 200
     * captchaEnabled : true
     * uuid : 02c66f1baf88432da8149b270f50b65a
     */

    private String msg;
    private String img;
    private int code;
    private boolean captchaEnabled;
    private String uuid;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isCaptchaEnabled() {
        return captchaEnabled;
    }

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
