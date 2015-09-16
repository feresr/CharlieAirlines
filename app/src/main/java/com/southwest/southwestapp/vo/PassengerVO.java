package com.southwest.southwestapp.vo;

/**
 * Created by zadtanikus on 15/09/15.
 */
public class PassengerVO {

    private String name;
    private String group;
    private int position;

    public PassengerVO(String name, String group, int position) {
        this.name = name;
        this.group = group;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
