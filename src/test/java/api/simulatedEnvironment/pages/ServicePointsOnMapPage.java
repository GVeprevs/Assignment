package api.simulatedEnvironment.pages;

import api.simulatedEnvironment.utilities.helpers.Driver;
import java.net.MalformedURLException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ServicePointsOnMapPage {

  public ServicePointsOnMapPage() throws MalformedURLException {
    PageFactory.initElements(Driver.get(), this);
  }

  @FindBy(id = "searchTerm")
  public WebElement nearestPickupPointSearchField;

  @FindBy(xpath = "//*[contains(@class, \"searchButton\")]")
  public WebElement searchNearestPickupPointButton;

  @FindBy(xpath = "(//*[contains(@class, 't-address')])[1]")
  public WebElement nearestServicePointAddress;

  @FindBy(xpath = "//*[@aria-label = 'Share your location to search for service points.']")
  public WebElement shareYourLocationButton;

  @FindBy(xpath = "//*[text() = 'Pickup point']")
  public WebElement pickupPointButton;

  @FindBy(xpath = "(//*[text() = 'Parcel locker '])[1]")
  public WebElement parcelLockerButton;

  @FindBy(xpath = "//*[@title = 'Zoom in']")
  public WebElement mapZoomIn;

  @FindBy(xpath = "//*[@title = 'Zoom out']")
  public WebElement mapZoomOut;

  @FindBy(xpath = "//*[@title = 'Toggle fullscreen view']")
  public WebElement mapFullScreenButton;
}
