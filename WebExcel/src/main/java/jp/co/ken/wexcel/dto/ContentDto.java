package jp.co.ken.wexcel.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentDto {
	
	private int wtableId;
	private int itemId;
	private int id;
	private String content;
	private String inputType;
	private int masterBigId;
	private int masterSmallId;
	private Date contDate;
	private String contDateStr;
	private Date createDate;
	private Date updateDate;

	private ItemDto itemDto;
	private int itemWidth;
	private String itemName;

	private String contSelValue;
	private String rowColor;
	
	public void setContDateStr(String contDateStr) {
		this.contDateStr = contDateStr;
	}
	public int getWtableId() {
		return wtableId;
	}
	public void setWtableId(int wtableId) {
		this.wtableId = wtableId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getMasterSmallId() {
		return masterSmallId;
	}
	public void setMasterSmallId(int masterSmallId) {
		this.masterSmallId = masterSmallId;
	}
	public Date getContDate() {
		return contDate;
	}
	public void setContDate(Date contDate) {
		this.contDate = contDate;
	}
	
	public String getContDateStr() {
		if(this.contDate == null){
			this.contDateStr = "";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.contDateStr = sdf.format(this.contDate);
		}
		return contDateStr;
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
	
	public ItemDto getItemDto() {
		return itemDto;
	}
	public void setItemDto(ItemDto itemDto) {
		this.itemDto = itemDto;
	}
	public int getItemWidth() {
		return itemWidth;
	}
	public void setItemWidth(int itemWidth) {
		this.itemWidth = itemWidth;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getContSelValue() {
		return contSelValue;
	}
	public void setContSelValue(String contSelValue) {
		this.contSelValue = contSelValue;
	}
	public String getRowColor() {
		return rowColor;
	}
	public void setRowColor(String rowColor) {
		this.rowColor = rowColor;
	}
}
