package tests.homework_2_first_autotest_launching;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AgrodomaCartTests {
  private static String url = "http://agrodoma.ru/";

  void openBrowser(){
    // Открыть главную страницу
    open(url);
  }

  @Test
  void addingToCartFromProductPage () {
    openBrowser();
    // Навести курсор на раздел
    $(byTitle("Домашнее дерево")).hover();
    // Выбрать подраздел
    $(byText("Цитрус")).click();
    // Перейти на страницу продукта
    $(byText("Лимон комнатный")).click();
    // Добавить продукт в корзину
    $(byValue("В корзину")).click();
    // Проверить, что продукт есть в корзине
    $(byClassName("mini-cart-info")).shouldHave(text("Лимон комнатный"));
  }

  @Test
  void addingToCartFromCatalogPage() {
    openBrowser();
    // Перейти на страницу каталога
    $(byTitle("Бонсай домашний")).click();
    // Найти карточку товара и добавить с неё продукт в корзину
    $(withText("Гинкго")).parent().parent().$(byValue("В корзину")).click(); // как найти просто по слову "Гинкго"?
    // Проверить, что продукт есть в корзине
    $(byClassName("mini-cart-info")).shouldHave(text("Гинкго (бонсай)"));
  }

  @AfterEach
  void removeFromCart() {
    // Удалить продукт из корзины
    $(".remove").shouldBe(visible).click();
  }

}
