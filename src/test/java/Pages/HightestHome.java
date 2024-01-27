package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HightestHome extends BasePage {
    public HightestHome(WebDriver driver) {
        super(driver);
    }
    
    private WebElement getToolboxLink() {
        return driver.findElement(By.xpath("//a[@class='nav-link' and contains(text(),'Toolbox')]"));
    }
    
    public WebElement getNavbar() {
    	return driver.findElement(By.cssSelector("div.navbar-brand"));
    }

    
    public WebElement getNavBtn() {
        WebElement button = driver.findElement(By.cssSelector("button.navbar-toggler.d-block.d-xl-none"));
        return button;
    }
    
    
    // Click on Toolbox link
    public void clickToolboxLink() {
    	//attend que la navbar s'affiche
    	WebElement navbar = getNavbar();
    	waitForElement(navbar);
        
        
        WebElement navBtn = getNavBtn();
        
        if (navBtn.isDisplayed()) {		// test responsive
        	navBtn.click();
        	waitForElement(getToolboxLink());	//attend que le lien vers toolbox soit affiché car il arrive progressivement
        	moveToElement(getToolboxLink());	//focus sur le lien ToolBox
        	getToolboxLink().click();
        }
        else
        {
            getToolboxLink().click();
        }
        
    }
}
