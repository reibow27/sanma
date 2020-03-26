package jp.co.ken.wexcel.dao;

import java.util.List;

import jp.co.ken.wexcel.entity.ItemMst;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.SqlFile;

@S2Dao(bean = ItemMst.class)
public interface ItemMstDao {
	
	@SqlFile
	@Arguments({ "bigId" })
	List<ItemMst> findData(int bigId);
	
	ItemMst findOneData(int smallId);
	
	void createData(List<ItemMst> itemMstList);
	
}
