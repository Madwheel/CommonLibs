package com.iwangzhe.commonview.adv.model;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/7/3115:23
 * desc   :
 */
public class PositionInfo {
    private String name;
    private String titleTxt;
    private String moreTxt;
    private String descs;
    private int advType;
    private int advStyle;
    private int width;
    private int height;
    private int width1;
    private int height1;
    private int width2;
    private int height2;

    public PositionInfo() {
        this.name = "";
        this.titleTxt = "";
        this.moreTxt = "";
        this.descs = "";
        this.advType = 0;
        this.advStyle = 0;
        this.width = 0;
        this.height = 0;
        this.width1 = 0;
        this.height1 = 0;
        this.width2 = 0;
        this.height2 = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitleTxt() {
        return titleTxt;
    }

    public void setTitleTxt(String titleTxt) {
        this.titleTxt = titleTxt;
    }

    public String getMoreTxt() {
        return moreTxt;
    }

    public void setMoreTxt(String moreTxt) {
        this.moreTxt = moreTxt;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public int getAdvType() {
        return advType;
    }

    public void setAdvType(int advType) {
        this.advType = advType;
    }

    public int getAdvStyle() {
        return advStyle;
    }

    public void setAdvStyle(int advStyle) {
        this.advStyle = advStyle;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth1() {
        return width1;
    }

    public void setWidth1(int width1) {
        this.width1 = width1;
    }

    public int getHeight1() {
        return height1;
    }

    public void setHeight1(int height1) {
        this.height1 = height1;
    }

    public int getWidth2() {
        return width2;
    }

    public void setWidth2(int width2) {
        this.width2 = width2;
    }

    public int getHeight2() {
        return height2;
    }

    public void setHeight2(int height2) {
        this.height2 = height2;
    }
}
