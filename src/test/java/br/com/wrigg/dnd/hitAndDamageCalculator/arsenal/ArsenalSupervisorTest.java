package br.com.wrigg.dnd.hitAndDamageCalculator.arsenal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.wrigg.dnd.hitAndDamage.DiceType;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;

public class ArsenalSupervisorTest {

	@Test
	public void findWeaponByWeaponDTOTest() {
		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");

		ArsenalSupervisor arsenalSupervisor = new ArsenalSupervisor();
		Weapon weaponFound = arsenalSupervisor.findWeapon(weaponDTO);
		
		assertEquals(weapon, weaponFound);
	}

}
