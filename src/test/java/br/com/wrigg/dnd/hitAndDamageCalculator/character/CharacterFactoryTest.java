package br.com.wrigg.dnd.hitAndDamageCalculator.character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.wrigg.dnd.hitAndDamage.DiceType;
import br.com.wrigg.dnd.hitAndDamage.Type;
import br.com.wrigg.dnd.hitAndDamage.Item.Item;
import br.com.wrigg.dnd.hitAndDamage._class.ClassFeature;
import br.com.wrigg.dnd.hitAndDamage._class.TurnLevel;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Attribute;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamage.damage.DamageBonus;
import br.com.wrigg.dnd.hitAndDamage.feat.Feat;
import br.com.wrigg.dnd.hitAndDamage.spell.CasterLevel;
import br.com.wrigg.dnd.hitAndDamage.spell.Spell;

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
	
	@Test
	public void characterWithWeaponStrChaAndFeatCreationTest() {
		Character character = new Character();
		Weapon weapon = new Weapon("Kukri", new DiceType(4));		
		character.equip(weapon);
		Attribute str = new Attribute(18);
		character.setStrength(str);
		
		Feat feat = new Feat("divineMetamagic", "Divine Metamagic", Type.FEATURE_DEPENDENT);
		character.activateFeat(feat);

		Attribute cha = new Attribute(21);
		character.setCharisma(cha);
		
		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		characterDTO.equip(weaponDTO);

		Attribute strDTO = new Attribute(18);
		characterDTO.setStrength(strDTO);

		Attribute chaDTO = new Attribute(21);
		characterDTO.setCharisma(chaDTO);
		
		//FIXME busca esta sendo feita pelo nome por falha no Spring em popular o id
		//Feat featDTO = new Feat("divineMetamagic", "Divine Metamagic");
		Feat featDTO = new Feat("divineMetamagic");
		characterDTO.getFeats().add(featDTO);
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}

	@Test
	public void characterWithFeatWithTypeCreationTest() {
		Character character = new Character();
		
		Feat feat = new Feat("divineMetamagic", "Divine Metamagic", Type.FEATURE_DEPENDENT);
		character.activateFeat(feat);

		Character characterDTO = new Character();
		
		//FIXME a busca esta sendo feita pelo nome por falha no Spring na hora de popular o objeto, corrigir
		//Feat featDTO = new Feat("divineMetamagic", "Divine Metamagic");
		Feat featDTO = new Feat("divineMetamagic");
		characterDTO.getFeats().add(featDTO);
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
	
	@Test
	public void characterWithPowerAttackWithBonusCreationTest() {
		Character character = new Character();
		
		Feat powerAttack = new Feat("powerAttack", "Power Attack", Type.VARIABLE_IMPUT);
		powerAttack.setDamageBonus(new DamageBonus(5));
		character.activateFeat(powerAttack);

		Character characterDTO = new Character();
		
		//FIXME busca esta sendo feita por nome por falha no Spring em popular o ID
		//Feat powerAttackDTO = new Feat("powerAttack", "Power Attack");
		Feat powerAttackDTO = new Feat("powerAttack");
		powerAttackDTO.setDamageBonus(new DamageBonus(5));
		characterDTO.getFeats().add(powerAttackDTO);
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
	
	@Test
	public void characterWithMagicalWeaponCreationTest() {
		Character character = new Character();

		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		weapon.setBonus(new DamageBonus(1));
		character.equip(weapon);

		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		weaponDTO.setBonus(new DamageBonus(1));
		characterDTO.equip(weaponDTO);
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
	
	@Test
	public void characterWithWeaponAndSpellCreationTest() {
		Character character = new Character();

		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		character.equip(weapon);
		
		Spell divineFavor = new Spell("divineFavor", "Divine Favor");
		character.getSpells().add(divineFavor);

		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		characterDTO.equip(weaponDTO);
		
		Spell spellDTO = new Spell();
		spellDTO.setId("divineFavor");
		characterDTO.getSpells().add(spellDTO);
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
	
	@Test
	public void characterWithWeaponAndSpellAndCasterLevelCreationTest() {
		Character character = new Character();

		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		character.equip(weapon);
		
		Spell divineFavor = new Spell("divineFavor", "Divine Favor", Type.FEATURE_DEPENDENT);
		character.castSpell(divineFavor);
		
		character.setCasterLevel(new CasterLevel(2));

		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		characterDTO.equip(weaponDTO);
		
		Spell spellDTO = new Spell();
		spellDTO.setId("divineFavor");
		characterDTO.castSpell(spellDTO);
		
		characterDTO.setCasterLevel(new CasterLevel(2));
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
	
	@Test
	public void characterWithWeaponAndClassFeatureAndTurnLevelCreationTest() {
		Character character = new Character();

		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		character.equip(weapon);
		
		ClassFeature smite = new ClassFeature("smite", "Smite", Type.FEATURE_DEPENDENT);
		character.activateClassFeature(smite);
		
		character.setTurnLevel(new TurnLevel(2));

		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		characterDTO.equip(weaponDTO);

		ClassFeature smiteDTO = new ClassFeature();
		smiteDTO.setId("smite");
		characterDTO.activateClassFeature(smiteDTO);
		
		characterDTO.setTurnLevel(new TurnLevel(2));
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
	
	@Test
	public void characterWithWeaponAndEnlargePersonPotionCreationTest() {
		Character character = new Character();

		Weapon weapon = new Weapon("Kukri", new DiceType(4));
		character.equip(weapon);
		
		Item enlargePersonPotion = new Item("enlargePersonPotion", "Enlarge Person Potion");
		character.activateItem(enlargePersonPotion);
		
		Character characterDTO = new Character();
		Weapon weaponDTO = new Weapon();
		weaponDTO.setName("Kukri");
		characterDTO.equip(weaponDTO);

		Item enlargePersonPotionDTO = new Item();
		enlargePersonPotionDTO.setId("enlargePersonPotion");
		characterDTO.getItems().add(enlargePersonPotionDTO);
		
		CharacterFactory characterFactory = new CharacterFactory();
		Character characterCreated = characterFactory.create(characterDTO);

		assertEquals(character, characterCreated);
	}
}