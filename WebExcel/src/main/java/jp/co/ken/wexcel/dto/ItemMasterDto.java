package jp.co.ken.wexcel.dto;

import java.util.Date;


public class ItemMasterDto {
	
	private int bigId;
	private int smallId;
	private String name;
	private Date createDate;
	private Date updateDate;
	
	public int getBigId() {
		return bigId;
	}
	public void setBigId(int bigId) {
		this.bigId = bigId;
	}
	public int getSmallId() {
		return smallId;
	}
	public void setSmallId(int smallId) {
		this.smallId = smallId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
