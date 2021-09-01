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

public class LeftSideBar extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    private final By buttonInMenuBar = By.className("sidebar_arrow__2XyM_");

    @FindBy(className = "sidebar_top-options__2OuKb")
    private WebElement topLeftSideMenuBarContent;

    @FindBy(className = "sidebar_bottom-options__1MQZd")
    private WebElement bottomLeftSideMenuBarContent;

    public LeftSideBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected LeftSideBar openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public LeftSideBar clickOnLeftMenuBar() {
        getElement(buttonInMenuBar).click();
        return new LeftSideBar(driver);
    }

    public List<String> getTextLeftMenuBar() {
        List<WebElement> elementsSideBarTop = topLeftSideMenuBarContent.findElements(By.className("sidebarOption_wrapper-for-tooltip__3oVHb"));
        List<String> resultContent = addWebElementsInList(elementsSideBarTop);
        List<WebElement> elementsSideBarBottom = bottomLeftSideMenuBarContent.findElements(By.className("sidebarOption_wrapper-for-tooltip__3oVHb"));
        resultContent.addAll(addWebElementsInList(elementsSideBarBottom));
        return resultContent;
    }

    private List<String> addWebElementsInList(List<WebElement> elements){
        List<String> result = new ArrayList<>();
        for (WebElement element : elements) {
            result.add(element.getText());
        }
        return result;
    }
}
