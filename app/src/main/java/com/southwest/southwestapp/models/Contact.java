package com.southwest.southwestapp.models;

/**
 * Created by luisalfonsobejaranosanchez on 9/14/15.
 */
public class Contact {

    private String name;
    private String area;
    private String phone;

    public Contact(String name, String phone, String area) {
        this.name = name;
        this.area = area;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
