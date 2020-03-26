package jp.co.ken.wexcel.entity;

import java.util.Date;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="MST_SEQ_TBL")
public class MstSeq {
	
	private int id;
	private int mstSeq;
	private Date createDate;
	private Date updateDate;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMstSeq() {
		return mstSeq;
	}
	public void setMstSeq(int mstSeq) {
		this.mstSeq = mstSeq;
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
