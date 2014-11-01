package br.com.wrigg.dnd.hitAndDamageCalculator.feat;

import java.beans.PropertyEditorSupport;

import br.com.wrigg.dnd.hitAndDamage.feat.Feat;

public class FeatEditor extends PropertyEditorSupport {
	 
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Feat feat = new Feat(text);
        setValue(feat);
	}
 
}
