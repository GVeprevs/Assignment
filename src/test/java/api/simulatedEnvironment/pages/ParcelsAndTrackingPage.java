package api.simulatedEnvironment.pages;

import api.simulatedEnvironment.utilities.helpers.Driver;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParcelsAndTrackingPage {

  public ParcelsAndTrackingPage() throws MalformedURLException {
    PageFactory.initElements(Driver.get(), this);
  }

  @FindBy(id = "onetrust-accept-btn-handler")
  public WebElement acceptAllCookiesButton;

  @FindBy(xpath = "//*[@name = 'JJFI']")
  public WebElement itemTrackingIdInputField;

  @FindBy(xpath = "//*[@aria-label = 'Track']")
  public WebElement itemTrackingSearchButton;

  @FindBy(xpath = "//*[@src = '/img/404-500-error.svg']")
  public WebElement noItemCouldBeFoundImage;

  @FindBy(xpath = "//*[contains(@class, 'blocks')]")
  public WebElement noItemFoundTextBlock;

  @FindBy(xpath = "(//*[@href = '/en/private/letters-and-mail'])[1]")
  public WebElement letterAndMailsNavigationButton;

  @FindBy(xpath = "//*[@aria-label = 'Send a letter']")
  public WebElement sendALetterButton;

  @FindBy(xpath = "//*[@aria-label = 'Log in']")
  public WebElement logInButton;

  @FindBy(xpath = "//*[text() = 'Regular letter']")
  public WebElement regularLetterSendingOption;

  @FindBy(xpath = "//*[contains(text(), 'Post card')]")
  public WebElement postCardSendingOption;

  @FindBy(xpath = "(//*[@id = 'main']//button)[1]")
  public WebElement forwardToCalculatePricePageButton;

  @FindBy(xpath = "//*[@name= 'letterWeight']")
  public WebElement letterWeightListButton;

  @FindBy(xpath = "//*[@role= 'menuitem'][2]")
  public WebElement letterWeight250gOption;

  @FindBy(xpath = "(//*[@class = 'consumerLetterCalculator']//button)[3]")
  public WebElement showCalculatedResultButton;

  @FindBy(xpath = "//*[@class = 'productlistitem']")
  public List<WebElement> suitableProductsAfterCalculation;

  @FindBy(xpath = "//*[@title = 'Buy stamps']")
  public WebElement buyStampsButton;

  @FindBy(xpath = "(//*[@aria-label = 'Add to cart'])[1]")
  public WebElement addStampsToCartButton;

  @FindBy(xpath = "//*[@data-testid = 'cart-button']")
  public WebElement checkMyCartButton;

  // validate https://shop.posti.fi/en/cart

  @FindBy(id = "cart-totals-cta")
  public WebElement checkoutButton; // check that button is not disables and it is clickable

}
