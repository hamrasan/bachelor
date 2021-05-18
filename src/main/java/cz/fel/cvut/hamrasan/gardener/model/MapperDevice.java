package cz.fel.cvut.hamrasan.gardener.model;

public class MapperDevice {
    private Device result;
    private String success;
    private String t;


    public MapperDevice(Device result, String success, String t) {

        this.result = result;
        this.success = success;
        this.t = t;
    }


    public MapperDevice() {

    }


    public Device getResult() {

        return result;
    }


    public void setResult(Device result) {

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
