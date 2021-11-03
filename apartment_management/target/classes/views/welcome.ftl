<!DOCTYPE html>
<html>
<head>
  <#include "common.ftl">
</head>


<body class="childrenBody">

<div class="layui-tab-item layui-show">
    <div class="layui-carousel" id="test10" align="center">
        <div carousel-item>
            <div><img src="${ctx}/images/a.png" style="max-width:1800px;max-height:600px"></div>
            <div><img src="${ctx}/images/b.png" style="max-width:1800px;max-height:600px"></div>
            <div><img src="${ctx}/images/c.jpg" style="max-width:1800px;max-height:600px"></div>
            <div><img src="${ctx}/images/d.jpg" style="max-width:1800px;max-height:600px"></div>
            <div><img src="${ctx}/images/e.jpg" style="max-width:1800px;max-height:600px"></div>
            <div><img src="${ctx}/images/f.jpg" style="max-width:1800px;max-height:600px"></div>
            <div><img src="${ctx}/images/g.jpg" style="max-width:1800px;max-height:600px"></div>
        </div>
    </div>
</div>

<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
            ,form = layui.form;

        //图片轮播
        var ins10 =carousel.render({
            elem: '#test10'
            ,width: '100%'
            ,height: '550px'
            ,interval: 800
            ,anim: 'fade'
        });

       //事件
        carousel.on('change(test10)', function(res){
            console.log(res)
        });

        var $ = layui.$, active = {
            set: function(othis){
                var THIS = 'layui-bg-normal'
                    ,key = othis.data('key')
                    ,options = {};

                othis.css('background-color', '#5FB878').siblings().removeAttr('style');
                options[key] = othis.data('value');
                ins3.reload(options);
            }
        };

        //监听开关
        form.on('switch(autoplay)', function(){
            ins10.reload({
                autoplay: this.checked
            });
        });

        $('.demoSet').on('keyup', function(){
            var value = this.value
                ,options = {};
            if(!/^\d+$/.test(value)) return;

            options[this.name] = value;
            ins10.reload(options);
        });

        //其它示例
        $('.demoTest .layui-btn').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    });
</script>
</body>
</html>
