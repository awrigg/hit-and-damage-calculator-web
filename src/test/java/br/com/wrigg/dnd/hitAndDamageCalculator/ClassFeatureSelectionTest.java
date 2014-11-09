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

public class ClassFeatureSelectionTest {
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
	public void classFeatureWithWeaponSelectionTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		List<WebElement> weapons = weaponSelect.getOptions();
		WebElement kukri = weapons.get(0);
		assertEquals("Kukri", kukri.getText());
		
		weaponSelect.selectByIndex(0);
		
		WebElement smiteClassFeature = driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"smite\"]"));
		String smiteClassFeatureId = smiteClassFeature.getAttribute("id");
		WebElement smiteClassFeatureLabel = driver.findElement(By.xpath("//label[@for=\"" + smiteClassFeatureId + "\"]"));
		assertEquals("Smite", smiteClassFeatureLabel.getText());
		
		smiteClassFeature.click();

		WebElement turnLevel = driver.findElement(By.id("turnLevel"));
		turnLevel.clear();
		turnLevel.sendKeys("6");

		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement damageRoll = driver.findElement(By.id("damageRoll"));
		assertEquals("1D4+6", damageRoll.getText());
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}

}
