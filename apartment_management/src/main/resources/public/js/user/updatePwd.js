layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    //监听提交
    form.on('submit(saveBtn)', function(data){
        //layer.msg(JSON.stringify(data.field));
        var fieldData=data.field;

        //发送ajax
        $.ajax({
            type:"post",
            url:ctx+"/user/updatePword",
            data:{
                "oldPassword":fieldData.old_password,
                "newPassword":fieldData.new_password,
                "confirmPwd":fieldData.again_password
            },
            dataType:"json",
            success:function (data){
                if(data.code==200){
                    layer.msg("修改密码成功了，系统三秒钟后退出",function (){
                        //清空Cookie
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/am"});
                        $.removeCookie("userName",{domain:"localhost",path:"/am"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/am"});
                        //跳转页面
                        window.parent.location.href=ctx+"/index";
                    });
                }else{
                    //登录失败的提示
                    layer.msg(data.msg);
                }
            }
        });


        //取消默认行为
        return false;
    });

});