package api.simulatedEnvironment.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/features"},
    plugin = {"pretty", "html:target/cucumber/report.html", "json:target/cucumber-report.json"},
    glue = {"api.simulatedEnvironment.stepDefs", "api.simulatedEnvironment.hooks"}
)
public class TestRunner {

  private TestRunner() {
  }
}
