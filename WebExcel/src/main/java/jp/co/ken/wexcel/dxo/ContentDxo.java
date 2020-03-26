package jp.co.ken.wexcel.dxo;

import jp.co.ken.wexcel.dto.ContentDto;
import jp.co.ken.wexcel.entity.Content;

public interface ContentDxo {

	ContentDto toDto(Content entity);
	Content toEntity(ContentDto toDto);
}
