package br.com.wrigg.dnd.hitAndDamageCalculator.feat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.wrigg.dnd.hitAndDamage.Type;
import br.com.wrigg.dnd.hitAndDamage.feat.Feat;

@RunWith(JUnit4.class)
public class FeatCataloguerTest {

	@Test
	public void findWeaponByWeaponDTOTest() {
		Feat feat = new Feat("divineMetamagic", "Divine Metamagic", Type.FEATURE_DEPENDENT); 

		Feat featDTO = new Feat();
		//FIXME a busca esta sendo feita por nome pois nao consegui acertar no Spring para criar preenchendo  ID
		featDTO.setName("divineMetamagic");

		FeatCataloguer featCataloguer = new FeatCataloguer();
		Feat featFound = null;
		try {
			featFound = featCataloguer.findFeat(featDTO);
		} catch(Exception e) {
		}
		
		assertEquals(feat, featFound);
	}

	@Test(expected=FeatNotFoundException.class)
	public void findFeatByFeatDTOPassingNullShowldReturnExceptionTest() throws FeatNotFoundException {
		FeatCataloguer featCataloguer = new FeatCataloguer();
		featCataloguer.findFeat(null);
	}
	
	@Test(expected=FeatNotFoundException.class)
	public void findInexistentFeatByFeatDTOShowldReturnExceptionTest() throws FeatNotFoundException {
		FeatCataloguer featCataloguer = new FeatCataloguer();

		Feat featDTO = new Feat();
		featDTO.setId("Inexistent Feat");
		
		featCataloguer.findFeat(featDTO);
	}
}
