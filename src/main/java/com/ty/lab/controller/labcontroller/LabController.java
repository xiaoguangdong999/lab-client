package com.ty.lab.controller.labcontroller;

import com.ty.lab.pojo.TbLabname;
import com.ty.lab.pojo.TbLabrecord;
import com.ty.lab.pojo.lab.Lab;
import com.ty.lab.service.LabnameService;
import com.ty.lab.service.LabrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Classname LabController
 * @Description TODO
 * @Date 2019/8/9 0009
 * @Created by Administrator
 * @Version 1.0
 */
@RestController
public class LabController {

    @Autowired
    private LabrecordService labrecordService;

    @Autowired
    private LabnameService labnameService;

    /**
     * 获取可以预约的实验室
     * @param datestr
     * @param timestate
     * @return
     */
    @RequestMapping("/labnamelist")
    public List<TbLabname> getTbLabNameList (String datestr, String timestate) {
        //查询的结果
        List<TbLabname> labnamesResult = new ArrayList<>();

        TbLabname tbLabname = new TbLabname();
        tbLabname.setState(0);
        //获取所有实验室预约时间段
        List<TbLabname> tbLabnameListAll = labnameService.findByExample(tbLabname);

        //获取已经预约的实验室时间段
        TbLabrecord labrecord = new TbLabrecord();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        labrecord.setDate(date);
        labrecord.setTimestate(timestate);
        labrecord.setState(0);
        List<TbLabrecord> labrecords = labrecordService.findAllByExample(labrecord);
        if (labrecords.size() == 0) {
            return tbLabnameListAll;
        }

        //过滤已预约实验室时间段
        List<Long> lid = new ArrayList<>();
        for(TbLabname labname : tbLabnameListAll){
            lid.add(labname.getId());
        }
        List<Long> lid2 = new ArrayList<>();
        for(TbLabrecord labrecord1 : labrecords){
            lid2.add(labrecord1.getLid());
        }

        for(Long a : lid){
            if(lid2.contains(a)){
                continue;
            }
            labnamesResult.add(labnameService.findOne(a));
        }

        return labnamesResult;
    }

    /**
     * 预约
     * @param lid
     * @param uid
     * @param timestate
     * @return
     */
    @RequestMapping("/appointment")
    public Map appointment(String lid,String uid,String timestate,String datestr){

        TbLabrecord labrecord = new TbLabrecord();
        labrecord.setLid(Long.valueOf(lid));
        labrecord.setAccountid(Integer.valueOf(uid));
        labrecord.setTimestate(timestate);

        Map<String,String > map = new HashMap<String,String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            labrecord.setDate(simpleDateFormat.parse(datestr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        labrecord.setState(0);

        List<TbLabrecord> labrecordList =  labrecordService.findAllByExample(labrecord);
        if (labrecordList.size() > 0) {
            map.put("message","您已预约");
            return map;
        }

        labrecordService.add(labrecord);

        map.put("message","预约成功");
        return map;
    }

    /**
     * 预约记录
     * @param uid
     * @return
     */
    @RequestMapping("/labList")
    public List<Lab> labList(Integer uid){
        List<Lab> labList = new ArrayList<>();

        TbLabrecord tbLabrecord = new TbLabrecord();
        tbLabrecord.setAccountid(uid);

        List<TbLabrecord> labrecordList = labrecordService.findAllByExample(tbLabrecord);
        for (TbLabrecord tbLabrecord1 : labrecordList) {
            Lab lab = new Lab();
            lab.setLabname(labnameService.findOne(tbLabrecord1.getLid()).getName());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(tbLabrecord1.getDate());
            lab.setDate(date);
            lab.setTimestate(tbLabrecord1.getTimestate());
            lab.setLrid(tbLabrecord1.getId());
            lab.setState(tbLabrecord1.getState());
            labList.add(lab);
        }
        Collections.sort(labList, new Comparator<Lab>() {
            @Override
            public int compare(Lab o1, Lab o2) {
                return o2.getLrid().compareTo(o1.getLrid());
            }
        });
        return labList;

    }

    /**
     * 取消预约
     * @param lrid
     * @return
     */
    @RequestMapping("/cancel")
    public Map cancelAppointment(Long lrid){
        TbLabrecord labrecord = labrecordService.findOne(lrid);
        labrecord.setState(2);
        labrecordService.update(labrecord);

        Map<String,String> map = new HashMap<String,String>();

        map.put("message","取消成功");

        return map;

    }

}
