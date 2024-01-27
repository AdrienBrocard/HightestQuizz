package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HightestQuizzIstqb extends BasePage {
    public HightestQuizzIstqb(WebDriver driver) {
        super(driver);
    }

    //permet de récupérer un radio bouton afin de le cocher
    public WebElement getRadioElement(int index, int value) {
        return driver.findElement(By.xpath("//input[@name='" + index + "' and @value='" + value + "']"));
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.xpath("//input[@id='submit']"));
    }
    
    public WebElement getQuizzTitle() {
        return driver.findElement(By.xpath("//h1[contains(text(),'Test ISTQB Foundation en ligne')]"));
    }
    
    
    // répond au questionnaire avec les réponses fournies en param.
    public void completeQuizz(int[] answers) {
    	WebElement quizzTitle = getQuizzTitle();
    	waitForElement(quizzTitle);
        
        // "hack" - Suppression du chrono en bas de page our éviter que les éléments radio btn ciblés soient cachés
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('chrono').remove()");

        int counter = answers.length;
        // Complète le quizz
        for (int i = 0; i < counter; i++) {
            WebElement radio = getRadioElement(i, answers[i]);
            moveToElement(radio);
            radio.click();
        }

        //Validation du formulaire
        WebElement btnTermine = getSubmitButton();
        btnTermine.click();
    }
}