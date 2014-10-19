package br.com.wrigg.dnd.hitAndDamageCalculator.character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.wrigg.dnd.hitAndDamage.DiceType;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Attribute;
import br.com.wrigg.dnd.hitAndDamage.character.Character;

@RunWith(MockitoJUnitRunner.class)
public class CharacterFactoryTest {

	@Test
	public void characterWithWeaponCreationTest() {
		Character character = new Character();
		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		character.equip(weapon);
		
		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		characterDTO.equip(weaponDTO);
	
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
	
	@Test
	public void characterWithWeaponAndStrCreationTest() {
		Character character = new Character();
		Weapon weapon = new Weapon("Kukri", new DiceType(4));		
		character.equip(weapon);
		Attribute str = new Attribute(18);
		character.setStrength(str);
		
		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		characterDTO.equip(weaponDTO);
		
		Attribute strDTO = new Attribute(18);
		characterDTO.setStrength(strDTO);
	
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}

}