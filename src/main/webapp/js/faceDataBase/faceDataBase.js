/**
 * Created by mass on 2016/4/20.
 */

/**
 * 注册父函数
 * createTime:  2016/7/12
 */
function loadImgEnrollResult ( text ) {
    var data = {};
    data.image = text;
    data.name = $ ( "#Enrollform" ).find ( "input[name='name']" ).val ();//姓名
    data.passport = $ ( "#Enrollform" ).find ( "input[name='passport']" ).val ();//身份证号
    data.gender = $ ( "#Enrollform" ).find ( "select[name='gender']" ).val ();//性别
    data.nation = $ ( "#Enrollform" ).find ( "select[name='nation']" ).val ();//籍贯
    data.mac = $ ( "#Enrollform" ).find ( "input[name='mac']" ).val ();//mac地址
    data.comment = $ ( "#Enrollform" ).find ( "input[name='comment']" ).val ();//备注
    data.type = $ ( "#Enrollform" ).find ( "#ofFd" ).combobox ( "getValue" ); //所属人口库
    data.borned = $ ( "#Enrollform" ).find ( "#borned1" ).datebox ( 'getValue' );  //出生日期
    $.post ( "/ns/peopleBase/enrollUser" ,
        {
            image : data.image ,
            name : data.name ,
            passport : data.passport ,
            gender : data.gender ,
            nation : data.nation ,
            comment : data.comment ,
            type : data.type ,
            mac : data.mac,
            borned : data.borned
        } , function ( result , status ) {
            if ( status == "success" ) {
                aload.hide ();
                if ( result.statu == "success" ) {
                    $.messager.confirm ( '成功' , '注册成功，是否继续注册？' , function ( r ) {
                        if ( r ) {
                            $ ( '#enform' )[ 0 ].reset ();
                            $ ( "#imgShow" ).attr ( "src" , "" );
                        } else { //否，返回条件查询，并打开对应的人口库节点
                            $ ( '#enform' )[ 0 ].reset ();
                            $ ( "#imgShow" ).attr ( "src" , "" );
                            $ ( "#selectTab" ).tabs ( "select" , 0 );
                            $ ( "#faceDbtree" ).tree ( "reload" ); //重载树(刷新统计数据)
                            $ ( "#faceDbtree" ).tree ( {
                                onLoadSuccess : function () { //重定义数据加载成功方法
                                    xl.hide ();
                                    var node = treeGetNodeByname ( "faceDbtree" , data.type ); //根据text取节点
                                    $ ( '#faceDbtree' ).tree ( 'select' , node.target ); //选择注册树节点
                                    
                                  //显示人口库数据总量标签
                                    //获取dom
                                    var list = $ ( "#faceDbtree" ).find ( ".tree-title" );
                                    var chs = $ ( "#faceDbtree" ).tree ( "getChildren" );
                                    var count = 0;
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

                                    } );
                                    $.each ( list , function ( k , v ) {
                                        if ( $ ( v ).text () == "人像库清单" ) {
                                            $ ( v ).html ( $ ( v ).text () + "<span style='color:blue;padding-left: 15px;font-size:0.8em'>(" + count + ")</span>" );
                                            return;
                                        }
                                    } );
                                    
                                    
                                }
                            } )
                        }
                    } );

                } else {
                    $.messager.alert ( '失败' , result.msg + ",请重新核对表单" );
                }
            }
        } )
}

$ ( document ).ready ( function () {

    /**
     * 自适应布局
     * createTime:  2016/7/11
     */
    var main = $ ( "#ly" );
    var west = main.layout ( 'panel' , 'west' );
    west.panel ( 'resize' , { width : mass.width * 0.2 } );
    var westMain = $ ( "#west-main" );
    var westNorth = westMain.layout ( 'panel' , 'north' );
    westNorth.panel ( 'resize' , { height : mass.height * 0.5 } );
    $ ( "#tools" ).panel ( 'resize' , { width : mass.width * 0.2 } );  //左侧菜单
    $ ( "#treec" ).panel ( 'resize' , { height : 700 } ); //树容器
    main.layout ( 'resize' );
    westMain.layout ( 'resize' );

    //尝试解决自适应布局问题 end
    $ ( '#dlg' ).dialog ( 'close' ); //对话框初始关闭1
    $ ( '.face-list' ).live ( 'click' , function () {
        $ ( ".face-close" ).hide ();
        $ ( this ).find ( ".face-close" ).show ();
        return false;
    } )
    $ ( 'body' ).live ( 'click' , function () {
        $ ( ".face-close" ).hide ();
    } )

    /**
     * 刷新人像库列表函数
     * createTime:  2016/7/11
     */
    function dorefe () {
        $ ( "#ofFd,#ofFd2" ).combobox ( {
            url : ' /ns/peopleBase/getPeopleBasesSelect?user=' + getCookie ( "user" ) ,
            valueField : 'text' ,
            textField : 'text'
        } );
        $ ( "#faceDbtree" ).tree ( "reload" ); //重载树
    }

    dorefe (); //初始化刷新

    /**
     * 单例注册
     * createTime:  2016/7/11
     */
    //预览
    var is_success;  //上传状态 默认undifine
    new uploadPreview ( {
        UpBtn : "uploadImg" ,
        DivShow : "imgPriview" ,
        ImgShow : "imgShow" ,
        Width : 250 ,
        Height : 300 ,
        callback : function ( statu ) {
            is_success = statu;
        }
    } );
    //提交注册
    $ ( "#enrollSubmit" ).click ( function () {
        var enroll = {};
        enroll.name = $ ( "#Enrollform" ).find ( "input[name='name']" ).val ();
        enroll.passport = $ ( "#Enrollform" ).find ( "input[name='passport']" ).val ();
        enroll.gender = $ ( "#Enrollform" ).find ( "select[name='gender']" ).val ();
        enroll.nation = $ ( "#Enrollform" ).find ( "select[name='nation']" ).val ();
        enroll.comment = $ ( "#Enrollform" ).find ( "input[name='comment']" ).val ();
        enroll.borned = $ ( "#Enrollform" ).find ( "input[name='borned']" ).val ();
        enroll.type = $ ( "#Enrollform" ).find ( "#ofFd" ).combobox ( "getValue" );
        enroll.image = $ ( "#uploadImg" ).val ();
        enroll.isUpload = is_success;  //图片文件上传状态
        var msg = vd ( enroll );
        if ( msg != 0 ) {
            $.messager.alert ( '警告' , msg );

//            $ ( ".errorMsg" ).text ( msg );
            return;
        } else {
//            $ ( ".errorMsg" ).text ( "" );
        }
        aload.show ( "正在注册" );
        $ ( "#enform" ).submit ();
    } )


    /**
     * 条件查询
     * createTime:  2016/7/11
     */
    $ ( "#searchFd" ).click ( function () {
        var info = {};
        info.name = $ ( "#infoForm" ).find ( "input[name='name']" ).val ();
        info.passportNumber = $ ( "#infoForm" ).find ( "input[name='passportNumber']" ).val ();
        var msg = vd ( info );
        if ( msg != 0 ) {
            $ ( ".errorMsg" ).text ( msg );
            return;
        } else {
            $ ( ".errorMsg" ).text ( "" );
        }
        aload.show ();
        $ ( "#paginationContainer" ).hide (); //分页栏隐藏
        $.post ( " /ns/peopleBase/getUsersInfoByName" , {
            name : info.name ,
            passportNumber : info.passportNumber
        } , function ( data ) {
            if ( data == "" || data == null ) {
                var content = "<h3 align='center' style='color: #EE484C'>搜索结果为'空'</h3>";
            } else {
                var content = "<h3 align='center' style='color: #EE484C'>搜索结果</h3><div class='baike369' style='margin-bottom:20px;'></div>" +
                    "<div class='face-list'>" +
                    "<img src='data:image/jpeg;base64," + data[ 'faceImage' ] + "' alt='" + data[ 'name' ] + "'>" +
                    "<div class='face-info'>" +
                    "<div class='face-info-child face-info-edit'>" +
                    "<img src='img/editFace.png' alt='face'>" +
                    "<span>详情</span>" +
                    "</div>" +
                    "<div class='face-info-child face-info-name'>" +
                    "<img src='img/faceInfo.png' alt='face'>" +
                    "<span>" + data[ 'name' ] + "</span>" +
                    "<span class='peopleId' style='display: none;'>" + data[ 'id' ] + "</span>" +
                    "</div>" +
                    "<div></div>" +
                    "</div>" +
                    "<div class='face-close'>×</div>" +
                    "</div>";

            }
            $ ( "#peopleList" ).html ( content );
            var faceWidth = $ ( ".face-list" ).width ();
            $ ( ".face-list" ).css ( {
                height : faceWidth * 4 / 3
            } )
            aload.hide ();
        } )

    } )

    /**
     * 人像库左侧树形菜单管理
     * createTime:  2016/7/11
     */
    $ ( "#addDialog" ).dialog ( "close" );
    //add face database
    $ ( "#addFd" ).click ( function () {
        $ ( "#addDialog" ).dialog ( "open" );
    } );
    $ ( "#submitAddFd" ).click ( function () {
        var fd = {};
        fd.faceDbName = $ ( "#addFacedbForm" ).find ( "input[name='faceDbName']" ).val ();
        fd.user = getCookie ( "user" );
        var msg = vd ( fd );
        if ( msg != 0 ) {
            $.messager.alert ( '失败' , msg );
            return;
        }
        $.post ( " /ns/peopleBase/addPeopleBase" , {
            name : fd.faceDbName ,
            user : fd.user
        } , function ( data ) {
            if ( data.statu = "success" ) {
                $.messager.alert ( '成功' , data.message );
                dorefe ();
            } else {
                $.messager.alert ( '失败' , data.message );
            }
            $ ( "#addDialog" ).dialog ( "close" );
        } )
    } );
    //delete face database
    $ ( "#deleteFd" ).click ( function () {
        var node = $ ( "#faceDbtree" ).tree ( "getSelected" );
        if ( node.text == "人像库清单" ) {
            return;
        }
        $.messager.confirm ( '确认' , '是否强制删除' + node.text + "?" , function ( r ) {
            if ( r ) {
                $.post ( " /ns/peopleBase/deletePeopleBase" , {
                    name : node.text ,
                    user : getCookie ( "user" )
                } , function ( data ) {
                    if ( data.statu = "success" ) {
                        $.messager.alert ( '成功' , data.message );
                        dorefe ();
                    } else {
                        $.messager.alert ( '失败' , data.message );
                    }
                    $ ( "#addDialog" ).dialog ( "close" );
                } )
            }
        } )
    } )
    //reload face database
    $ ( "#reloadFd" ).click ( function () {
        dorefe ();
        // $.messager.alert ( '成功' ,"刷新成功");
    } )

    /**
     * 左侧树加载数据
     * createTime:  2016/7/11
     */
    xl = new xload ( $ ( "#faceDbtree" ).parent () );
    $ ( "#faceDbtree" ).tree ( {
        url : ' /ns/peopleBase/getPeopleBasesIndex?user=' + getCookie ( "user" ) ,
        method : 'post' ,
        animate : true ,
        lines : true ,
        onBeforeLoad : function () {
            xl.show (); //打开加载动画
        } ,
        onSelect : function ( node ) {
            //判断当前tab
            var tab = $ ( '#selectTab' ).tabs ( 'getSelected' );
            var indexTab = $ ( '#selectTab' ).tabs ( 'getTabIndex' , tab );
            if ( indexTab != 0 ) {
                $ ( '#selectTab' ).tabs ( 'select' , 0 );
            }
            //清空检索条件提示信息
            $ ( ".errorMsg" ).text ( "" );
            if ( node.text == "人像库清单" ) {
                return;
            }
            $ ( "#paginationContainer" ).show (); //分页栏显示
            $.post ( " /ns/peopleBase/getLatestEnrolledUsersInfoCount" , {
                lib : node.text
            } , function ( data , status ) {
                if ( data.statu == "success" ) { //正确获得数据总量
                    $ ( '#pagination' ).pagination ( {
                        total : data.num ,
                        onSelectPage : function ( pageNum , pageSize ) {
                            var self = $ ( this );
                            self.pagination ( 'loading' );
                            aload.show ();
                            $.post ( " /ns/peopleBase/getLatestEnrolledUsersInfo" , {
                                lib : node.text ,
                                pageNum : pageNum ,
                                pageSize : pageSize
                            } , function ( data , status ) {
                                if ( data.length != 0 ) {
                                    var content = "";
                                    $.each ( data , function ( key , value ) {
                                        content += "<div class='face-list'>" +
                                            "<img src='data:image/jpeg;base64," + value[ 'faceImage' ] + "' alt='" + value[ 'name' ] + "'>" +
                                            "<div class='face-info'>" +
                                            "<div class='face-info-child face-info-edit'>" +
                                            "<img src='img/editFace.png' alt='face'>" +
                                            "<span style='margin-left:5px;'>详情</span>" +
                                            "</div>" +
                                            "<div class='face-info-child face-info-name'>" +
                                            "<img src='img/faceInfo.png' alt='face'>" +
                                            "<span style='margin-left:5px;'>" + value[ 'name' ] + "</span>" +
                                            "<span class='peopleId' style='display: none;'>" + value[ 'id' ] + "</span>" +
                                            "</div>" +
                                            "<div></div>" +
                                            "</div>" +
                                            "<div class='face-close'>×</div>" +
                                            "</div>";
                                    } )
                                    $ ( "#peopleList" ).html ( content );
                                    $ ( ".face-close" ).hide ();
                                    var faceWidth = $ ( ".face-list" ).width ();
                                    $ ( ".face-list" ).css ( {
                                        height : faceWidth * 4 / 3
                                    } )
                                } else {
                                    var content = "<h1 style='text-align: center;color:  #EE484C'>没有数据</h1>";
                                    $ ( "#peopleList" ).html ( content );
                                }
                                aload.hide ();
                                self.pagination ( 'loaded' );
                            } )
                        }
                    } );
                    $ ( '#pagination' ).pagination ( 'select' , 1 );
                }

            } )
            return false;
        } ,
        onLoadSuccess : function ( node , data ) {
            xl.hide ();  //删除加载动画
            //显示人口库数据总量标签
            //获取dom
            var list = $ ( "#faceDbtree" ).find ( ".tree-title" );
            var chs = $ ( "#faceDbtree" ).tree ( "getChildren" );
            var count = 0;
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

            } );
            $.each ( list , function ( k , v ) {
                if ( $ ( v ).text () == "人像库清单" ) {
                    $ ( v ).html ( $ ( v ).text () + "<span style='color:blue;padding-left: 15px;font-size:0.8em'>(" + count + ")</span>" );
                    return;
                }
            } );


            //加载图表数据
            var arr = new Array ();
            $.each ( data[ 0 ][ "children" ] , function ( k , v ) { //拼接数组
                var arrc = new Array ();
                arrc.push ( v.text );
                arrc.push ( parseInt ( v.attributes.cou ) );
                arr.push ( arrc );
            } )

            var width = $ ( "#charts" ).width ();
            var height = $ ( "#charts" ).height ();
            $ ( '#container' ).css ( {
                width : width ,
                height : height
            } )
            $ ( '#container' ).highcharts ( {                   //图表展示容器，与div的id保持一致
                credits : {
                    enabled : false // 禁用版权信息
                } ,
                chart : {
                    plotBackgroundColor : null ,
                    plotBorderWidth : null ,
                    plotShadow : false
                } ,
                title : {
                    text : null
                } ,
                tooltip : {
                    pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
                } ,
                plotOptions : {
                    pie : {
                        allowPointSelect : true ,
                        cursor : 'pointer' ,
                        dataLabels : {
                            enabled : false
                        } ,
                        showInLegend : true
                    }
                } ,
                series : [ {
                    type : 'pie' ,
                    name : '所占比例:' ,
                    data : arr
                    //     [
                    //     [ '测试人像库1' , 100 ] ,
                    //     {
                    //         name : '测试人像库2' ,
                    //         y : 200 ,
                    //         sliced : true ,
                    //         selected : true
                    //     } ,
                    //     [ '测试人像库3' , 300 ]
                    // ]
                } ]
            } );
            //判读是不是首页跳转而来
            var fc = getCookie ( "faceDataBaseCookie" ); //取跳转cookie
            if ( fc != null ) {
                var node = treeGetNodeById ( "faceDbtree" , fc );//根据节点名称
                $ ( "#faceDbtree" ).tree ( "select" , node.target );//选择节点
                delCookie ( "faceDataBaseCookie" );//清除cookie
                return; //不执行加载第一节点代码
            }
            //默认加载第一节点
            $ ( "#faceDbtree ul li:eq(0)" ).find ( "div" ).addClass ( "tree-node-selected" );   //设置第一个节点高亮
            var n = $ ( "#faceDbtree" ).tree ( "getSelected" );
            if ( n != null ) {
                $ ( "#faceDbtree" ).tree ( "select" , n.target );    //相当于默认点击了一下第一个节点，执行onSelect方法
            }
            // $("#selectTab").tabs("select",1);

        }
    } );

    /**
     * 人脸编辑
     * createTime:  2016/7/11
     */
    $ ( '.face-info-edit' ).live ( 'click' , function () {
        $ ( '#dlg' ).dialog ( 'open' );
        aload.show ();
        //ajaxDialog
        var id = $ ( this ).parent ().find ( '.peopleId' ).text ();
        $.post ( " /ns/peopleBase/getUsersInfoById" , {
                id : id
            } , function ( data , status ) {
                if ( data != "" ) {
                    var dlg = $ ( '#dlg' );
                    dlg.find ( "img" ).attr ( "src" , "data:image/jpeg;base64," + data[ 'faceImage' ] ); //设置图片
                    dlg.find ( "img" ).attr ( "alt" , data[ 'name' ] ); //设置alt
                    dlg.find ( "input[name='name']" ).val ( data[ 'name' ] );
                    dlg.find ( "input[name='passport']" ).val ( data[ 'passportNumber' ] );
                    dlg.find ( "select[name='gender']" ).val ( data[ 'gender' ] );
                    dlg.find ( "#ofFd2" ).combobox ( "setValue" , data[ 'identityType' ] );  //TODO 所属库
                    dlg.find ( "select[name='nation']" ).val ( data[ 'nation' ] );
                    data[ 'bornedDate' ] = data[ 'bornedDate' ].substr ( 0 , 4 ) + "-" + data[ 'bornedDate' ].substr ( 4 , 2 ) + "-" + data[ 'bornedDate' ].substr ( 6 , 2 );
                    dlg.find ( "#borned" ).datebox ( 'setValue' , data[ 'bornedDate' ] );
                    dlg.find ( "input[name='mac']" ).val ( data[ 'mac' ] );
                    dlg.find ( "input[name='comment']" ).val ( data[ 'comment' ] );
                }
                aload.hide ();
            }
        );
        return false;
    } );

    /**
     * 人脸删除
     * createTime:  2016/7/11
     */
    $ ( '.face-close' ).live ( 'click' , function () {
        var id = $ ( this ).parent ().find ( '.peopleId' ).text ();
        var self = $ ( this );  //闭包
        $.messager.confirm ( "警告" , "确定要删除吗？" , function ( y ) {
            if ( y ) {
                $.post ( " /ns/peopleBase/deleteUser" , {
                    userId : id
                } , function ( data ) {
                    if ( data.statu = "success" ) {
                        self.parents ( ".face-list" ).remove ();
                        $.messager.alert ( "成功" , data.msg );

                    } else {
                        $.messager.alert ( "失败" , data.msg );
                    }
                } )
            }
        } );
        return false;
    } );

} );


