package jp.co.ken.wexcel.service;

import java.util.List;

import jp.co.ken.wexcel.dao.WebTableDao;
import jp.co.ken.wexcel.entity.WebTable;

public class WebTableService {
	
	private WebTableDao webTableDao;
	
	public int countUpContSeq(int wtableId){
		webTableDao.modifyContSeq(wtableId);
		return webTableDao.selectLasInsertSeq();
	}
	
	public int createNewTable(String tableName){
		WebTable webTable = new WebTable();
		webTable.setName(tableName);
		webTableDao.createData(webTable);
		return webTableDao.selectLasInsertSeq();
	}
	
	public WebTable getTableInfo(int wtableId){
		return webTableDao.findData(wtableId);
	}
	
	public List<WebTable> getTableList(){
		return webTableDao.findAllData();
	}
	
	public void setWebTableDao(WebTableDao webTableDao) {
		this.webTableDao = webTableDao;
	}
}
