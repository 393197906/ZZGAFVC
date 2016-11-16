/**
 * Created by mass on 2016/4/20.
 */
/**
 * 1:1验证父函数
 * createTime:  2016/6/23
 */
function faceVerifyResult ( text ) {
    var vdata = {};
    var imgArr = text.split ( "@biao@ji@" );
    vdata.img1 = imgArr[ 0 ];
    vdata.img2 = imgArr[ 1 ];
    vdata.threshold = $ ( "#FaceVerifyForm" ).find ( "select[name='threshold']" ).val ();
    $.post ( "/ns/peopleBase/faceVerify" , {
        img1 : vdata.img1 ,
        img2 : vdata.img2 ,
        threshold : vdata.threshold
    } , function ( data , statu ) {
        if ( statu == "success" ) {
            if ( data.statu == "success" ) {
                var msg = "验证通过";
                switch ( data.data.colorStatu ) {
                    case ">":
                        var className = "face-verify-statu-color-da";
                        break;
                    case "<":
                        var className = "face-verify-statu-color-xiao";
                        break;
                    case "=":
                        var className = "face-verify-statu-color-deng";
                        break;
                }
                $ ( ".face-verify-statu-score" ).css ( { width : 0 } ); //初始长度至零
                $ ( ".face-verify-statu-score" ).removeClass ().addClass ( "face-verify-statu-score" );
                $ ( ".face-verify-statu-score" ).addClass ( className ); //添加色彩class
                $ ( ".face-verify-statu-score" ).animate ( { //比分动画
                    width : $ ( ".face-verify-statu-score" ).parent ().width () * parseFloat ( data.data.result )
                } , 2000 );
                $ ( "#face-verify-score" ).text ( data.data.result );
                $ ( ".face-verify-statu-text" ).text ( data.data.result );
            } else {
                var msg = data.msg;
                $.messager.alert ( "错误" , data.msg );
            }
        } else {
            var msg = "验证失败"
        }
        $ ( "#face-verify-result" ).text ( msg );
        aload.hide ();
    } , 'json' )
}

/**
 * 图片检索父函数
 * createTime:  2016/6/23
 */
function loadImgSearchResult ( text ) {
    var data = {};
    data.img = text;
    data.type = $ ( "#imgSearchForm" ).find ( "#ofFd" ).combobox ( "getValue" );
    data.threshold = $ ( "#imgSearchForm" ).find ( "#threshold" ).combobox ( "getValue" );
    data.topN = $ ( "#imgSearchForm" ).find ( "#topN" ).combobox ( "getValue" );
    $.post ( " /ns/peopleBase/searchFaceImageCount" ,
        {
            image : data.img ,
            type : data.type ,
            threshold : data.threshold ,
            topN : data.topN

        } , function ( count ) {
            if ( parseInt ( count ) <= 0 || count == "" ) {
                $ ( "#searchImgList,#searchListList" ).html ( "<h1 style='color:red;text-align: center;margin-top: 100px;'>检索结果为空</h1>" );
                aload.hide ();
                return;
            }
            var url = " /ns/peopleBase/searchFaceImage";
            //缩略图形式
            $ ( '#pagination' ).pagination ( {
                total : parseInt ( count ) ,
                onSelectPage : function ( pageNum , pageSize ) {
                    var self = $ ( this );
                    self.pagination ( 'loading' );
                    aload.show ();
                    $.post ( url , {
                        pageSize : pageSize ,
                        pageNum : pageNum ,
                        image : data.img ,
                        type : data.type ,
                        threshold : data.threshold ,
                        topN : data.topN ,
                        model : "img"
                    } , function ( data , status ) {
                        if ( data != null ) {
                            var content = "";
                            $.each ( data , function ( key , value ) {
                                content += "<div class='face-list'>" +
                                    "<img src='data:image/jpeg;base64," + value[ 'hitFace' ] + "' alt='" + value[ 'createTime' ] + "'>" +
                                    "<div class='face-info'>" +
                                    "<div class='face-info-child face-info-detail'>" +
                                    "<img src='img/detail.png' alt='face'>" +
                                    "<span>详情</span>" +
                                    "<input type='hidden' value='" + value[ 'id' ] + "'>" +
                                    "</div>" +
                                    "<div class='face-info-child face-info-name'>" +
                                    "<img src='img/faceInfo.png' alt='face'>" +
                                    "<span>" + value[ 'name' ] + "</span>" +
                                    "</div>" +
                                    "<div></div>" +
                                    "</div>" +
                                    "<div class='face-score'>比分：" + value[ 'score' ] + "</div>" +
                                    "</div>";
                            } )
                            $ ( "#searchImgList" ).html ( content );
                            var faceWidth = $ ( ".face-list" ).width ();
                            $ ( ".face-list" ).css ( {
                                height : faceWidth * 4 / 3
                            } )
                        }
                        aload.hide ();
                        self.pagination ( 'loaded' );
                    } )

                }
            } );
//    		            //列表形式
            $ ( '#pagination2' ).pagination ( {
                total : parseInt ( count ) ,
                onSelectPage : function ( pageNum , pageSize ) {
                    var self = $ ( this );
                    self.pagination ( 'loading' );
                    aload.show ();
                    $.post ( url , {
                        pageSize : pageSize ,
                        pageNum : pageNum ,
                        image : data.img ,
                        type : data.type ,
                        threshold : data.threshold ,
                        topN : data.topN ,
                        model : "list"
                    } , function ( result , status ) {
                        if ( result != "" ) {
                            var content = "<div class='cap-main'>" +
                                "<div class='cap-title'>" +
                                "<div class='cap-title-left'>" +
                                "<b>检索人像</b>" +
                                "</div>" +
                                "<div class='cap-title-right'>" +
                                "<b>比中信息</b>" +
                                "</div>" +
                                "</div>" +
                                "<div class='cap-content'>" +
                                "<div class='cap-content-left'>" +
                                "<div class='cap-content-img'>" +
                                "<img src='data:image/jpeg;base64," + data.img + "'>" +
                                "</div>" +
                                "<div class='cap-content-btitle'></div>" +
                                "</div>" +
                                "<div class='cap-content-right'>";
                            var num = 0;
                            $.each ( result , function ( k2 , v2 ) {
                                num++;
                                if ( num > 3 ) {
                                    return;
                                }
                                content += "<div class='cap-content-right-1'>" +
                                    "<div class='cap-content-1-1'>" +
                                    "<div class='cap-content-img'>" +
                                    "<img src='data:image/jpeg;base64," + v2[ 'hitFace' ] + "'>" +
                                    "</div>" +
                                    "<div class='cap-content-btitle'>比分:" + v2[ 'score' ] + "</div>" +
                                    "</div>" +
                                    "<div class='cap-content-1-2'>" +
                                    "<div class='cap-content-1-2-content'>" +
                                    "<p>姓名：" + v2[ 'name' ] + "</p>" +
                                    "<p class='baike369'></p>" +
                                    "<p>所属库：" + v2[ 'identityType' ] + "</p>" +
                                    "<p class='detail-p'><a class='detail-b'>详细〉〉</a></p>" +
                                    "<input type='hidden' name='id' value='" + v2[ 'id' ] + "'>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>";
                            } )
                            for ( ; num < 3 ; num++ ) {
                                content += "<div class='cap-content-right-1'>" +
                                    "<div class='cap-content-1-1'>" +
                                    "<div class='cap-content-img'>" +
                                    "<img src='img/facebg.jpg'>" +
                                    "</div>" +
                                    "<div class='cap-content-btitle'>比分:0</div>" +
                                    "</div>" +
                                    "<div class='cap-content-1-2'>" +
                                    "<div class='cap-content-1-2-content'>" +
                                    "<p>姓名：无</p>" +
                                    "<p class='baike369'></p>" +
                                    "<p>所属库：无</p>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>";
                            }
                            content += "</div></div>";

                            content += "</div>";
                            $ ( "#searchListList" ).html ( content );
                            // 规范格式
                            var cwidth = $ ( ".cap-title-left" ).width ();
                            var cheight = (cwidth * 1.1);
                            $ ( ".cap-content" ).css ( {
                                height : cheight + 20
                            } )
                            $ ( ".cap-content-img" ).css ( {
                                height : cheight
                            } )
                        }
                        aload.hide ();
                        self.pagination ( 'loaded' );
                    } )

                }
            } );
//    		             $ ( '#pagination' ).pagination ( 'select' , 1 );
//    		            // $ ( '#pagination2' ).pagination ( 'select' , 1 );
//            var tab = $ ( "#viewModel" ).tabs ( "getSelected" );
//            var index = $ ( '#viewModel' ).tabs ( 'getTabIndex' , tab );
//            switch ( index ) {
//                case 0:
//                    $ ( '#pagination2' ).pagination ( 'select' , 1 );
//                    break;
//                case 1:
//                    $ ( '#pagination' ).pagination ( 'select' , 1 );
//                    break;
//            }

            //默认显示缩略图形式
            $ ( "#viewModel" ).tabs ( "select" , 1 );
            $ ( '#pagination' ).pagination ( 'select' , 1 );


        } )
}


$ ( document ).ready ( function () {
    $ ( '#faceDetail' ).dialog ( 'close' ); //对话框初始关闭
    /**
     * 左侧条件选择视图切换
     * createTime:  2016/6/23
     */
    $ ( "#searchTab" ).tabs ( {
        onSelect : function ( title , index ) {
            var main = $ ( "#ly" );
            if ( index == 2 || index == 3 ) { //1:1验证 mac检索
                main.layout ( 'panel' , 'west' ).panel ( 'resize' , { width : mass.width } );
                main.layout ( 'resize' );
                return;
            }
            //恢复布局
            main.layout ( 'panel' , 'west' ).panel ( 'resize' , { width : 410 } );
            main.layout ( 'resize' );
        }
    } );
    // $ ( "#searchTab" ).tabs ( "select" , 3 );

    /**
     * 视图切换
     * createTime:  2016/6/23
     */
    $ ( ".viewToggle" ).click ( function () {
        var tab = $ ( "#viewModel" ).tabs ( "getSelected" );
        var index = $ ( '#viewModel' ).tabs ( 'getTabIndex' , tab );
        switch ( index ) {
            case 0:
                $ ( "#viewModel" ).tabs ( "select" , 1 );
                $ ( '#pagination' ).pagination ( 'select' , 1 );
                break;
            case 1:
                $ ( "#viewModel" ).tabs ( "select" , 0 );
                $ ( '#pagination2' ).pagination ( 'select' , 1 );
                break;
        }
    } )

    /**
     * 人像库列表(select)
     * createTime:  2016/6/23
     */
    $ ( "#ofFd" ).combobox ( {
        url : ' /ns/peopleBase/getPeopleBasesSelect?user=' + getCookie ( "user" ) ,
        valueField : 'text' ,
        textField : 'text'
    } )


    /**
     * 大图展示
     * createTime:  2016/6/23
     */
    $ ( "body" ).click ( function ( e ) {
        if ( $ ( e.target ).is ( "#big-img img" ) ) {
            return false;
        }
        $ ( '#big-img' ).dialog ( 'close' );
    } )
    $ ( '#big-img' ).dialog ( 'close' ); //默认关闭
    $ ( ".cap-zoom,.open-big-img" ).live ( "click" , function () {
        $ ( '#big-img' ).dialog ( 'open' );
    } )

    //缩略图模式（大图）	
    $ ( "#searchImgList" ).delegate ( ".face-list" , "hover" , function () {
        $ ( ".cap-zoom" ).hide ();
        $ ( this ).find ( ".cap-zoom" ).show ();
    } )
    //列表模式（大图）
    $ ( "#searchListList" ).delegate ( ".cap-content-img" , "hover" , function () {
        $ ( ".cap-zoom" ).hide ();
        $ ( this ).find ( ".cap-zoom" ).show ();
    } )


    /**
     * 上传图片预览
     * createTime:  2016/6/23
     */
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

    /**
     * 1:1布局调整
     * createTime:  2016/6/23
     */
    var fvm = (mass.height - 600) / 2;
    if ( fvm > 0 ) {
        $ ( "#face-verify-container" ).css ( {
            marginTop : fvm
        } )
    }

    /**
     * 1:1验证图片预览
     * createTime:  2016/6/23
     */
    var is_success_img1
    new uploadPreview ( {
        UpBtn : "uploadFaceVerifyImg1" ,
        DivShow : "imgPriviewBi1" ,
        ImgShow : "imgShowBi1" ,
        Width : 287 ,
        Height : 314 ,
        callback : function ( statu ) {
            is_success_img1 = statu;
        }
    } );
    var is_success_img2
    new uploadPreview ( {
        UpBtn : "uploadFaceVerifyImg2" ,
        DivShow : "imgPriviewBi2" ,
        ImgShow : "imgShowBi2" ,
        Width : 287 ,
        Height : 314 ,
        callback : function ( statu ) {
            is_success_img2 = statu;
        }
    } );

    /**
     * 1:1 验证表单提交
     * createTime:  2016/6/23
     */
    $ ( ".face-verify-submit" ).click ( function () {
        var vdata = {};
        vdata.isUpload1 = is_success_img1;
        vdata.isUpload2 = is_success_img2;
        var msg = vd ( vdata );

        if ( msg != 0 ) {
            $.messager.alert ( '提示' , msg );
            return;
        }
        aload.show ();
        $ ( "#FaceVerifyForm" ).submit ();

    } )


    /**
     * 图像搜索表单提交
     * createTime:  2016/6/23
     */
    $ ( "#submitImgSearchForm" ).click ( function () {
        var data = {};
        data.isUpload = is_success;  //图片文件上传状态
        data.libType = $ ( "#imgSearchForm" ).find ( "#ofFd" ).combobox ( "getValue" );
        var msg = vd ( data );
        if ( msg != 0 ) {
            $ ( this ).parent ().siblings ( ".errorMsg" ).text ( msg );
            return;
        } else {
            $ ( this ).parent ().siblings ( ".errorMsg" ).text ( "" );
        }
        aload.show ();
        $ ( "#imgSearchForm" ).submit ();
    } )


    /**
     * 摄像机选择
     * createTime:  2016/6/23
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
     * 条件检索提交
     * createTime:  2016/6/23
     */
    $ ( "#conditionSearchSubmit" ).click ( function () {
        var cond = {};
        cond.dtFrom = $ ( "#conditionSearch" ).find ( "input[name='dtFrom']" ).val (); //起始日期
        cond.dtTo = $ ( "#conditionSearch" ).find ( "input[name='dtTo']" ).val (); //终止日期
        cond.dtFromTime = $ ( "#conditionSearch" ).find ( "input[name='dtFromTime']" ).val ();//起始时间
        cond.dtToTime = $ ( "#conditionSearch" ).find ( "input[name='dtToTime']" ).val (); //终止时间
        cond.username = $ ( "#conditionSearch" ).find ( "input[name='username']" ).val (); //人员姓名
        cond.strSourceId = getCombValue ( $ ( "#strSourceId" ) , "attributes" ).sourceId; //摄像头
        cond.searchType = $ ( "#conditionSearch" ).find ( "#searchType" ).combobox ( "getValue" ); //检索类型
        if ( cond.searchType == "hit" ) { //检索类型判断
            var urlTotal = " /ns/peopleBase/getHitRecordTotal";
            var url = " /ns/peopleBase/getHitRecord";
        } else if ( cond.searchType == "notHit" ) {
            var urlTotal = " /ns/peopleBase/getNotHitRecordTotal";
            var url = " /ns/peopleBase/getNotHitRecord";
        }
        var msg = vdw ( cond );
        if ( msg != 0 ) {
            $ ( this ).parent ().siblings ( ".errorMsg" ).text ( msg );
            return;
        } else {
            $ ( this ).parent ().siblings ( ".errorMsg" ).text ( "" );
        }
        //取总页数
        $.post ( urlTotal , {
            userType : getCookie ( 'user' ) ,
            dtFrom : cond.dtFrom + " " + cond.dtFromTime , //拼接
            dtTo : cond.dtTo + " " + cond.dtToTime ,  //拼接
            strSourceId : cond.strSourceId ,
            username : cond.username
        } , function ( count ) {
            if ( parseInt ( count ) <= 0 || count == "" ) {
                $ ( "#searchImgList,#searchListList" ).html ( "<h1 style='color:red;text-align: center;margin-top: 100px;'>检索结果为空</h1>" );
                return;
            }
            //缩略图形式
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
                        if ( data != "" && data != null ) {
                            var content = "";
                            $.each ( data , function ( key , value ) {
                                if ( 'hitDetail' in value ) { //比中
//                                    content += "<div class='face-list'>" +
//                                        "<img src='data:image/jpeg;base64," + value[ 'queryImage' ] + "' alt='" +
// value[ 'createTime' ] + "'>" + "<div class='face-info'>" + "<p>" + value[ 'cameraName' ] + "</p>" + "<p>" + value[
// 'createTime' ] + "<span class='peopleId' style='display: none;'>" + value[ 'id' ] + "</span></p>" + "</div>" + //   
//                                     "<div class='cap-zoom'>大图</div>" +  //TODO //                                   
//     "<input type='hidden' name='uuid' value='122' />" + "</div>";
                                    content += "<div class='face-list'><img src='data:image/jpeg;base64," + value[ 'queryImage' ] + "' alt='" + value[ 'createTime' ] + "'>" +
                                        "<div class='face-info'>" +
                                        "<div class='face-info-child' style='width:100%;'>" +
//                                    "<img src='img/01camera-2.png' alt='face'>" +
                                        "<span style='margin-left:0px;'>" + value[ 'cameraName' ] + "</span>" +
                                        "</div>" +
                                        "<div></div>" +
                                        "</div>" +
                                        "<div class='face-score'>" + value[ 'createTime' ] + "</div>" +
//                                    "<div class='cap-zoom'>大图</div>" +   //TODO
//                                    "<input type='hidden' name='uuid' value='122' />" +
                                        "</div>";

                                } else { //未比中
                                    content += "<div class='face-list'><img src='data:image/jpeg;base64," + value[ 'queryImage' ] + "' alt='" + value[ 'createTime' ] + "'>" +
                                        "<div class='face-info'>" +
                                        "<div class='face-info-child' style='width:100%;'>" +
//                                        "<img src='img/01camera-2.png' alt='face'>" +
                                        "<span style='margin-left:0px;'>" + value[ 'cameraName' ] + "</span>" +
                                        "</div>" +
                                        "<div></div>" +
                                        "</div>" +
                                        "<div class='face-score'>" + value[ 'createTime' ] + "</div>" +
//                                        "<div class='cap-zoom'>大图</div>" +   //TODO
//                                        "<input type='hidden' name='uuid' value='122' />" +
                                        "</div>";
                                }

                            } )
                            $ ( "#searchImgList" ).html ( content );
                            var faceWidth = $ ( ".face-list" ).width (); //动态设置高度
                            $ ( ".face-list" ).css ( {
                                height : faceWidth * 4 / 3
                            } )
                        }
                        aload.hide ();
                        self.pagination ( 'loaded' );
                    } )

                }
            } );
            //列表形式
            $ ( '#pagination2' ).pagination ( {
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
                            var nhcontent = "";
                            var content = "<div class='cap-main'>" +
                                "<div class='cap-title'>" +
                                "<div class='cap-title-left'>" +
                                "<b>检索人像</b>" +
                                "</div>" +
                                "<div class='cap-title-right'>" +
                                "<b>比中信息</b>" +
                                "</div>" +
                                "</div>";
                            $.each ( data , function ( key , value ) {
                                if ( 'hitDetail' in value ) {
                                    content += "<div class='cap-content'>" +
                                        "<div class='cap-content-left'>" +
                                        "<div class='cap-content-img'>" +
                                        "<img src='data:image/jpeg;base64," + value[ 'queryImage' ] + "'>" +
                                        "<div class='cap-content-img-btitle'>" +
                                        "<img src='themes/IconsExtension/01camera-2.png' alt='camera'><span>" + value[ 'cameraName' ] + "</span>" +
                                        "</div>" +
                                        // "<div class='cap-zoom'>大图</div>" +
                                        // "<input type='hidden' name='uuid' value='122'/>" + //TODO
                                        "</div>" +
                                        "<div class='cap-content-btitle'>" + value[ 'createTime' ] + "</div>" +
                                        "</div>" +
                                        "<div class='cap-content-right'>";
                                    var num = 0;
                                    if( value[ 'hitDetail' ]!=null && value[ 'hitDetail' ] !=''){
                                    $.each ( value[ 'hitDetail' ] , function ( k2 , v2 ) {
                                        if(v2 == null || v2 ==''){
                                        	return;
                                        }
                                    	num++;
                                        content += "<div class='cap-content-right-1'>" +
                                            "<div class='cap-content-1-1'>" +
                                            "<div class='cap-content-img'>" +
                                            "<img src='data:image/jpeg;base64," + v2[ 'faceImage' ] + "'>" +
                                            "</div>" +
                                            "<div class='cap-content-btitle'>比分:" + v2[ 'score' ] + "</div>" +
                                            "</div>" +
                                            "<div class='cap-content-1-2'>" +
                                            "<div class='cap-content-1-2-content'>" +
                                            "<p>姓名：" + v2[ 'name' ] + "</p>" +
                                            "<p class='baike369'></p>" +
                                            "<p>所属库：" + v2[ 'identityType' ] + "</p>";

                                        var levelString;
                                        switch ( v2[ 'level' ] ) {
                                            case 1:
                                                levelString = "低";
                                                break;
                                            case 2:
                                                levelString = "一般";
                                                break;
                                            case 3:
                                                levelString = "高";
                                                break;
                                            case 4:
                                                levelString = "极高";
                                                break;
                                            default:
                                                levelString = "极低";
                                        }

                                        content += "<div>可信度等级：" + levelString + "</div>" +
                                            "<div class='hit-level-container'>";

                                        var level = parseInt ( v2[ 'level' ] );
                                        var slevel = 4 - level;
                                        for ( var i = 0 ; i < level ; i++ ) {
                                            content += "<div class='hit-level-cell hit-level-" + (i + 1) + "'></div>"
                                        }
                                        for ( var j = 0 ; j < slevel ; j++ ) {
                                            content += "<div class='hit-level-cell'></div>"
                                        }

                                        content += "</div>" +
                                            "<p class='detail-p'><a class='detail-b'>详细〉〉</a></p>" +  //TODO
                                            "<input type='hidden' name='id' value='" + v2[ 'id' ] + "'>" +
                                            "</div>" +
                                            "</div>" +
                                            "</div>";
                                    } )
                                    }
                                    for ( ; num < 3 ; num++ ) {
                                        content += "<div class='cap-content-right-1'>" +
                                            "<div class='cap-content-1-1'>" +
                                            "<div class='cap-content-img'>" +
                                            "<img src='img/facebg.jpg'>" +
                                            "</div>" +
                                            "<div class='cap-content-btitle'>比分:0</div>" +
                                            "</div>" +
                                            "<div class='cap-content-1-2'>" +
                                            "<div class='cap-content-1-2-content'>" +
                                            "<p>姓名：无</p>" +
                                            "<p class='baike369'></p>" +
                                            "<p>所属库：无</p>" +
                                            "</div>" +
                                            "</div>" +
                                            "</div>";
                                    }
                                    content += "</div></div>";
                                } else {
                                    nhcontent += "<div class='face-list-list' >" +
                                        "<div class='face-scoll' style='width:100%;'>" +
                                        "<img src='data:image/jpeg;base64," + value[ 'queryImage' ] + "' alt='" + value[ 'createTime' ] + "'>" +
                                        "<div>" +
                                        "<h2>" + value[ 'createTime' ] + "</h2>" +
                                        "<p>" + value[ 'cameraName' ] + "</p>" +
//                                        "<p><a href='#' class='open-big-img'>查看大图</a><input type='hidden' name='uuid'
// value='122'></p>" +  //TODO
                                        "</div>";
                                    nhcontent += "</div></div>"
                                }
                            } )
                            content += "</div>";
                            if ( nhcontent != "" ) {
                                $ ( "#searchListList" ).html ( nhcontent );
                            } else {
                                $ ( "#searchListList" ).html ( content );
                                //规范样式
                                var cwidth = $ ( ".cap-title-left" ).width ();
                                var cheight = (cwidth * 1.1) / 1; //0.9?
                                $ ( ".cap-content" ).css ( {
                                    height : cheight + 20
                                } )
                                $ ( ".cap-content-img" ).css ( {
                                    height : cheight
                                } )
                            }
                        }
                        aload.hide ();
                        self.pagination ( 'loaded' );
                    } )

                }
            } );
            // $ ( '#pagination' ).pagination ( 'select' , '1' );
            // $ ( '#pagination2' ).pagination ( 'select' , '1' );
            var tab = $ ( "#viewModel" ).tabs ( "getSelected" );
            var index = $ ( '#viewModel' ).tabs ( 'getTabIndex' , tab );
            if ( index == 1 && cond.searchType == "hit" ) {
                $.messager.confirm ( '提示' , '比中记录在列表形式下有更好的视图展现，当前为缩略图模式，是否切换为列表模式？' , function ( r ) {
                    if ( r ) {
                        $ ( "#viewModel" ).tabs ( "select" , 0 );
                        $ ( '#pagination2' ).pagination ( 'select' , 1 );
                        return;
                    }
                } );
            }
            switch ( index ) {
                case 0:
                    $ ( '#pagination2' ).pagination ( 'select' , 1 );
                    break;
                case 1:
                    $ ( '#pagination' ).pagination ( 'select' , 1 );
                    break;
            }
        } )
    } );

    /**
     * 人员信息详情(缩略图)
     * createTime:  2016/6/23
     */
    $ ( '.face-info-detail' ).live ( 'click' , function () {
        //ajaxDialog
        var id = $ ( this ).find ( 'input' ).val ();
        writeDetail ( id );
        return false;
    } );

    /**
     * 人员信息详情(列表)
     * createTime:  2016/6/23
     */
    $ ( '.detail-b' ).live ( 'click' , function () {
        var id = $ ( this ).parent ().next ().val ();
        writeDetail ( id ); //写详情
        return false;
    } );


    /**
     *  写详情函数
     * createTime:  2016/6/23
     */
    function writeDetail ( id ) {
        $ ( '#faceDetail' ).dialog ( 'open' );
        aload.show ();
        $.post ( " /ns/peopleBase/getUsersInfoById" , {
                id : id
            } , function ( data , status ) {
                if ( data != "" ) {
                    var dlg = $ ( '#faceDetail' );
                    dlg.find ( "img" ).attr ( "src" , "data:image/jpeg;base64," + data[ 'faceImage' ] ); //设置图片
                    dlg.find ( "img" ).attr ( "alt" , data[ 'name' ] ); //设置alt
                    dlg.find ( "input[name='name']" ).val ( data[ 'name' ] );
                    dlg.find ( "input[name='passport']" ).val ( data[ 'passportNumber' ] );
                    dlg.find ( "input[name='gender']" ).val ( data[ 'gender' ] );
                    dlg.find ( "input[name='identityType']" ).val ( data[ 'identityType' ] );
                    dlg.find ( "input[name='nation']" ).val ( data[ 'nation' ] );
                    data[ 'bornedDate' ] = data[ 'bornedDate' ].substr ( 0 , 4 ) + "-" + data[ 'bornedDate' ].substr ( 4 , 2 ) + "-" + data[ 'bornedDate' ].substr ( 6 , 2 );
                    //dlg.find ( "#borned" ).datebox ( 'setValue' , data[ 'bornedDate' ] );
                    dlg.find ( "input[name='borned']" ).val ( data[ 'bornedDate' ] );
                    dlg.find ( "input[name='mac']" ).val ( data[ 'mac' ] );
                    dlg.find ( "input[name='comment']" ).val ( data[ 'comment' ] );
                }
                aload.hide ();
            }
        )
    }

    /**
     *  mac检索显示格式控制
     * createTime:  2016/7/18
     */

    $ ( '#macTable' ).datagrid ( {
        fit : true ,
        fitColumns : true ,
        autoRowHeight : false ,
        striped : true , //奇偶行颜色区分
        singleSelect : true , //单选
        rownumbers : true , //行号
        pageList : [ 30 , 50 , 100 ] ,
        columns : [ [
            { field : 'macAddr' , title : 'MAC' , width : mass.width * 0.1 , align : 'center'} ,
            { field : 'date' , title : '出现日期' , width : mass.width * 0.1 , align : 'center' } ,
            { field : 'place' , title : '出现地点' , width : mass.width * 0.1 , align : 'center' } ,
            { field : 'count' , title : '出现次数' , width : mass.width * 0.1 , align : 'center' } ,
            { field : 'lastTime' , title : '最后出现时间' , width : mass.width * 0.1 , align : 'center' }
        ] ],
        onSortColumn:function (sort,order){
            //TODO
        }
    } );

    // $ ( '#macTable' ).datagrid ( {
    //     url : "./json/threshold.json" ,
    //     pagination : true
    // } );


    /**
     * mac 检索表单提交
     * createTime:  2016/7/18
     */
    $ ( "#searchMac" ).click ( function ( e ) {
        var macObj = {};
        macObj.mac = $ ( "#infoForm" ).find ( "input[name='mac']" ).val ();
        macObj .dateFrm = $ ( "#dateFrm" ).datetimebox ( "getValue" );
        macObj .dateTo = $ ( "#dateTo" ).datetimebox ( "getValue" );
        // macObj.dateFrm = "2016-01-01 00:00:00";
        // macObj.dateTo = "2016-07-20 00:00:00";
        macObj.place = $ ( "#place" ).combobox ( "getValue" );
        var msg = vd ( macObj );
        if ( msg != 0 ) {
            $ ( "#infoForm" ).find ( ".errorMsg" ).text ( msg );
            return;
        }
        $ ( "#infoForm" ).find ( ".errorMsg" ).text ("");

        macObj.pnum = 1;
        macObj.psize = 30;
        $ ( '#macTable' ).datagrid ( {
            url : "/ns/mac/searchMac" ,
            queryParams : macObj ,
            pagination : true
        } );
        var page = $ ( '#macTable' ).datagrid ( "getPager" );
        page.pagination ( {
            onSelectPage : function ( pnum , psize ) {
                macObj.pnum = pnum;
                macObj.psize = psize;
                $ ( '#macTable' ).datagrid ( "reload" );
            }
        } );
    } );


} );


