package jp.co.ken.wexcel.dto;

import java.util.Date;


public class WebTableDto {
	
	private int id;
	private String name;
	private String remarks;
	private int contSeq;
	private Date createDate;
	private Date updateDate;
	
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
	
	
}
