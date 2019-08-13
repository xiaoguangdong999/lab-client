package com.ty.lab.pojo.lab;

/**
 * @Classname Lab
 * @Description TODO
 * @Date 2019/8/9 0009
 * @Created by Administrator
 * @Version 1.0
 */
public class Lab {

    private String labname;

    private String date;

    private String timestate;

    private Long lrid;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getLrid() {
        return lrid;
    }

    public void setLrid(Long lrid) {
        this.lrid = lrid;
    }

    public String getLabname() {
        return labname;
    }

    public void setLabname(String labname) {
        this.labname = labname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimestate() {
        return timestate;
    }

    public void setTimestate(String timestate) {
        this.timestate = timestate;
    }
}
