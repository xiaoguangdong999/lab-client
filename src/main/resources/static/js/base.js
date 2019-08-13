//获取url中的参数
var getParam = function(name){
    var search = document.location.search;
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if(null != matcher){
        try{
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        }catch(e){
            try{
                items = decodeURIComponent(matcher[1]);
            }catch(e){
                items = matcher[1];
            }
        }
    }
    return items;
};

//注销
function logout() {
    $.ajax({
        //请求方式
        type : "get",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/logout",

        //数据，json字符串
        dataType:"json",
        //请求成功
        success : function(data) {
            console.log(data);
            if(data.message == '已注销'){
                alert(data.message);
                //刷新页面
                location.reload();

            }else{

            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }

    })
}