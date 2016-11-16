$ ( function () {
    /**
     * 右侧内容展示自适应布局
     * createTime:  2016/6/27
     */
    var pWidth = $ ( "#right" ).parent ().width (); //右侧容器宽度
    $ ( "#right" ).layout ( 'panel' , 'east' ).panel ( 'resize' , { width : 405 } );
    $ ( "#right" ).layout ( 'resize' );
    
    
    /**
     * 中间容器防遮挡
     * createTime:  2016/7/21
     */
    $(".judge-list-left,.judge-list-right").css({
    	height:$(".judge-list-continer").height()-60
    })
    

    /**
     * 监控页跳转处理
     * createTime:  2016/7/7
     */
   
    var id = getCookie ( "id" ); //取跳转cookie
    if ( id != null ) {
        aload.show();
        $.post ( "/ns/peopleBase/getHitRecordByID" , {
            id : id
        } , function ( data , statu ) {
            if ( data != null ) {
                $ ( ".judge-list-continer" ).show ().prev ().hide ();
                var content = "";
                content += "<div class='judge-list-info'>";
                content += "<div class='judge-list-info-img'>";
                content += "  <img src='data:image/png;base64," + data[ 'queryImage' ] + "' alt='" + data[ 'id' ] + "'>";
                content += "</div>";
                content += "<div class='judge-list-info-content'>";
                content += "<div>";
                content += "<h4>" + data[ 'cameraName' ] + "</h4>";
                content += "<div class='baike369'></div>";
                content += "<p>" + data[ 'createTime' ] + "</p>";
                content += "<br/>";
                content += "<input name='id' type='hidden' value='" + data[ 'id' ] + "'/>";
                content += "<input name='hitTime' type='hidden' value='" + data[ 'createTime' ] + "'/>";
                // content += "<p align='right'><a class='detail-b'>比中信息>></a></p>";
                var hiddInfo = "";
                $.each ( data['details'][ 'hitDetail' ] , function ( k2 , v2 ) {
                	if ( v2 == null ) return;
                    hiddInfo += v2[ 'userID' ] + ","
                } );
                content += "<input type='hidden' name='info' value='" + hiddInfo + "'/>";
                content += "</div>";
                content += "<div id='arrow'></div>";
                content += "</div>";
                content += "</div>";
                content += "<div class='baike369'></div>";
                $ ( ".judge-list-left" ).html ( content );
                $ ( ".judge-list-left .judge-list-info" ).eq ( 0 ).trigger ( "click" );
            }
            aload.hide();
        } );
        delCookie ( "id" );//清除cookie
    };
    /**
     * 摄像机选择
     * createTime:  2016/7/25
     */
    $ ( "#strSourceId" ).combotree ( {
        url : " /ns/camera/cameraListChoseByUser?username=" + getCookie ( "user" ) ,
        method : 'GET' ,
        required : false , //必填字段？
        onSelect : function ( node ) {
            var tree = $ ( this ).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree ( 'isLeaf' , node.target );
            if ( !isLeaf && node.text != "全部设备" ) {
                //清除选中
                $ ( '#strSourceId' ).treegrid ( "unselect" );
            }
        }
    } )
    
    
    
    /**
     * 条件检索
     * createTime:  2016/6/30
     */
    $ ( "#conditionSearchSubmit" ).click ( function () {
        var cond = {};
         cond.dtFrom = $ ( "#conditionSearch" ).find ( "input[name='dtFrom']" ).val (); //起始日期
         cond.dtTo = $ ( "#conditionSearch" ).find ( "input[name='dtTo']" ).val (); //终止日期
//        cond.dtFrom = "2016-06-10";
//        cond.dtTo = "2016-06-30";
        cond.dtFromTime = $ ( "#conditionSearch" ).find ( "input[name='dtFromTime']" ).val ();//起始时间
        cond.dtToTime = $ ( "#conditionSearch" ).find ( "input[name='dtToTime']" ).val (); //终止时间
        cond.username = $ ( "#conditionSearch" ).find ( "input[name='username']" ).val (); //人员姓名
        cond.strSourceId = getCombValue ( $ ( "#strSourceId" ) , "attributes" ).sourceId; //摄像头
        var urlTotal = "  /ns/peopleBase/getHitRecordTotal";
        var url = "  /ns/peopleBase/getHitRecord";
        var msg = vdw ( cond );
        if ( msg != 0 ) {
            $ ( this ).parent ().siblings ( ".errorMsg" ).text ( msg );
            return;
        } else {
            $ ( this ).parent ().siblings ( ".errorMsg" ).text ( "" );
        }
        aload.show ();
        //取总页数
        $.post ( urlTotal , {
                userType : getCookie ( 'user' ) ,
                dtFrom : cond.dtFrom + " " + cond.dtFromTime , //拼接
                dtTo : cond.dtTo + " " + cond.dtToTime ,  //拼接
                strSourceId : cond.strSourceId ,
                username : cond.username
            } , function ( count ) {
                if ( parseInt ( count ) <= 0 || count == "" ) {
                	 $ ( ".judge-list-continer" ).hide ().prev ().show().html("<h1 style='color:red;text-align: center;margin-top: 100px;'>检索结果为空</h1>");
                	 aload.hide();
//                    $ ( ".judge-list-continer" ).html ( "<h1 style='color:red;text-align: center;margin-top: 100px;'>检索结果为空</h1>" );
                    return;
                }
                $ ( ".judge-list-continer" ).show ().prev ().hide ();
                //列表
                $ ( '#pagination' ).pagination ( {
                    total : parseInt ( count ) ,
                    onSelectPage : function ( pageNum , pageSize ) {
                        var self = $ ( this );
                        self.pagination ( 'loading' );
                        aload.show ();
                        $.post ( url , {
                            userType : getCookie ( 'user' ) ,
                            nPageSize : pageSize ,
                            nPage : pageNum ,
                            dtFrom : cond.dtFrom + " " + cond.dtFromTime , //拼接
                            dtTo : cond.dtTo + " " + cond.dtToTime ,  //拼接
                            strSourceId : cond.strSourceId ,
                            username : cond.username
                        } , function ( data , status ) {
                            if ( data != "" ) {
                                var content = "";
                                $.each ( data , function ( k , v ) {
                                    content += "<div class='judge-list-info'>";
                                    content += "<div class='judge-list-info-img'>";
                                    content += "  <img src='data:image/png;base64," + v[ 'queryImage' ] + "' alt='" + v[ 'id' ] + "'>";
                                    content += "</div>";
                                    content += "<div class='judge-list-info-content'>";
                                    content += "<div>";
                                    content += "<h4>" + v[ 'cameraName' ] + "</h4>";
                                    content += "<div class='baike369'></div>";
                                    content += "<p style='padding-top:20px;'>" + v[ 'createTime' ] + "</p>";
                                    content += "<br/>";
                                    content += "<input name='id' type='hidden' value='" + v[ 'id' ] + "'/>";
                                    content += "<input name='hitTime' type='hidden' value='" + v[ 'createTime' ] + "'/>";
                                    // content += "<p align='right'><a class='detail-b'>比中信息>></a></p>";
                                    // content += "<p class='arrow'>→</p>";
                                    var hiddInfo = "";
                                    $.each ( v[ 'hitDetail' ] , function ( k2 , v2 ) {
                                        if ( v2 == null ) return;
                                        hiddInfo += v2[ 'id' ] + ","
                                    } );
                                    content += "<input type='hidden' name='info' value='" + hiddInfo + "'/>";
                                    content += "</div>";
                                    content += "<div id='arrow'></div>";
                                    content += "</div>";
                                    content += "</div>";
                                    content += "<div class='baike369'></div>";
                                } );
                                $ ( ".judge-list-left" ).html ( content );
                                // var width = $(".judge-list-info-img").width();
                                // $(".judge-list-info").css({height:width*4/3});
                            }
                            aload.hide ();
                            self.pagination ( 'loaded' );
                            $ ( ".judge-list-left .judge-list-info" ).eq ( 0 ).trigger ( "click" );
                        } )
                    }
                } ).pagination ( 'select' , 1 );
            }
        );
    } );

    /**
     * 比中信息
     * createTime:  2016/7/7
     */
    $ ( ".judge-list-left .judge-list-info" ).live ( "click" , function ( event ) {
        //active
        $ ( ".judge-list-info" ).removeClass ( "judge-list-info-active" );
        $ ( this ).addClass ( "judge-list-info-active" );
        aload.show ();
        var id = $ ( this ).find("input[name='id']").val ();
        var hitTime = $ ( this ).find("input[name='hitTime']").val ();
        $ ( ".showResponse" ).find ( "input" ).val ( id+"@"+hitTime ).trigger ( "click" ); //模拟点击
        var info = $ ( this ).find("input[name='info']").val ();
        $.post ( "  /ns/peopleBase/getUserBaseByIdStr" , {
            info : info
        } , function ( data , status ) {
            var content = "";
            $.each ( data , function ( k , v ) {
                content += "<div class='judge-list-info'>";
                content += "<div class='judge-list-info-img'>";
                content += "<img src='data:image/png;base64," + v[ 'faceImage' ] + "' alt='" + v[ 'id' ] + "'>";
                content += "</div>";
                content += "<div class='judge-list-info-content'>";
                content += "<div>";
                content += "<p>姓名：" + v[ 'name' ] + "</p>";
                content += "<div class='baike369'></div>";
                content += "<p>性别：" + v[ 'gender' ] + "</p>";
                content += "<div class='baike369'></div>";
                content += "<p>籍贯：" + v[ 'nation' ] + "</p>";
                content += "<div class='baike369'></div>";
                content += "<p>证件：" + v[ 'passportNumber' ] + "</p>";
                content += "<div class='baike369'></div>";
                v[ 'bornedDate' ] = v[ 'bornedDate' ].substr ( 0 , 4 ) + "-" + v[ 'bornedDate' ].substr ( 4 , 2 ) + "-" + v[ 'bornedDate' ].substr ( 6 , 2 );
                content += "<p>生日：" + v[ 'bornedDate' ] + "</p>";
                content += "<div class='baike369'></div>";
                content += "<p>MAC：" + v[ 'macAddr' ] + "</p>";
                content += "<div class='baike369'></div>";
                content += "<p>所属库：" + v[ 'identityType' ] + "</p>";
                content += "</div>";
                content += "</div>";
                content += "</div>";
            } );
            $ ( ".judge-list-right" ).html ( content );
            var width = $ ( ".judge-list-info-img" ).width ();
            $ ( ".judge-list-info" ).css ( { height : width * 4 / 3.2 } );
            aload.hide ();
        } )
    } );

    /**
     * 研判流程控制
     * createTime:  2016/6/29
     */
    var speed = 1000;
    $ ( ".judge-statu-main-info-1" ).click ( function () {
        var dp = $ ( ".judge-statu-main-3" ).css ( "display" );
        if ( dp == "block" ) return;
        $ ( this ).text ( "正在审核.." ); //改变文字
        $ ( this ).css ( { color : "#E2473E" } );
        $ ( "#selectInfo1" ).fadeIn ( speed );
    } );

    $ ( "#auditPerson" ).combobox ( {
        onSelect : function () {
            var time = getNowFormatDate ();
            $ ( ".auditTime" ).text ( time ); //填入审核时间
            //下一步流程渐现
            $ ( ".judge-statu-direction-1" ).fadeIn ( speed / 2 , function () {
                $ ( ".judge-statu-main-2" ).fadeIn ( speed );
            } )
        }
    } );

    $ ( "input[name='isP']" ).click ( function () {
        if ( $ ( this ).val () == "true" ) { //出警
            $ ( "#selectInfo2" ).fadeIn ( speed );
            $ ( ".judge-statu-main-3" ).fadeOut ( speed , function () {
                $ ( ".judge-statu-direction-2" ).fadeOut ( speed / 2 );
            } )
        } else { //不出警
            $.messager.confirm ( '确认' , '确定要选择否吗？一经选择不可更改' , function ( r ) {
                if ( r ) {
                    var pData = {};
                    pData.auditPersonId = $ ( "#auditPerson" ).combobox ( "getValue" ); //审核人
                    pData.auditTime = $ ( ".auditTime" ).text (); //审核时间
                    pData.hitId = $ ( "#hitId" ).val ();
                    pData.hitTime = $ ( "#hitTime" ).val ();
                    $.post ( " /ns/police/addNoResponse" , pData , function ( data ) {
                        if ( data.statu == "success" ) {
                            endResponse (); //结束函数
                            writeRes ( pData.hitId );
                        } else {
                            $.messager.alert ( data.statu , data.msg );
                        }
                    } , "json" )
                } else {
                    $ ( "input[name='isP']" ).attr ( "checked" , false ); //清空选项
                    $ ( "#selectInfo2" ).fadeOut ( speed );
                }
            } );
        }
    } );
    //结束函数（重复调用）
    function endResponse () {
        $ ( ".judge-statu-direction-2" ).fadeIn ( speed / 2 , function () {
            $ ( ".judge-statu-main-3" ).fadeIn ( speed );
        } );

        $ ( "#auditCompany" ).combotree ( "setValue","" );
        $ ( "#auditPerson" ).combobox ( "setValue","" );
        $ ( ".auditTime" ).text ("");
    }

    /**
     * 出警单提交
     * createTime:  2016/6/29
     */
    $ ( ".submitPRes" ).click ( function ( e ) {
        var pData = {};
        pData.auditPersonId = $ ( "#auditPerson" ).combobox ( "getValue" ); //审核人
        pData.auditTime = $ ( ".auditTime" ).text (); //审核时间
        pData.pResCompanyId = $ ( "#pResCompany" ).combotree ( "getValue" ); //出警单位id
        pData.pResPersonId = $ ( "#pResPerson" ).combobox ( "getValue" ); //出警警员id
        pData.pResTime = $ ( "#pResTime" ).datetimebox ( "getValue" ); //出警时间
        pData.pResReason = $ ( "#pResReason" ).combobox ( "getValue" ); // 出警事由
        pData.pResResult = $ ( "#pResResult" ).combobox ( "getValue" ); // 出警结果
        pData.hitId = $ ( "#hitId" ).val ();
        pData.hitTime = $ ( "#hitTime" ).val ();
        var msg = vd ( pData );
        if ( msg != 0 ) {
            $.messager.alert ( '提示' , msg );
            // $ ( ".errorMsg" ).text ( msg );
            return;
        }
        // $ ( ".errorMsg" ).text ( "" );
        $.post ( " /ns/police/addResponse" , pData , function ( data ) {
            if ( data.statu == "success" ) {
                endResponse (); //结束函数
                writeRes ( pData.hitId );
            } else {
                $.messager.alert ( data.statu , data.msg );
            }
        } , "json" );
    } );


    /**
     *  选择出警单位与出警人
     * createTime:  2016/6/29
     */
    $ ( "#pResCompany" ).combotree ( {
        onSelect : function ( rec ) {
            $ ( "#pResPerson" ).combobox ( {
                method : 'get' ,
                valueField : 'id' ,
                textField : 'text' ,
                url : " /ns/police/policemanListByCompany?cid=" + rec.id
            } )
        }
    } );

    $ ( "#auditCompany" ).combotree ( {
        onSelect : function ( rec ) {
            $ ( "#auditPerson" ).combobox ( {
                method : 'get' ,
                valueField : 'id' ,
                textField : 'text' ,
                url : " /ns/police/policemanListByCompany?cid=" + rec.id
            } )
        }
    } );

    /**
     * 查看研判流程（点击按钮事件）
     * createTime:  2016/6/30
     */
    $ ( ".showResponse" ).click ( function () {
        var hinfo = $ ( this ).find ( "input" ).val ();
        if ( hinfo == "" ) return;
        writeRes ( hinfo );
    } );

    function writeRes ( hinfo ) {
    	var hid = hinfo.split("@")[0];
    	var htime = hinfo.split("@")[1];
        $.get ( " /ns/police/getPoliceResponseByHitId?hitId=" + hid , function ( data ) {
            var jsmain1 = $ ( ".judge-statu-main-1" );
            var jsmain2 = $ ( ".judge-statu-main-2" );
            var jsmain3 = $ ( ".judge-statu-main-3" );
            var jsmainImg1 = $ ( ".judge-statu-main-img-1" );
            var jsmainInfo1 = $ ( ".judge-statu-main-info-1" );
            var jsd1 = $ ( ".judge-statu-direction-1" );
            var jsd2 = $ ( ".judge-statu-direction-2" );
            var selectInfo1 = $ ( "#selectInfo1" );
            var showInfo1 = $ ( "#showInfo1" );
            var selectInfo2 = $ ( "#selectInfo2" );
            var showInfo2 = $ ( "#showInfo2" );
            //隐匿
            jsmain1.hide ();
            jsmain2.hide ();
            jsmain3.hide ();
            jsd1.hide ();
            jsd2.hide ();
            selectInfo1.hide ();
            selectInfo2.hide ();
            showInfo1.hide ();
            showInfo2.hide ();
            $ ( "input[name='isP']" ).attr ( "disabled" , false ).attr ( "checked" , false );
            if ( data == null ) { //未审核
                jsmain1.show ();
                jsmainImg1.text ( "未审核" );
                jsmainInfo1.text ( "审核流程>>" );
                $ ( "#hitId" ).val ( hid ); //设置hitId
                $ ( "#hitTime" ).val ( htime ); //设置hitTime
            } else {  //已审核
                if ( data.hasOwnProperty ( "responseCompanyId" ) ) { //出警
                    jsmain1.show ();
                    jsmainInfo1.css ( { color : "white" } ).text ( "审核结束" );
                    jsmainImg1.text ( "已审核" );
                    showInfo1.show ();
                    var content1 = "<p>审&nbsp;核&nbsp;人：" + data.auditPolicemanName + "</p><p>审核时间：" + data.auditTime + "</p>";
                    showInfo1.find ( ".judge-statu-info-title" ).next ().html ( content1 );
                    showInfo2.show ();
                    var content2 = "<p>出警单位：" + data.responseCompanyName + "</p><p>出警人&#12288;：" + data.responsePolicemanName + "</p><p>出警时间：" + data.responseTime + "</p> <p>出警事由：" + data.responseReason + "</p> <p>出警结果：" + data.responseResult + "</p>";
                    showInfo2.find ( ".judge-statu-info-title" ).next ().html ( content2 );

                    jsmain2.show ();
                    $ ( "input[name='isP']" ).attr ( "disabled" , true ).eq ( 0 ).attr ( "checked" , true );
                    jsmain3.show ();
                    jsd1.show ();
                    jsd2.show ();
                } else { //未出警
                    jsmain1.show ();
                    jsmainInfo1.css ( { color : "white" } ).text ( "审核结束" );
                    jsmainImg1.text ( "已审核" );
                    showInfo1.show ();
                    var content1 = "<p>审&nbsp;核&nbsp;人：" + data.auditPolicemanName + "</p><p>审核时间：" + data.auditTime + "</p>";
                    showInfo1.find ( ".judge-statu-info-title" ).next ().html ( content1 );

                    jsmain2.show ();
                    $ ( "input[name='isP']" ).attr ( "disabled" , true ).eq ( 1 ).attr ( "checked" , true );
                    jsmain3.show ();
                    jsd1.show ();
                    jsd2.show ();
                }
            }
        } );
    }

} );

