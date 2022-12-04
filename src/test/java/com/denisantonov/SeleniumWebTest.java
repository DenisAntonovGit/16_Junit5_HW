package com.denisantonov;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SeleniumWebTest {
    @BeforeAll
    static void configuration() {
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void setUp() {
        open("https://www.selenium.dev");
    }

    @CsvSource({
            "Português (Brasileiro), Selenium automatiza navegadores. É isso!",
            "日本語, Seleniumはブラウザを自動化します。そうです！"
    })
    @ParameterizedTest(name = "Проверка перевода текста на выбранный язый {1} при выборе языка {0}")
    @Tag("BLOCKER")
    void seleniumLanguageTest(String selectedLanguage, String expectedTranslatedText) {
        $("#main_navbar").find(byText("English")).click();
        $$(".dropdown-menu a").filterBy(cssClass("dropdown-item")).findBy(exactText(selectedLanguage)).click();
        $("#td-cover-block-0").shouldHave(text(expectedTranslatedText));
    }

}
