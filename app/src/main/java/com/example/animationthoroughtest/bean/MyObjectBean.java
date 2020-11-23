package com.example.animationthoroughtest.bean;

/**
 * 类描述
 *
 * @author HB.yangzuozhe
 * @date 2020-11-19
 */
public class MyObjectBean {

    private int height;
    private int width;


    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public MyObjectBean(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


}
