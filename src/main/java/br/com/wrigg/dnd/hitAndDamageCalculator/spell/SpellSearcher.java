package br.com.wrigg.dnd.hitAndDamageCalculator.spell;

import br.com.wrigg.dnd.hitAndDamage.spell.Spell;
import br.com.wrigg.dnd.hitAndDamage.spell.SpellBook;

public class SpellSearcher {
	public Spell findSpell(Spell spellDTO) throws SpellNotFoundException {
		SpellBook spellBook = new SpellBook();
		if(spellDTO != null) {
			//FIXME a busca deveria ser por ID, mas nao consigo trazer ID preenchido da tela
			Spell spellFound = spellBook.findSpellById(spellDTO.getId());
			if(spellFound != null)
				return spellFound;
		}
		throw new SpellNotFoundException();
	}
}
