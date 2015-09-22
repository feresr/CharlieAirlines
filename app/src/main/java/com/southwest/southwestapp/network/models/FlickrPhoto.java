package com.southwest.southwestapp.network.models;

public class FlickrPhoto {

    private String id;
    private String secret;
    private String server;
    private String farm;

        /*
        Size Suffixes
        The letter suffixes are as follows:

        s	small square 75x75
        q	large square 150x150
        t	thumbnail, 100 on longest side
        m	small, 240 on longest side
        n	small, 320 on longest side
        -	medium, 500 on longest side
        z	medium 640, 640 on longest side
        c	medium 800, 800 on longest side†
        b	large, 1024 on longest side*
        h	large 1600, 1600 on longest side†
        k	large 2048, 2048 on longest side† */

    public String getUrl(String size) {
        return "https://farm" + this.farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_" + size + ".jpg";
    }
}
