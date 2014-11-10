package br.com.wrigg.dnd.hitAndDamageCalculator.item;

import br.com.wrigg.dnd.hitAndDamage.Item.Item;
import br.com.wrigg.dnd.hitAndDamage.Item.ItemCatalog;

public class ItemCataloguer {

	public Item findItem(Item itemDTO) throws ItemNotFoundException {
		ItemCatalog itemCatalog = new ItemCatalog();
		if(itemDTO != null) {
			Item itemFound = itemCatalog.findItemById(itemDTO.getId());
			if(itemFound != null)
				return itemFound;
		}
		throw new ItemNotFoundException();
	}

}
