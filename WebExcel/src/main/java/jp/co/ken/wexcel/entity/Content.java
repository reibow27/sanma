package jp.co.ken.wexcel.entity;

import java.util.Date;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="CONTENT_TBL")
public class Content {
	
	private int wtableId;
	private int itemId;
	private int id;
	private String content;
	private String inputType;
	private int masterBigId;
	private int masterSmallId;
	private Date contDate;
	private Date createDate;
	private Date updateDate;
	
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

	
	
}
