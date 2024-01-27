package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class HightestToolBox extends BasePage {
    public HightestToolBox(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getPopupPartage()
    {
    	return driver.findElement(By.id("pum-468"));
    }
    
    public WebElement getClosePopupPartageBtn()
    {
    	return driver.findElement(By.xpath("button.pum-close.popmake-close"));
    }
    
    public WebElement getLinkToTest()
    {
    	return driver.findElement(By.cssSelector("a[href='https://hightest.nc/ressources/test-istqb.php']"));

    }

    
    public void closePopupPartage()
    {
    	WebElement popup = getPopupPartage();
    	
    	if(popup.isDisplayed())
    	{
    		getClosePopupPartageBtn().click();
    	}

    }
    
    public void clickToTest() {
		moveToElement(getLinkToTest());
    	getLinkToTest().click();
    }
}
