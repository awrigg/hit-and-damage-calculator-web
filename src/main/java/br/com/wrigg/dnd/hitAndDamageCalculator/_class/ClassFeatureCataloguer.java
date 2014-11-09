package br.com.wrigg.dnd.hitAndDamageCalculator._class;

import br.com.wrigg.dnd.hitAndDamage._class.ClassFeature;
import br.com.wrigg.dnd.hitAndDamage._class.ClassFeatureCatalog;

public class ClassFeatureCataloguer {
	
	public ClassFeature findClassFeature(ClassFeature classFeatureDTO) throws ClassFeatureNotFoundException {
		ClassFeatureCatalog classFeatureCatalog = new ClassFeatureCatalog();
		if(classFeatureDTO != null) {
			ClassFeature classFeatureFound = classFeatureCatalog.findClassFeatureById(classFeatureDTO.getId());
			if(classFeatureFound != null)
				return classFeatureFound;
		}
		throw new ClassFeatureNotFoundException();
	}
}
