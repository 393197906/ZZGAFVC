/**
 * Created by mass on 2016/5/6.
 */
$ ( function () {
    //尝试解决自适应布局问题 start
    var main = $ ( "#ly" );
    main.layout ( 'panel' , 'west' ).panel ( 'resize' , { width : mass.width * 0.24 } );
    main.layout ( 'panel' , 'east' ).panel ( 'resize' , { width : mass.width * 0.24 } );
    //右
    var eastLy = $ ( "#east-ly" );
    eastLy.layout ( 'panel' , 'north' ).panel ( 'resize' , { height : mass.height * 0.5 } );
    //左
    var westLy = $ ( "#west-ly" );
    westLy.layout ( 'panel' , 'north' ).panel ( 'resize' , { height : mass.height * 0.5 } );


    //中
    var centerLy = $ ( "#center-ly" );
    centerLy.layout ( 'panel' , 'north' ).panel ( 'resize' , { height : mass.height * 0.5 } );
    var centerTopLy = $ ( "#center-top-ly" );
    centerTopLy.layout ( 'panel' , 'west' ).panel ( 'resize' , { width : mass.width * 0.26 } );
    var centerBottomLy = $ ( "#center-bottom-ly" );
    centerBottomLy.layout ( 'panel' , 'west' ).panel ( 'resize' , { width : mass.width * 0.26 } );

    main.layout ( 'resize' );
    //尝试解决自适应布局问题 end

    $ ( ".panel-header" ).css ( {
        paddingTop : 8 ,
        paddingBottom : 8
    } )

    //摄像机列表
    var xl = new xload ( $ ( "#cameralist" ).parent () ); //加载动画对象
    $ ( "#cameralist" ).tree ( {
        url : " /ns/camera/cameraListByUser?username=" + getCookie ( "user" ) ,
        method : 'get' ,
        animate : false ,
        lines : true ,
        onBeforeLoad : function () {
            xl.show ();
        } ,
        onLoadSuccess : function () {
            xl.hide ();
        } ,
        onSelect : function ( node ) { //点击事件
            if ( 'attributes' in node ) {
                //$.messager.confirm('提示','是否打开'+node.text+'设备的实时预览？',function(r){
                // if (r){
                setCookie ( "cameraCookie" , node.id ); //设置cookie
                window.location.href = "monitor.html"; //跳转目标页
                //  }
                //});
            }
        }
    } )

    //人像库列表
    var xl2 = new xload ( $ ( "#fdList" ).parent () ); //加载动画对象
    $ ( "#fdList" ).tree ( {
        url : " /ns/peopleBase/getPeopleBasesIndex?user=" + getCookie ( "user" ) ,
        method : 'post' ,
        animate : false ,
        lines : true ,
        onBeforeLoad : function () {
            xl2.show ();
        } ,
        onSelect : function ( node ) { //点击事件
            if ( node.text == "人像库清单" ) {
                return;
            }
            //$.messager.confirm('提示','是否打开'+node.text+'人像库？',function(r){
            //if (r){
            setCookie ( "faceDataBaseCookie" , node.id ); //设置cookie
            window.location.href = "faceDataBase.html"; //跳转目标页
            //}
            //});
        } ,
        onLoadSuccess : function ( node , data ) {
            xl2.hide ();
            //获取dom
            var list = $ ( "#fdList" ).find ( ".tree-title" );
            var count = 0;
            var chs = $ ( "#fdList" ).tree ( "getChildren" );
            $.each ( chs , function ( key , value ) {
                if ( value.hasOwnProperty ( "attributes" ) ) {
                    count += parseInt ( value.attributes.cou );
                    $.each ( list , function ( k , v ) {
                        if ( $ ( v ).text () == value.text ) {
                            $ ( v ).html ( $ ( v ).text () + "<span style='color:blue;padding-left: 15px;font-size:0.8em'>(" + value.attributes.cou + ")</span>" );
                            return;
                        }
                    } )
                }

            } )
            $.each ( list , function ( k , v ) {
                if ( $ ( v ).text () == "人像库清单" ) {
                    $ ( v ).html ( $ ( v ).text () + "<span style='color:blue;padding-left: 15px;font-size:0.8em'>(" + count + ")</span>" );
                    return;
                }
            } )

        }

    } )


    //服务状态
    var xl3 = new xload ( $ ( "#service-statu" ) ); //加载动画对象
    xl3.show ();
    $.get ( " /ns/user/getServiceStatus?user=" + getCookie ( "user" ) , function ( data ) {
        var content = "";
        $.each ( data , function ( key , value ) {
            $.each ( value , function ( k , v ) {
                var className;
                switch ( v ) {
                    case "运行":
                        className = "statu-run";
                        break;
                    case "空闲":
                        className = "statu-idle";
                        break;
                    case "错误":
                        className = "statu-fault";
                        break;
                    case "停止":
                        className = "statu-stopped";
                        break;
                }
                content += "<p><span class='statu'>" + k + "</span><span class='statu-info " + className + "'>" + v + "</span></p>";
            } )
        } )
        $ ( "#service-statu" ).html ( content ); //写入
        xl3.hide ();
        $ ( ".statu-info" ).animate ( {  //动画 自适应
            paddingLeft : $ ( "#service-statu" ).width () / 4 ,
            paddingRight : $ ( "#service-statu" ).width () / 4
        } , 1000 );
    } ) 
    


    //报警信息图表
    $ ( '#alert' ).css ( {
        width : $ ( '#alert' ).parent ().width () ,
        height : $ ( '#alert' ).parent ().height ()
    } )
    var xl4 = new xload ( $ ( "#alert" ) ); //加载动画对象
    xl4.show ();
    $.get ( " /ns/camera/getThreeDayAlertTotal?user=" + getCookie ( "user" ) , function ( data ) {
        var arr = getTJson ( data );
        $ ( '#alert' ).highcharts ( {
            credits : {
                enabled : false // 禁用版权信息
            } ,
            chart : {
                type : 'column'
            } ,
            title : {
                text : null ,
                x : -20 //center
            } ,
            subtitle : {
                text : null ,
                x : -20
            } ,
            xAxis : {
                categories : [ '前天' , '昨天' , '今天' ]
            } ,
            yAxis : {
                title : {
                    text : null
                }
            } ,
            tooltip : {
                valueSuffix : '条'
            } ,
//            legend : {
//            	enabled: false,
//                layout : 'vertical' ,
//                align : 'left' ,
//                verticalAlign : 'middle' ,
//                borderWidth : 0
//            } ,
            series : arr
        } );
        xl4.hide ();

    } )

    //抓拍信息图表
    $ ( '#capture' ).css ( {
        width : $ ( '#capture' ).parent ().width () ,
        height : $ ( '#capture' ).parent ().height ()
    } )
    var xl5 = new xload ( $ ( "#capture" ) ); //加载动画对象
    xl5.show ();
    $.get ( " /ns/camera/getThreeDayCaptureTotal?user=" + getCookie ( "user" ) , function ( data ) {
        var arr = getTJson ( data );
        $ ( '#capture' ).highcharts ( {
            credits : {
                enabled : false // 禁用版权信息
            } ,
            chart : {
                type : 'column'
            } ,
            title : {
                text : null ,
                x : -20 //center
            } ,
            subtitle : {
                text : null ,
                x : -20
            } ,
            xAxis : {
                categories : [ '前天' , '昨天' , '今天' ]
            } ,
            yAxis : {
                title : {
                    text : null
                }
            } ,
            tooltip : {
                valueSuffix : '条'
            } ,
//            legend : {
//                layout : 'vertical' ,
//                align : 'left' ,
//                verticalAlign : 'middle' ,
//                borderWidth : 0
//            } ,
            series : arr
        } );
        xl5.hide ();

    } );

    //登陆信息图
    $ ( '#loginInfo' ).datagrid ( {
        url : " /ns/user/getLoginTime?user=" + getCookie ( "user" ) ,
        method : "get" ,
        singleSelect : true ,
        fitColumns : true ,
        autoRowHeight : false ,
        striped : true , //奇偶行颜色区分
        rownumbers : true , //行号
        columns : [ [
            { field : 'user' , title : '用户' , width : $ ( "#loginInfo" ).parent ().width () * 0.4 , align : 'center' } ,
            {
                field : 'loginTime' ,
                title : '登录时间' ,
                width : $ ( "#loginInfo" ).parent ().width () * 0.595 ,
                align : 'center'
            }
        ] ]
    } );

    /**
     * 报警研判
     * createTime:  2016/7/22
     */
    var xl6 = new xload ( $ ( "#responedInfo" ) ); //加载动画对象
    xl6.show ();
    $.get("/ns/police/getResponseIndex",function(data){
        $ ( "#responedInfo" ).highcharts ( {
            credits : {
                enabled : false // 禁用版权信息
            } ,
            chart : {
                type : 'column' ,
                marginTop : 20,
                marginRight : 10
            } ,

            title : {
                text : null
            } ,
            colors: ['#F7A35C', '#90ed7d'],

            xAxis : {
                categories : [ '前天' , '昨天' , '今天' ]
            } ,

            yAxis : {
                allowDecimals : false ,
                min : 0 ,
                title : {
                    text : null
                }
            } ,

            tooltip : {
                headerFormat : '<b>{point.key}</b><br>' ,
                pointFormat : '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
            } ,

            plotOptions : {
                column : {
                    stacking : 'normal' ,
                    depth : 40
                }
            } ,
            series : [ {
                name : '未审核' ,
                data : [ {y:data.beforeYesterday.notAudiTotal},{y:data.yesterday.notAudiTotal},{y:data.today.notAudiTotal}]
            } ,{
                name : '已审核' ,
                data : [  data.beforeYesterday.audiTotal,data.yesterday.audiTotal,data.today.audiTotal  ]
            } ]
        } );
        xl6.hide ();
    });

    /**
    * 日历
    * createTime:  2016/7/22
    */
    $("#calendar").calendar({
        current:new Date(),
        width:$(this).parent().width(),
        height:$("#calendar").parent().height(),
        border:false
    });


} );
