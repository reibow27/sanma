package jp.co.ken.wexcel.entity;

import java.util.Date;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="ITEM_MST")
public class ItemMst {
	
	private int bigId;
	private int smallId;
	private String name;
	private String color;
	private String colorCheck;
	private Date createDate;
	private Date updateDate;
	
	private boolean isSelect;
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
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
	public String getColorCheck() {
		return colorCheck;
	}
	public void setColorCheck(String colorCheck) {
		this.colorCheck = colorCheck;
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
	public boolean isSelect() {
		return isSelect;
	}
	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
}
