package br.com.wrigg.dnd.hitAndDamageCalculator.item;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;

import br.com.wrigg.dnd.hitAndDamage.Item.Item;

public class ItemEditor extends PropertyEditorSupport {
	
	private static final Logger logger = Logger.getLogger(ItemEditor.class); 
	 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		logger.debug("ItemEditor called whith text [" + text + "]");
		Item item = new Item();
		item.setId(text);
        this.setValue(item);
	}
 
}
