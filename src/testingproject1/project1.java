package testingproject1;
/*Url: https://itera-qa.azurewebsites.net/
        Locatorlar: Css Selector ve xPath olacak
        Not: Projeyi baştan kendiniz oluşturun. Kopyalama yapmayın.



        Test Case 1: Kayıt oluşturma

        • Siteye gidin.
        • “Sign Up” tıklatınız.
        • “Ad new User” altındaki açılan formu eksiksiz doldurunuz.
        • Submit butonuna tıklatınız.
        • Başarılı bir şekilde kayıt oluşturduğunuzu doğrulayınız.



        Test Case 2: Giriş Yapma

        • Siteye gidiniz.
        • “Login” butonuna tıklatınız.
        • Oluşturmuş olduğunuz username ve password’u giriniz.
        • Login Butonuna tıklayınız.
        • Siteye giriş yaptığınızı doğrulayınız.



        Test Case 3: Yeni Müşteri Oluşturma

        • Siteye gidin.
        • Siteye giriş yapın.
        • “CREATE NEW” Butonuna tıklayınız.
        • Customer altındaki formu doldurunuz.
        • Create Butonuna tıklatınız.*/
import org.junit.Test;
import utility.BaseDriver;

public class project1 extends BaseDriver {
    @Test
    public void test1(){
        driver.get("https://itera-qa.azurewebsites.net/");

    }
}
