package com.example.administrator.xingyi.project.projectDonateRank;

import android.widget.TextView;

import com.example.administrator.xingyi.more.RoundImageView;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/11 0011
 * Author:  Infinity
 */
public class ProjectDonateView {
    private int touxiang;
    private String DonateName;
    private int DonateStars;
    private int DonateRank;

    public ProjectDonateView(int touxiang, String donateName, int donateStars, int donateRank) {
        this.touxiang = touxiang;
        DonateName = donateName;
        DonateStars = donateStars;
        DonateRank = donateRank;
    }

    public int getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(int touxiang) {
        this.touxiang = touxiang;
    }

    public String getDonateName() {
        return DonateName;
    }

    public void setDonateName(String donateName) {
        DonateName = donateName;
    }

    public int getDonateStars() {
        return DonateStars;
    }

    public void setDonateStars(int donateStars) {
        DonateStars = donateStars;
    }

    public int getDonateRank() {
        return DonateRank;
    }

    public void setDonateRank(int donateRank) {
        DonateRank = donateRank;
    }
}
