package com.example.administrator.xingyi.sqlite;

/**
 * Project Name:  Xingyi
 * Date:  2018/11/26 0026
 * Author:  Infinity
 */
public class Sqlite {//赤、橙、黄、绿、青、蓝、紫
    private static final String insertCommodities = "INSERT INTO tb_commodity (commodityName, commodityIntroduction, commodityStars) VALUES('小红帽', '可爱的小红帽', 55),"
            + "('小黑帽', '酷酷的小黑帽', 95),"
            + "('小蓝帽', '低调的小蓝帽', 45),"
            + "('小绿帽', '绿绿的小绿帽', 9),"
            + "('小酒帽', '暖暖的小酒帽', 99),"
            + "('小咖帽', '别致的小咖帽', 65),"
            + "('小青帽', '平凡的小青帽', 55);";
    private static final String[] sqlite = {"CREATE TABLE \"tb_admin\" (\n" +
            "\"name\" TEXT NOT NULL,\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"pwd\" TEXT NOT NULL,\n" +
            "\"permission\" TEXT NOT NULL DEFAULT unknow\n" +
            ");\n" ,
            "CREATE TABLE \"tb_user\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userImgRes\" INTEGER NOT NULL,\n" +
            "\"name\" TEXT NOT NULL,\n" +
            "\"pwd\" TEXT NOT NULL,\n" +
            "\"tel\" INTEGER(11) NOT NULL DEFAULT 00000000000,\n" +
            "\"address\" TEXT NOT NULL DEFAULT unknow,\n" +
            "\"registrationDate\" TEXT NOT NULL,\n" +
            "\"donableStars\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"donatedStars\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"exchangeableStars\" INTEGER NOT NULL,\n" +
            "\"exchangedStars\" INTEGER NOT NULL DEFAULT 0\n" +
            ");\n" ,
            "CREATE TABLE \"tb_clocks\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL,\n" +
            "\"state\" TEXT NOT NULL DEFAULT unClocks,\n" +
            "\"clockDate\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"clocks_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON DELETE RESTRICT ON UPDATE RESTRICT\n" +
            ");\n" ,
            "CREATE TABLE \"tb_commodity\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"commodityImgRes\" INTEGER NOT NULL,\n" +
            "\"commodityName\" TEXT NOT NULL,\n" +
            "\"commodityIntroduction\" TEXT NOT NULL DEFAULT noIntroduction,\n" +
            "\"commodityStars\" INTEGER NOT NULL DEFAULT 0\n" +
            ");\n" ,
            "CREATE TABLE \"tb_company\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"companyImgRes\" INTEGER NOT NULL,\n" +
            "\"companyName\" TEXT NOT NULL,\n" +
            "\"companyIntroduction\" TEXT NOT NULL DEFAULT noIntroduction,\n" +
            "\"fundNum\" INTEGER NOT NULL DEFAULT 0\n" +
            ");\n" ,
            "CREATE TABLE \"tb_project\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"adminId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"projectImgRes\" INTEGER NOT NULL,\n" +
            "\"projectName\" TEXT NOT NULL,\n" +
            "\"projectIntroduction\" TEXT NOT NULL,\n" +
            "\"projectUse\" TEXT NOT NULL,\n" +
            "\"companyId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"projectSponser\" TEXT NOT NULL,\n" +
            "\"organizationId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"projectOriginator\" TEXT NOT NULL,\n" +
            "\"projectNeed\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"projectHave\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"projectMessageNum\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"projectPraiseNum\" INTEGER NOT NULL DEFAULT 0,\n" +
            "CONSTRAINT \"project_admin\" FOREIGN KEY (\"adminId\") REFERENCES \"tb_admin\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"project_company\" FOREIGN KEY (\"companyId\") REFERENCES \"tb_company\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"project_organization\" FOREIGN KEY (\"organizationId\") REFERENCES \"tb_organization\" (\"_id\") ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_organization\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"organizationImgRes\" INTEGER NOT NULL,\n" +
            "\"organizationName\" TEXT NOT NULL,\n" +
            "\"organizationIntroduction\" TEXT NOT NULL DEFAULT noIntroduction,\n" +
            "\"sponsorNum\" INTEGER NOT NULL DEFAULT 0\n" +
            ");\n" ,
            "CREATE TABLE \"tb_donate\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"projectId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"donateStarsNum\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"donateTime\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"donate_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"donate_project\" FOREIGN KEY (\"projectId\") REFERENCES \"tb_project\" (\"_id\") ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_exchange\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"receiver\" TEXT NOT NULL DEFAULT unknow,\n" +
            "\"tel\" INTEGER(11) NOT NULL DEFAULT 00000000000,\n" +
            "\"address\" TEXT NOT NULL DEFAULT unknow,\n" +
            "\"exchangeTime\" TEXT NOT NULL,\n" +
            "\"costStars\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"state\" TEXT NOT NULL DEFAULT unpay,\n" +
            "CONSTRAINT \"exchange_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_exchangeDetails\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"exchangeId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"commodityId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"commodityName\" TEXT NOT NULL DEFAULT unknow,\n" +
            "\"num\" INTEGER NOT NULL DEFAULT 0,\n" +
            "CONSTRAINT \"exchangeDetails_exchange\" FOREIGN KEY (\"exchangeId\") REFERENCES \"tb_exchange\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"exchangeDetails_commodity\" FOREIGN KEY (\"commodityId\") REFERENCES \"tb_commodity\" (\"_id\") ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_news\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"adminId\" INTEGER NOT NULL,\n" +
            "\"content\" TEXT NOT NULL,\n" +
            "\"time\" TEXT NOT NULL,\n" +
            "\"newsMessageNum\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"nwesPraiseNum\" INTEGER NOT NULL DEFAULT 0,\n" +
            "CONSTRAINT \"news_admin\" FOREIGN KEY (\"adminId\") REFERENCES \"tb_admin\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_newsMessage\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"newsId\" INTEGER NOT NULL,\n" +
            "\"content\" TEXT NOT NULL,\n" +
            "\"messageTime\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"newsMessage_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"newsMessage_news\" FOREIGN KEY (\"newsId\") REFERENCES \"tb_news\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_newsPraise\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"newsId\" INTEGER NOT NULL,\n" +
            "\"praiseTime\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"newsPraise_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"newsPraise_news\" FOREIGN KEY (\"newsId\") REFERENCES \"tb_news\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_projectMessage\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"projectId\" INTEGER NOT NULL,\n" +
            "\"content\" TEXT NOT NULL,\n" +
            "\"message\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"projectMessage_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"projectMessage_project\" FOREIGN KEY (\"projectId\") REFERENCES \"tb_project\" (\"_id\") ON DELETE CASCADE ON UPDATE \n" +
            "CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_projectPraise\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"projectId\" INTEGER NOT NULL,\n" +
            "\"praiseTime\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"projectPraise_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"projectPraise_project\" FOREIGN KEY (\"projectId\") REFERENCES \"tb_project\" (\"_id\") ON DELETE CASCADE ON UPDATE \n" +
            "CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_shoppingCart\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL,\n" +
            "\"commodityId\" INTEGER NOT NULL DEFAULT -1,\n" +
            "\"commodityName\" TEXT NOT NULL DEFAULT 0,\n" +
            "\"num\" INTEGER NOT NULL DEFAULT 1,\n" +
            "CONSTRAINT \"shoppingCart_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE,\n" +
            "CONSTRAINT \"shoppingCart_commodity\" FOREIGN KEY (\"commodityId\") REFERENCES \"tb_commodity\" (\"_id\") ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_stepNum\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"userId\" INTEGER NOT NULL,\n" +
            "\"stepNums\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"stepDate\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"stepNums_user\" FOREIGN KEY (\"userId\") REFERENCES \"tb_user\" (\"_id\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
            ");\n" ,
            "CREATE TABLE \"tb_stepNumDetails\" (\n" +
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\"stepnumId\" INTEGER NOT NULL,\n" +
            "\"stepNum\" INTEGER NOT NULL DEFAULT 0,\n" +
            "\"timeBlock\" TEXT NOT NULL,\n" +
            "CONSTRAINT \"stepNumDetails\" FOREIGN KEY (\"stepnumId\") REFERENCES \"tb_stepNum\" (\"userId\") ON DELETE CASCADE ON UPDATE CASCADE\n" +
            ");\n",
            insertCommodities};

    public String[] getSqlite() {
        return this.sqlite;
    }
}
