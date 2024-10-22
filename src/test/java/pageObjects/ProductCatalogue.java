package pageObjects;

import AbstractComponent.AbstractReusable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractReusable {
        WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addTOCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector(".ng-animating");



    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;

    }

    public WebElement getProductByName(String productName){
        return getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText()
                        .equals(productName)).findFirst().orElse(null);


    }

    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addTOCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }





}
