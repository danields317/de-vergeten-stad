package Model.data;

import Controller.Login_Controllers.Login_Controller;

public class StaticData {

    static StaticData staticData;
    private Object roomInfo;
    private String username;
    private String allias;

    public static StaticData getInstance() {
        if (staticData == null) {
            staticData = new StaticData();
        }
        return staticData;
    }

    public Object getRoomInfo() {
        return roomInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getAllias() {
        return allias;
    }

    public void setRoomInfo(Object roomInfo) {
        this.roomInfo = roomInfo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAllias(String allias) {
        this.allias = allias;
    }
}
