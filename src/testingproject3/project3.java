package testingproject3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

import java.time.Duration;

/* ➢ https://shopdemo.e-junkie.com/ sitesine gidiniz
➢ E-book'a tıklatınız
➢ E-book add to cart butonuna tıklatınız.
➢ Add promo code butonuna tıklatıp veri giriniz.
➢ Apply butonuna tıklayınız.
➢ Invalid promo code yazısının görüldüğünü doğrulayınız
 */
public class project3 extends BaseDriver {

    WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
    @Test
    public void test1(){

        driver.get("https://shopdemo.e-junkie.com/");

        WebElement eBook= driver.findElement(By.linkText("Ebook"));
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
    public void test2(){
        driver.get("https://shopdemo.e-junkie.com/");
        WebElement eBook= driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payCreditCard= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Payment-Button CC']")));
        payCreditCard.click();

        WebElement payButton= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Pay-Button']")));
        payButton.click();

        WebElement confirmationMessage= driver.findElement(By.xpath("//span[text()='Invalid Email']"));
        Assert.assertEquals(confirmationMessage.getText(),"Invalid Email\n" + "Invalid Email\n" + "Invalid Billing Name");

    }


}
