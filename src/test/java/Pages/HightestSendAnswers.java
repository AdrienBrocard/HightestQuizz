package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HightestSendAnswers extends BasePage {
    public HightestSendAnswers(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getEmailField()
    {
    	return driver.findElement(By.id("email"));
    }
    
    public WebElement getValidateBtn()
    {
    	return driver.findElement(By.id("submitMail"));
    }
    
    public void sendForm(String emailAdress)
    {
    	getEmailField().sendKeys(emailAdress);
    	getValidateBtn().click();
    }
    

}
