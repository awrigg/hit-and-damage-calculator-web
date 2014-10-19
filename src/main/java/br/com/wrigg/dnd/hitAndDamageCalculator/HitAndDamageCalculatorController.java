package br.com.wrigg.dnd.hitAndDamageCalculator;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wrigg.dnd.hitAndDamage.Feat;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamage.damage.DamageRollCalculator;
import br.com.wrigg.dnd.hitAndDamageCalculator.character.CharacterFactory;
import br.com.wrigg.dnd.hitAndDamageCalculator.feat.FeatEditor;

@Controller
@RequestMapping("/")
public class HitAndDamageCalculatorController {
 
	private static final  Logger logger = Logger.getLogger(HitAndDamageCalculatorController.class);
	
	@Autowired
	CharacterFactory characterFactory;
	
	@ModelAttribute("weapons")
	public Weapon[] weapons() {
		//FIXME Apenas para efeito dos testes iniciais
		Weapon weapon = new Weapon();
		return weapon.values();
	}

	@ModelAttribute("feats")
	public List<Feat> feats() {
		//FIXME Apenas para efeito dos testes iniciais
		Feat feat = new Feat();
		return feat.values();
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
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Feat.class, new FeatEditor());
    }
}
