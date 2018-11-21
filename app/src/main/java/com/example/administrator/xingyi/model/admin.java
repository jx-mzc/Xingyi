package com.example.administrator.xingyi.model;

public class admin {
    private int _id;//管理员ID
    private String pwd;//管理员密码
    private String permission;//管理员权限

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
