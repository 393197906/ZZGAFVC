/**
 * Created by mass on 2016/4/20.
 */
/**
*  判断ie6
* createTime:  2016/7/20
*/

function isIe6(){
    return !-[1,]&&!window.XMLHttpRequest;
}
/**
 * 获取当前时间字符串
 * createTime:  2016/6/29
 */
function getNowFormatDate () {
    var date = new Date ();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth () + 1;
    var strDate = date.getDate ();
    if ( month >= 1 && month <= 9 ) {
        month = "0" + month;
    }
    if ( strDate >= 0 && strDate <= 9 ) {
        strDate = "0" + strDate;
    }
    var currentDate = date.getFullYear () + seperator1 + month + seperator1 + strDate
        + " " + date.getHours () + seperator2 + date.getMinutes ()
        + seperator2 + date.getSeconds ();
    return currentDate;
}

/**
 *  加载动画
 * createTime:  2016/6/28
 */
var aload = {
    show : function () {
        var info = arguments[ 0 ] ? arguments[ 0 ] : '数据加载中';
        $.messager.progress ( {
            title : '请稍后' ,
            msg : info ,
            text : ""
        } );
    } ,
    hide : function () {
        $.messager.progress ( 'close' );
    }

}

/**
 * 自定义加载动画
 * createTime:  2016/6/28
 */
function xload ( dom ) {
    this.md;  //加载动画dom
    this.show = function () { //显示（添加）加载动画
        dom.append ( this.content () );
        this.md = dom.find ( '.load-style' );
        //设置css 格式
        var pw = this.md.parent ().width ();
        var ph = this.md.parent ().height ();
        var w = this.md.width ();
        var h = this.md.height ();
        this.md.css ( {
            top : (ph - h) / 2 ,
            left : (pw - w) / 2
        } )
    },
        this.hide = function () { //隐藏加载动画
            this.md.remove ();
        },
        this.content = function () { //加载动画内容
            var content = "<div class='load-style' style='width:150px;height:35px;background:white;border:1px solid #0a8ce2;position: absolute;top:0px;left:0px;text-align: center;line-height: 35px;z-index:999'>";
            content += "<div style='height:100%;width:35px;float: left;'>";
            content += "<img alt='load' src='img/load1.gif' style='width:100%;height:100%;' /></div>";
            content += "<div style='height:100%;width:115px;float: left;line-height:35px;text-align: center;'>";
            content += "	数据加载中···";
            content += "</div>";
            content += "</div>";
            return content;
        }
}

/**
 *  报警动画（dom 高耦合）
 * createTime:  2016/6/28
 */
function aanimate () {
    var speed = 25;
    var speed2 = 800;
    var width = $ ( ".alert-animate-height-left" ).parent ().width ();
    var height = $ ( ".alert-animate-height-left" ).parent ().height () + 40;
    $ ( ".alert-animate" ).show ();
    $ ( ".alert-animate-height-left" ).animate ( {
        height : height
    } , speed , function () {
        $ ( ".alert-animate-width-bottom" ).animate ( {
            width : width
        } , speed , function () {
            $ ( ".alert-animate-height-right" ).animate ( {
                height : height
            } , speed , function () {
                $ ( ".alert-animate-width-top" ).animate ( {
                    width : width
                } , speed , function () {
                    $ ( ".alert-animate" ).fadeOut ( speed2 , function () {
                        $ ( ".alert-animate-height" ).css ( { height : 0 } );
                        $ ( ".alert-animate-width" ).css ( { width : 0 } );
                    } );
                } );
            } );
        } );
    } );
}

/**
 *  表格宽度设置百分比
 * createTime:  2016/6/28
 */
function fixWidth ( percent ) {
    return ($ ( "#registerInfo" ).width () - 8) * percent;
}

/**
 * 浏览器可用宽高
 * createTime:  2016/6/28
 */
var mass = {
    width : $ ( window ).width () ,
    height : $ ( window ).height () - 150
}

/**
 * 禁用缓存
 * createTime:  2016/6/28
 */
$.ajaxSetup ( {
    cache : false ,
    timeout : 70000 ,
    dataType : "json" ,
     error : function ( xhr , err , e ) {
         aload.hide ();
         $.messager.alert ( '错误' , "status:" + xhr.status + " " + xhr.statusText + " " + err );
     }
} );

/**
 * commotree get value
 * createTime:  2016/6/28
 */
function getCombValue ( el , value ) {
    var checked = el.combotree ( 'tree' ).tree ( 'getSelected' );
    if ( checked != null ) {
        if ( checked.text == "全部设备" ) {
            return {
                sourceId : ""
            };
        }
        return checked[ value ];
    } else {
        return "";
    }

}
/**
 *  条件检索验证规则防污染
 * createTime:  2016/7/18
 */
function vdw ( obj ) {
    var msg = 0;
    // 条件检索
    if ( obj[ "dtFrom" ] == '' ) {
        msg = "起始日期不能为空";
        return msg;
    }
    if ( obj[ "dtTo" ] == '' ) {
        msg = "终止日期不能为空";
        return msg;
    }
    if ( obj[ "strSourceId" ] == undefined ) {
        if ( getCookie ( "user" ) != "admin" ) {
            msg = "未选择摄像头";
            return msg;
        } else {
            obj[ "strSourceId" ] = "";
        }
    }

    return msg;


}
/**
 * 验证规则
 * createTime:  2016/6/28
 */
function vd ( obj ) {
    var msg = 0;
    // 出警单审核开始
    if ( obj[ "pResCompanyId" ] == '' ) {
        msg = "出警单位不能为空";
        return msg;
    }
    if ( obj[ "pResPersonId" ] == '' ) {
        msg = "出警人不能为空";
        return msg;
    }
    if ( obj[ "pResTime" ] == '' ) {
        msg = "出警时间不能为空";
        return msg;
    }
    // 出警单审核结束
    if ( obj[ "placeName" ] == '' ) {
        msg = "地点名称不能为空";
        return msg;
    }
    if ( obj[ "cameraName" ] == '' ) {
        msg = "摄像机名称不能为空";
        return msg;
    }
    if ( obj[ "cameraSourceId " ] == '' ) {
        msg = "摄像机SOURCEID不能为空";
        return msg;
    }
    if ( obj[ "cameraPlaceId" ] == '' ) {
        msg = "摄像机所在地不能为空";
        return msg;
    }
    if ( obj[ "cameraIPAddress" ] == '' ) {
        msg = "摄像机IP不能为空";
        return msg;
    }
    if ( obj[ "cameraUser" ] == '' ) {
        msg = "摄像机登陆名不能为空";
        return msg;
    }
    if ( obj[ "cameraPassword" ] == '' ) {
        msg = "摄像机登陆密码不能为空";
        return msg;
    }
    if ( obj[ "cameraUrl" ] == '' ) {
        msg = "摄像机URL不能为空";
        return msg;
    }
    if ( obj[ "faceDbName" ] == '' ) {
        msg = "人像库名称不能为空";
        return msg;
    }
    // 注册
    if ( obj[ "name" ] == '' ) {
        msg = "姓名不能为空";
        return msg;
    }
    if ( obj[ "passportNumber" ] == '' ) {
        msg = "身份证号码不能为空";
        return msg;
    }
    if ( obj[ "isUpload" ] == false ) {
        msg = "请上传正确的图片类型";
        return msg;
    }
    if ( obj[ "isUpload" ] == undefined && obj.hasOwnProperty ( "isUpload" ) ) {
        msg = "请选择图片上传";
        return msg;
    }
    if ( obj[ "isUpload1" ] == false ) {
        msg = "请上传正确的图片类型";
        return msg;
    }
    if ( obj[ "isUpload1" ] == undefined && obj.hasOwnProperty ( "isUpload1" ) ) {
        msg = "请选择源图像";
        return msg;
    }
    if ( obj[ "isUpload2" ] == false ) {
        msg = "请上传正确的图片类型";
        return msg;
    }
    if ( obj[ "isUpload2" ] == undefined && obj.hasOwnProperty ( "isUpload2" ) ) {
        msg = "请选择目标图像";
        return msg;
    }

    // 图片检索
    if ( obj[ "libType" ] == '' && getCookie ( "user" ) != "admin" ) {
        msg = "查询库类型不能为空";
        return msg;
    }
    // if ( obj[ "username" ] == ''&& obj["searchType"]=="hit") {
    // msg = "人员姓名不能为空";
    // return msg;
    // }
    // 用户分组
    if ( obj[ "groupName" ] == '' ) {
        msg = "用户分组名不能为空";
        return msg;
    }
    // 用户注册
    if ( obj[ "rootUsername" ] == '' ) {
        msg = "用户名不能为空";
        return msg;
    }
    if ( obj[ "rootPassword" ] == '' ) {
        msg = "密码不能为空";
        return msg;
    }
    if ( obj[ "rootPassword2" ] != obj[ "rootPassword" ]
        && obj[ "rootPassword2" ] != undefined ) {
        msg = "两次密码不一致";
        return msg;
    }
    if ( obj[ "permission" ] == '' ) {
        msg = "权限不能为空";
        return msg;
    }
    if ( obj[ "ofGroupId" ] == '' ) {
        msg = "所属分组不能为空";
        return msg;
    }
    //mac搜索提交
    if ( obj[ "dateFrm" ] == '' ) {
        msg = "起始日期不能为空";
        return msg;
    }
    if ( obj[ "dateTo" ] == '' ) {
        msg = "终止日期不能为空";
        return msg;
    }
    if ( obj[ "place" ] == '' ) {
        msg = "地点不能为空";
        return msg;
    }
    return msg
}

/**
 * 写cookies
 * createTime:  2016/6/28
 */

function setCookie ( c_name , value , expiredays ) {
    var exdate = new Date ();
    exdate.setDate ( exdate.getDate () + expiredays );
    document.cookie = c_name + "=" + escape ( value )
        + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString ());
}

/**
 * // 读取cookies
 * createTime:  2016/6/28
 */
function getCookie ( name ) {
    var arr , reg = new RegExp ( "(^| )" + name + "=([^;]*)(;|$)" );

    if ( arr = document.cookie.match ( reg ) )

        return (arr[ 2 ]);
    else
        return null;
}

/**
 * 删除cookies
 * createTime:  2016/6/28
 */
function delCookie ( name ) {
    var exp = new Date ();
    exp.setTime ( exp.getTime () - 1 );
    var cval = getCookie ( name );
    if ( cval != null )
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString ();
}


/**
 * 权限管理过滤
 * createTime:  2016/6/28
 */
function getOld ( jiu , xin , str , type ) {
    if ( type == "lib" ) {
        var jiuArr = LibObjToArr ( jiu , str );
        var xinArr = LibObjToArr ( xin , str );
    } else if ( type == "cam" ) {
        var jiuArr = camObjToArr ( jiu , str );
        var xinArr = camObjToArr ( xin , str );
    }
    return jian ( jiuArr , xinArr ); // ||占位用 无实际意义
}
function getNew ( jiu , xin , str , type ) {
    if ( type == "lib" ) {
        var jiuArr = LibObjToArr ( jiu , str );
        var xinArr = LibObjToArr ( xin , str );
    } else if ( type == "cam" ) {
        var jiuArr = camObjToArr ( jiu , str );
        var xinArr = camObjToArr ( xin , str );
    }
    return (jia ( jiuArr , xinArr )); // ||占位用 无实际意义
}

function LibObjToArr ( ol , str ) {
    var arr = new Array ();
    for ( var i = 0 ; i < ol.length ; i++ ) {
        if ( ol[ i ].text != str ) {
            arr.push ( ol[ i ].text );
        }
    }
    return arr;
}

function camObjToArr ( ol , str ) {
    var arr = new Array ();
    for ( var i = 0 ; i < ol.length ; i++ ) {
        if ( ol[ i ].text != str && ol[ i ].attributes != undefined ) {
            arr.push ( ol[ i ].attributes.sourceId );
        }
    }
    return arr;
}
function jian ( a , b ) {
    var sheng = new Array ();
    for ( var i = 0 ; i < a.length ; i++ ) {
        var is = false;
        for ( var j = 0 ; j < b.length ; j++ ) {
            if ( a[ i ] == b[ j ] ) {
                is = true;
                break;
            }
        }
        if ( !is ) {
            sheng.push ( a[ i ] );
        }
    }
    return sheng;
}

function jia ( a , b ) {
    var duo = new Array ();
    for ( var i = 0 ; i < b.length ; i++ ) {
        var is = false;
        for ( var j = 0 ; j < a.length ; j++ ) {
            if ( b[ i ] == a[ j ] ) {
                is = true;
                break;
            }
        }
        if ( !is ) {
            duo.push ( b[ i ] );
        }
    }
    return duo;
}

/**
 * 三天数据json 格式转换 图表用
 * createTime:  2016/6/28
 */
function getTJson ( data ) {
    var arr = new Array ();
    for ( var i = 0 ; i < data[ 'today' ].length ; i++ ) {
        var obj = {};
        obj.name = data[ 'today' ][ i ][ 'cameraName' ]
        obj.data = new Array ();
        try {
            obj.data.push ( parseInt ( data[ 'beforeYesterday' ][ i ][ 'count' ] ) )
        } catch ( e ) {
            obj.data.push ( 0 );
        }
        try {
            obj.data.push ( parseInt ( data[ 'yesterday' ][ i ][ 'count' ] ) )
        } catch ( e ) {
            obj.data.push ( 0 );
        }
        try {
            obj.data.push ( parseInt ( data[ 'today' ][ i ][ 'count' ] ) )
        } catch ( e ) {
            obj.data.push ( 0 );
        }
        arr.push ( obj );
    }
    return arr;
}


/**
 * 根据树节点text 取dom
 * createTime:  2016/6/28
 */
function treeGetNodeByname ( idName , text ) {
    var root = $ ( '#' + idName ).tree ( "getRoot" );
    var chi = $ ( '#' + idName ).tree ( "getChildren" , root.target );
    var node;
    $.each ( chi , function ( k , v ) {
        if ( v.text == text ) {
            node = v;
            return;
        }
    } )
    return node;
}


/**
 * 根据树节点id 取dom(只选最子节点)
 * createTime:  2016/6/28
 */
function treeGetDiNodeById ( idName , id ) {
    var root = $ ( '#' + idName ).tree ( "getRoot" );
    var chi = $ ( '#' + idName ).tree ( "getChildren" , root.target );
    var node;
    $.each ( chi , function ( k , v ) {
        if ( 'attributes' in v ) {
            if ( v.id == id ) {
                node = v;
                return;
            }
        }
    } )
    return node;
}
/**
 * 根据树节点id 取dom
 * createTime:  2016/6/28
 */
function treeGetNodeById ( idName , id ) {
    var root = $ ( '#' + idName ).tree ( "getRoot" );
    var chi = $ ( '#' + idName ).tree ( "getChildren" , root.target );
    var node;
    $.each ( chi , function ( k , v ) {
        if ( v.id == id ) {
            node = v;
            return;
        }
    } );
    return node;
}


/**
 * 判断登陆
 * createTime:  2016/6/28
 */
function loginStatu () {
    // islogin
    // setCookie ( "user" , "admin" ); //TODO
    var iuser = getCookie ( "user" );

    var strUrl = window.location.href;
    var arrUrl = strUrl.split ( "/" );
    var strPage = arrUrl[ arrUrl.length - 1 ];

    if ( iuser == null && strPage != "login.html" ) {
        window.location.href = "login.html";
    } else {
        $ ( ".nav-right span" ).text ( iuser );
    }
}

/**
 * 插入音频
 * createTime:  2016/6/28
 */
function insertMp3 ( url ) {
    var content = "";
    if ( navigator.userAgent.indexOf ( "Chrome" ) > -1 ) {
        //如果是Chrome：
        content += "<audio src='" + url + "' type='audio/mp3' autoplay='autoplay' hidden='true'></audio>";
    } else if ( navigator.userAgent.indexOf ( "Firefox" ) != -1 ) {
        //如果是Firefox：
        content += "<embed src='" + url + "' type='audio/mp3'  hidden='true' loop='false' ></embed>";
    } else if ( navigator.appName.indexOf ( "Microsoft Internet Explorer" ) != -1 && document.all ) {
        //如果是IE(6,7,8):
        content += "<object classid='clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95'><param name='AutoStart' value='1' /><param name='Src' value='" + url + "' /></object>";
    } else if ( navigator.appName.indexOf ( "Opera" ) != -1 ) {
        //如果是Oprea：
        content += "<embed src='" + url + "' type='audio/mpeg'   loop='false'></embed>";
    } else {
        content += "<embed src='" + url + "' type='audio/mp3' hidden='true' loop='false' ></embed>";
    }
    return content;
}

$ ( function () {
    loginStatu (); // 执行
    $ ( ".logout" ).click ( function () {
        delCookie ( "user" );
        window.location.href = "login.html";
    } );

    // 动态添加系统管理
    if ( getCookie ( "user" ) == "admin"
        && window.location.href.split ( "/" )[ window.location.href.split ( "/" ).length - 1 ] != "setting.html" ) {
        $ ( ".nav" ).append ( "<a href='setting.html'><li>系统管理</li></a>" );
    }

} );
