package br.com.wrigg.dnd.hitAndDamageCalculator.feat;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;

import br.com.wrigg.dnd.hitAndDamage.feat.Feat;

public class FeatEditor extends PropertyEditorSupport {
	
	private static final Logger logger = Logger.getLogger(FeatEditor.class); 
	 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		logger.debug("FeatEditor called whith text [" + text + "]");
		Feat feat = new Feat();
		feat.setId(text);
        this.setValue(feat);
	}
 
}
