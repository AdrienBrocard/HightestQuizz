package Test;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.BasePage;
import Pages.HightestHome;
import Pages.HightestToolBox;
import Pages.HightestQuizzIstqb;
import Pages.HightestSendAnswers;
import Pages.YopmailEmails;
import Pages.YopmailLogin;


public class test {
    public static void main(String[] args) {
    	
    	//initialisation du driver Chrome
    	String relativePathToExe = "src/test/resources/Chrome_Driver/chromedriver.exe";
    	String absolutePathToExe = System.getProperty("user.dir") + "/" + relativePathToExe;
    	System.setProperty("webdriver.chrome.driver", absolutePathToExe);
        WebDriver driver = new ChromeDriver();
        
        BasePage pagebase = new BasePage(driver);
        HightestHome homePage = new HightestHome(driver);   
        HightestToolBox toolBoxPage = new HightestToolBox(driver);
        HightestQuizzIstqb pageQuizz = new  HightestQuizzIstqb(driver);
        HightestSendAnswers sendQuizz = new HightestSendAnswers(driver);       
        
        YopmailEmails pageEmails = new YopmailEmails(driver);
        YopmailLogin pageYopmailLogin = new YopmailLogin(driver);
    	
    	
        
        //Passage de la fenêtre Chrome en plein écran
        driver.manage().window().maximize();
        
        //pour éviter que yopmail me prenne pour un robot on génère une nouvelle adresse email à chaque lancement.
	    String emailAdress= pagebase.emailRandomizer();
	    
	    // réponses au Quizz
        int[] quizzAnswers = {1, 2, 1, 2, 2, 3, 2, 4, 1, 3, 4, 2, 3, 2, 4, 3, 3, 1, 2, 2};
        
        
        pagebase.navigateToURL("https://hightest.nc/");
        homePage.clickToolboxLink();
        toolBoxPage.closePopupPartage();
        toolBoxPage.clickToTest();
        pagebase.changeTab("https://hightest.nc/ressources/test-istqb.php");
        pageQuizz.completeQuizz(quizzAnswers);
        sendQuizz.sendForm(emailAdress);
        pagebase.navigateToURL("https://yopmail.com/fr/");
        pageYopmailLogin.manageCookiesYopmail();
        pageYopmailLogin.goToEmailBox(emailAdress);
        pageEmails.refreshEmailList("refresh");
        pageEmails.searchEmail("contact@hightest.nc");
        JOptionPane.showMessageDialog(null, "Vous n'avez pas obtenu 100% de bonnes réponses au Quizz, recommencez ;)");

        // Fermez le navigateur
        driver.quit();
    }
}