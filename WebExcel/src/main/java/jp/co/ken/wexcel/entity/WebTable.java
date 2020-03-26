package jp.co.ken.wexcel.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="WEB_TABLE_TBL")
public class WebTable {
	
	private int id;
	private String name;
	private String remarks;
	private int contSeq;
	private Date createDate;
	private Date updateDate;
	
	private List<Item> itemList = new ArrayList();
	
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getContSeq() {
		return contSeq;
	}
	public void setContSeq(int contSeq) {
		this.contSeq = contSeq;
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
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	

}
