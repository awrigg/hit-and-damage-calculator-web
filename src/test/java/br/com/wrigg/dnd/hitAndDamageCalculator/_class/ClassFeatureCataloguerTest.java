package br.com.wrigg.dnd.hitAndDamageCalculator._class;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.wrigg.dnd.hitAndDamage.Type;
import br.com.wrigg.dnd.hitAndDamage._class.ClassFeature;

@RunWith(JUnit4.class)
public class ClassFeatureCataloguerTest {

	@Test
	public void findClassFeatureByClassFeatureDTOTest() {
		ClassFeature smite = new ClassFeature("smite", "Smite", Type.FEATURE_DEPENDENT); 

		ClassFeature classFeatureDTO = new ClassFeature();
		classFeatureDTO.setId("smite");

		ClassFeatureCataloguer classFeatureCataloguer = new ClassFeatureCataloguer();
		ClassFeature classFeatureFound = null;
		try {
			classFeatureFound = classFeatureCataloguer.findClassFeature(classFeatureDTO);
		} catch(Exception e) {
		}
		
		assertEquals(smite, classFeatureFound);
	}

	@Test(expected=ClassFeatureNotFoundException.class)
	public void findFeatByFeatDTOPassingNullShowldReturnExceptionTest() throws ClassFeatureNotFoundException {
		ClassFeatureCataloguer classFeatureCataloguer = new ClassFeatureCataloguer();
		classFeatureCataloguer.findClassFeature(null);
	}
	
	@Test(expected=ClassFeatureNotFoundException.class)
	public void findInexistentFeatByFeatDTOShowldReturnExceptionTest() throws ClassFeatureNotFoundException {
		ClassFeatureCataloguer classFeatureCataloguer = new ClassFeatureCataloguer();

		ClassFeature classFeatureDTO = new ClassFeature();
		classFeatureDTO.setId("Inexistent Feat");
		
		classFeatureCataloguer.findClassFeature(classFeatureDTO);
	}
}
