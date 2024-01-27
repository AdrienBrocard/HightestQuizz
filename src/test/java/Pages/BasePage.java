package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;
import java.util.Set;


public class BasePage {
	 protected WebDriver driver;

	 
	    public BasePage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void navigateToURL(String url) {
	        driver.get(url);
	    }
	    
	    
	    
	  //pour éviter que yopmail me prenne pour un robot on génère une nouvelle adresse email (mix maj, min et chiffres sur 10 char) à chaque lancement.
	    public String emailRandomizer() {
	        Random random = new Random();
	        StringBuilder emailAdress = new StringBuilder();

	        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	        while (emailAdress.length() < 10) {
	            int randomIndex = random.nextInt(characters.length());
	            char randomChar = characters.charAt(randomIndex);
	            emailAdress.append(randomChar);
	        }
	        emailAdress.append("@yopmail.com");
	        System.out.println(emailAdress.toString());
	        return emailAdress.toString();
	    }

	    
	    
	    // Permet d'afficher l'élément ciblé en parametre
	    public void moveToElement(WebElement elem) {
	        Actions actions = new Actions(driver);
	        actions.moveToElement(elem).perform();
	    }
	    
	    public void quitDriver() {
	        driver.quit();
	    }
	    
	    public void changeTab(String tabTitle) {	//changement d'onglet du navigateur
	    	// Récupérer les identifiants de tous les onglets
	    	Set<String> handles = driver.getWindowHandles();

	    	// Parcourir les onglets et basculer vers l'onglet cible
	    	for (String handle : handles) {
	    	    driver.switchTo().window(handle);
	    	    // Vérifier si l'onglet actuel est celui que vous recherchez
	    	    if (driver.getTitle().equals(tabTitle)) {
	    	        break;
	    	    }
	    	}
	    }
	    
	    
	    //attendre qu'un élément soit affiché
	    public void waitForElement(WebElement elem)
	    {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	         wait.until(ExpectedConditions.visibilityOf(elem));
	    }
	}