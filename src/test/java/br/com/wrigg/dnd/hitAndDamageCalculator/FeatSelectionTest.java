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

public class FeatSelectionTest {
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
	public void featWithWeaponSelectionTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		List<WebElement> weapons = weaponSelect.getOptions();
		WebElement kukri = weapons.get(0);
		assertEquals("Kukri", kukri.getText());
		
		weaponSelect.selectByIndex(0);
		
		WebElement str = driver.findElement(By.id("str"));
		str.clear();
		str.sendKeys("18");

		WebElement cha = driver.findElement(By.id("cha"));
		cha.clear();
		cha.sendKeys("21");

		WebElement divineMetamagic = driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"divineMetamagic\"]"));
		String divineMetamagicId = divineMetamagic.getAttribute("id");
		WebElement divineMetamagicLabel = driver.findElement(By.xpath("//label[@for=\"" + divineMetamagicId + "\"]"));
		assertEquals("Divine Metamagic", divineMetamagicLabel.getText());
		
		divineMetamagic.click();

		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement damageRoll = driver.findElement(By.id("damageRoll"));
		assertEquals("1D4+9", damageRoll.getText());
	}
	
	@Test
	public void powerAttackShouldAddAInputedBonusToDamageTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		List<WebElement> weapons = weaponSelect.getOptions();
		WebElement kukri = weapons.get(0);
		assertEquals("Kukri", kukri.getText());
		
		weaponSelect.selectByIndex(0);
		
		WebElement str = driver.findElement(By.id("str"));
		str.clear();
		str.sendKeys("18");

		WebElement powerAttack = driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"powerAttack\"]"));
		String powerAttackId = powerAttack.getAttribute("id");
		WebElement powerAttackLabel = driver.findElement(By.xpath("//label[@for=\"" + powerAttackId + "\"]"));
		assertEquals("Power Attack", powerAttackLabel.getText());

		powerAttack.click();

		WebElement powerAttackInput = driver.findElement(By.id("powerAttack_value"));
		powerAttackInput.clear();
		powerAttackInput.sendKeys("4");

		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement damageRoll = driver.findElement(By.id("damageRoll"));
		assertEquals("1D4+8", damageRoll.getText());
	}
	
	@Test
	public void powerAttackAndKnowledgeDevotionShouldAddAInputedBonusToDamageTest() throws Exception {
		this.driver.get("http://localhost:8080/hit-and-damage-calculator/hitAndDamageCalculator");
		
		Select weaponSelect = new Select(driver.findElement(By.id("weaponSelect")));
		List<WebElement> weapons = weaponSelect.getOptions();
		WebElement kukri = weapons.get(0);
		assertEquals("Kukri", kukri.getText());
		
		weaponSelect.selectByIndex(0);
		
		WebElement str = driver.findElement(By.id("str"));
		str.clear();
		str.sendKeys("16");

		WebElement powerAttack = driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"powerAttack\"]"));
		String powerAttackId = powerAttack.getAttribute("id");
		WebElement powerAttackLabel = driver.findElement(By.xpath("//label[@for=\"" + powerAttackId + "\"]"));
		assertEquals("Power Attack", powerAttackLabel.getText());

		powerAttack.click();

		WebElement powerAttackInput = driver.findElement(By.id("powerAttack_value"));
		powerAttackInput.clear();
		powerAttackInput.sendKeys("4");

		WebElement knowledgeDevotion = driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"knowledgeDevotion\"]"));
		String knowledgeDevotionId = knowledgeDevotion.getAttribute("id");
		WebElement knowledgeDevotionLabel = driver.findElement(By.xpath("//label[@for=\"" + knowledgeDevotionId + "\"]"));
		assertEquals("Knowledge Devotion", knowledgeDevotionLabel.getText());

		knowledgeDevotion.click();

		WebElement knowledgeDevotionInput = driver.findElement(By.id("knowledgeDevotion_value"));
		knowledgeDevotionInput.clear();
		knowledgeDevotionInput.sendKeys("5");

		WebElement calculateButton = driver.findElement(By.id("calculateButton"));
		calculateButton.click();
		
		WebElement damageRoll = driver.findElement(By.id("damageRoll"));
		assertEquals("1D4+12", damageRoll.getText());
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
}
