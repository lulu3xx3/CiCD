package pageObjects;


import VarunHasToWork.BaseTest;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainTest extends BaseTest {

    String productName;

    @Test(dataProvider="getData", groups = {"purchase"})
    public void submitOrder(HashMap <String, String>input) throws IOException {



        landingPage.loginApplication(input.get("email"),input.get("password"));

        String countryName = "India";
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addProductToCart(input.get("productName"));
        landingPage.clickOnCartButton();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        boolean match = checkOutPage.verifyListOfCartProducts(input.get("productName"));
        Assert.assertTrue(match);
        checkOutPage.ClickOnCheckOutButton();
        checkOutPage.selectCountry(countryName);
        checkOutPage.placeOrder();
        Assert.assertTrue(checkOutPage.confirmOrder().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }



    @Test(dependsOnMethods = {"submitOrder"},dataProvider="getData")
    public void OrderHistoryTest(HashMap <String, String>input){
        landingPage.loginApplication(input.get("email"),input.get("password"));
        OrderPage orderPage = landingPage.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyListOfCartProducts(input.get("productName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/pageObjects/dataPackage/purchaseOrder.json");
        return new Object[][] {{data.get(0)} , {data.get(1)}};

    }




}


