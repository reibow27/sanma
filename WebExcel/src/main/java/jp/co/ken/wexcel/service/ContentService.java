package jp.co.ken.wexcel.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jp.co.ken.wexcel.dao.ContentDao;
import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.dxo.ContentDxo;
import jp.co.ken.wexcel.entity.Content;

public class ContentService {
	
	private ContentDao contentDao;
	private ContentDxo contentDxo;
	
	private Logger logger = Logger.getLogger("webexcel");
	
	public List<ContentDto> getContent(int wtableId){
		return contentDao.findDataByTableId(wtableId);
	}
	
	public List<Content> getContent(int wtableId, int contId){
		return contentDao.findData(wtableId, contId);
	}
	
	public ContentDto getContent(int wtableId, int itemId, int contId){
		Content content = contentDao.findOneData(wtableId, itemId, contId);
		return contentDxo.toDto(content);
	}

	public List<ContentDto> getFilteredContent(int wtableId, int mstBigId, int mstSmallId){
		List<Content> contList = contentDao.findFilteredContId(wtableId, mstBigId, mstSmallId);
		List<ContentDto> contDtoList = new ArrayList<ContentDto>();
		for(Content cont : contList){
			contDtoList.addAll(contentDao.findDtoData(wtableId, cont.getId()));
		}
		return contDtoList;
	}
		
	public void updateContent(ContentDto contDto){
		Content content = contentDxo.toEntity(contDto);
		contentDao.modifyData(content);
	}
	
	public void createContent(ContentDto contDto){
		Content content = contentDxo.toEntity(contDto);
		contentDao.createData(content);
	}
	
	public void deleteContentByIds(int wtableId, int id){
		contentDao.removeDataByIds(wtableId, id);
	}
	
	public int calcTableWidth(List<ContentDto> tableList){
		int tableWidth = 40;
		int contId = 0;
		int contIdOld = 0;
		for(ContentDto conDto : tableList){
			contId = conDto.getId();
			if(contIdOld != 0){
				if(contIdOld != contId){
					break;
				}
			}
			tableWidth = tableWidth + conDto.getItemWidth();
			contIdOld = contId;
		}
		return tableWidth;
	}
	
	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}
	public void setContentDxo(ContentDxo contentDxo) {
		this.contentDxo = contentDxo;
	}
}
