package com.andersen.borisov.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    private final By buttonOpenLeftMenuBar = By.className("sidebar_arrow__2XyM_");
    private final By myProfileButtonLiftMenuBar = By.xpath("//*[@id='root']//div[2]//div[2]//a");

    private ModalWindow modalWindow;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public MainPage clickToMyProfileButton() {
        getElement(myProfileButtonLiftMenuBar).click();
        this.modalWindow = new ModalWindow(this.driver);
        return this;
    }

    public LeftSideBarMenu clickOnLeftMenuBar() {
        getElement(buttonOpenLeftMenuBar).click();
        logger.info("---> Click on menu bar");
        return new LeftSideBarMenu(driver);
    }

    public boolean clickOnLeftSideMenuBarAndExit() {
        getElement(buttonOpenLeftMenuBar).click();
        clickOnMyProfileInModalWindow();
        boolean result = isLeftMenuBarOpen();
        exit();
        return result;
    }

    public boolean isLeftMenuBarOpen() {
        String classAttribute = getElement(By.tagName("nav")).getAttribute("class");
        return classAttribute.endsWith("ECnxL");
    }

    public List<String> getTextModalWindow(){
        return new ModalWindow(this.driver).getContentModalWindow();

    }

    //TODO
    public MainPage clickOnMyProfileInModalWindow() {
        this.modalWindow = new ModalWindow(driver);
        clickToMyProfileButton();
        return this;
    }

    public LoginPage exit() {
        modalWindow.clickExit();
        return new LoginPage(driver);
    }

    class ModalWindow {

        WebDriver driver;

        final By exitButton = By.xpath("//*[text()='Выйти']");
        final By supportButton = By.xpath("//*[text()='Support']");
        final By myProfileButton = By.xpath("//*[text()='Мой профиль']");

        final By popUpMenu = By.xpath("//*[@id='root']//nav//div[2]/div[2]/div[2]");


        ModalWindow(WebDriver driver) {
            this.driver = driver;
        }

        List<String> getContentModalWindow(){
            List<String> listNamesInModalWindow = new ArrayList<>();
            WebElement modalWindowElement = getElement(popUpMenu);
            List<WebElement> elements = modalWindowElement.findElements(By.className("popupMenu_option__3ZD4c"));
            for(WebElement element : elements){
                listNamesInModalWindow.add(element.getText());
            }
            return listNamesInModalWindow;
        }

        void clickExit() {
            getElement(exitButton).click();
        }

        void clickOnTelegramAdmin() {
            getElement(myProfileButton).click();
        }

        void clickOnMyProfileButton() {
            getElement(myProfileButton).click();
        }

        void clickOnSupportButton() {
            getElement(supportButton).click();
        }
    }
}
