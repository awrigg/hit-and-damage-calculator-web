package br.com.wrigg.dnd.hitAndDamageCalculator.character;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamage.damage.DamageBonus;
import br.com.wrigg.dnd.hitAndDamage.feat.Feat;
import br.com.wrigg.dnd.hitAndDamage.spell.Spell;
import br.com.wrigg.dnd.hitAndDamageCalculator.arsenal.ArsenalSupervisor;
import br.com.wrigg.dnd.hitAndDamageCalculator.arsenal.WeaponNotFoundException;
import br.com.wrigg.dnd.hitAndDamageCalculator.feat.FeatCataloguer;
import br.com.wrigg.dnd.hitAndDamageCalculator.feat.FeatNotFoundException;
import br.com.wrigg.dnd.hitAndDamageCalculator.spell.SpellNotFoundException;
import br.com.wrigg.dnd.hitAndDamageCalculator.spell.SpellSearcher;

public class CharacterFactory {

	private static final Logger logger = Logger.getLogger(CharacterFactory.class);
	
	public Character create(Character characterDTO) {
		Character character = new Character();
		
		ArsenalSupervisor arsenalSupervisor = new ArsenalSupervisor();
		//TODO melhorar o codigo
		Weapon weapon = null;
		try {
			Weapon weaponDTO = characterDTO.getWeapon();
			weapon = arsenalSupervisor.findWeapon(weaponDTO);
			weapon.setBonus(weaponDTO.getBonus());
		} catch (WeaponNotFoundException e) {
			logger.warn("Arma [" + weapon + "] nao encontrada no arsenal");
		}
		character.equip(weapon);
		
		character.setStrength(characterDTO.getStrength());
		
		List<Feat> feats = characterDTO.getFeats();
		
		if(feats != null)
			for (Feat featDTO : characterDTO.getFeats()) {
				logger.debug("Feat Id [" + featDTO.getId() + "] name [" + featDTO.getName() + "]");
				FeatCataloguer featCataloguer = new FeatCataloguer();
				Feat feat = null;
				try {
					feat = featCataloguer.findFeat(featDTO);
					DamageBonus damageBonus = featDTO.getDamageBonus();
					logger.debug("DamageBonus [" + damageBonus + "]");
					if(damageBonus != null)
						logger.debug("bonus [" + damageBonus.printDamageBonus() + "]");
					feat.setDamageBonus(damageBonus);
				} catch (FeatNotFoundException e) {
					logger.warn("Feat [" + feat + "] nao encontrada no catalogo");
				}
				
				character.addFeat(feat);
			}

		List<Spell> spells = characterDTO.getSpells();

		if(spells != null)
			for (Spell spellDTO : characterDTO.getSpells()) {
				logger.debug("Spell Id [" + spellDTO.getId() + "] name [" + spellDTO.getName() + "]");
				SpellSearcher spellSearcher = new SpellSearcher();
				Spell spell = null;
				try {
					spell = spellSearcher.findSpell(spellDTO);
//					DamageBonus damageBonus = spellDTO.getDamageBonus();
//					logger.debug("DamageBonus [" + damageBonus + "]");
//					if(damageBonus != null)
//						logger.debug("bonus [" + damageBonus.printDamageBonus() + "]");
//					spell.setDamageBonus(damageBonus);
				} catch (SpellNotFoundException e) {
					logger.warn("Spell [" + spell + "] nao encontrada no catalogo");
				}
				
				character.castSpell(spell);
			}

		character.setCasterLevel(characterDTO.getCasterLevel());

		character.setCharisma(characterDTO.getCharisma());

		return character;
	}
}
