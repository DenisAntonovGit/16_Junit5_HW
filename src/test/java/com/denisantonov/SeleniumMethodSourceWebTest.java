package com.denisantonov;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SeleniumMethodSourceWebTest {
    @BeforeAll
    static void configuration() {
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void setUp() {
        open("https://www.selenium.dev");
    }

    static Stream<Arguments> seleniumMainMenuTest() {
        return Stream.of(
                Arguments.of("About", List.of("About Selenium", "Structure and Governance", "Events", "Ecosystem", "History", "Get Involved", "Sponsors")),
                Arguments.of("English", List.of("Português (Brasileiro)", "中文简体", "日本語", "Other"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок дропдауна {1} на сайте селениум в разделах меню{0}")
    @Tag("BLOCKER")
    void seleniumMainMenuTest(String subject, List<String> buttons) {
        $("#main_navbar").find(byText(subject)).click();
        $$(".dropdown-menu a").filter(visible)
                .shouldHave(CollectionCondition.texts(buttons));
    }
}
