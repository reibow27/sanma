/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ken.wexcel.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.ken.wexcel.bean.JsonBean;
import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.dto.ItemDto;
import jp.co.ken.wexcel.entity.Content;
import jp.co.ken.wexcel.entity.Item;
import jp.co.ken.wexcel.entity.ItemMst;
import jp.co.ken.wexcel.form.EditContentForm;
import jp.co.ken.wexcel.service.ContentService;
import jp.co.ken.wexcel.service.ItemService;
import jp.co.ken.wexcel.service.MailService;
import jp.co.ken.wexcel.service.WebTableService;

import net.arnx.jsonic.JSON;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class EditContentAction {
	
	@ActionForm
	@Resource
	protected EditContentForm editContentForm;
	
    @Resource
    protected HttpServletRequest request;
    
	@Resource
	protected ItemService itemService;
	
	@Resource
	protected ContentService contentService;
	
	@Resource
	protected WebTableService webTableService;
	
	@Resource
	protected MailService mailService;
	
    @Resource
    protected HttpServletResponse response;
	
	private Logger logger = Logger.getLogger("webexcel");
	
    @Execute(validator = false)
	public String selectForEdit() {
    	
    	String tableIdStr = editContentForm.tableId;
    	int tableId = Integer.parseInt(tableIdStr);
    	logger.debug("param tableId :"+tableId);
    	
    	String contIdStr = editContentForm.contId;
    	int contId = Integer.parseInt(contIdStr);
    	logger.debug("param contId :"+contId);
		
    	// 新規作成
    	if(contId == 0){
    		List<ContentDto> cntDtoList = getItemInfo(tableId, contId, true);
    		editContentForm.contentDtoList = cntDtoList;
    	// 既存更新
    	} else {
    		List<ContentDto> cntDtoList = getItemInfo(tableId, contId, false);
    		editContentForm.contentDtoList = cntDtoList;
    	}
    	
        return "/edit.jsp";
	}

    
	private List<ContentDto> getItemInfo(int tableId, int contId, boolean isNew) {
		// 項目情報取得
		List<Item> itemList = itemService.getItems(tableId);
		// リスト系の項目である場合リスト値を取得しコンテンツ情報に設定して返却
		List<ContentDto> contDtoList = new ArrayList();
		for(Item itm : itemList){
			ItemDto itmDto = new ItemDto();
			itmDto.setItem(itm);
			if(itm.getMasterBigId() != 0 && !("".equals(itm))){
				List<ItemMst> itmMasterList = 
						itemService.getItemMasterList(itm.getMasterBigId());
				itmDto.setItmMasterList(itmMasterList);
			}
			ContentDto cntDto = makeContentDto(tableId, itmDto.getItem().getId(), contId, isNew);
			cntDto.setItemDto(itmDto);
			contDtoList.add(cntDto);
		}
		return contDtoList;
	}

    private ContentDto makeContentDto(int tableId, int itemId, int contId, boolean isNew) {
    	logger.info("新規？：" + isNew);
		if(isNew){
			return new ContentDto();
		}
		return contentService.getContent(tableId, itemId, contId);
	}

    @Execute(validator = false)
    public String checkSeq() throws IOException{
    	JsonBean jsonBean = new JsonBean();
    	try{
			String wtableIdStr = editContentForm.tableId;
			String contIdStr = editContentForm.contId;
			List<Content> contList = contentService.getContent(Integer.parseInt(wtableIdStr), Integer.parseInt(contIdStr));

			int contSeq=0;
			if(contList == null || contList.size() == 0){
				// コンテンツ自体新規
				contSeq = webTableService.countUpContSeq(Integer.parseInt(wtableIdStr));
				logger.info("新規コンテンツNoは" + contSeq);
			}else{
				contSeq = Integer.parseInt(contIdStr);
			}
			outputToJsonForSeq(jsonBean, contSeq, true);
			return null;
    	}catch(Exception e){
    		outputToJson(jsonBean, false);
    		logger.error("",e);
    		return null;
    	}
    }

	@Execute(validator = false)
	/* AJAXで呼ばれJSONで返す */
	public String edit() throws IOException, MessagingException {
		logger.info("edit開始します");
    	JsonBean jsonBean = new JsonBean();
    	try{
    		editContent(jsonBean);
    	}catch(Exception e){
    		outputToJson(jsonBean, false);
    		return null;
    	}
    	outputToJson(jsonBean, true);
    	return null;
	}
	
	@Execute(validator = false)
	/* AJAXで呼ばれJSONで返す */
	public String sendMail() throws MessagingException, IOException {
		logger.info("edit開始します");
    	JsonBean jsonBean = new JsonBean();
    	try{
    		mailService.sendMail();
    	}catch(Exception e){
    		outputToJson(jsonBean, false);
    		return null;
    	}
    	outputToJson(jsonBean, true);
    	return null;
	}

	private synchronized void editContent(JsonBean jsonBean) throws Exception {
		try{
			String wtableIdStr = editContentForm.tableId;
			String contIdStr = editContentForm.contId;
			String itemIdStr = editContentForm.itemId;
			logger.debug("---" + wtableIdStr);
			logger.debug("---" + contIdStr);
			ContentDto contDtoByItem = contentService.getContent(Integer.parseInt(wtableIdStr), Integer.parseInt(itemIdStr), Integer.parseInt(contIdStr));
			ContentDto contNewDto = createNewContentDtoByItem();
			if(contDtoByItem == null){
				// 項目に対するレコードがない場合
				contentService.createContent(contNewDto);
			} else {
				// 既存の値を更新
				contentService.updateContent(contNewDto);
			}
		}catch(Exception e){
			outputToJson(jsonBean, false);
			logger.error("",e);
			throw e;
		}
	}
	
	private ContentDto createNewContentDto() throws ParseException{
		ContentDto contNewDto  = new ContentDto();
		contNewDto.setWtableId(Integer.parseInt(editContentForm.tableId));
		contNewDto.setItemId(Integer.parseInt(editContentForm.itemId));	
		if(editContentForm.itemType.equals("select")){
			logger.info("--select--");
			contNewDto.setInputType("select");
			contNewDto.setMasterBigId(Integer.parseInt(editContentForm.itemMstBigId));
			contNewDto.setMasterSmallId(Integer.parseInt(editContentForm.seleValue));
		} else if(editContentForm.itemType.equals("date")){
			contNewDto.setInputType("date");
			if("".equals((editContentForm.contValue).trim())){
				contNewDto.setContDate(null);
			}else{
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format.parse(editContentForm.contValue); 
				contNewDto.setContDate(date);
			}
		} else if(editContentForm.itemType.equals("text")){
			logger.info("--text--");
			contNewDto.setInputType("text");
			contNewDto.setContent(editContentForm.contValue);
		} else if(editContentForm.itemType.equals("textarea")){
			logger.info("--textarea--");
			contNewDto.setInputType("textarea");
			contNewDto.setContent(editContentForm.contValue);
		}
		return contNewDto;
	}

	private ContentDto createNewContentDto(int contSeq) throws ParseException {
		ContentDto contNewDto  = createNewContentDto();
		contNewDto.setId(contSeq);
		return contNewDto;
	}
	
	private ContentDto createNewContentDtoByItem() throws ParseException{
		ContentDto contNewDto  = createNewContentDto();
		contNewDto.setId(Integer.parseInt(editContentForm.contId));
		return contNewDto;	
	}

	
	@Execute(validator = false)
	/* AJAXで呼ばれJSONで返す */
	public String delete() throws IOException {
    	JsonBean jsonBean = new JsonBean();
    	try{
    		String wtableIdStr = editContentForm.tableId;
    		String contIdStr = editContentForm.contId;
    		List<Content> contList = contentService.getContent(Integer.parseInt(wtableIdStr), Integer.parseInt(contIdStr));
    		if(contList != null && contList.size() != 0){
        		logger.info("wtableId:" + wtableIdStr + " contId:" + contIdStr + "のデータを削除");
    			contentService.deleteContentByIds(Integer.parseInt(wtableIdStr), Integer.parseInt(contIdStr));
    		}
    	}catch(Exception e){
	    	outputToJson(jsonBean, false);
	    	return null;
	    }
    	outputToJson(jsonBean, true);
    	return null;
	}
	
	private void outputToJsonForSeq(JsonBean jsonBean, int contSeqId, boolean b) throws IOException{
		jsonBean.setSuccess(b);
		jsonBean.setContSeqId(contSeqId);
		outJson(jsonBean);
	}
	
    private void outputToJson(JsonBean jsonBean, boolean b) throws IOException{
    	jsonBean.setSuccess(b);
    	outJson(jsonBean);
    }

	private void outJson(JsonBean jsonBean) throws IOException {
		String encoded = JSON.encode(jsonBean);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter pw = new PrintWriter(response.getWriter());
        pw.write(encoded);
	}
}
