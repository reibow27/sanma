package jp.co.ken.wexcel.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jp.co.ken.wexcel.dto.ItemDto;
import jp.co.ken.wexcel.entity.ItemMst;
import jp.co.ken.wexcel.entity.WebTable;


public class CreateTableForm {
	
	public int tableId;
	public String tableName;
	public List<ItemDto> itemDtoList;
	public List<WebTable> webTableList;	
	private Logger logger = Logger.getLogger("webexcel");
		
	public int getTableId() {
		return tableId;
	}
	
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public List<ItemDto> getItemDtoList() {
		if(itemDtoList == null){
			logger.debug("Listがnullなので初期化");
			itemDtoList = new ArrayList();
			for(int i=0; i<15; i++){
				ItemDto itemDto = new ItemDto();
				itemDtoList.add(itemDto);
			}
		}
		return itemDtoList;
	}
	
	public void setItemDtoList(List<ItemDto> itemDtoList) {
		this.itemDtoList = itemDtoList;
	}

}
