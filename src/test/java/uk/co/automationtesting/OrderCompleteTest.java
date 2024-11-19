package uk.co.automationtesting;



import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pageobj.HomePage;
import com.pageobj.OrderFormDelivery;
import com.pageobj.OrderFormPayment;
import com.pageobj.OrderFormPersInfo;
import com.pageobj.OrderFormShippingMethod;
import com.pageobj.ShopContentPanel;
import com.pageobj.ShopHomePage;
import com.pageobj.ShopProdPage;
import com.pageobj.ShoppingCart;
import com.w2a.baseclass.BaseClass1;



public class OrderCompleteTest extends BaseClass1 {

	public OrderCompleteTest() throws IOException {
		super(); // calling baseclass1 constructor ,prop
	}
	
	@BeforeTest
	public void setup() throws IOException {
		driver = getDriver();
		driver.get(getUrl());
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		driver = null;
	}
	
	@Test
	public void endToEndTest() throws IOException, InterruptedException {

		HomePage home = new HomePage(driver);

		//handles cookie prompt
		home.getCookie().click();

		home.getTestStoreLink().click();
		
		ShopHomePage shopHome = new ShopHomePage(driver);
		shopHome.getProdOne().click();
		
		ShopProdPage shopProd = new ShopProdPage(driver);
		Select option = new Select(shopProd.getSizeOption());// selecting dropdown
		
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();
		Thread.sleep(5000);
		
		
		ShopContentPanel cPanel = new ShopContentPanel(driver);
		cPanel.getCheckoutBtn().click();
		
		ShoppingCart cart = new ShoppingCart(driver);
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		Thread.sleep(5000);
		cart.getProceedCheckoutBtn().click();
		
		// creating an object of the order personal information page
				OrderFormPersInfo pInfo = new OrderFormPersInfo(driver);
				pInfo.getGenderMr().click();
				pInfo.getFirstNameField().sendKeys("John");
				pInfo.getLastnameField().sendKeys("Smith");
				pInfo.getEmailField().sendKeys("johnsmith1@test.com");
				pInfo.getTermsConditionsCheckbox().click();
				pInfo.getContinueBtn().click();

				// creating an object of the order delivery info page
				OrderFormDelivery orderDelivery = new OrderFormDelivery(driver);
				orderDelivery.getAddressField().sendKeys("123 Main Street");
				orderDelivery.getCityField().sendKeys("Houston");
				Select state = new Select(orderDelivery.getStateDropdown());
				state.selectByVisibleText("Texas");
				orderDelivery.getPostcodeField().sendKeys("77021");
				orderDelivery.getContinueBtn().click();

				// creating an object of the shipping method page
				OrderFormShippingMethod shipMethod = new OrderFormShippingMethod(driver);
				shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
				shipMethod.getContinueBtn().click();

				// creating an object of the payment options page
				OrderFormPayment orderPay = new OrderFormPayment(driver);
				orderPay.getPayByCheckRadioBtn().click();
				orderPay.getTermsConditionsCheckbox().click();
				orderPay.getOrderBtn().click();
		
	}
	}


