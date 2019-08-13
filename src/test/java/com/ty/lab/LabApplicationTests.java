package com.ty.lab;

import com.ty.lab.entity.PageResult;
import com.ty.lab.pojo.TbLabname;
import com.ty.lab.pojo.TbLabrecord;
import com.ty.lab.pojo.lab.Lab;
import com.ty.lab.service.LabnameService;
import com.ty.lab.service.LabrecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabApplicationTests {

    @Autowired
    private LabrecordService labrecordService;

    @Autowired
    private LabnameService labnameService;

    @Test
    public void contextLoads() {
        Integer uid= 14;
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
            labList.add(lab);
        }


        for (Lab lab : labList) {
            System.out.println(lab.getTimestate());
        }

    }

}
