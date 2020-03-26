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

import java.util.List;

import javax.annotation.Resource;

import jp.co.ken.wexcel.entity.Item;
import jp.co.ken.wexcel.entity.ItemMst;
import jp.co.ken.wexcel.entity.WebTable;
import jp.co.ken.wexcel.form.TableListForm;
import jp.co.ken.wexcel.service.ItemService;
import jp.co.ken.wexcel.service.WebTableService;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class TableListAction {
	
	@ActionForm
	@Resource
	protected TableListForm tableListForm;
	
	@Resource
	protected WebTableService webTableService;
	
	@Resource
	protected ItemService itemService;
	
    @Execute(validator = false)
	public String index() {
    	List<WebTable> webTableList = webTableService.getTableList();
    	for(WebTable webTable:webTableList){
	    	List<Item> itemList = itemService.getItems(webTable.getId());
	    	for(Item item : itemList){
	    		if(item.getMasterBigId() == 0){
	    			continue;
	    		}
	    		List<ItemMst> itemMstList = itemService.getItemMasterList(item.getMasterBigId());
	    		item.setItemMstList(itemMstList);
	    	}
	    	webTable.setItemList(itemList);
    	}
    	//itemService.getItems(tableId)
    	tableListForm.webTableList = webTableList;
        return "/tableList.jsp";
	}
    @Execute(validator = false)
	public String edit() {
        return "/edit.jsp";
	}
}
