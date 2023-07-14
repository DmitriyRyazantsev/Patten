package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id='city'] [placeholder='Город']").setValue(validUser.getCity());
        $("[class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__box'] [placeholder='Дата встречи'] ").sendKeys(firstMeetingDate);
        $("[data-test-id=name] [type=text]").setValue(validUser.getName());
        $("[class='input__control'][name='phone']").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__text").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + firstMeetingDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);


    }
}