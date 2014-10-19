package br.com.wrigg.dnd.hitAndDamageCalculator.character;

import br.com.wrigg.dnd.hitAndDamage.Feat;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamageCalculator.arsenal.ArsenalSupervisor;

public class CharacterFactory {

	public Character create(Character characterDTO) {
		Character character = new Character();
		
		ArsenalSupervisor arsenalSupervisor = new ArsenalSupervisor();
		Weapon weapon = arsenalSupervisor.findWeapon(characterDTO.getWeapon());
		character.equip(weapon);
		
		character.setStrength(characterDTO.getStrength());
		
		for (Feat feat : characterDTO.getFeats()) {
			character.addFeat(feat);
		}

		character.setCharisma(characterDTO.getCharisma());

		return character;
	}
}
