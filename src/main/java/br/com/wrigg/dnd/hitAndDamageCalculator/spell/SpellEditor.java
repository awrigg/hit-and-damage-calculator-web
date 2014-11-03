package br.com.wrigg.dnd.hitAndDamageCalculator.spell;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;

import br.com.wrigg.dnd.hitAndDamage.spell.Spell;

public class SpellEditor extends PropertyEditorSupport {
	
	private static final Logger logger = Logger.getLogger(SpellEditor.class); 
	 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		logger.debug("SpellEditor called whith text [" + text + "]");
		Spell spell = new Spell();
		spell.setId(text);
        this.setValue(spell);
	}
 
}
