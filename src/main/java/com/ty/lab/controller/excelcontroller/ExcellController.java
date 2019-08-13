package com.ty.lab.controller.excelcontroller;

import com.ty.lab.utils.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
public class ExcellController {



    @RequestMapping("/export")
    public void exportOrder(HttpServletResponse response, HttpServletRequest requestExportExcelUtils) {

        /*List<OrderDetail> orderDetails = new ArrayList<>();

        String excelName = "订单明细表";

        List<TbTorder> torders = torderService.findAll();

        for (TbTorder tbTorder : torders) {

            orderDetails.add(orderDetail);

        }

        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("ordernum", "订单号");
        fieldMap.put("paynum", "交易号");
        fieldMap.put("username", "用户姓名");
        fieldMap.put("phone", "电话");
        fieldMap.put("province", "省份");
        fieldMap.put("city", "城市");
        fieldMap.put("county", "县/区");
        fieldMap.put("town", "镇");
        fieldMap.put("detailAddress", "详细地址");
        fieldMap.put("goodsName", "商品名称");
        fieldMap.put("price", "商品单价");
        fieldMap.put("buycount", "购买数量");
        fieldMap.put("statu", "订单状态");
        fieldMap.put("createTime", "创建时间");
        fieldMap.put("remark", "备注");

        //导出用户相关信息
        new ExportExcelUtils().export(excelName, orderDetails, fieldMap, response);*/

    }
}
