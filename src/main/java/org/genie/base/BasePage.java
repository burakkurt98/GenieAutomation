package org.genie.base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.genie.utils.DriverManager;
import io.appium.java_client.touch.WaitOptions;
import java.time.Duration;


public class BasePage {

    private final WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getAppiumDriver(), 30);

    /**
     * Checks if the provided MobileElement is displayed on the screen.
     *
     * @param element The MobileElement to check visibility.
     * @return True if the element is displayed, false otherwise.
     */
    public boolean isElementDisplayed(MobileElement element) {
        try {
            if (element != null) {
                wait.until(ExpectedConditions.visibilityOf(element));
                return element.isDisplayed();
            }
        } catch (Exception e) {
            System.out.println("Error while checking if the element is displayed.");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Gets the text from the provided MobileElement, waiting until it is visible.
     *
     * @param element The MobileElement from which to retrieve the text.
     * @return The text content of the element, or null if the element is not found or the text is empty.
     */
    public String getAccessibilityId(MobileElement element) {
        try {
            if (element != null) {
                wait.until(ExpectedConditions.visibilityOfAllElements(element));
                return element.getAttribute("content-desc");
            }
        } catch (Exception e) {
            System.out.println("Failed to get text from the element.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the text from the provided MobileElement, waiting until it is visible.
     *
     * @param element The MobileElement from which to retrieve the text.
     * @return The text content of the element, or null if the element is not found or the text is empty.
     */
    public String getTextFromElement(MobileElement element) {
        try {
            if (element != null) {
                // Waiting until the element is visible
                wait.until(ExpectedConditions.visibilityOf(element));
                return element.getText();
            }
        } catch (Exception e) {
            System.out.println("Failed to get text from the element.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Clicks on the provided MobileElement, waiting until it is clickable.
     *
     * @param element The MobileElement to click.
     */
    public void clickElement(MobileElement element) {
        try {
            if (element != null) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                System.out.println("Element clicked successfully.");
            }
        } catch (Exception e) {
            System.out.println("Failed to click the element.");
            e.printStackTrace();
        }
    }

    /**
     * Sends the specified text to the provided MobileElement, waiting until it is visible.
     *
     * @param element The MobileElement to send the text to.
     * @param text The text to be sent to the element.
     */
    public void sendTextToElement(MobileElement element, String text) {
        try {
            if (element != null) {
                wait.until(ExpectedConditions.visibilityOfAllElements(element));
                element.sendKeys(text);
                System.out.println("Text sent to element: " + text);
            }
        } catch (Exception e) {
            System.out.println("Failed to send text to the element.");
            e.printStackTrace();
        }
    }

    /**
     * Gets the value of the specified attribute from the provided MobileElement, waiting until it is visible.
     *
     * @param element The MobileElement from which to retrieve the attribute.
     * @param attribute The name of the attribute to retrieve.
     * @return The value of the attribute, or null if not found.
     */
    public String getAttributeFromElement(MobileElement element, String attribute) {
        try {
            if (element != null) {
                wait.until(ExpectedConditions.visibilityOf(element));
                return element.getAttribute(attribute);
            }
        } catch (Exception e) {
            System.out.println("Failed to get attribute: " + attribute);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Clears the text in the provided MobileElement, waiting until it is visible.
     *
     * @param element The MobileElement to clear the text from.
     */
    public void clearElementText(MobileElement element) {
        try {
            if (element != null) {
                wait.until(ExpectedConditions.visibilityOf(element));
                element.clear();
                System.out.println("Text cleared from element.");
            }
        } catch (Exception e) {
            System.out.println("Failed to clear text from the element.");
            e.printStackTrace();
        }
    }

    /**
     * Waits until the provided MobileElement becomes visible.
     *
     * @param element The MobileElement to wait for.
     * @param timeoutInSeconds The maximum time to wait for the element to become visible.
     * @return The visible MobileElement, or null if not visible within the timeout.
     */
    public MobileElement waitForElementVisibility(MobileElement element, int timeoutInSeconds) {
        try {
            return (MobileElement) wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Failed to wait for the element to become visible.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Waits until the provided MobileElement becomes clickable.
     *
     * @param element The MobileElement to wait for.
     * @param timeoutInSeconds The maximum time to wait for the element to become clickable.
     * @return The clickable MobileElement, or null if not clickable within the timeout.
     */
    public MobileElement waitForElementToBeClickable(MobileElement element, int timeoutInSeconds) {
        try {
            return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Failed to wait for the element to become clickable.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if the "First Reply" element is visible and its content is not empty.
     *
     * @return True if the first reply is visible and its content is not empty, false otherwise.
     */
    public boolean isFirstReplyVisibleAndNotEmpty(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));

            String accessibilityId = getAccessibilityId(element);
            if (accessibilityId != null && !accessibilityId.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error while checking first reply visibility and content.");
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Long-presses on the provided element and then pastes the copied content into the element.
     *
     * @param elementToPress The MobileElement where the long-press action will be performed.
     */
    public void longPressAndPaste(MobileElement elementToPress) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elementToPress));
            TouchAction<?> touchAction = new TouchAction<>(DriverManager.getInstance().getAppiumDriver());
            touchAction
                    .longPress(ElementOption.element(elementToPress)) // Elemente uzun basma
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) // 1 saniye boyunca basılı tut
                    .release()
                    .perform();
            MobileElement pasteButton = DriverManager.getInstance().getAppiumDriver().findElement(By.xpath("//android.widget.Button[@content-desc='Paste']"));
            wait.until(ExpectedConditions.elementToBeClickable(pasteButton));
            pasteButton.click();
        } catch (Exception e) {
            System.out.println("Failed to perform long-press and paste action.");
            e.printStackTrace();
        }
    }
}
