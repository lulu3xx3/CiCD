package pageObjects;

import AbstractComponent.AbstractReusable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractReusable {


    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> cartProducts;



    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyListOfCartProducts(String productName){
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
}
