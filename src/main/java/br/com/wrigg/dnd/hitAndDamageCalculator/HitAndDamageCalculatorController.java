package br.com.wrigg.dnd.hitAndDamageCalculator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wrigg.dnd.hitAndDamage.DiceType;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamage.damageRollCalculator.DamageRollCalculator;
import br.com.wrigg.dnd.hitAndDamageCalculator.character.CharacterFactory;

import org.apache.log4j.Logger;

@Controller
@RequestMapping("/")
public class HitAndDamageCalculatorController {
 
	private static final  Logger logger = Logger.getLogger(HitAndDamageCalculatorController.class);
	
	@Autowired
	CharacterFactory characterFactory;
	
	@ModelAttribute("weapons")
	public Weapon[] weapons() {
		//FIXME Apenas para efeito dos testes iniciais
		Weapon weapon = new Weapon(new DiceType(4));
		return weapon.values();
	}

	@RequestMapping(value="hitAndDamageCalculator", method=RequestMethod.GET)
	public String loadFormPage(Model model) {
		logger.trace("loadFormPage");
		model.addAttribute("character", new Character());
		return "hitAndDamageCalculator";
	}

	@RequestMapping(value="hitAndDamageCalculator", method=RequestMethod.POST)
	public String submitForm(@ModelAttribute("character") Character characterDTO, Model model) {
		logger.trace("submitForm");
		
		logger.debug("[characterDTO] = " + characterDTO);
		logger.debug("[weaponDTO] = " + characterDTO.getWeapon());

		Character character = characterFactory.create(characterDTO);

		DamageRollCalculator damageRollCalculator = new DamageRollCalculator();
		String damageRoll = damageRollCalculator.calculateDamageRoll(character);
		
		model.addAttribute("damageRoll", damageRoll);
		return "hitAndDamageCalculator";
	}	
}
