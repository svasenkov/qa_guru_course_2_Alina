package tests.homework_2_first_autotest_launching;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AgrodomaAuthTests {
  private static String url = "http://agrodoma.ru/";
  private static String email = "piffatko@gmail.com";
  private static String password = "Qwerty123!";
  private static String firstname = "Lilit";
  private static String lastname = "Darkmoon";

  void goToAuthPage() {
    open(url);
    $(".fa-user").click();
  }

  @Test
  void successfulLogin() {
    goToAuthPage();
    $("#input-email").setValue(email);
    $("#input-password").setValue(password);
    $(byValue("Войти")).click();

    // Перейти в раздел "Основные данные"
//    $x("//ul[@class='list-unstyled']//a").shouldHave(text("Основные данные")).click();
    $(byText("Основные данные")).click();
    // Проверить, что данные в профиле совпадают с нужными
    $("#input-firstname").shouldHave(value(firstname));
    $("#input-lastname").shouldHave(value(lastname));
    $("#input-email").shouldHave(value(email));

    // Выйти из аккаунта
//    $(".box-category a[href='http://agrodoma.ru/logout/']");
    $(byText("Выйти")).click();
  }

  @Test
    void wrongEmailLogin() {
    goToAuthPage();
    $("#input-email").setValue("wrongemail@mail.com");
    $("#input-password").setValue(password);
    $(byValue("Войти")).click();

    // Проверить, что есть сообщение об ошибке
    $(".alert-danger").shouldHave(text(" Неправильно заполнены поля E-Mail и/или пароль!"));
  }

  @Test
    void wrongPasswordLogin() {
    goToAuthPage();
    $("#input-email").setValue(email);
    $("#input-password").setValue("Wrong123!");
    $(byValue("Войти")).click();

    // Проверить, что есть сообщение об ошибке
    $(".alert-danger").shouldHave(text(" Неправильно заполнены поля E-Mail и/или пароль!"));
  }

}
