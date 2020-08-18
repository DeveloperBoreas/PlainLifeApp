package com.xwzx.equipmanager.mvp.models;

import com.xwzx.equipmanager.mvp.ModelInterface;

public class MUpComingModel implements ModelInterface {
    private int id;
    private int urgent = 0;
    private String title;
    private String proposer;
    private long applyDate;

    public MUpComingModel(int id, int urgent, String title, String proposer, long applyDate) {
        this.id = id;
        this.urgent = urgent;
        this.title = title;
        this.proposer = proposer;
        this.applyDate = applyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUrgent() {
        return urgent;
    }

    public void setUrgent(int urgent) {
        this.urgent = urgent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }
}
