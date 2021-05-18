package cz.fel.cvut.hamrasan.gardener.model;

public class DeviceStatus {
    private String code;
    private String value;


    public DeviceStatus(String code, String value) {

        this.code = code;
        this.value = value;
    }


    public DeviceStatus() {

    }


    public String getCode() {

        return code;
    }


    public void setCode(String code) {

        this.code = code;
    }


    public String getValue() {

        return value;
    }


    public void setValue(String value) {

        this.value = value;
    }
}
