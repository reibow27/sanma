package jp.co.ken.wexcel.service;

import java.util.List;

import org.apache.log4j.Logger;

import jp.co.ken.wexcel.dao.ItemDao;
import jp.co.ken.wexcel.dao.ItemMstDao;
import jp.co.ken.wexcel.dao.MstSeqDao;
import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.dto.ItemDto;
import jp.co.ken.wexcel.dxo.ItemDxo;
import jp.co.ken.wexcel.entity.Item;
import jp.co.ken.wexcel.entity.ItemMst;

public class ItemService {

	private ItemDao itemDao;
	private ItemMstDao itemMstDao;
	private MstSeqDao mstSeqDao;
	private ItemDxo itemDxo;
	
	private Logger logger = Logger.getLogger("webexcel");
	
	public void createItemList(List<ItemDto> itemDtoList){
		for(ItemDto itemDto : itemDtoList){
			Item item = itemDxo.toEntity(itemDto);
			List<ItemMst> itemMstList = itemDto.getItmMasterList();
			createData(item, itemMstList);
		}
	}
	
	public void createData(Item item, List<ItemMst> itemMstList){
		if(itemMstList != null && itemMstList.size() > 0){
			mstSeqDao.modifySeq(1);
			int newSeq = mstSeqDao.selectLasInsertSeq();
			for(ItemMst itemMst : itemMstList){
				itemMst.setBigId(newSeq);
			}
			item.setMasterBigId(newSeq);
		}
		itemMstDao.createData(itemMstList);
		itemDao.createData(item);
	}
	
	public List<Item> getItems(int tableId) {
		return itemDao.findData(tableId);
	}
	
	public int getCount(int tableId) {
		return itemDao.findCountOfData(tableId);
	}
	
	public List<ItemMst> getItemMasterList(int bigId) {
		logger.debug("bigId : " + bigId);
		return itemMstDao.findData(bigId);
	}
	
	public int calcTableWidth(List<Item> itemList){
		int tableWidth = 70;
		for(Item itm : itemList){
			tableWidth = tableWidth + itm.getWidth();
			logger.debug("テーブル幅にプラス：" + itm.getWidth());
		}
		return tableWidth;
	}

	
	public void setItemDao(ItemDao dao) {
		this.itemDao = dao;
	}
	
    public void setItemMstDao(ItemMstDao dao) {
        this.itemMstDao = dao;
    }
	
    public void setMstSeqDao(MstSeqDao dao) {
        this.mstSeqDao = dao;
    }
    
	public void setItemDxo(ItemDxo itemDxo) {
		this.itemDxo = itemDxo;
	}
}
