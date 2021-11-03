layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    /**
     * 用户登录，提交表单
     */
    form.on("submit(login)",function(data){
        //获取表单元素的值
        var fieldData = data.field;
        //判断参数是否为空
        if(fieldData.username == "undefined" || fieldData.username == ""){
            layer.msg("用户名称不能为空！");
            return false;
        }
        if (fieldData.password == "undefined" || fieldData.password.trim() ==
            "") {
            layer.msg("用户密码不能为空！");
            return false;
        }
        //发送Ajax请求
        $.ajax({
            type:"post",
            url:ctx + "/user/login",
            data:{
                userName:fieldData.username,
                userPwd:fieldData.password
            },
            dataType:"json",
            success:function(data){
                // 判断是否登录成功
                if (data.code == 200) {
                    layer.msg("登录成功！", function () {
                        // 将用户信息存到cookie中
                        var result = data.result;
                        $.cookie("userIdStr", result.userIdStr);
                        $.cookie("userName", result.userName);


                        //判断是否选中记住我
                        if($("input[type='checkbox']").is(":checked")){
                            $.cookie("userIdStr",data.result.userIdStr,{expires:7});
                            $.cookie("userName",data.result.userName,{expires:7});
                            console.log(data.result.userpwd);
                        }
                        // 登录成功后，跳转到首页
                        window.location.href = ctx + "/main";
                    });
                } else {
                    layer.msg(data.msg);
                }
            },
            error:function (){
            }
        });
// 阻止表单跳转
        return false;
    })
    
});