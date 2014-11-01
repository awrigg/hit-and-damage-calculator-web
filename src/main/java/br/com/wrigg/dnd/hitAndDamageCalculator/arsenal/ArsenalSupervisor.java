package br.com.wrigg.dnd.hitAndDamageCalculator.arsenal;

import br.com.wrigg.dnd.hitAndDamage.arsenal.Arsenal;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;

public class ArsenalSupervisor {

	public Weapon findWeapon(Weapon weaponDTO) throws WeaponNotFoundException {
		Arsenal arsenal = new Arsenal();
		if(weaponDTO != null) {
			Weapon weaponFound = arsenal.findWeaponByName(weaponDTO.getName());
			if(weaponFound != null)
				return weaponFound; 
		}
		throw new WeaponNotFoundException();
	}
}
