package jp.co.ken.wexcel.dao;

import java.util.List;

import jp.co.ken.wexcel.entity.WebTable;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.dao.annotation.tiger.SqlFile;

@S2Dao(bean = WebTable.class)
public interface WebTableDao {
	
	@Query("ORDER BY ID")
	List<WebTable> findAllData();
	
	@SqlFile
	@Arguments({ "id" })
	int modifyContSeq(int id);
	
	@Sql("SELECT LAST_INSERT_ID();")
	int selectLasInsertSeq();
	
	@SqlFile
	@Arguments({ "id" })
	WebTable findData(int id);
	
	void createData(WebTable table);
}
