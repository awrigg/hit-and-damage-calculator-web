package br.com.wrigg.dnd.hitAndDamageCalculator.arsenal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.wrigg.dnd.hitAndDamage.DiceType;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.arsenal.WeaponNotFoundException;

@RunWith(JUnit4.class)
public class ArsenalSupervisorTest {

	@Test
	public void findWeaponByWeaponDTOTest() {
		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");

		ArsenalSupervisor arsenalSupervisor = new ArsenalSupervisor();
		Weapon weaponFound = null;
		try {
			weaponFound = arsenalSupervisor.findWeapon(weaponDTO);
		} catch(Exception e) {
		}
		
		assertEquals(weapon, weaponFound);
	}

	@Test(expected=WeaponNotFoundException.class)
	public void findWeaponByWeaponDTOPassingNullShowldReturnExceptionTest() throws WeaponNotFoundException {
		ArsenalSupervisor arsenalSupervisor = new ArsenalSupervisor();
		arsenalSupervisor.findWeapon(null);
	}
}
