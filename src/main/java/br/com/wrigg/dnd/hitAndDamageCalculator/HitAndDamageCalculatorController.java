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

import br.com.wrigg.dnd.hitAndDamage._class.ClassFeature;
import br.com.wrigg.dnd.hitAndDamage.arsenal.Weapon;
import br.com.wrigg.dnd.hitAndDamage.character.Character;
import br.com.wrigg.dnd.hitAndDamage.damage.DamageRollCalculator;
import br.com.wrigg.dnd.hitAndDamage.feat.Feat;
import br.com.wrigg.dnd.hitAndDamage.spell.Spell;
import br.com.wrigg.dnd.hitAndDamageCalculator.character.CharacterFactory;
import br.com.wrigg.dnd.hitAndDamageCalculator.character.ClassFeatureEditor;
import br.com.wrigg.dnd.hitAndDamageCalculator.spell.SpellEditor;

@Controller
@RequestMapping("/")
public class HitAndDamageCalculatorController {
 
	private static final  Logger logger = Logger.getLogger(HitAndDamageCalculatorController.class);
	
	@Autowired
	CharacterFactory characterFactory;

	//FIXME mesmo com o binder nao estava criando a Feat com ID preenchido
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		logger.debug("INIT BINDER CALLED");
		//binder.registerCustomEditor(Feat.class, new FeatEditor());
		binder.registerCustomEditor(Spell.class, new SpellEditor());
		binder.registerCustomEditor(ClassFeature.class, new ClassFeatureEditor());
    }
	
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
	
	@ModelAttribute("spells")
	public List<Spell> spells() {
		//FIXME Apenas para efeito dos testes iniciais
		Spell spell = new Spell();
		return spell.values();
	}
	
	@ModelAttribute("classFeatures")
	public List<ClassFeature> classFeatures() {
		//FIXME Apenas para efeito dos testes iniciais
		ClassFeature feature = new ClassFeature();
		return feature.values();
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

		logger.debug("[character] = " + character);
		
		DamageRollCalculator damageRollCalculator = new DamageRollCalculator();		
		String damageRoll = damageRollCalculator.calculateDamageRoll(character);

		logger.debug("damageRoll calculado [" + damageRoll + "]");
		
		model.addAttribute("damageRoll", damageRoll);
		model.addAttribute("criticalDamageRoll", damageRoll);

		return "hitAndDamageCalculator";
	}
}
