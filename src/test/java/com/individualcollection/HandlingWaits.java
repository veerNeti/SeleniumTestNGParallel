package com.individualcollection;

import com.drivers.TestBase;
import com.implementation.UtilitiesImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.services.Utilities;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HandlingWaits extends TestBase {
    private Utilities utilities;
    private WebElement countdown;
    private By countdownBy = By.id("javascript_countdown_time");
    private String auTestURL = "http://seleniumsimplified.com/testpages/javascript_countdown.html";

    public HandlingWaits() {
        this.utilities = new UtilitiesImplementation();
    }

/*    @BeforeMethod
    void report(Method method){
        extent.createTest(getClass().getName());
        extent.createTest(method.getName());
    }*/

    @Test
    void lauchjavascriptCountdownURL() {
        webDriverInstance.get(auTestURL);
        webDriverInstance.navigate().refresh();
        waitforInterface.ajaxComplete();
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
    }

    @Test(dependsOnMethods = "lauchjavascriptCountdownURL")
    void validateCountDownIsVisibleWithWebDriverWait() {
        countdown = webDriverInstance.findElement(By.id("javascript_countdown_time"));
        this.countdown = waitforInterface.webDriverWaitTillVisibilityOfElementBy(countdownBy);
        assertThat(countdown.isDisplayed());
    }

    @Test(dependsOnMethods = "validateCountDownIsVisibleWithWebDriverWait")
    public void waitValidatecountdownBy() {
        waitforInterface.waitForAngularRequestsToFinish();
        assertThat(waitforInterface.waitFluentForTexttoBeBy(countdownBy).endsWith("04"));
    }
}