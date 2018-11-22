package com.example.administrator.xingyi.model;

public class Admin {//管理员实体类
    private int _id;//管理员ID
    private String pwd;//管理员密码
    private String permission;//管理员权限

    public Admin(){
        super();
    }

    public Admin(int _id,String pwd,String permission){
        this._id = _id;
        this.pwd = pwd;
        this.permission = permission;
    }

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
