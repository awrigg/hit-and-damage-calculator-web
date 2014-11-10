package br.com.wrigg.dnd.hitAndDamageCalculator.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.wrigg.dnd.hitAndDamage.Item.Item;

@RunWith(JUnit4.class)
public class ItemCataloguerTest {

	@Test
	public void findItemByItemDTOTest() {
		Item enlargePersonPotion = new Item("enlargePersonPotion", "Enlarge Person Potion"); 

		Item enlargePersonPotionDTO = new Item();
		enlargePersonPotionDTO.setId("enlargePersonPotion");

		ItemCataloguer itemCataloguer = new ItemCataloguer();
		Item itemFound = null;
		try {
			itemFound = itemCataloguer.findItem(enlargePersonPotionDTO);
		} catch(Exception e) {
		}
		
		assertEquals(enlargePersonPotion, itemFound);
	}

	@Test(expected=ItemNotFoundException.class)
	public void findItemByItemDTOPassingNullShowldReturnExceptionTest() throws ItemNotFoundException {
		ItemCataloguer itemCataloguer = new ItemCataloguer();
		itemCataloguer.findItem(null);
	}
	
	@Test(expected=ItemNotFoundException.class)
	public void findInexistentItemByItemDTOShowldReturnExceptionTest() throws ItemNotFoundException {
		ItemCataloguer itemCataloguer = new ItemCataloguer();

		Item itemDTO = new Item();
		itemDTO.setId("Inexistent Item");
		
		itemCataloguer.findItem(itemDTO);
	}
}
