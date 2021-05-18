package cz.fel.cvut.hamrasan.gardener.model;

public class TokenEntity {
    private String access_token;
    private String expire_time;
    private String refresh_token;
    private String uid;


    public TokenEntity(String access_token, String expire_time, String refresh_token, String uid) {

        this.access_token = access_token;
        this.expire_time = expire_time;
        this.refresh_token = refresh_token;
        this.uid = uid;
    }


    public TokenEntity() {

    }


    public String getAccess_token() {

        return access_token;
    }


    public void setAccess_token(String access_token) {

        this.access_token = access_token;
    }


    public String getExpire_time() {

        return expire_time;
    }


    public void setExpire_time(String expire_time) {

        this.expire_time = expire_time;
    }


    public String getRefresh_token() {

        return refresh_token;
    }


    public void setRefresh_token(String refresh_token) {

        this.refresh_token = refresh_token;
    }


    public String getUid() {

        return uid;
    }


    public void setUid(String uid) {

        this.uid = uid;
    }
}
