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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.ken.wexcel.bean.JsonBean;
import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.dto.ItemDto;
import jp.co.ken.wexcel.entity.Content;
import jp.co.ken.wexcel.entity.Item;
import jp.co.ken.wexcel.entity.ItemMst;
import jp.co.ken.wexcel.entity.WebTable;
import jp.co.ken.wexcel.form.ListForm;
import jp.co.ken.wexcel.service.ContentService;
import jp.co.ken.wexcel.service.ItemService;
import jp.co.ken.wexcel.service.WebTableService;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class ListAction {
	
	@ActionForm
	@Resource
	protected ListForm listForm;
	
	@Resource
	protected WebTableService webTableService;
    
	@Resource
	protected ItemService itemService;
	
	@Resource
	protected ContentService contentService;
	
	private Logger logger = Logger.getLogger("webexcel");
	
    @Execute(validator = false)
	public String index() {
    	List<Item> itemList = itemService.getItems(listForm.wtableId);
    	for(Item item : itemList){
    		if(item.getMasterBigId() == 0){
    			continue;
    		}
    		List<ItemMst> itemMstList = itemService.getItemMasterList(item.getMasterBigId());
    		item.setItemMstList(itemMstList);
    	}
    	int itemCount = itemService.getCount(listForm.wtableId);
    	int tableWidth = itemService.calcTableWidth(itemList);
    	
    	List<ContentDto> tableList = new ArrayList<ContentDto>();
    	if(listForm.itemMstBigId == 0){
    		tableList = contentService.getContent(listForm.wtableId);
    	}else{
    		tableList = contentService.getFilteredContent(listForm.wtableId, listForm.itemMstBigId, listForm.itemMstSmallId);
    	}
    	//int tableWidth = contentService.calcTableWidth(tableList);
    	int contCount = tableList.size();
    	
    	WebTable webTable =  webTableService.getTableInfo(listForm.wtableId);
    	String tableName = webTable.getName();
    	
    	listForm.itemList = itemList;
    	listForm.tableList = tableList;
    	listForm.itemCount = itemCount;
    	listForm.contCount = contCount;
    	listForm.tableName = tableName;
    	listForm.tableWidth = tableWidth;
        return "/hyou.jsp";
	}
    
    // 今使ってない
    @Execute(validator = false)
    public String listItemMst(){
    	List<ItemMst> itmMstList = itemService.getItemMasterList(listForm.itemMstBigId);
    	listForm.itemMstList = itmMstList;
    	logger.debug("吹き出し用Itemlist：" + itmMstList.size());
    	return "/itemMstFilter.jsp";
    }
    
    
}
