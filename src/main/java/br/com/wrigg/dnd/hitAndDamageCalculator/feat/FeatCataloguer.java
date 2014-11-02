package br.com.wrigg.dnd.hitAndDamageCalculator.feat;

import br.com.wrigg.dnd.hitAndDamage.feat.Feat;
import br.com.wrigg.dnd.hitAndDamage.feat.FeatCatalog;

public class FeatCataloguer {
	public Feat findFeat(Feat featDTO) throws FeatNotFoundException {
		FeatCatalog featCatalog = new FeatCatalog();
		if(featDTO != null) {
			//FIXME a busca deveria ser por ID, mas nao consigo trazer ID preenchido da tela
			Feat featFound = featCatalog.findFeatById(featDTO.getName());
			if(featFound != null)
				return featFound;
		}
		throw new FeatNotFoundException();
	}
}
