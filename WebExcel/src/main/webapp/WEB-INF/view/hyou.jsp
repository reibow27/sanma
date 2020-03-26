<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xhtml="true" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>WebExcel</title>
<meta name="keywords" content="" lang="ja" xml:lang="ja" />
<meta name="description" content="" lang="ja" xml:lang="ja" />
<meta http-equiv="content-style-type" content="text/css" />
<meta http-equiv="content-script-type" content="text/javascript" />
<meta http-equiv="content-language" content="ja" />
<meta name="author" content="author" />
<link rel="stylesheet" href="/WebExcel/style.css" type="text/css" />
<link type="text/css" href="/WebExcel/jquery-ui-1.8.24.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="/WebExcel/hyou.css" type="text/css" />
<script type="text/javascript" src="/WebExcel/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/WebExcel/js/jquery-ui-1.8.24.custom.min.js"></script>
<script type="text/javascript" src="/WebExcel/js/jquery.ui.datepicker-ja.js"></script>
<script type="text/javascript" src="/WebExcel/js/jquery.balloon.js"></script>
<link rel="alternate" type="application/atom+xml" title="Atom" href="atom.xml" />
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="index.xml" />

<script language="JavaScript">
<!--

$(document).ready(function() {		
});
	
$(function() {
	
	var urlParam;
	
	// Datepicker
	$('.datepicker').datepicker({
		inline: true,
		dateFormat: "yy-mm-dd"
	});
	
	setModal();
	
	$(".filter img")
    .mouseenter(function(){
    	urlParam = $(this).attr('value');
    })

	
	$('.filter img').balloon({
	});
});


//function popBalloon(target, wtableId, bigId){
//	$('.' + target).balloon({
//		url: '/WebExcel/list/listItemMst?wtableId=' + wtableId + '&itemMstBigId=' + bigId
//	});
//}

function setModal() {
 
	//HTML読み込み時にモーダルウィンドウの位置をセンターに調整
	adjustCenter("div#modal div.container");
 
	//ウィンドウリサイズ時にモーダルウィンドウの位置をセンターに調整
	$(window).resize(function() {
		adjustCenter("div#modal div.container");
	});
 
	//背景がクリックされた時にモーダルウィンドウを閉じる
	$("div#modal div.background").click(function() {
		displayModal(false);
		window.location.reload();
	});
 
	//リンクがクリックされた時にAjaxでコンテンツを読み込む
	$("a.modal").click(function() {
		$("div#modal div.container").load($(this).attr("href"), data="html", onComplete);
		return false;
	});
 
	//コンテンツの読み込み完了時にモーダルウィンドウを開く
	function onComplete() {
		displayModal(true);
		$("div#modal div.container a.close").click(function() {
			displayModal(false);
			return false;
		});
	}
}
 
//モーダルウィンドウを開く
function displayModal(sign) {
	if (sign) {
		$("div#modal").fadeIn(500);
	} else {
		$("div#modal").fadeOut(250);
	}
}
 
//ウィンドウの位置をセンターに調整
function adjustCenter(target) {
	var margin_top = ($(window).height()-$(target).height())/2;
	var margin_left = ($(window).width()-$(target).width())/2;
	$(target).css({top:margin_top+"px", left:margin_left+"px"});
}

function setRowColor(index, color){
	$("#trId" + index).css("background-color", color);
}

-->
</script>
</head>

<body>

<div id="modal">
<div class="background"></div>
<div class="container"></div>
</div>

<div id="out">

<div id="head">
	<h1>${tableName}</h1>
</div>

	<div id="main">
		<div id="menu">
			<a href="/WebExcel/tableList">案件一覧へ</a> | <a href="#">XXXXX</a> | <a href="#">XXXXX</a>
		</div>
		<p style="margin-left:60px; font-size:12px;"><a href="http://localhost:8080/WebExcel/list/?wtableId=${wtableId}">すべて表示</a></p>
		<table class="kadai_tbl" width="${tableWidth}">
		<colgroup class="item_w30"></colgroup>
		<colgroup class="item_w40"></colgroup>
		<c:forEach var="record_itm" items="${itemList}" varStatus="status">
		<colgroup class="item_w${record_itm.width}"></colgroup>
		</c:forEach>
			
		<thead>
		<tr>
		<th style="background-color:#fff; border: 1px #c0c0c0 dashed;"></th>
		<th style="background-color:#fff; border: 1px #c0c0c0 dashed;"></th>
		<c:if test="${contCount > 0}">
		<c:forEach var="record_th" items="${itemList}" varStatus="status">
			<th class="sortAndFilter" style="background-color:#fff; border: 1px #c0c0c0 dashed;">
			<c:if test="${!empty record_th.masterBigId && record_th.masterBigId != 0}">
				<c:set var="balloonData" value=""></c:set>
				<c:forEach var="mstItem" items="${record_th.itemMstList}" varStatus="mst_status">
					<c:if test="${mst_status.index != 0}">
						<c:set var="balloonData" value="${balloonData}<br/>" />
					</c:if>
					<c:set var="balloonData" value="${balloonData}<a href='/WebExcel/list?wtableId=${wtableId}&itemMstBigId=${mstItem.bigId}&itemMstSmallId=${mstItem.smallId}'>${mstItem.name}</a>" />
				</c:forEach>
				<span class="filter"><img style="float:right; margin-left:5px;" src="/WebExcel/img/filter.png" title="${balloonData}" /></span>
			</c:if>
			<img style="float:right;" src="/WebExcel/img/s_up.png" onclick="alert('comming soon・・')"/>
			<img style="float:right; margin-right:3px;" src="/WebExcel/img/s_down.png"  onclick="alert('comming soon・・')"/>
			</th>
		</c:forEach>
		</c:if>
		</tr>
		
		<tr>
		<th>#</th>
		<th></th>		
		<c:forEach var="record_th" items="${itemList}" varStatus="status">
			<th class="item_w${record_th.width}"  style="padding: 10px 6px; table-layout: fixed; "/>
			${record_th.name}
			</th>
		</c:forEach>
		</tr>
		</thead>
		
		<tbody>
		
		<c:if test="${contCount > 0}">
		<c:forEach var="cicle" begin="1" end="${contCount}" varStatus="status_tr">
		<tr id="trId${status_tr.index}">
		<c:forEach var="record" items="${tableList}" begin="${itemCount*cicle-itemCount}" end="${itemCount*cicle-itemCount}" varStatus="status">
			<td>${record.id}</td>
			<td><a href="/WebExcel/editContent/selectForEdit?tableId=${wtableId}&contId=${record.id}" class="modal"><img src="/WebExcel/img/edit.png" /></a></td>
		</c:forEach>
		<c:forEach var="record" items="${tableList}" begin="${itemCount*cicle-itemCount}" end="${itemCount*cicle-1}" varStatus="status">
			<c:set var="isExist" value="0" />
			<c:if test="${!empty record.contSelValue}">
				<td style="width:${record.itemWidth}px; table-layout: fixed; text-align: center;">${record.contSelValue}</td>
				<c:set var="isExist" value="1" />
				<c:if test="${!empty record.rowColor}">
					<script>setRowColor('${status_tr.index}','${record.rowColor}');</script>
				</c:if>
			</c:if>
			<c:if test="${!empty record.content}">
				<td style="width:${record.itemWidth}px; table-layout: fixed;">${f:br(f:nbsp(f:h(record.content)))}</td>
				<c:set var="isExist" value="1" />
			</c:if>
			<c:if test="${!empty record.contDateStr}">
				<td style="width:${record.itemWidth}px; table-layout: fixed; text-align: center;">${record.contDateStr}</td>
				<c:set var="isExist" value="1" />
			</c:if>
			<c:if test="${isExist == '0'}">
				<td> </td>
			</c:if>
		</c:forEach>
		</c:forEach>
		</tr>
		</c:if>
			
		<tr>
			<td> </td>
			<td><a href="/WebExcel/editContent/selectForEdit?tableId=${wtableId}&contId=0" class="modal"><img src="/WebExcel/img/edit.png" /></a></td>
			<c:forEach var="record_itm" items="${itemList}" varStatus="status">
			<td class="item_w${record_itm.width}"> </td>
			</c:forEach>
		</tr>
		
		</tbody>
		</table>
	</div>
	
	<div id="footer">
		Copyright &copy; <a href="http://www.ken-first.com/">◎Ｘ△</a> All Rights Reserved.
	</div>
	
	<div class="clears"></div>
</div>

</body>
</html>