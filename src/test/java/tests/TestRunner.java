package tests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Feature",
        glue = {
                "tests",           // step definitions
                "stepsforHooks"    // hooks (VERY IMPORTANT)
        },
        plugin = {"pretty"},
        monochrome = true
)
public class TestRunner {
}
