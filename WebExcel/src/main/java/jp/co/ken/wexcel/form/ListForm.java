package jp.co.ken.wexcel.form;

import java.util.List;

import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.entity.Item;
import jp.co.ken.wexcel.entity.ItemMst;



public class ListForm {
	
	public int wtableId;
	public List<Item> itemList;
	public List<ContentDto> tableList;	
	public int itemCount;
	public int contCount;
	public String tableName;
	public int tableWidth;
	
	public int itemMstBigId;
	public int itemMstSmallId;
	
	public List<ItemMst> itemMstList;
}
