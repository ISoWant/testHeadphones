package ru.yandex.market.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;
import static org.junit.Assert.fail;

public class MySteps {
   private WebDriver driver;
   private String baseUrl;
   private static String firstTV;
   private boolean acceptNextAlert = true;
   private StringBuffer verificationErrors = new StringBuffer();

   @Given("browser open the page on yandex.ru")
   public void setUp() throws Exception {
      driver = new FirefoxDriver();
      driver.manage().window().maximize();

      baseUrl = "https://www.yandex.ru/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   }

   @When("the transition to the section market")
   public void transitionToTheMarket() {
      driver.get(baseUrl + "/");
      driver.findElement(By.linkText("Маркет")).click();}

   @When ("choose section Electronics")
   public void chooseElectronics(){
      driver.findElement(By.linkText("Электроника")).click();
   }

   @When("choose section headphones")
   public void chooseTV() {
      driver.findElement(By.linkText("Наушники")).click();
   }

   @When("go to advanced search")
   public void advancedSearch()
   {
      driver.findElement(By.linkText("Расширенный поиск →")).click();
   }

   @When("sets the specified search criteria")
   public void setSearchCriteria() {
      driver.findElement(By.id("gf-pricefrom-var")).click();
      driver.findElement(By.id("gf-pricefrom-var")).clear();
      driver.findElement(By.id("gf-pricefrom-var")).sendKeys("5000");
      driver.findElement(By.xpath("//div[3]/span/label")).click();
      driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
   }

   @Then("page elements should be 10")
   public void numberOfElementsFound(){
      assertEquals(10, driver.findElements(By.xpath("(//div[@onclick='return \"\"'])")).size());
   }

   @Then("to keep the name of the first element in the list")
   public void keepNameOfFirstElement() {
      firstTV = driver.findElement(By.cssSelector("span.snippet-card__header-text")).getText();
   }

   @When("copy a saved name in the search box")
   public void copyNameInSearch() {
      driver.findElement(By.id("header-search")).click();
      driver.findElement(By.id("header-search")).clear();
      driver.findElement(By.id("header-search")).sendKeys(firstTV);
      driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
      driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
   }

   @Then("found element should have a similar name")
   public void compareNames() throws Exception {

   try {
         assertEquals(firstTV, driver.findElement(By.xpath("//h1")).getText());
      } catch (Error e) {
         verificationErrors.append(e.toString());
      }
   }



   @Then("finished test")
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
