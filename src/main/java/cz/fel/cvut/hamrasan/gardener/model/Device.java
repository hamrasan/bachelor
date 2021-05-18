package cz.fel.cvut.hamrasan.gardener.model;

public class Device {
    private long active_time;
    private String biz_type;
    private String category;
    private long create_time;
    private String icon;
    private String id;
    private String ip;
    private String lat;
    private String local_key;
    private String lon;
    private String name;
    private boolean online;
    private String owner_id;
    private String product_id;
    private String product_name;
    private DeviceStatus[] status;
    private boolean sub;
    private String time_zone;
    private String uid;
    private String update_time;
    private String uuid;


    public Device(long active_time, String biz_type, String category, long create_time, String icon, String id, String ip, String lat, String local_key, String lon, String name, boolean online,
                  String owner_id, String product_id, String product_name, DeviceStatus[] status, boolean sub, String time_zone, String uid, String update_time, String uuid) {

        this.active_time = active_time;
        this.biz_type = biz_type;
        this.category = category;
        this.create_time = create_time;
        this.icon = icon;
        this.id = id;
        this.ip = ip;
        this.lat = lat;
        this.local_key = local_key;
        this.lon = lon;
        this.name = name;
        this.online = online;
        this.owner_id = owner_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.status = status;
        this.sub = sub;
        this.time_zone = time_zone;
        this.uid = uid;
        this.update_time = update_time;
        this.uuid = uuid;
    }


    public Device() {

    }


    public long getActive_time() {

        return active_time;
    }


    public void setActive_time(long active_time) {

        this.active_time = active_time;
    }


    public String getBiz_type() {

        return biz_type;
    }


    public void setBiz_type(String biz_type) {

        this.biz_type = biz_type;
    }


    public String getCategory() {

        return category;
    }


    public void setCategory(String category) {

        this.category = category;
    }


    public long getCreate_time() {

        return create_time;
    }


    public void setCreate_time(long create_time) {

        this.create_time = create_time;
    }


    public String getIcon() {

        return icon;
    }


    public void setIcon(String icon) {

        this.icon = icon;
    }


    public String getId() {

        return id;
    }


    public void setId(String id) {

        this.id = id;
    }


    public String getIp() {

        return ip;
    }


    public void setIp(String ip) {

        this.ip = ip;
    }


    public String getLat() {

        return lat;
    }


    public void setLat(String lat) {

        this.lat = lat;
    }


    public String getLocal_key() {

        return local_key;
    }


    public void setLocal_key(String local_key) {

        this.local_key = local_key;
    }


    public String getLon() {

        return lon;
    }


    public void setLon(String lon) {

        this.lon = lon;
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public boolean isOnline() {

        return online;
    }


    public void setOnline(boolean online) {

        this.online = online;
    }


    public String getOwner_id() {

        return owner_id;
    }


    public void setOwner_id(String owner_id) {

        this.owner_id = owner_id;
    }


    public String getProduct_id() {

        return product_id;
    }


    public void setProduct_id(String product_id) {

        this.product_id = product_id;
    }


    public String getProduct_name() {

        return product_name;
    }


    public void setProduct_name(String product_name) {

        this.product_name = product_name;
    }


    public DeviceStatus[] getStatus() {

        return status;
    }


    public void setStatus(DeviceStatus[] status) {

        this.status = status;
    }


    public boolean isSub() {

        return sub;
    }


    public void setSub(boolean sub) {

        this.sub = sub;
    }


    public String getTime_zone() {

        return time_zone;
    }


    public void setTime_zone(String time_zone) {

        this.time_zone = time_zone;
    }


    public String getUid() {

        return uid;
    }


    public void setUid(String uid) {

        this.uid = uid;
    }


    public String getUpdate_time() {

        return update_time;
    }


    public void setUpdate_time(String update_time) {

        this.update_time = update_time;
    }


    public String getUuid() {

        return uuid;
    }


    public void setUuid(String uuid) {

        this.uuid = uuid;
    }
}
