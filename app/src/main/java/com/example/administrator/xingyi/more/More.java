package com.example.administrator.xingyi.more;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/28 0028
 * Author:  Infinity
 */
public class More {
    private int leftIcon;
    private String text;

    public More(int leftIcon, String text) {
        this.leftIcon = leftIcon;
        this.text = text;
    }

    public int getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(int leftIcon) {
        this.leftIcon = leftIcon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
