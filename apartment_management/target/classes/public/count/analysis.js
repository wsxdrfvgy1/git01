layui.use(['element', 'echarts', 'layer'], function () {     //如果只加载一个模块，可以不填数组。
    var $ = layui.jquery,
        echarts = layui.echarts;


    var chart = echarts.init(document.getElementById('datatable'));

    $.ajax({
        type:"post",
        url:ctx+"/user/findType",
        dataType:"json",
        success:function (obj){
            console.log(obj);
            var date = new Array();
            for (var x in obj){
                console.log(x);
                date[x] = obj[x].name;

            }
            var optionBar = {
                title: {
                    text: '租客分类统计',
                    subtext: '分类情况', //副标题
                    x: 'center' //标题居中
                },
                tooltip: {
                    trigger: 'item' //悬浮显示对比
                },
                legend: {
                    orient: 'vertical', //类型垂直,默认水平
                    left: 'left', //类型区分在左 默认居中
                    data: date
                },
                toolbox: {      // 工具栏组件
                    feature: {      // 工具配置项
                        restore: { show: true },        // 配置项还原
                        saveAsImage: {show: true}     // 保存为图片
                    }
                },

                series: [{
                    type: 'pie', //饼状
                    radius: '60%', //圆的大小
                    roseType:'area',
                    center: ['50%', '50%'], //居中
                    data: obj,
                    selectedMode: "multiple",        // 多个选中
                    selectedOffset: 30           // 选中偏移量
                }]
            };
            chart.setOption(optionBar, true);
        }
    });
});
