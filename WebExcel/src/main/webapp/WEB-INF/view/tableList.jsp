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
<link rel="stylesheet" href="/WebExcel/spectrum.css" type="text/css" />
<script type="text/javascript" src="/WebExcel/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/WebExcel/js/jquery-ui-1.8.24.custom.min.js"></script>
<script type="text/javascript" src="/WebExcel/js/jquery.ui.datepicker-ja.js"></script>
<script type="text/javascript" src="/WebExcel/js/spectrum.js"></script>

<script type="text/javascript">

	$(document).ready(function() {

		$(".showPalette").spectrum({
		    showPalette: true,
		    color: '#d1cfd5',
		    palette: [
		        ['black', 'white', 'blanchedalmond'],
		        ['rgb(255, 128, 0);', 'hsv 100 70 50', 'lightyellow']
		    ]
		});
	});
	function tblReg_show(){
		document.getElementById("tblRegiArea_inner").style.display="inline";
	}
		
	function tblReg_hide(){
		document.getElementById("tblRegiArea_inner").style.display="none";
	}
	
	function doSelectChange(targetNo){
		var itemType = $('#seleType' + targetNo + " option:selected").text();
		if(itemType == "select"){
			tblReg_sele_show(targetNo);
		}else{
			tblReg_sele_hide(targetNo);
		}
	}
	function tblReg_sele_show(targetNo){
		document.getElementById("tblRegiArea_sele_inner" + targetNo).style.display="inline";
	}
		
	function tblReg_sele_hide(targetNo){
		document.getElementById("tblRegiArea_sele_inner" + targetNo).style.display="none";
	}
	
	function prjEdit_show(targetNo){
		document.getElementById("tblEditArea_inner" + targetNo).style.display="inline";
	}
		
	function prjEdit_hide(targetNo){
		document.getElementById("tblEditArea_inner" + targetNo).style.display="none";
	}
	


</script>	
</head>
<body>

<div id="out_tableList">

<div id="head">
	<h1>WebExcel一覧</h1>
</div>
<div id="main">
	<div id="category">新規作成</div>
	<div id="cate_img">
		<a href="#"><img src="/WebExcel/img/roll_close.png" onclick="tblReg_hide();" /></a>
		<a href="#"><img src="/WebExcel/img/roll_open.png" onclick="tblReg_show();" /></a>
	</div>
	<div class="clears"></div>
	<br/>
	<div id="tblRegiArea">
	<div id="tblRegiArea_inner" style="display:none;">
	<s:form action="/createTable/createTbl" method="post" >
		<div class="item_t">表名：</div>
		<div class="item"><html:text property="tableName" styleId="tableName" size="60" maxlength="255" /></div>
		<div class="clears"></div>
		<div class="item_t">------</div>
		<div class="clears"></div>
		<logic:iterate id="itemDtoList" name="createTableForm" property="itemDtoList" indexId="idx" length="15">
			<div class="item_small_t">項目名：</div>
			<div class="item_small"><html:text name="itemDtoList" property="name" indexed="true" size="30" maxlength="50" /></div>
			<div class="item_small_t">並び順：</div>
			<div class="item_small"><html:text name="itemDtoList" property="itemOrder" indexed="true" size="5" maxlength="5" /></div>
			<div class="item_small_t">入力TYPE：</div>
			<div class="item_small">
			<html:select name="itemDtoList" property="inputType" indexed="true" styleId="seleType${idx}" onchange="doSelectChange(${idx});">
			<html:option value="text">text</html:option>
			<html:option value="textarea">textarea</html:option>
			<html:option value="select">select</html:option>
			<html:option value="date">date</html:option>
			</html:select>
			</div>
			<div class="item_small_t">幅：</div>
			<div class="item_small">
			<html:select name="itemDtoList" indexed="true" property="width">
			<html:option value="10">10</html:option>
			<html:option value="50">50</html:option>
			<html:option value="70">70</html:option>
			<html:option value="150">150</html:option>
			<html:option value="300">300</html:option>
			</html:select>
			</div>
			<div class="clears"></div>
			<div id="tblRegiArea_sele_inner${idx}"  style="display:none;">
			<br/>
				<%-- <logic:iterate id="itmMasterListForCreate" name="itemDtoList" property="itmMasterListForCreate" indexId="idx_mst" length="7">--%>
				<c:forEach var="i" begin="0" end="7" step="1">
				<div class="item_small_t_sele">選択項目${i}：</div>
				<div class="item_small"><input type="text" name="itemDtoList[${idx}].itmMasterList[${i}].name" size="5" maxlength="5" /></div>
				<div class="item_small_t"><input type="checkbox" name="itemDtoList[${idx}].itmMasterList[${i}].colorCheck" id="colorCheck${idx_mst}" value="1" />選択時に行の色を変える：</div>
				<div class="item_small">
				<input type="text" name="itemDtoList[${idx}].itmMasterList[${i}].color" id="colorOnSelect${i}" class="showPalette" value="#efefef" />
				</div>
				<div class="clears"></div>
				</c:forEach>
				<%-- </logic:iterate>--%>
			<br/>
			</div>
		</logic:iterate>
		<html:submit styleClass="sbmtbtn" styleId="submitNew" value="新規作成" />
	</s:form>
	</div>
	</div>
	
	<div class="clears"></div>
	
	<br/>


	<c:forEach var="tableInfo" items="${webTableList}" varStatus="status">
	<c:set var="i_table" value="${status.index}" />
	<div id="category"> <a href="/WebExcel/list?wtableId=${tableInfo.id}">${tableInfo.name}</a></div>
	<div id="cate_img">
		<a href="#"><img src="/WebExcel/img/roll_close.png" onclick="prjEdit_hide(${i_table});" /></a>
		<a href="#"><img src="/WebExcel/img/roll_open.png" onclick="prjEdit_show(${i_table});" /></a>
	</div>
	
	<div class="clears"></div>
	</c:forEach>
	
<%--
	<div id="tblRegiArea">
	<div id="tblEditArea_inner${i_table}" style="display:none;">
	<s:form action="/tableList/editTable" method="post" >
		<c:forEach var="itemList" items="${tableInfo.itemList}" varStatus="status_item">
		<logic:iterate id="itemList" name="tableListForm" property="webTableList.itemList" indexId="idx">
			<div class="item_small_t">項目名：</div>
			<div class="item_small"><html:text name="itemList.name" size="30" maxlength="50" property=""></html:text></div>
			<div class="item_small_t">並び順：</div>
			<div class="item_small"><html:text name="itemList.itemOrder" type="text" size="5" maxlength="5" property=""></html:text></div>
			<div class="item_small_t">入力TYPE：</div>
			<div class="item_small">
			<c:if test=${itemList.inputType == 'text'} >
			<select><option selected>text</option><option>textarea</option></select>
			</c:if>
			<c:if test=${itemList.inputType == "textarea"ß} >
			<select><option>text</option><option selected>textarea</option></select>
			</c:if>
			<c:if test=${itemList.inputType == "select"}>select</c:if>
			<c:if test=${itemList.inputType == "date"}>date</c:if>
			</div>
			<div class="item_small_t">幅：</div>
			<div class="item_small">
				<html:select name="itemList" property="width">
				<html:option value="10" />
				<html:option value="20" />
				<html:option value="50" />
				<html:option value="60" />
				<html:option value="70" />
				<html:option value="150" />
				<html:option value="300" />
				</html:select>
			</div>
			<div class="clears"></div>
			<div id="tblEditArea_sele_inner${status}${idx}"  style="display:none;">
			<br/>
				 <logic:iterate id="itmMasterListForCreate" name="itemDtoList" property="itmMasterListForCreate" indexId="idx_mst" length="7">--%>
				<%--
				<%-- <c:forEach var="i" begin="0" end="7" step="1">--%>
<%--				
				<logic:iterate id="itemMstList" name="itemList" property="itemMstList" indexId="idx_itmmst">
				<div class="item_small_t_sele">選択項目${idx_itmmst}：</div>
				<div class="item_small"><input type="text" name="itemMstList.name" size="5" maxlength="5" /></div>
				<div class="item_small_t"><input type="checkbox" name="itemMstList.colorCheck" id="colorCheck${idx_itmmst}" value="1" />選択時に行の色を変える：</div>
				<div class="item_small">
				<input type="text" name="itemMstList.color" id="colorOnSelect${idx_itmmst}" class="showPalette" value="" />
				</div>
				<div class="clears"></div>
--%>
				<%-- </c:forEach>--%>
<%--
				</logic:iterate>
			<br/>
			</div>
		</logic:iterate>
		</c:forEach>
		<input type="button" name="OK" class="sbmtbtn" onclick="alert('comming soon・・');" value="更新"/>
	</s:form>
	</div>
	</div>
	</c:forEach>
--%>
	<div class="prjList" id="prjList">
		<!--
		<div class="prjSet">
			<div class="prjPerson"><img src="./img/prjlist_person.png" /></div>
			<div class="prjName">プロジェクト名プロジェクトプロジェクトプロ</div>
			<div id="menu_reg">
				<a href="xxxx?prjID=X">見積</a> | <a href="xxxx?prjID=X">XXX</a>
			</div>
		</div>
		-->
	</div>

	<div class="clears"></div>
</div><!-- main -->

<div id="footer">
Copyright &copy; <a href="http://www.ken-first.com/">◎Ｘ△</a> All Rights Reserved.
</div><!-- footer -->

<div class="clears"></div>

</div><!-- out -->
</body>
</html>
