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

public class CriticalDamageRollTest {
	private WebDriver driver;
	//private WebElement element;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		//capabilities.setCapability("version", "11");
		capabilities.setCapability("platform", Platform.WINDOWS);
		capabilities.setCapability("name", "Testing Weapon Selection");

		this.driver = new FirefoxDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
		
	@Test
	public void weaponSelectionCreationTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		List<WebElement> weapons = weaponSelect.getOptions();
		WebElement kukri = weapons.get(0);
		assertEquals("Kukri", kukri.getText());
		
		//FIXME concluir o calculo do critico
		WebElement criticalDamageRoll = driver.findElement(By.id("criticalDamageRoll"));
		assertEquals("Critical Damage Roll: 2D4", criticalDamageRoll.getText());
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
}
