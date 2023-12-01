package testingproject3;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;
import utility.MyFunction;

import java.time.Duration;

/* ➢ https://shopdemo.e-junkie.com/ sitesine gidiniz
➢ E-book'a tıklatınız
➢ E-book add to cart butonuna tıklatınız.
➢ Add promo code butonuna tıklatıp veri giriniz.
➢ Apply butonuna tıklayınız.
➢ Invalid promo code yazısının görüldüğünü doğrulayınız
 */
public class project3 extends BaseDriver {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Faker randomGenerator = new Faker();

    @Test
    public void test1() {

        driver.get("https://shopdemo.e-junkie.com/");

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement addPromoCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Apply-Button Show-Promo-Code-Button']")));
        addPromoCode.click();

        WebElement promoCodeButton = driver.findElement(By.className("Promo-Code-Value"));
        promoCodeButton.sendKeys("Promo Code");

        WebElement applyPromoCode = driver.findElement(By.xpath("//button[@class='Promo-Apply']"));
        applyPromoCode.click();

        WebElement verificationMessage = driver.findElement(By.xpath("//span[text()='Invalid promo code']"));

        Assert.assertEquals(verificationMessage.getText(), "Invalid promo code");


        //waitAndClose();
    }

    @Test()
    /* Test Case:2

➢ https://shopdemo.e-junkie.com/ sitesine gidiniz
➢ E-book'a tıklatınız
➢ E-book add to cart butonuna tıklatınız.
➢ Pay using debit card a tıklatınız.
➢ Pay butonuna tıklatınız.
➢ Invalid Email, Invalid Email, invalid billing name mesajlarının görüldüğünü doğrulayınız.
 */
    public void test2() {
        driver.get("https://shopdemo.e-junkie.com/");
        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payCreditCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Payment-Button CC']")));
        payCreditCard.click();

        WebElement payButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Pay-Button']")));
        payButton.click();

        WebElement confirmationMessage = driver.findElement(By.xpath("//span[text()='Invalid Email']"));
        Assert.assertEquals(confirmationMessage.getText(), "Invalid Email\n" + "Invalid Email\n" + "Invalid Billing Name");

    }

    @Test
    public void test3() {
        /*

Test Case:3

➢ https://shopdemo.e-junkie.com/ sitesine gidiniz
➢ E-book add to cart butonuna tıklatınız. ➢ Pay using debit card a tıklatınız. ➢ Card numarasına “1111 1111 1111 1111” giriniz
➢ “Your card number is invalid” mesajının görüldüğünü doğrulayınız.
 */
        driver.get("https://shopdemo.e-junkie.com/");

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payCreditCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Payment-Button CC']")));
        payCreditCard.click();

        WebElement iframeCardNumber = driver.findElement(By.cssSelector("iframe[name^='__privateStripeFrame']"));
        driver.switchTo().frame(iframeCardNumber);

        WebElement cardNumber = driver.findElement(By.cssSelector("[name='cardnumber']"));
        cardNumber.sendKeys("1111 1111 1111 1111");

        driver.switchTo().parentFrame();

        WebElement confirmationMessage = driver.findElement(By.xpath("//span[text()='Your card number is invalid.']"));

        Assert.assertEquals(confirmationMessage.getText(), "Your card number is invalid.");


    }

    @Test
    public void test4() {
        /*

Test Case: 4

➢ https://shopdemo.e-junkie.com/ sitesine gidiniz
➢ E-book add to cart butonuna tıklatınız.
➢ Pay using debit card a tıklatınız.
➢ Email, confirm Email, name, telefon, company, Card number(“4242 4242 4242 4242” ) giriniz,
expdate, cvc kodu bilgilerini doldurunuz
➢ Pay butonuna tıklayınız
➢ “your order is confirmed. Thank you!” mesajının görüldüğünü doğrulayınız
 */
        driver.get("https://shopdemo.e-junkie.com/");

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payCreditCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Payment-Button CC']")));
        payCreditCard.click();
        String mail = RandomStringUtils.randomAlphanumeric(8) + "@gmail.com"; // bu sekilde bir String deger olusturduk

        WebElement email = driver.findElement(By.cssSelector("[placeholder='Email']"));
        email.sendKeys(mail); // gmailden onceki harf ve rakam grubunu olusturduk
        // commons-lang3 project structure kismindan ekledik
        WebElement confirmEmail = driver.findElement(By.cssSelector("[placeholder='Confirm Email']"));
        confirmEmail.sendKeys(mail);

        WebElement name = driver.findElement(By.cssSelector("[placeholder='Name On Card']"));
        name.sendKeys(randomGenerator.name().fullName());
        WebElement phoneNumber = driver.findElement(By.xpath("(//input[@autocomplete='phone'])[1]"));
        phoneNumber.sendKeys(RandomStringUtils.randomNumeric(10));

        WebElement companyName = driver.findElement(By.xpath("//input[@autocomplete='company']"));
        companyName.sendKeys(randomGenerator.company().name());

        WebElement iframeCardNumber = driver.findElement(By.cssSelector("iframe[name^='__privateStripeFrame']"));
        driver.switchTo().frame(iframeCardNumber);

        WebElement cardNumber = driver.findElement(By.cssSelector("[name='cardnumber']"));
        cardNumber.sendKeys("4242 4242 4242 4242");

        WebElement expiryDate = driver.findElement(By.xpath("//input[@placeholder='MM / YY']"));
        expiryDate.sendKeys("1223");

        WebElement CVC = driver.findElement(By.xpath("//input[@placeholder='CVC']"));
        CVC.sendKeys("000");

        driver.switchTo().parentFrame();

        WebElement payButton = driver.findElement(By.cssSelector("[class='Pay-Button']"));
        payButton.click();
        wait.until(ExpectedConditions.titleContains("E-junkie - Thank you"));
        //MyFunction.wait(20);

        WebElement confirmationMessage = driver.findElement(By.xpath("//span[@class='green_text_margin']"));
        Assert.assertTrue(confirmationMessage.getText().contains("your order is confirmed. Thank you!"), "the process was not true");

//        System.out.println("driver.getCurrentUrl() = " + driver.getCurrentUrl());
    }

    @Test
    public void test5() {
          /*

Test Case:5

➢ https://shopdemo.e-junkie.com/ sitesine gidiniz
➢ ContactUs butonuna tıklattınız
➢ Name, Email, Subject ve mesaj kısımlarını doldurun
➢ Send butonuna tıklatın
➢ Alert in görüldüğünü doğrulayın ve alert I kapatın
 */
        driver.get("https://shopdemo.e-junkie.com/");

        WebElement contactUs = driver.findElement(By.linkText("Contact Us"));
        contactUs.click();

        WebElement name = driver.findElement(By.id("sender_name"));
        name.sendKeys(randomGenerator.name().fullName());

        WebElement email = driver.findElement(By.id("sender_email"));
        email.sendKeys(randomGenerator.internet().emailAddress());

        WebElement subject = driver.findElement(By.id("sender_subject"));
        subject.sendKeys(randomGenerator.shakespeare().kingRichardIIIQuote());

        WebElement message = driver.findElement(By.id("sender_message"));
        message.sendKeys(randomGenerator.chuckNorris().fact());

        WebElement sendMessage = driver.findElement(By.id("send_message_button"));
        sendMessage.click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        waitAndClose();





    }

}
