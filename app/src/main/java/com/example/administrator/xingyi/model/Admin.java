package com.example.administrator.xingyi.model;

public class Admin {//管理员实体类
    private int _id;//管理员ID
    private int AdminResImg;//
    private String name;//管理员name
    private String pwd;//管理员密码
    private String permission;//管理员权限

    public Admin(){
        super();
    }

    public Admin(int _id,int AdminResImg, String name, String pwd, String permission) {
        this._id = _id;
        this.AdminResImg = AdminResImg;
        this.name = name;
        this.pwd = pwd;
        this.permission = permission;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAdminResImg() {
        return AdminResImg;
    }

    public void setAdminResImg(int adminResImg) {
        AdminResImg = adminResImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
