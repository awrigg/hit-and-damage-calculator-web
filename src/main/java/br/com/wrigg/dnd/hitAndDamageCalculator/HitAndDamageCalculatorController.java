package br.com.wrigg.dnd.hitAndDamageCalculator;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wrigg.dnd.hitAndDamage.Character;
import br.com.wrigg.dnd.hitAndDamage.DiceType;
import br.com.wrigg.dnd.hitAndDamage.Weapon;
import br.com.wrigg.dnd.hitAndDamage.damageRollCalculator.DamageRollCalculator;

import org.apache.log4j.Logger;

@Controller
@RequestMapping("/")
public class HitAndDamageCalculatorController {
 
	private static final  Logger logger = Logger.getLogger(HitAndDamageCalculatorController.class);
	
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
	public String submitForm(@ModelAttribute("character") Character character, Model model) {
		logger.trace("submitForm");
		
		logger.debug("[character] = " + character);
		logger.debug("[weapon] = " + character.getWeapon());
		
		DamageRollCalculator damageRollCalculator = new DamageRollCalculator();
		damageRollCalculator.calculateDamageRoll(character);
		
		model.addAttribute("damageRoll", damageRollCalculator.calculateDamageRoll(character));
		return "hitAndDamageCalculator";
	}	
}
