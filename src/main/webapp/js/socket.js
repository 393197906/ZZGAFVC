/**
 * Created by mass on 2016/4/28.
 */
var socket = {

    config : {

        ip : "localhost" ,

        port : 4511 ,

        flashcontainer : "flashcontainer" ,

        auto : true

    } ,

    connect : function () {

        socket.flash.log ( "begin connect to session server" );

        socket.flash.connect ( socket.config.ip , socket.config.port );

    } ,

    send : function ( msg ) {

        if ( socket.isConnected >= 1 ) {

            socket.flash.send ( msg );

        }

    } ,

    loaded : function () {

        socket.flash = document.getElementById ( "socketBridge" );

        socket.isConnected = 0;

        if ( socket.config.auto ) {

            socket.connect ();
        }

    } ,

    connected : function () {

        socket.isConnected = 1;

        socket.flash.log ( "connected to session server" );
        //长传递
        setInterval ( function () {
        	var user = getCookie ( "socketInfo" );
            socket.send ( user + "\n" );
            socket.flash.log ( "send " + user );
        } , 1000 )

    } ,

    close : function () {

        socket.flash.close ();

        socket.isConnected = 0;

        socket.flash.log ( "close connection" );

    } ,

    disconnected : function () {

        socket.isConnected = 0;

        socket.flash.log ( "disconnected" );

    } ,

    ioError : function ( msg ) {

        socket.flash.log ( msg );

        socket.isConnected = 0;

    } ,
    securityError : function ( msg ) {

        socket.flash.log ( msg );

        socket.isConnected = 0;

    } ,
    receive : function ( msg ) {
        msg = msg.substr ( 0 , msg.length - 5 );
        var infoArr = msg.split ( "|||||" );
        var hitInfo = infoArr[ 0 ]; // 报警信息
        var notHitInfo = infoArr[ 1 ]; // 抓拍信息
        // 抓拍信息dom
        if ( notHitInfo != "null" ) {
            var notHitInfoarr = JSON.parse ( notHitInfo );
            var notHitDom = $ ( ".capturedFaces" );
            if ( notHitInfoarr.length != 0 ) {
            	//从后删除dom
                $.each ( notHitInfoarr , function ( k , v ) {
                    notHitDom.prepend ( function () {
                        return "<div class='capturedFaces-img'>" +
                            "<div class='capturedFaces-img-container'>" +
                            "<img src='data:image/jpeg;base64," + v[ 'faceStr' ] + "'  alt='" + v[ 'createTime' ] + "'>" +
                            "<div class='cap-content-img-btitle'>" +
                            "<img src='themes/IconsExtension/01camera-2.png' alt='camera'><span>" + v[ 'sourceID' ] + "</span>" +
                            "</div>" +
                            "</div>" +
                            "<p >" + v[ 'createTime' ] + "</p>" +
                            // "<div class='capturedFaces-zoom'>大图</div>" +
                            // "<input type='hidden' name='uuid' value='122'>" +    //TODO
                            "</div>";
                    } );
                    $ ( ".capturedFaces-img" ).css (
                        {
                            width : $ ( ".capturedFaces-img" )
                                .parent ().parent ().width () * 0.31
                        } )
                } )
                
            }
            if(notHitDom.children().length>20){
            	for(var i = 0,j = -1;i<notHitInfoarr.length;i++,j--){
                	notHitDom.children().eq(-1).remove();
                }
            }
            
            // var nothitle = notHitDom.children ().length;
            // notHitDom.css ( {
            // width : 240 * nothitle
            // } )
        }

        // 报警信息dom
        if ( hitInfo != "null" ) {
            var hitInfoarr = JSON.parse ( hitInfo );
            var hitDom = $ ( ".hitFaces" );
            if ( hitInfoarr.length != 0 ) {
                $.each (
                    hitInfoarr ,
                    function ( k , v ) {
                        hitDom.prepend ( function () {
                            var content = "<div class='cap-content'>" +
                                "<div class='cap-content-left'>" +
                                "<div class='cap-content-img'>" +
                                "<img src='data:image/jpeg;base64," + v[ 'queryImage' ] + "' alt='" + v[ 'createTime' ] + "'>" +
                                "<div class='cap-content-img-btitle'>" +
                                "<img src='themes/IconsExtension/01camera-2.png' alt='camera'><span>" + v[ 'cameraName' ] + "</span>" +
                                "</div>" +
                                //"<div class='cap-label cap-zoom'>大图</div>" +
                                //"<input type='hidden' name='uuid' value='122'>" + //TODO
                                "<div class='cap-label cap-judge'>研判</div>" +
                                "<input type='hidden' name='id' value='" + v[ 'id' ] + "'>" +
                                "</div>" +
                                "<div class='cap-content-btitle'>" + v[ 'createTime' ] + "</div>" +
                                "</div>" +
                                "<div class='cap-content-right'>";
                            var num = 0;
                            $.each ( v[ 'detail' ] , function ( k2 , v2 ) {
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
                                    "<p>所属库：" + v2[ 'type' ] + "</p>";
                                var levelString;
                                switch ( v2[ 'level' ] ) {
                                    case "1":
                                        levelString = "低";
                                        break;
                                    case "2":
                                        levelString = "一般";
                                        break;
                                    case "3":
                                        levelString = "高";
                                        break;
                                    case "4":
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
                                    "<p class='detail-p'><a class='detail-b'>详细〉〉</a></p>" +
                                    "<input type='hidden' name='id' value='" + v2[ 'id' ] + "'>" +
                                    "</div>" +
                                    "</div>" +
                                    "</div>";
                            } );
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

                            return content;
                        } )
                    } );
                // 模拟点击
                $ ( '.detail-b' ).eq ( 0 ).trigger ( "click" );
                // 规范格式
                var cwidth = $ ( ".cap-title-left" ).width ();
                var cheight = (cwidth * 4)/3;
                $ ( ".cap-content" ).css ( {
                    height : cheight + 20
                } );
                $ ( ".cap-content-img" ).css ( {
                    height : cheight
                } );
                $ ( "#aSound" ).html ( insertMp3 ( "song/bi.mp3" ) );  //报警声音
                aanimate (); //报警动画
            }
        }
    }
};

function initialFlash () {
    var so = new SWFObject ( "js/socket_bridge.swf" , "socketBridge" , "800" ,
        "300" , "9" , "red" );
    so.addParam ( "allowscriptaccess" , "always" );
    so.addVariable ( "scope" , "socket" );
    so.write ( socket.config.flashcontainer );
}

onload = function () {
    initialFlash ();
};