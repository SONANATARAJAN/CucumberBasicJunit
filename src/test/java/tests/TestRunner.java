package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Feature",
        glue = {"tests"}, // <-- MUST MATCH PACKAGE NAME
        plugin = {"pretty", "html:target/cucumber.html"},
        monochrome = true
)
public class TestRunner  {
}
