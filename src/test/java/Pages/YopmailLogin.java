package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YopmailLogin extends BasePage {
    public YopmailLogin(WebDriver driver) {
        super(driver);
    }

    
    public WebElement getCookiePopup() {
        return driver.findElement(By.className("fc-dialog-container"));
    }

    public WebElement getConsentButton(WebElement dialogContainer) {
        return dialogContainer.findElement(By.cssSelector("button.fc-button.fc-cta-consent.fc-primary-button"));
    }
    
    public WebElement getEmailField()
    {
    	 return driver.findElement(By.cssSelector("input.ycptinput"));
    }
    
    public WebElement getEmailBtn()
    {
    	return driver.findElement(By.id("refreshbut"));
    }
    
    
    public void goToEmailBox(String email)
    {
    	getEmailField().sendKeys(email);
    	getEmailBtn().click();
    }
    
    //Gère la popup de cookies Yopmail (accepte les cookies)
    public void manageCookiesYopmail() {
        WebElement dialogContainer = getCookiePopup();
        if (dialogContainer != null) {	//vérifie si la popup de gestion des cookies est affichée
            WebElement consentButton = getConsentButton(dialogContainer);
            consentButton.click();
        }
    }
}