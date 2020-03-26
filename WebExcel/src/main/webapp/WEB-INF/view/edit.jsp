<script language="JavaScript">
$(function(){
	// Datepicker
	$('.datepicker').datepicker({
		inline: true,
		dateFormat: "yy-mm-dd"
	});
	
	$('.imgBtn_edit').live('click',function(){
		var tableId = $('#h_tableId').attr('value');
		var contId = $('#h_contId').attr('value');
		var itmCount = $('#h_itmCount').attr('value');
		$.ajax({
			type:'POST',
			url: '/WebExcel/editContent/checkSeq',
			cache:false,
			data: {'tableId' : tableId, 'contId' : contId},
			dataType:'json',
			success: function(response) {
				// 一覧の該当データ更新処理
				var success = response['success'];
				if(success){
					for (var i=0 ; i<=itmCount ; i++){
						var itemId = $('#h_itemId' + i).attr('value');
						var itemMstBigId = $('#h_itemMstBigId' + i).attr('value');
						var itemType = $('#h_inptType' + i).attr('value');
						var contValue = $('#contValue' + i).attr('value');
						var seleValue = $('#seleValue' + i).val();
						$.ajax({
							type:'POST',
							url: '/WebExcel/editContent/edit',
							cache:false,
							data: {'tableId' : tableId, 'contId' : response['contSeqId'], 'itemId' : itemId, 'itemType' : itemType, 'itemMstBigId' : itemMstBigId, 'contValue' : contValue, 'seleValue' : seleValue},
							dataType:'json',
							success: function(response) {
								// 一覧の該当データ更新処理
								var success = response['success'];
								/*
								if(!success){
									alert('登録・更新処理に失敗しました');
								}else{
									alert('登録・更新処理が正常に完了しました');
								}
								*/
							},
							error: function(xhr,status,errorThrown) {
								alert('通信エラー：delete');
							}
						});
					}
				} else {
					alert('通信エラー：delete');
				}
				$.ajax({
					type:'POST',
					url: '/WebExcel/editContent/sendMail',
					cache:false,
					data: {'tableId' : tableId},
					dataType:'json',
					success: function(response) {
						var success = response['success'];
						if(!success){
							alert('メール送信処理中にエラー');
						}else{
							alert('メール送信成功');
						}
					},
					error: function(xhr,status,errorThrown) {
						alert('メール送信失敗');
					}
				});
				alert('登録・更新処理が正常に完了しました');
				
			},
			error: function(xhr,status,errorThrown) {
				alert('通信エラー：delete');
			}
		});
	});
	
	$('.imgBtn_del').live('click',function(){
		var tableId = $('#h_tableId').attr('value');
		var contId = $('#h_contId').attr('value');
		$.ajax({
			type:'POST',
			url: '/WebExcel/editContent/delete',
			cache:false,
			data: {'tableId' : tableId, 'contId' : contId},
			dataType:'json',
			success: function(response) {
				var success = response['success'];
				if(!success){
					alert('削除処理に失敗しました');
				}else{
					displayModal(false);
					window.location.reload();
				}
			},
			error: function(xhr,status,errorThrown) {
				alert('通信エラー：delete');
			}
		});
	});
});
</script>



<div id="out1">
<div id="head_pop">
	<h1><div class="h1pop">WebExcel</div></h1>
</div>
<div id="main_pop">
	<c:forEach var="content" items="${contentDtoList}" varStatus="status">
	<c:set var="cnt" value="${status.index}" />
		<div class="itemset">
		<div class="title">${content.itemDto.item.name}</div>
		<c:if test="${content.itemDto.item.inputType == 'date'}" >
			<input type="text" id="contValue${cnt}" class="datepicker" value="${content.contDateStr}"/>
		</c:if>
		<c:if test="${content.itemDto.item.inputType == 'text'}" >
			<input type="text" id="contValue${cnt}" size="30" value="${content.content}" />
		</c:if>
		<c:if test="${content.itemDto.item.inputType == 'textarea'}" >
			<textarea rows="5" cols="30" id="contValue${cnt}">${content.content}</textarea>
		</c:if>
		<c:if test="${content.itemDto.item.inputType == 'select'}" >
			<select id="seleValue${cnt}">
			<c:forEach var="itmMst" items="${content.itemDto.itmMasterList}">
			<option value="${itmMst.smallId}" <c:if test="${content.masterSmallId == itmMst.smallId}" >selected</c:if>>${itmMst.name}</option>
			</c:forEach>
			<select>
		</c:if>
		</div>
		<div class="clears"></div>
		<input type="hidden" id="h_itemId${cnt}" value="${content.itemDto.item.id}" />
		<input type="hidden" id="h_inptType${cnt}" value="${content.itemDto.item.inputType}" />
		<input type="hidden" id="h_itemMstBigId${cnt}" value="${content.itemDto.item.masterBigId}" />
	</c:forEach>
	<input type="hidden" id="h_itmCount" value="${cnt}" />
	<input type="hidden" id="h_tableId" value="${tableId}" />
	<input type="hidden" id="h_contId" value="${contId}" />
	<div class="itemset_btn">
	<c:if test="${contId != 0}" >
	<input type="button" class="imgBtn_del" value="削除">
	</c:if>
	<input type="button" class="imgBtn_edit" value="登録・更新">&nbsp;&nbsp;
	</div>
</div><!-- main -->

<div id="footer">
Copyright &copy; <a href="http://www.netmania.jp/">◎Ｘ△</a> All Rights Reserved.
</div><!-- footer -->
<div class="clears"></div>
</div><!-- out1 -->
