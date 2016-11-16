/**
 * Created by mass on 2016/5/6.
 */

$ ( function () {
    //尝试解决自适应布局问题 start
    var main = $ ( "#ly" );
    main.layout ( 'panel' , 'west' ).panel ( 'resize' , { width : mass.width * 0.18 } );
    main.layout ( 'panel' , 'east' ).panel ( 'resize' , { width : mass.width * 0.25 } );
    //右
    var eastLy = $ ( "#east-ly" );
    eastLy.layout ( 'panel' , 'north' ).panel ( 'resize' , { height : mass.height * 0.61 } );
    //左
    var westLy = $ ( "#west-ly" );
    westLy.layout ( 'panel' , 'north' ).panel ( 'resize' , { height : mass.height * 0.61 } );

    //中
    var centerLy = $ ( "#center-ly" );
    centerLy.layout ( 'panel' , 'north' ).panel ( 'resize' , { height : mass.height * 0.61 } );
    main.layout ( 'resize' );
    //尝试解决自适应布局问题 end

    //抓拍图片宽度设置
    $ ( ".capturedFaces-img" ).css ( {
        width : $ ( ".capturedFaces-img" ).parent ().parent ().width () * 0.35
    } )


    //大图展示
    $ ( "body" ).click ( function ( e ) {
        if ( $ ( e.target ).is ( "#big-img img" ) ) {
            return false;
        }
        $ ( '#big-img' ).dialog ( 'close' );
    } );
    $ ( '#big-img' ).dialog ( 'close' ); //默认关闭
    $ ( ".capturedFaces-zoom,.cap-zoom" ).live ( "click" , function () {
        $ ( '#big-img' ).dialog ( 'open' );
    } );

    //抓拍记录大图展示处理
    $ ( ".capturedFaces" ).delegate ( ".capturedFaces-img-container" , "hover" , function () {
        $ ( ".capturedFaces-zoom" ).hide ();
        $ ( this ).siblings ( ".capturedFaces-zoom" ).show ();
    } );
    //报警记录大图展示处理
    $ ( ".hitFaces" ).delegate ( ".cap-content-img" , "hover" , function () {
        $ ( ".cap-zoom,.cap-judge" ).hide ();
        $ ( this ).find ( ".cap-zoom,.cap-judge" ).show ();
    } );

    /**
     * 研判信息跳转
     * createTime:  2016/7/7
     */
    $ ( ".cap-judge" ).live ( "click" , function () {
        setCookie ( "id" , $ ( this ).next ().val () );
        window.location.href = "judge.html"; //跳转目标页
    } );


    /**
    * 详细信息
    * createTime:  2016/7/11
    */
    $ ( '.detail-b' ).live ( 'click' , function () {
        var id = $ ( this ).parent ().next ().val ();
        writeDetail ( id ); //写详情
    } )
    
    
    /**
     * 摄像机选择
     * createTime:  2016/8/2
     */
    $ ( "#sourceId" ).combotree ( {
        url : " /ns/camera/cameraListChoseByUser?username=" + getCookie ( "user" ) ,
        valueField:'attributes.sourceId',    
        textField:'text',  
        method : 'GET' ,
        required : false , //必填字段？
        onSelect : function ( node ) {
            var tree = $ ( this ).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree ( 'isLeaf' , node.target );
            if ( !isLeaf && node.text != "全部设备" ) {
                //清除选中
                $ ( '#sourceId' ).treegrid ( "unselect" );
            }
            var sourceId = getCombValue ($ ( "#sourceId" ) , "attributes" ).sourceId; //sourceId
          
            setCookie("socketInfo",getCookie("user")+"@@@"+sourceId );
        },
        onLoadSuccess:function(){ //默认选择第一个摄像机
        	var t = $("#sourceId").combotree('tree');
        	var root = t.tree("getRoot");
        	var placeArr = t.tree("getChildren",root.target);
        	var cameraArrFirst  = t.tree("getChildren",placeArr[0].target);
        	t.tree("select",cameraArrFirst[0].target);
        	 $ ( "#sourceId" ).combotree("setValue",cameraArrFirst[0].text)
        }
    } )


} );

function writeDetail ( id ) {
//    aload.show ();
    //ajaxDialog
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
                dlg.find ( "input[name='type']" ).val ( data[ 'identityType' ] );
                dlg.find ( "input[name='nation']" ).val ( data[ 'nation' ] );
                data[ 'bornedDate' ] = data[ 'bornedDate' ].substr ( 0 , 4 ) + "-" + data[ 'bornedDate' ].substr ( 4 , 2 ) + "-" + data[ 'bornedDate' ].substr ( 6 , 2 );
                dlg.find ( "input[name='bornedDate']" ).val ( data[ 'bornedDate' ] );
                dlg.find ( "input[name='mac']" ).val ( data[ 'mac' ] );
                dlg.find ( "input[name='comment']" ).val ( data[ 'comment' ] );
            }
//            aload.hide ();
        }
    );
    return false;
}