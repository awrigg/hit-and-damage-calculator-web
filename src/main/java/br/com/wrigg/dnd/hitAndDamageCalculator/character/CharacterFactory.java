package br.com.wrigg.dnd.hitAndDamageCalculator.character;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamage.feat.Feat;
import br.com.wrigg.dnd.hitAndDamageCalculator.arsenal.ArsenalSupervisor;
import br.com.wrigg.dnd.hitAndDamageCalculator.arsenal.WeaponNotFoundException;

public class CharacterFactory {

	private static final Logger logger = Logger.getLogger(CharacterFactory.class);
	
	public Character create(Character characterDTO) {
		Character character = new Character();
		
		ArsenalSupervisor arsenalSupervisor = new ArsenalSupervisor();
		//TODO melhorar o codigo
		Weapon weapon = null;
		try {
			weapon = arsenalSupervisor.findWeapon(characterDTO.getWeapon());
		} catch (WeaponNotFoundException e) {
			e.printStackTrace();
		}
		character.equip(weapon);
		
		character.setStrength(characterDTO.getStrength());
		
		List<Feat> feats = characterDTO.getFeats();
		
		if(feats != null)
			for (Feat feat : characterDTO.getFeats()) {
				logger.debug("Feat [" + feat.getName() + "]");
				character.addFeat(feat);
			}

		character.setCharisma(characterDTO.getCharisma());

		return character;
	}
}
