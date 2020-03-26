package jp.co.ken.wexcel.dao;

import java.util.List;

import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.entity.Item;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.dao.annotation.tiger.SqlFile;

@S2Dao(bean = Item.class)
public interface ItemDao {
	
	void createData(Item item);
	
	@SqlFile
	@Arguments({ "wtableId" })
	List<Item> findData(int wtableId);
	
	@SqlFile
	@Arguments({ "wtableId" })
	int findCountOfData(int wtableId);
	
	@Sql("SELECT LAST_INSERT_ID();")
	int selectLasInsertSeq();
	
}
