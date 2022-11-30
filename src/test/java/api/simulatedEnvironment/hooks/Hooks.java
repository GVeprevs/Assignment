package api.simulatedEnvironment.hooks;

import api.simulatedEnvironment.utilities.helpers.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.net.MalformedURLException;
import java.time.Duration;

public class Hooks {

  @Before("@ui")
  public void beforeUiTaggedTests() throws MalformedURLException {
    Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    Driver.get().manage().window().maximize();
  }

  @After("@ui")
  public void afterUiTaggedTests() throws MalformedURLException {
    Driver.closeDriver();
  }
}
