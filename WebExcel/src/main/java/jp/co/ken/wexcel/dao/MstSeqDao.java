package jp.co.ken.wexcel.dao;

import jp.co.ken.wexcel.entity.MstSeq;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.Sql;
import org.seasar.dao.annotation.tiger.SqlFile;

@S2Dao(bean = MstSeq.class)
public interface MstSeqDao {
	
	@SqlFile
	@Arguments({ "id" })
	void modifySeq(int id);
	
	@Sql("SELECT LAST_INSERT_ID();")
	int selectLasInsertSeq();
	
}
