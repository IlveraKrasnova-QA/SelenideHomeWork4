import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FindingAnExampleJUnitTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1928x1080";
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void successfulFindingAnExampleJUnitTest() {
        open("https://github.com/");
        $("[class=search-input]").click();
        $("[id=query-builder-test]").setValue("Selenide").pressEnter();
        $("[class=prc-Link-Link-85e08]").click();
        $("[id=wiki-tab]").click();
        $("[id=wiki-body]").shouldHave(text("Soft assertions"));
        $("[id=wiki-body]").$(byText("Soft assertions")).click();
        $("[class=markdown-body]").shouldHave(text("3. Using JUnit5 extend test class:"));
        String expectedCode =
                """
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                        
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }""";
        $("[class=markdown-body]").shouldHave(Condition.text(expectedCode));
    }
}
