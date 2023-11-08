package tests;
// 1- Open https://formsmarts.com/form/yu?mode=h5
// 2- Checked the Business.
// 3- Click on discover XYZ and select Online Advertising
// 4- Choose every day
// 5- Choose good
// 6- Click using XYZ and choose option 3
// Css selector
// Put MyFunction.wait(2); between each click vs action

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseDriver;
import utility.MyFunction;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class tests extends BaseDriver {

    @Test
    public void test1(){
        driver.get("https://formsmarts.com/form/yu?mode=h5");

        WebElement business= driver.findElement(By.cssSelector("[type='radio'][value='Business']"));
        business.click();
        MyFunction.wait(2);

        WebElement discoverXYZ= driver.findElement(By.cssSelector("[id$='_4588']"));
        discoverXYZ.click();

        MyFunction.wait(2);


        Select smenu= new Select(discoverXYZ);
        smenu.selectByVisibleText("Online Advertising");
        MyFunction.wait(2);

        WebElement everyday= driver.findElement(By.cssSelector("[id$='_89585_0']"));
        everyday.click();
        MyFunction.wait(2);

        WebElement good= driver.findElement(By.cssSelector("[id$='_4589_0']"));
        good.click();
        MyFunction.wait(2);

        WebElement XYZ= driver.findElement(By.cssSelector("[id$='_4597']"));
        XYZ.click();
        MyFunction.wait(2);

        Select smenu2= new Select(XYZ);
        smenu2.selectByIndex(3);
        waitAndClose();


    }

@Test
//     Scenario
//     1- https://www.saucedemo.com/
//     2- Do the login process.
//     3- Click Sauce Labs Backpack and add to cart.
//     4- Then go back (click Back to products)
//     5- Click Sauce Labs Bolt T-Shirt and add to cart.
//     6- Click to cart
//     7- Click CheckOut
//     8- Fill in the user information and click Continue
//     9- Here, test with Assert whether the sum of the costs of each item is equal to the Item total below.
//
//     XPATH will be used entirely in this question.
    public void test2(){
    driver.get("https://www.saucedemo.com/");
    WebElement username= driver.findElement(By.xpath("//input[@id='user-name']"));
    username.sendKeys("standard_user");

    WebElement password= driver.findElement(By.xpath("//input[@id='password']"));
    password.sendKeys("secret_sauce");

    WebElement loginButton= driver.findElement(By.xpath("//input[@id='login-button']"));
    loginButton.click();

    WebElement sauceLabsBackpack= driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
    sauceLabsBackpack.click();

    WebElement addToCardSauceLabsBackpack= driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
    addToCardSauceLabsBackpack.click();

    WebElement backToProducts= driver.findElement(By.xpath("//button[@id='back-to-products']"));
    backToProducts.click();

    WebElement sauceLabTshirt= driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
    sauceLabTshirt.click();

    WebElement addToCardTshirt= driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
    addToCardTshirt.click();

    WebElement shoppingTrolley= driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    shoppingTrolley.click();

    WebElement checkout= driver.findElement(By.xpath("//button[@id='checkout']"));
    checkout.click();

    WebElement firstName= driver.findElement(By.xpath("//input[@id='first-name']"));
    firstName.sendKeys("Ahmet");
    WebElement lastName= driver.findElement(By.xpath("//input[@id='last-name']"));
    lastName.sendKeys("Karaca");
    WebElement postalCode= driver.findElement(By.xpath("//input[@id='postal-code']"));
    postalCode.sendKeys("33423");

    WebElement continueButton= driver.findElement(By.xpath("//input[@id='continue']"));
    continueButton.click();

    List<WebElement> prices= driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
    System.out.println("prices.size() = " + prices.size());
    double total=0;
    for (WebElement price:prices){
        System.out.println("price.getText() = " + price.getText());
        total+=Double.parseDouble(price.getText().replaceAll("[^0-9,.]",""));
    }
    System.out.println("total = " + total);

//    String item1=prices.get(0).getText().substring(1);
//    String item2=prices.get(1).getText().substring(1);
//
//    System.out.println("item1 = " + item1);
//    System.out.println("item2 = " + item2);

//    WebElement inventoryItemPrice1= driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[1]"));
//    WebElement inventoryItemPrice2= driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[2]"));
    WebElement subTotal= driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
    double totalPrice= Double.parseDouble(subTotal.getText().replaceAll("[^0-9,.]",""));
    System.out.println("totalPrice = " + totalPrice);
    Assert.assertEquals("addition is not true", total, totalPrice, 0.0);
//    System.out.println("inventoryItemPrice1.getText() = " + inventoryItemPrice1.getText());
//    System.out.println("inventoryItemPrice2.getText() = " + inventoryItemPrice2.getText());
//    System.out.println("subTotal.getText() = " + subTotal.getText());
//
//    String itemprice1= inventoryItemPrice1.getText().substring(1);
//    String itemprice2= inventoryItemPrice2.getText().substring(1);
//    String totalPrice= subTotal.getText().replaceAll("[^0-9 .]","");
//
//    System.out.println("itemprice1 = " + itemprice1);
//    System.out.println("itemprice2 = " + itemprice2);
//    System.out.println("totalPrice = " + totalPrice);
//
//    double itemPrice1= Double.parseDouble(itemprice1);
//    double itemPrice2= Double.parseDouble(itemprice2);
//    double itemtotalPrice= itemPrice1+itemPrice2;
//    double totalPricewithoutTax= Double.parseDouble(totalPrice);
//
//    System.out.println("itemPrice1 = " + itemPrice1);
//    System.out.println("itemPrice2 = " + itemPrice2);
//    System.out.println("totalPricewithoutTax = " + totalPricewithoutTax);
//
//    Assert.assertTrue("addition is wrong",(itemtotalPrice==totalPricewithoutTax));
//

    waitAndClose();


}
@Test
/**
 * Go to this site. http://demo.seleniumeasy.com/ajax-form-submit-demo.html
 * Enter Name.
 * Enter comments.
 * Click on Submit.
 * Form submitted Successfully! text should be displayed.
 * Check with Assert
 * (Do not use XPath) **/
    public void test3(){

        driver.get("http://demo.seleniumeasy.com/ajax-form-submit-demo.html");


        WebElement name= driver.findElement(By.cssSelector("[id='title']"));
        name.sendKeys("Hasan");

        WebElement comments= driver.findElement(By.cssSelector("[id='description']"));
        comments.sendKeys("iyi gunler ");

        WebElement submitButton= driver.findElement(By.cssSelector("[id='btn-submit']"));
        submitButton.click();


        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(By.cssSelector("[id='submit-control']"),"Form submited Successfully!"));
        WebElement confirmationText= driver.findElement(By.cssSelector("[id='submit-control']")); // oncebekleyecek sonra text hazir olunca elementi alacak
        //MyFunction.wait(3);

        System.out.println("confirmationText.getText() = " + confirmationText.getText()); // it gave me  'Ajax Request is Processing!' on my first request


    //Assert.assertTrue("the test failed, the confirmation message did not appear on the screen",confirmationText.isDisplayed());
   // Assert.assertTrue("the test failed, the confirmation message did not appear on the screen",confirmationText.getText().equals("Form submited Successfully!")); asagidaki ile ayni ama asagidaki simplify edilmis daha guzel hali
    Assert.assertEquals("the test failed, the confirmation message did not appear on the screen", "Form submited Successfully!", confirmationText.getText());

    waitAndClose();
    }
@Test
//        Test Scenario
//        1- Go to https://www.facebook.com/
//        2- Click CreateNewAccount.
//        3- In the new window that opens, enter your name, surname and email.
//        4- Select your date of birth with the Select class
//        5- Verify with assert that an input that asks you to re-enter the email pops up, does not appear before, and then appears.
    public void test4(){
        driver.get("https://www.facebook.com/");

//    List<WebElement> cookiesAccept= driver.findElements(By.xpath("//button[@class='_42ft _4jy0 _al65 _4jy3 _4jy1 selected _51sy']"));
//    if (cookiesAccept.size()>0)
//        cookiesAccept.get(0).click();

    List<WebElement> cookiesAccept2= driver.findElements(By.xpath("//button[@class='_42ft _4jy0 _al65 _4jy3 _4jy1 selected _51sy']"));
    if (!cookiesAccept2.isEmpty())
        cookiesAccept2.get(0).click();


    WebElement createNewAccount= driver.findElement(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"));
        createNewAccount.click();

        WebElement name= driver.findElement(By.name("firstname"));
        name.sendKeys("hasan");

        WebElement lastname= driver.findElement(By.name("lastname"));
        lastname.sendKeys("yildiz");

        WebElement confirmationMail= driver.findElement(By.name("reg_email_confirmation__"));
        Assert.assertFalse("The confirmation email box appeared", confirmationMail.isDisplayed());

        WebElement email= driver.findElement(By.name("reg_email__"));
        email.sendKeys("asdasd@gmail.com");

        Assert.assertTrue("the confirmation email box did not appear", confirmationMail.isDisplayed());

        WebElement dayMenu= driver.findElement(By.name("birthday_day"));
        WebElement dayMonth= driver.findElement(By.name("birthday_month"));
        WebElement dayYear= driver.findElement(By.name("birthday_year"));

        Select daySelect= new Select(dayMenu);
        Select monthSelect= new Select(dayMonth);
        Select yearSelect= new Select(dayYear);

        daySelect.selectByValue("5");
        MyFunction.wait(2);
        monthSelect.selectByVisibleText("Nov");
        MyFunction.wait(2);
        yearSelect.selectByIndex(1);
        MyFunction.wait(2);

        waitAndClose();


}
/*
 Scenario:
 1- Open the site https://www.demoblaze.com/index.html.
 2- Click on the Samsung galaxy s6 link.
 3- Add to cart.
 4- Then click on PRODUCT STORE to return to the main screen.*/

    @Test
    public void test5(){

        driver.get("https://www.demoblaze.com/index.html");

        WebElement samsungS6= driver.findElement(By.linkText("Samsung galaxy s6"));
        samsungS6.click();

        WebElement addToCard= driver.findElement(By.linkText("Add to cart"));
        addToCard.click();

        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        WebElement backToProductStore= driver.findElement(By.id("nava"));
        backToProductStore.click();

        wait.until(ExpectedConditions.urlToBe("https://www.demoblaze.com/index.html"));
        Assert.assertEquals("The page did not return to the mainpage",driver.getCurrentUrl(),"https://www.demoblaze.com/index.html");

        waitAndClose();


}

     /* Scenario:
            * Search for selenium on https://google.com/.
            * Click on the first result and print the resulting URL to the screen.
     * Verify that the expected url is https://www.selenium.dev/
            */
@Test
    public void test6(){

        driver.get("https://google.com/");
        List<WebElement> cookiesAccept= driver.findElements(By.xpath("(//div[@class='QS5gu sy4vM'])[2]"));
        if (!cookiesAccept.isEmpty())
            cookiesAccept.get(0).click();
        WebElement searchBox= driver.findElement(By.id("APjFqb"));
        searchBox.sendKeys("selenium"+ Keys.ENTER); // yenilik, bu sekilde google da ara butonunu locate etmekten kurtulduk pratik

    //        WebElement searchButton=driver.findElement(By.name("btnK"));
//        searchButton.click();  // Instead of these 2 lines, the ENTER key was sent above

    WebElement seleniumFirstresult= driver.findElement(By.xpath("(//a/h3)[1]")); // dinamik olmasi icin
    seleniumFirstresult.click();

    Assert.assertEquals(driver.getCurrentUrl(),"https://www.selenium.dev/");

    waitAndClose();

}
}
