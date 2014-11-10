package br.com.wrigg.dnd.hitAndDamageCalculator.character;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.wrigg.dnd.hitAndDamage.Item.Item;
import br.com.wrigg.dnd.hitAndDamage._class.ClassFeature;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamage.damage.DamageBonus;
import br.com.wrigg.dnd.hitAndDamage.feat.Feat;
import br.com.wrigg.dnd.hitAndDamage.spell.Spell;
import br.com.wrigg.dnd.hitAndDamageCalculator._class.ClassFeatureCataloguer;
import br.com.wrigg.dnd.hitAndDamageCalculator._class.ClassFeatureNotFoundException;
import br.com.wrigg.dnd.hitAndDamageCalculator.arsenal.ArsenalSupervisor;
import br.com.wrigg.dnd.hitAndDamageCalculator.arsenal.WeaponNotFoundException;
import br.com.wrigg.dnd.hitAndDamageCalculator.feat.FeatCataloguer;
import br.com.wrigg.dnd.hitAndDamageCalculator.feat.FeatNotFoundException;
import br.com.wrigg.dnd.hitAndDamageCalculator.item.ItemCataloguer;
import br.com.wrigg.dnd.hitAndDamageCalculator.item.ItemNotFoundException;
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
			for (Feat featDTO : feats) {
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
				
				character.activateFeat(feat);
			}

		List<Spell> spells = characterDTO.getSpells();

		if(spells != null)
			for (Spell spellDTO : spells) {
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
		
		List<ClassFeature> classFeatures = characterDTO.getClassFeatures();

		if(classFeatures != null)
			for (ClassFeature classFeatureDTO : classFeatures) {
				logger.debug("ClassFeature Id [" + classFeatureDTO.getId() + "] name [" + classFeatureDTO.getName() + "]");
				ClassFeatureCataloguer classFeatureCataloguer = new ClassFeatureCataloguer();
				ClassFeature classFeature = null;
				try {
					classFeature = classFeatureCataloguer.findClassFeature(classFeatureDTO);
//					DamageBonus damageBonus = spellDTO.getDamageBonus();
//					logger.debug("DamageBonus [" + damageBonus + "]");
//					if(damageBonus != null)
//						logger.debug("bonus [" + damageBonus.printDamageBonus() + "]");
//					spell.setDamageBonus(damageBonus);
				} catch (ClassFeatureNotFoundException e) {
					logger.warn("ClassFeature [" + classFeature + "] nao encontrada no catalogo");
				}
				
				character.activateClassFeature(classFeature);
			}

		List<Item> items = characterDTO.getItems();

		if(items != null)
			for (Item itemDTO : items) {
				logger.debug("Item Id [" + itemDTO.getId() + "] name [" + itemDTO.getName() + "]");
				ItemCataloguer itemCataloguer = new ItemCataloguer();
				Item item = null;
				try {
					item = itemCataloguer.findItem(itemDTO);
//					DamageBonus damageBonus = spellDTO.getDamageBonus();
//					logger.debug("DamageBonus [" + damageBonus + "]");
//					if(damageBonus != null)
//						logger.debug("bonus [" + damageBonus.printDamageBonus() + "]");
//					spell.setDamageBonus(damageBonus);
				} catch (ItemNotFoundException e) {
					logger.warn("Item [" + item + "] nao encontrada no catalogo");
				}
				
				character.activateItem(item);
			}
		
		character.setCasterLevel(characterDTO.getCasterLevel());

		character.setTurnLevel(characterDTO.getTurnLevel());
		
		character.setCharisma(characterDTO.getCharisma());

		return character;
	}
}
