package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCard {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldDeliveryCartTest() {
      String planningDate = generateDate(4);
        open("http://localhost:9999/");
        $("[data-test-id='city'] [placeholder='Город']").setValue("Ростов-на-Дону");
        $("[class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__box'] [placeholder='Дата встречи'] ").sendKeys(planningDate);
        $("[data-test-id=name] [type=text]").setValue("Петров Петр");
        $("[class='input__control'][name='phone']").setValue("+79280000000");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
       $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);;
    }
}