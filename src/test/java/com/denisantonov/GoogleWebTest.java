package com.denisantonov;

import org.junit.jupiter.api.*;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleWebTest {
    @BeforeAll
    static void configuration() {
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void setUp() {
        open("https://www.google.com");
    }

    @CsvSource({
            "selenide, https://selenide.org",
            "junit 5, https://junit.org"
    })
    @ParameterizedTest(name = "Проверка наличия урла {1} в результатах выдачи гугла по запросу {0}")
    @Tag("BLOCKER")
    void googleSearchTest(String searchQuery, String expectedUrl) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $("[id=search]").shouldHave(text(expectedUrl));
    }

    @DisplayName("Проерка попапа загрузки фото")
    @Test
    @Tag("BLOCKER")
    void googlePhotoPopupTest() {
        $("[class=nDcEnd]").click();
        $(byText("Выполните поиск по изображению в Google Объективе")).shouldBe(visible);
    }
}
