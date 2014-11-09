package br.com.wrigg.dnd.hitAndDamageCalculator.character;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;

import br.com.wrigg.dnd.hitAndDamage._class.ClassFeature;

public class ClassFeatureEditor extends PropertyEditorSupport {
	
	private static final Logger logger = Logger.getLogger(ClassFeatureEditor.class); 
	 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		logger.debug("ClassFeatureEditor called whith text [" + text + "]");
		ClassFeature classFeature = new ClassFeature();
		classFeature.setId(text);
        this.setValue(classFeature);
	}
 
}
