package br.com.wrigg.dnd.hitAndDamageCalculator;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class PotionSelectionTest {
	private WebDriver driver;
	//private WebElement element;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		//capabilities.setCapability("version", "11");
		capabilities.setCapability("platform", Platform.WINDOWS);
		capabilities.setCapability("name", "Testing Weapon Selection");

		this.driver = new FirefoxDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
		
	@Test
	public void potionSelectionTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		List<WebElement> weapons = weaponSelect.getOptions();
		WebElement kukri = weapons.get(0);
		assertEquals("Kukri", kukri.getText());
		
		weaponSelect.selectByIndex(0);
		
		WebElement str = driver.findElement(By.id("str"));
		str.clear();
		str.sendKeys("18");
		
		WebElement enlargePersonPotion = driver.findElement(By.id("enlargePersonPotion"));
		String enlargePersonPotionId = enlargePersonPotion.getAttribute("id");
		WebElement enlargePersonPotionLabel = driver.findElement(By.xpath("//label[@for=\"" + enlargePersonPotionId + "\"]"));
		assertEquals("Enlarge Person Potion", enlargePersonPotionLabel.getText());
		
		enlargePersonPotion.click();
		
		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement damageRoll = driver.findElement(By.id("damageRoll"));
		assertEquals("1D6+4", damageRoll.getText());
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
}
