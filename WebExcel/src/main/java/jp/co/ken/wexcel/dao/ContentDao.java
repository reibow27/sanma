package jp.co.ken.wexcel.dao;

import java.util.List;

import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.entity.Content;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.SqlFile;

@S2Dao(bean = Content.class)
public interface ContentDao {
	
	@SqlFile
	@Arguments({ "wtableId", "id" })
	List<Content> findData(int wtableId, int id);
	
	@SqlFile
	@Arguments({ "wtableId" })
	List<ContentDto> findDataByTableId(int wtableId);
	
	@SqlFile
	@Arguments({ "wtableId", "id" })
	List<ContentDto> findDtoData(int wtableId, int id);
	
	@SqlFile
	@Arguments({ "wtableId", "itemId", "id" })
	Content findOneData(int wtableId, int itemId, int id);
	
	@SqlFile
	@Arguments({ "wtableId", "masterBigId", "masterSmallId" })
	List<Content> findFilteredContId(int wtableId, int masterBigId, int masterSmallId);
	
	int modifyData(Content content);
	
	int createData(Content content);
	
	int removeData(Content content);
	
	@SqlFile
	@Arguments({ "wtableId", "id" })
	int removeDataByIds(int wtableId, int id);
}
