package jp.co.ken.wexcel.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import jp.co.ken.wexcel.entity.Item;
import jp.co.ken.wexcel.entity.ItemMst;


public class ItemDto {
	
	private int wtableId;
	private int id;
	private String name;
	private int itemOrder;
	private String inputType;
	private int masterBigId;
	private int width;
	private Date createDate;
	private Date updateDate;
	
	private Item item;
	private List<ItemMst> itmMasterList = new ArrayList();
	
	private Logger logger = Logger.getLogger("webexcel");
	
	public int getWtableId() {
		return wtableId;
	}
	public void setWtableId(int wtableId) {
		this.wtableId = wtableId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemOrder() {
		return itemOrder;
	}
	public void setItemOrder(int itemOrder) {
		this.itemOrder = itemOrder;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public int getMasterBigId() {
		return masterBigId;
	}
	public void setMasterBigId(int masterBigId) {
		this.masterBigId = masterBigId;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<ItemMst> getItmMasterList() {
		return itmMasterList;
	}
	public void setItmMasterList(List<ItemMst> itmMasterList) {
		this.itmMasterList = itmMasterList;
	}
}
