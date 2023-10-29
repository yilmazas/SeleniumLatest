package testingproject2;

import com.github.javafaker.Faker;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import utility.BaseDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.TestRunner.PriorityWeight.dependsOnMethods;

public class project2 extends BaseDriver {
    Faker randomGenerator = new Faker(); // diger testlerde de kullanmak icin class a kopyaladik
    String randomEmail;
    Actions actions = new Actions(driver);
    Action action;
    String password = "12345fdr";

    /* Senaryo 1: Kayıt oluşturma Testi

    ➢ Siteye gidiniz
    ➢ Register butonuna tıklayınız
    ➢ Kişisel bilgileri doldurun ve register butonuna tıklayınız
    ➢ Başarılı bir şekilde kaydolduğunuzu doğrulayınız */
    @Test
    public void test1() {

        driver.get("http://demowebshop.tricentis.com/");


        randomEmail = randomGenerator.internet().emailAddress(); // farkli email aliyoruz Stringe esitleyip kolay pratik kullanim
        WebElement registerButton = driver.findElement(By.linkText("Register"));

        Action action = actions.moveToElement(registerButton).click().build();
        action.perform();

        WebElement maleButton = driver.findElement(By.cssSelector("[id='gender-male']"));
        action = actions
                .moveToElement(maleButton)
                .click()
                .sendKeys(Keys.TAB)
                .sendKeys("hasan")
                .sendKeys(Keys.TAB)
                .sendKeys("demir")
                .sendKeys(Keys.TAB)
                .sendKeys(randomEmail) // faker her seferinde farkli email veriyor
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();
        action.perform();

        WebElement emailConfirmation = driver.findElement(By.linkText(randomEmail)); // locate ederken dinamik bir sekilde string degeri yazdik
        Assert.assertEquals(emailConfirmation.getText(), randomEmail);

        WebElement logOut = driver.findElement(By.linkText("Log out"));
        action = actions.moveToElement(logOut).click().build();
        action.perform();


//        WebElement firstName= driver.findElement(By.cssSelector("[id='FirstName']"));
//        action= actions.moveToElement(firstName).sendKeys("hasan").build();
//        action.perform();

//        WebElement lastName= driver.findElement(By.cssSelector("[id='LastName']"));


        // waitAndClose();

    }

    @Test(dependsOnMethods = "test1")
/*Senaryo 2: Negatif kayıt Testi

➢ Siteye gidin
➢ Register butonuna tıklayınız
➢ Yukarida kaydolduğunuz email ile kaydolmaya çalışınız
➢ Register butonuna tıklayınız
➢ “The specified email already exists” mesajının görüldüğünü doğrulayınız */
    public void test2() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement registerButton = driver.findElement(By.linkText("Register"));

        Action action = actions.moveToElement(registerButton).click().build();
        action.perform();

        WebElement maleButton = driver.findElement(By.cssSelector("[id='gender-male']"));
        action = actions
                .moveToElement(maleButton)
                .click()
                .sendKeys(Keys.TAB)
                .sendKeys("hasan")
                .sendKeys(Keys.TAB)
                .sendKeys("demir")
                .sendKeys(Keys.TAB)
                .sendKeys(randomEmail) // faker her seferinde farkli email veriyor
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();
        action.perform();

        WebElement alreadyExists = driver.findElement(By.xpath("//li[text()='The specified email already exists']"));
        Assert.assertEquals(alreadyExists.getText(), "The specified email already exists");

        waitAndClose();

    }

    @Test(dependsOnMethods = "test1")
    public void test3() {
      /*  Senaryo 3: Login Test

➢ Siteye gidiniz
➢ Login butonuna tıklayınız
➢ Geçerli email ve password I giriniz
➢ Login butonuna tıklatınız ve login olduğunuzu doğrulayınız */

        driver.get("http://demowebshop.tricentis.com/");

        WebElement loginButton = driver.findElement(By.linkText("Log in"));
        action = actions
                .moveToElement(loginButton)
                .click()
                .build();
        action.perform();

        WebElement email = driver.findElement(By.cssSelector("[id='Email']"));

        action = actions
                .moveToElement(email)
                .sendKeys(randomEmail)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();

        action.perform();

        WebElement logOut = driver.findElement(By.linkText("Log out"));
        Assert.assertEquals("the login was unsuccessful", "Log out", logOut.getText());

        waitAndClose();


    }

    @Test(dependsOnMethods = "test1")
  /*  Senaryo 4: Negatif Login Test

➢ Siteye gidiniz
➢ Login butonuna tıklayınız
➢ Geçersiz email veya password giriniz
➢ Login butonuna tıklatınız ve login olamadığınızı doğrulayınız */

    public void test4() {
        driver.get("http://demowebshop.tricentis.com/");

        WebElement loginButton = driver.findElement(By.linkText("Log in"));
        action = actions
                .moveToElement(loginButton)
                .click()
                .build();
        action.perform();

        WebElement email = driver.findElement(By.cssSelector("[id='Email']"));

        action = actions
                .moveToElement(email)
                .sendKeys(randomGenerator.internet().emailAddress()) // basarisiz login olmasi icin
                .sendKeys(Keys.TAB)
                .sendKeys(randomGenerator.internet().password())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();

        action.perform();

        WebElement unsuccessfulLogin = driver.findElement(By.xpath("//span[text()='Login was unsuccessful. Please correct the errors and try again.']"));
        Assert.assertTrue("the login was successfull", unsuccessfulLogin.getText().contains("Login was unsuccessful"));


        waitAndClose();
    }

    @Test(dependsOnMethods = "test1")
    /*Senaryo 5: Order Test

➢ Siteye gidiniz
➢ Login olunuz
➢ Computers>Notebook un altında “14.1-inch Laptop” adli ürüne tıklatınız
➢ Add to Cart butonuna tıklatınız ve urunun başarılı bir şekilde eklendiğini doğrulayınız
➢ Shopping cart butonuna tıklatıp sepetinize gidin ve urunun orda görüldüğünü doğrulayınız
➢ Agree check box tıklatıp şartları kabul ediniz
➢ Checkout butonuna tıklatınız
➢ Açılan sayfada tüm bilgileri doldurun ve confirm order butonuna tıklatınız
➢ “Your order has been successfully processed!” mesajının görüldüğünü doğrulayınız */

    public void test5() {
        driver.get("http://demowebshop.tricentis.com/");

        WebElement loginButton = driver.findElement(By.linkText("Log in"));
        action = actions
                .moveToElement(loginButton)
                .click()
                .build();
        action.perform();

        WebElement email = driver.findElement(By.cssSelector("[id='Email']"));

        action = actions
                .moveToElement(email)
                .sendKeys(randomEmail)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .build();

        action.perform();

        WebElement computersBigPunto = driver.findElement(By.linkText("COMPUTERS"));


        action = actions
                .moveToElement(computersBigPunto)
                .build();

        action.perform();

        WebElement notebooks = driver.findElement(By.linkText("Notebooks"));
        notebooks.click();

        WebElement laptop = driver.findElement(By.linkText("14.1-inch Laptop"));
        action = actions.moveToElement(laptop).click().build();
        action.perform();

        WebElement addToCard = driver.findElement(By.cssSelector("[id='add-to-cart-button-31']"));
        addToCard.click();

        WebElement confirmationMessage= driver.findElement(By.xpath("//p[text()='The product has been added to your ']"));
        Assert.assertTrue("the process was unsuccessful",confirmationMessage.getText().contains("The product has been added to your"));

        WebElement shoppingCard= driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        action = actions.moveToElement(shoppingCard).click().build();
        action.perform();

        List<WebElement> products= driver.findElements(By.xpath("//td[@class='product']"));
        Assert.assertTrue("the process failed",products.get(0).getText().contains("14.1-inch Laptop"));
        //        for (int i = 0; i < products.size(); i++) {
//            Assert.assertTrue("the process failed",products.get(i).getText().contains("14.1-inch Laptop"));
//
//        }

    }

}
