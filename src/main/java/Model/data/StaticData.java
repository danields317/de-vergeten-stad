package Model.data;

import Controller.Login_Controllers.Login_Controller;

public class StaticData {

    static StaticData staticData;
    Object roomInfo;

    public static StaticData getInstance() {
        if (staticData == null) {
            staticData = new StaticData();
        }
        return staticData;
    }

    public Object getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(Object roomInfo) {
        this.roomInfo = roomInfo;
    }
}
