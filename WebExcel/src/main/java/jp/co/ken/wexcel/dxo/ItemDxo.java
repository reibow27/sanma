package jp.co.ken.wexcel.dxo;

import jp.co.ken.wexcel.dto.ItemDto;
import jp.co.ken.wexcel.entity.Item;

public interface ItemDxo {

	ItemDto toDto(Item entity);
	Item toEntity(ItemDto toDto);
}
