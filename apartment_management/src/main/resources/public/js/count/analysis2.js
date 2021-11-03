layui.use(['element', 'echarts', 'layer'], function () {     //如果只加载一个模块，可以不填数组。
    var $ = layui.jquery,
        echarts = layui.echarts;
    var myChart = echarts.init(document.getElementById('count1'));
    $.ajax({
        type: "post",
        url: ctx + "/user_role/findType",
        dataType: "json",
        success: function (obj) {
            var size = new Array();
            for (var x in obj) {
                size[x] = obj[x].name;
                console.log(size[x]);
            }
            var num = new Array();
            for (var x in obj) {
                num[x] = obj[x].value;
                console.log(num[x]);
            }
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '角色分类统计',
                    subtext: '分类情况', //副标题
                    x: 'center' //标题居中
                },
                tooltip: {
                    trigger: 'item' //悬浮显示对比
                },
                legend: {
                    orient: 'vertical', //类型垂直,默认水平
                    left: 'left', //类型区分在左 默认居中
                    data: size
                },
                toolbox: {      // 工具栏组件
                    feature: {      // 工具配置项
                        restore: { show: true },        // 配置项还原
                        saveAsImage: {show: true}     // 保存为图片
                    }
                },
               /*/!* 柱状图   *!/
                xAxis: {
                    data: size
                },
                yAxis: {},*/
                series: [
                    /*{
                        name: '房间数',
                        type: 'bar',
                        data: num
                    },*/
                   /* 饼状图*/
                    {
                        type: 'pie', //饼状
                        radius: '60%', //圆的大小
                        data: obj,
                        /*roseType:'area',
                        center: ['50%', '50%'], //居中
                        selectedMode: "multiple",        // 多个选中
                        selectedOffset: 30          // 选中偏移量*/
                    }
                ]

            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
    var myChart2 = echarts.init(document.getElementById('count2'));
    $.ajax({
        type: "post",
        url: ctx + "/user_role/findType",
        dataType: "json",
        success: function (obj) {
            var size = new Array();
            for (var x in obj) {
                size[x] = obj[x].name;
                console.log(size[x]);
            }
            var num = new Array();
            for (var x in obj) {
                num[x] = obj[x].value;
                console.log(num[x]);
            }
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '角色分类统计',
                    subtext: '分类情况', //副标题
                    x: 'center' //标题居中
                },
                tooltip: {
                    trigger: 'item' //悬浮显示对比
                },
                legend: {
                    orient: 'vertical', //类型垂直,默认水平
                    left: 'left', //类型区分在左 默认居中
                    data: size
                },
                toolbox: {      // 工具栏组件
                    feature: {      // 工具配置项
                        restore: { show: true },        // 配置项还原
                        saveAsImage: {show: true}     // 保存为图片
                    }
                },
                 xAxis: {
                     data: size
                 },
                 yAxis: {},
                series: [
                    {
                        name: '房间数',
                        type: 'bar',
                        data: num
                    },
                ]

            };

            // 使用刚指定的配置项和数据显示图表。
            myChart2.setOption(option);
        }
    });

});
