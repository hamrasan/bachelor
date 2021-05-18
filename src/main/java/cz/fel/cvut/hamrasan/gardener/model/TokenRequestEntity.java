package cz.fel.cvut.hamrasan.gardener.model;

public class TokenRequestEntity {

    private TokenEntity result;
    private String success;
    private String t;

    public TokenRequestEntity(TokenEntity result, String success, String t) {
        this.result = result;
        this.success= success;
        this.t = t;
    }


    public TokenRequestEntity() {

    }


    public TokenEntity getResult() {

        return result;
    }


    public void setResult(TokenEntity result) {

        this.result = result;
    }


    public String getSuccess() {

        return success;
    }


    public void setSuccess(String success) {

        this.success = success;
    }


    public String getT() {

        return t;
    }


    public void setT(String t) {

        this.t = t;
    }
}