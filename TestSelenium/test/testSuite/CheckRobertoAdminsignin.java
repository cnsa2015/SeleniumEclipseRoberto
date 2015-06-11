package testSuite;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckRobertoAdminsignin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    //driver = new FirefoxDriver();
	driver = new HtmlUnitDriver(true);
	
	java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
    baseUrl = "http://ualhmis15.cloudapp.net";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCheckRobertoAdminsignin() throws Exception {
    driver.get(baseUrl + "/");
    WebDriverWait wait = new WebDriverWait(driver, 5);
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.id("user_username")).clear();
    driver.findElement(By.id("user_username")).sendKeys("admin");
    driver.findElement(By.id("user_password")).clear();
    driver.findElement(By.id("user_password")).sendKeys("1234");
    driver.findElement(By.name("commit")).click();
    assertEquals("Protected page | Hmislogin", driver.getTitle());
    assertTrue(isElementPresent(By.linkText("Edit your account")));
    assertTrue(isElementPresent(By.linkText("Admin")));
    driver.findElement(By.linkText("Admin")).click();
    driver.findElement(By.linkText("Users")).click();
    driver.findElement(By.id("search")).clear();
    driver.findElement(By.id("search")).sendKeys("roberto");
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    assertEquals("Roberto", driver.findElement(By.linkText("Roberto")).getText());
    driver.findElement(By.linkText("Sign out")).click();
    assertEquals("Signed out successfully.", driver.findElement(By.id("flash_notice")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
