package br.com.wrigg.dnd.hitAndDamageCalculator.spell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.wrigg.dnd.hitAndDamage.spell.Spell;

@RunWith(JUnit4.class)
public class SpellSearcherTest {

	@Test
	public void findSpellBySpellDTOTest() {
		Spell spell = new Spell("divineFavor", "Divine Favor"); 

		Spell spellDTO = new Spell();
		spellDTO.setId("divineFavor");

		SpellSearcher spellSearcher = new SpellSearcher();
		Spell spellFound = null;
		try {
			spellFound = spellSearcher.findSpell(spellDTO);
		} catch(Exception e) {
		}
		
		assertEquals(spell, spellFound);
	}

	@Test(expected=SpellNotFoundException.class)
	public void findSpellBySpellDTOPassingNullShowldReturnExceptionTest() throws SpellNotFoundException {
		SpellSearcher spellSearcher = new SpellSearcher();
		spellSearcher.findSpell(null);
	}
	
	@Test(expected=SpellNotFoundException.class)
	public void findInexistentSpellBySpellDTOShowldReturnExceptionTest() throws SpellNotFoundException {
		SpellSearcher spellSearcher = new SpellSearcher();

		Spell spellDTO = new Spell();
		spellDTO.setId("Inexistent Feat");
		
		spellSearcher.findSpell(spellDTO);
	}
}
