package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.List;

public class YopmailEmails extends BasePage {
    public YopmailEmails(WebDriver driver) {
        super(driver);
    }

    public WebElement getRefreshButton(String btnName) {
        return driver.findElement(By.id(btnName));
    }

    public WebElement getParentEmailList() {
        return driver.findElement(By.cssSelector("div.mctn"));
    }

    public List<WebElement> getEmails(WebElement parentDiv) {
        return parentDiv.findElements(By.cssSelector("div.m"));
    }

    public WebElement getExpediteur(WebElement email) {
        return email.findElement(By.cssSelector("span.lmf"));
    }

    public WebElement getNextButton() {
        return driver.findElement(By.cssSelector("button[title='Suivant']"));
    }

    public List<WebElement> getDivsEmail() {
        return driver.findElements(By.tagName("div"));
    }

    public WebElement getIframeElement(String iframeName) {
        return driver.findElement(By.id(iframeName));
    }
    
    
    // rafraichir la liste des emails
    public void refreshEmailList(String btnName) {
        
    	//tempo de 3 secondes pour attendre que le mail arrive ;)
    	try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement btnRefresh = getRefreshButton(btnName);
        btnRefresh.click();
    }

    ////parcours tous les emails dans toutes les pages qui ont l'expéditeur passé en param.
    public void searchEmail(String emailAdresse) {
        
    	// Boucle pour trouver tous les emails provenant de contact@hightest.nc dans la page courante
    	while (true) {
    		
            switchToIframe("ifinbox");
            
            // Récupération de la balise parent qui contient la liste des emails
            WebElement parentDiv = getParentEmailList();
            
            // Création d'une liste de tous les emails contenues dans la balise parent
            List<WebElement> emails = getEmails(parentDiv);

            // Parcours de la liste d'emails
            for (WebElement email : emails) {
                WebElement expediteur = getExpediteur(email);
                if (expediteur.getText().equals(emailAdresse)) {
                    email.click();	// affiche l'email
                    SearchEmailContent();	//parcours du contenu du mail affiché
                    
                    
                  //"Hack" - pause de 1 a 2 secondes entre chaque email pour éviter que yopmail affiche la vérification de "je ne suis pas un bot"
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            // Revenir a la frame par défaut
            driver.switchTo().defaultContent();
            
            
            //gestion du changement de page d'emails
            WebElement btnNext = getNextButton();
            if (btnNext.isDisplayed() && btnNext.isEnabled()) {
                btnNext.click();
            } else {
                break;
            }
        }
    }

    //Parcours le contenu du mail affiché et recherche la valeur "Vous avez bien répondu à 20 question(s)"
    public void SearchEmailContent() {
        switchToIframe("ifmail");

     // Cherche dans le mail si le score obtenu est de 20/20
        List<WebElement> divElements = getDivsEmail();
        for (WebElement divElement : divElements) {
            if (divElement.getText().startsWith("Vous avez bien répondu à 20 question(s)")) {
                JOptionPane.showMessageDialog(null, "Félicitations, vous avez obtenu un score de 100% au Quizz!");
                System.exit(0);
                quitDriver();
                break;
            }
        }
        switchToIframe("ifinbox");
    }

    
    ////accède au contenu de l'iframe en param.
    public void switchToIframe(String iframeName) {
    	//retour à la frame générale
    	driver.switchTo().defaultContent();

    	// "Hack" pour éviter une erreure StaleElementReferenceException
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      //switch à la frame iframeName
        WebElement iframeElement = getIframeElement(iframeName);
        driver.switchTo().frame(iframeElement);
    }


}