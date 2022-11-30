package api.simulatedEnvironment.stepDefs;

import api.simulatedEnvironment.pages.ParcelsAndTrackingPage;
import api.simulatedEnvironment.pages.ServicePointsOnMapPage;
import api.simulatedEnvironment.utilities.helpers.BrowserUtils;
import api.simulatedEnvironment.utilities.helpers.ConfigurationReader;
import api.simulatedEnvironment.utilities.helpers.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class PostiStepDefs {

  String url = ConfigurationReader.get("baseUrl");
  String trackingNr = ConfigurationReader.get("trackingNrTestData");
  String locationTestData = ConfigurationReader.get("locationWithZipCodeTestData");
  ParcelsAndTrackingPage parcelsAndTrackingPage = new ParcelsAndTrackingPage();
  ServicePointsOnMapPage servicePointsOnMapPage = new ServicePointsOnMapPage();

  FluentWait wait = new FluentWait(Driver.get());
  String sendLetterPostcardUrlSecondPart = "/letters-and-mail/send-a-letter-or-postcard";

  String actualUrl;
  String expectedUrl;
  String urlBeforeFetchingLocation;
  String urlAfterFetchingLocation;

  public PostiStepDefs() throws MalformedURLException {
  }

  @Given("user navigates to {string}")
  public void userNavigatesTo(String urlEndpoint) throws MalformedURLException {
    Driver.get().get(url + urlEndpoint);
  }

  @Then("user accepts all cookies")
  public void userAcceptsAllCookies() throws MalformedURLException {
    BrowserUtils.waitForClickablility(parcelsAndTrackingPage.acceptAllCookiesButton, Duration.ofSeconds(30));
    parcelsAndTrackingPage.acceptAllCookiesButton.click();
  }

  @When("user inputs tracking number and searches for info about his shipment")
  public void userInputsTrackingNumberAndSearchesForInfoAboutHisShipment()
      throws MalformedURLException {
    BrowserUtils.waitForClickablility(parcelsAndTrackingPage.itemTrackingIdInputField, Duration.ofSeconds(30));
    parcelsAndTrackingPage.itemTrackingIdInputField.sendKeys(trackingNr);
    parcelsAndTrackingPage.itemTrackingSearchButton.sendKeys(Keys.RETURN);
  }

  @Then("user checks and validates notice text that shipment is not found")
  public void userChecksAndValidatesNoticeTextThatShipmentIsNotFound()
      throws IOException {
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    BrowserUtils.waitForVisibility(parcelsAndTrackingPage.noItemCouldBeFoundImage, Duration.ofSeconds(30));
    BrowserUtils.waitForVisibility(parcelsAndTrackingPage.noItemFoundTextBlock, Duration.ofSeconds(30));
    String actualText = parcelsAndTrackingPage.noItemFoundTextBlock.getText();
    String expectedText = new String(Files.readAllBytes(Paths.get("notFound.txt")));
    Assertions.assertEquals(expectedText, actualText);
  }

  @And("press on button to send a letter")
  public void pressOnButtonToSendALetter() throws MalformedURLException {
    BrowserUtils.waitForClickablility(parcelsAndTrackingPage.sendALetterButton, Duration.ofSeconds(30));
    parcelsAndTrackingPage.sendALetterButton.sendKeys(Keys.RETURN);
  }

  @Then("user validates that he can send letters & postcards without login")
  public void userValidatesThatHeCanSendLettersPostcardsWithoutLogin() throws MalformedURLException {
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    BrowserUtils.waitForVisibility(parcelsAndTrackingPage.regularLetterSendingOption, Duration.ofSeconds(30));
    actualUrl = Driver.get().getCurrentUrl();
    expectedUrl = url + sendLetterPostcardUrlSecondPart;
    Assertions.assertEquals(expectedUrl, actualUrl);
    Assertions.assertTrue(parcelsAndTrackingPage.logInButton.isDisplayed(), "Client is logged in, please logout, before further checking!");
    Assertions.assertTrue(parcelsAndTrackingPage.regularLetterSendingOption.isDisplayed(), "Something went wrong, you can not send a regular letter!");
    Assertions.assertTrue(parcelsAndTrackingPage.postCardSendingOption.isDisplayed(), "Something went wrong, you can not send a post card!");
  }

  @Then("user press on button to calculate the letter sending price and navigates to {string}")
  public void userPressOnButtonToCalculateThePriceAndNavigatesTo(String urlEndpoint) throws MalformedURLException {
    parcelsAndTrackingPage.forwardToCalculatePricePageButton.click();
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    actualUrl = Driver.get().getCurrentUrl();
    expectedUrl = url + urlEndpoint;
    Assertions.assertEquals(expectedUrl, actualUrl);
  }

  @And("user chooses letter details to calculate accurate price and press button to check results")
  public void userChoosesLetterDetailsToCalculateAccuratePriceAndPressButtonToCheckResults()
      throws MalformedURLException {
    BrowserUtils.waitForClickablility(parcelsAndTrackingPage.letterWeightListButton, Duration.ofSeconds(30));
    parcelsAndTrackingPage.letterWeightListButton.sendKeys(Keys.RETURN);
    BrowserUtils.waitForClickablility(parcelsAndTrackingPage.letterWeight250gOption, Duration.ofSeconds(30));
    parcelsAndTrackingPage.letterWeight250gOption.click();
    BrowserUtils.waitForClickablility(parcelsAndTrackingPage.showCalculatedResultButton, Duration.ofSeconds(30));
    parcelsAndTrackingPage.showCalculatedResultButton.click();
  }


  @Then("user validates that results are present after calculation")
  public void userValidatesThatResultsArePresentAfterCalculation() {
    int actualOptionsCount = parcelsAndTrackingPage.suitableProductsAfterCalculation.size();
    int expectedOptionsCount = 2;
    Assertions.assertEquals(expectedOptionsCount, actualOptionsCount);
  }

  @Then("user clicks a button and navigates to {string}")
  public void userClicksAButtonAndNavigatesTo(String urlEndpoint) throws MalformedURLException {
    BrowserUtils.waitForClickablility(parcelsAndTrackingPage.letterAndMailsNavigationButton, Duration.ofSeconds(30));
    parcelsAndTrackingPage.letterAndMailsNavigationButton.sendKeys(Keys.RETURN);
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    actualUrl = Driver.get().getCurrentUrl();
    expectedUrl = url + urlEndpoint;
    Assertions.assertEquals(expectedUrl, actualUrl);
  }

  @When("user enters a street address and zip code to find nearest pickup points")
  public void userEntersAStreetAddressAndZipCodeToFindNearestPickupPoints()
      throws MalformedURLException {
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.nearestPickupPointSearchField, Duration.ofSeconds(30));
    servicePointsOnMapPage.nearestPickupPointSearchField.sendKeys(locationTestData);
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.searchNearestPickupPointButton, Duration.ofSeconds(30));
    servicePointsOnMapPage.searchNearestPickupPointButton.click();
  }

  @Then("user should see dynamic view of highlighted service point and nearest point address {string}")
  public void userShouldSeeDynamicViewOfHighlightedServicePointAndNearestPointAddress(String nearestPointAddress) {
    wait.withTimeout(Duration.ofMillis(5000));
    wait.pollingEvery(Duration.ofMillis(250));
    wait.until(ExpectedConditions.textToBePresentInElement(servicePointsOnMapPage.nearestServicePointAddress, nearestPointAddress));
    String actualText = servicePointsOnMapPage.nearestServicePointAddress.getText();
    Assertions.assertEquals(nearestPointAddress, actualText);
  }

  @Then("user unselect pickup points and parcel lockers, to have only Postal outlets")
  public void userUnselectsPickupPointsAndParcelLockersToHaveOnlyPostalOutlets()
      throws MalformedURLException {
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.pickupPointButton, Duration.ofSeconds(30));
    servicePointsOnMapPage.pickupPointButton.click();
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.parcelLockerButton, Duration.ofSeconds(30));
    servicePointsOnMapPage.parcelLockerButton.click();
    urlBeforeFetchingLocation = Driver.get().getCurrentUrl();
  }

  @Then("user fetches his own location to check nearest service point and validates the action")
  public void userFetchesHisOwnLocationToCheckNearestServicePointAndValidatesTheAction()
      throws MalformedURLException {
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.shareYourLocationButton, Duration.ofSeconds(30));
    servicePointsOnMapPage.shareYourLocationButton.click();
    wait.withTimeout(Duration.ofMillis(10000));
    wait.pollingEvery(Duration.ofMillis(250));
    wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBeforeFetchingLocation)));
    urlAfterFetchingLocation = Driver.get().getCurrentUrl();
    Assertions.assertNotEquals(urlBeforeFetchingLocation, urlAfterFetchingLocation);
  }

  @Then("user checks that he can zoom in & out the map")
  public void userChecksThatHeCanZoomInOutTheMap() throws MalformedURLException {
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.mapZoomIn, Duration.ofSeconds(30));
    servicePointsOnMapPage.mapZoomIn.click();
    String urlAfterZoomingIn = Driver.get().getCurrentUrl();
    Assertions.assertNotEquals(urlAfterFetchingLocation, urlAfterZoomingIn);
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.mapZoomOut, Duration.ofSeconds(30));
    servicePointsOnMapPage.mapZoomOut.click();
    String urlAfterZoomingOut = Driver.get().getCurrentUrl();
    Assertions.assertNotEquals(urlAfterZoomingIn, urlAfterZoomingOut);
  }

  @Then("users that he can open map in full screen")
  public void usersThatHeCanOpenMapInFullScreen() throws MalformedURLException {
    BrowserUtils.waitForPageToLoad(Duration.ofSeconds(30));
    BrowserUtils.waitForClickablility(servicePointsOnMapPage.mapFullScreenButton, Duration.ofSeconds(30));
    servicePointsOnMapPage.mapFullScreenButton.click();
    Assertions.assertTrue(Driver.get().findElements(By.xpath("//*[@aria-pressed = 'false']")).isEmpty(), "Full screen button did not work!");
    servicePointsOnMapPage.mapFullScreenButton.click();
    Assertions.assertFalse(Driver.get().findElements(By.xpath("//*[@aria-pressed = 'false']")).isEmpty(), "Full screen button did not work!");
  }
}
