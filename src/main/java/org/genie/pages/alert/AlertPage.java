package org.genie.pages.alert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.genie.constant.alert.AlertPageConstants;
import org.openqa.selenium.support.PageFactory;
import org.genie.base.BasePage;
import org.junit.Assert;

public class AlertPage extends BasePage {

    /**
     * This page is responsible for handling operations on the alert page.
     */

    @AndroidFindBy(xpath = AlertPageConstants.CONTINUE)
    private MobileElement continueButton;
    @AndroidFindBy(xpath  = AlertPageConstants.PRIVACY_POLICY)
    private MobileElement privacyPolicy;
    @AndroidFindBy(xpath  = AlertPageConstants.TERMS_OF_USE)
    private MobileElement termsOfUse;

    public AlertPage(AndroidDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        Assert.assertTrue("Continue button is not displayed", isElementDisplayed(continueButton));
        Assert.assertTrue("Privacy Policy button is not displayed", isElementDisplayed(privacyPolicy));
        Assert.assertTrue("Terms of Use button is not displayed", isElementDisplayed(termsOfUse));
    }

    /**
     * Clicks the "Continue" element on the alert page.
     */
    public void clickContinue() {
        clickElement(continueButton);
    }
}

