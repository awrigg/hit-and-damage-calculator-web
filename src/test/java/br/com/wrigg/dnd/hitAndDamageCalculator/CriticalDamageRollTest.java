package br.com.wrigg.dnd.hitAndDamageCalculator;

import static org.junit.Assert.assertEquals;

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
	public void weaponCriticalDamageCalculateTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		weaponSelect.selectByIndex(0);
		
		WebElement kukri = weaponSelect.getFirstSelectedOption();
		assertEquals("Kukri", kukri.getText());		
		
		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement criticalDamageRoll = driver.findElement(By.id("criticalDamageRoll"));
		assertEquals("2D4", criticalDamageRoll.getText());
	}

	@Test
	public void weaponAndStrAndSpellCriticalDamageCalculateTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		weaponSelect.selectByIndex(0);
		
		WebElement kukri = weaponSelect.getFirstSelectedOption();
		assertEquals("Kukri", kukri.getText());		
		
		WebElement str = driver.findElement(By.id("str"));
		str.clear();
		str.sendKeys("18");
		
		WebElement divineFavorSpell = driver.findElement(By.id("divineFavor"));
		String divineFavorSpellId = divineFavorSpell.getAttribute("id");
		WebElement divineFavorSpellLabel = driver.findElement(By.xpath("//label[@for=\"" + divineFavorSpellId + "\"]"));
		assertEquals("Divine Favor", divineFavorSpellLabel.getText());
		
		WebElement casterLevel = driver.findElement(By.id("casterLevel"));
		casterLevel.clear();
		casterLevel.sendKeys("2");
		
		divineFavorSpell.click();

		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement criticalDamageRoll = driver.findElement(By.id("criticalDamageRoll"));
		assertEquals("2D4+10", criticalDamageRoll.getText());
	}
	
	@Test
	public void weaponAndStrAndSpellAndFeatAndClassFeatureCriticalDamageCalculateTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		weaponSelect.selectByIndex(0);
		
		WebElement kukri = weaponSelect.getFirstSelectedOption();
		assertEquals("Kukri", kukri.getText());		
		
		WebElement str = driver.findElement(By.id("str"));
		str.clear();
		str.sendKeys("18");

		WebElement cha = driver.findElement(By.id("cha"));
		cha.clear();
		cha.sendKeys("21");
		
		WebElement divineFavorSpell = driver.findElement(By.id("divineFavor"));
		String divineFavorSpellId = divineFavorSpell.getAttribute("id");
		WebElement divineFavorSpellLabel = driver.findElement(By.xpath("//label[@for=\"" + divineFavorSpellId + "\"]"));
		assertEquals("Divine Favor", divineFavorSpellLabel.getText());

		divineFavorSpell.click();

		WebElement divineMetamagic = driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"divineMetamagic\"]"));
		String divineMetamagicId = divineMetamagic.getAttribute("id");
		WebElement divineMetamagicLabel = driver.findElement(By.xpath("//label[@for=\"" + divineMetamagicId + "\"]"));
		assertEquals("Divine Metamagic", divineMetamagicLabel.getText());
		
		divineMetamagic.click();

		WebElement smiteClassFeature = driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"smite\"]"));
		String smiteClassFeatureId = smiteClassFeature.getAttribute("id");
		WebElement smiteClassFeatureLabel = driver.findElement(By.xpath("//label[@for=\"" + smiteClassFeatureId + "\"]"));
		assertEquals("Smite", smiteClassFeatureLabel.getText());
		
		smiteClassFeature.click();

		WebElement turnLevel = driver.findElement(By.id("turnLevel"));
		turnLevel.clear();
		turnLevel.sendKeys("6");

		WebElement casterLevel = driver.findElement(By.id("casterLevel"));
		casterLevel.clear();
		casterLevel.sendKeys("2");

		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement criticalDamageRoll = driver.findElement(By.id("criticalDamageRoll"));
		assertEquals("2D4+32", criticalDamageRoll.getText());
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
}
