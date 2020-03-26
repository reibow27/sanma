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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jp.co.ken.wexcel.bean.JsonBean;
import jp.co.ken.wexcel.dto.ItemDto;
import jp.co.ken.wexcel.entity.Item;
import jp.co.ken.wexcel.entity.ItemMst;
import jp.co.ken.wexcel.entity.WebTable;
import jp.co.ken.wexcel.form.CreateTableForm;
import jp.co.ken.wexcel.form.TableListForm;
import jp.co.ken.wexcel.service.ItemService;
import jp.co.ken.wexcel.service.WebTableService;

import net.arnx.jsonic.JSON;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class CreateTableAction {
	
	@ActionForm
	@Resource
	protected CreateTableForm createTableForm;
	
	@Resource
	protected TableListForm tableListForm;
	
	@Resource
	protected WebTableService webTableService;
	
	@Resource
	protected ItemService itemService;
	
    @Resource
    protected HttpServletResponse response;
    
    private Logger logger = Logger.getLogger("webexcel");
	
    @Execute(validator = false)
	public String createTbl() throws IOException {
    	try{
    		int tableId = webTableService.createNewTable(createTableForm.tableName);
    		Iterator itr_itm = createTableForm.getItemDtoList().iterator();
    		while(itr_itm.hasNext()){
    			ItemDto idto = (ItemDto)itr_itm.next();
    			if(idto.getName() == null || "".equals(idto.getName().trim())){
    				itr_itm.remove();
    			} else {
        			logger.debug("itemDto:name:" + idto.getName());
        			logger.debug("itemDto:order:" + idto.getItemOrder());
        			logger.debug("itemDto:inputType:" + idto.getInputType());
        			logger.debug("itemDto:width:" + idto.getWidth());
        			idto.setWtableId(tableId);
        			Iterator itr_mst = idto.getItmMasterList().iterator();
        			while(itr_mst.hasNext()){
        				ItemMst imst = (ItemMst)itr_mst.next();
        				logger.debug("imst:name:" + imst.getName());
        				logger.debug("imst:colorChk:" + imst.getColorCheck());
        				logger.debug("imst:color:" + imst.getColor());
        				if(imst.getName() == null || "".equals(imst.getName().trim())){
        					itr_mst.remove();
        				}else{
            				if(!"1".equals(imst.getColorCheck())){
            					imst.setColor(null);
            				}
        				}
        			}
    			}
    		}
    		itemService.createItemList(createTableForm.itemDtoList);
    		
        	List<WebTable> webTableList = webTableService.getTableList();
        	createTableForm.webTableList = webTableList;
    		
    		return "/tableList.jsp";
    		
    	}catch(Exception e){
    		logger.error("",e);
    		return null;
    	}
	}

	private void outputToJsonForId(JsonBean jsonBean, int tableId, boolean b) throws IOException{
		jsonBean.setSuccess(b);
		jsonBean.setTableId(tableId);
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
