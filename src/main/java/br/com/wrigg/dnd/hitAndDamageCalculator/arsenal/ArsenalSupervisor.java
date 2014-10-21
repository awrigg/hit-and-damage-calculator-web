package br.com.wrigg.dnd.hitAndDamageCalculator.arsenal;

import br.com.wrigg.dnd.hitAndDamage.arsenal.Arsenal;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.arsenal.WeaponNotFoundException;

public class ArsenalSupervisor {

	public Weapon findWeapon(Weapon weaponDTO) throws WeaponNotFoundException {
		Arsenal arsenal = new Arsenal();
		if(weaponDTO != null)
			return arsenal.findWeaponByName(weaponDTO.getName());
		throw new WeaponNotFoundException();
	}
}
