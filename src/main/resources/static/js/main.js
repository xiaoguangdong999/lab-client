//获取实验室信息
function getLabNameList(datestr,timestate) {

    var uid = getParam("uid");

    $.ajax({
        //请求方式
        type : "get",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/labnamelist?datestr="+datestr+"&timestate="+timestate,

        //数据，json字符串
        dataType:"json",
        //请求成功
        success : function(data) {
            console.log(data);
            $("#lab_list").empty();
            if (data.length == 0) {
                $("#lab_list").append("<h4>当前时间段无实验室可预约</h4>");
            }else{
                $("#labsize").empty();
                $("#labsize").append("余量:<span style='color:red;'>"+data.length+"</span>");
                $.each(data,function (index, value) {
                    $("#lab_list").append("<div class=\"col-md-2 col-sm-4 text-center \" style=\"padding: 0px 20px;\" onclick='appointment("+uid+","+value.id+")'>\n" +
                        "            <div class=\"lab_name_list\"><label style=\"cursor: pointer\">"+value.name+"</label><br>\n" +
                        "                <label style=\"cursor: pointer\">点击预约</label></div>\n" +
                        "        </div>");
                });
            }


        },
        //请求失败，包含具体的错误信息
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }

    })
}

