package br.com.wrigg.dnd.hitAndDamageCalculator.arsenal;

import br.com.wrigg.dnd.hitAndDamage.arsenal.Arsenal;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;

public class ArsenalSupervisor {

	public Weapon findWeapon(Weapon weaponDTO) {
		Arsenal arsenal = new Arsenal();
		return arsenal.findWeaponByName(weaponDTO.getName());
	}
}
