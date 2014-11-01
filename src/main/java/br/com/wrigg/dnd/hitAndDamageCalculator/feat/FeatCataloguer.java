package br.com.wrigg.dnd.hitAndDamageCalculator.feat;

import br.com.wrigg.dnd.hitAndDamage.feat.Feat;
import br.com.wrigg.dnd.hitAndDamage.feat.FeatCatalog;

public class FeatCataloguer {
	public Feat findFeat(Feat featDTO) throws FeatNotFoundException {
		FeatCatalog featCatalog = new FeatCatalog();
		if(featDTO != null) {
			Feat featFound = featCatalog.findFeatByName(featDTO.getName());
			if(featFound != null)
				return featFound;
		}
		throw new FeatNotFoundException();
	}
}
