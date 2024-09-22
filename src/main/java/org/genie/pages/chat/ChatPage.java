package org.genie.pages.chat;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.genie.constant.chat.ChatPageConstants;
import org.openqa.selenium.support.PageFactory;
import org.genie.base.BasePage;
import org.junit.Assert;

public class ChatPage extends BasePage {
    /**
     * This page is responsible for handling operations on the chat page.
     */

    @AndroidFindBy(xpath = ChatPageConstants.TEXT_FIELD)
    private MobileElement textField;
    @AndroidFindBy(xpath = ChatPageConstants.SEND_BUTTON)
    private MobileElement sendButton;
    @AndroidFindBy(xpath = ChatPageConstants.FIRST_MESSAGE)
    private MobileElement firstMessage;
    @AndroidFindBy(xpath = ChatPageConstants.SECOND_MESSAGE)
    private MobileElement secondMessage;
    @AndroidFindBy(xpath = ChatPageConstants.FIRST_REPLY)
    private MobileElement firstReply;
    @AndroidFindBy(xpath = ChatPageConstants.SECOND_REPLY)
    private MobileElement secondReply;
    @AndroidFindBy(xpath = ChatPageConstants.REGENERATE)
    private MobileElement regenerate;
    @AndroidFindBy(xpath = ChatPageConstants.COPY)
    private MobileElement copy;
    @AndroidFindBy(xpath = ChatPageConstants.SHARE)
    private MobileElement share;
    @AndroidFindBy(xpath = ChatPageConstants.MORE_OPTIONS)
    private MobileElement moreOptions;

    public ChatPage(AndroidDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        Assert.assertTrue(isElementDisplayed(textField));
        Assert.assertTrue(isElementDisplayed(sendButton));
    }

    /**
     * Clicks the "Send Button" element on the chat page.
     */
    public void clickSendButton(){
        clickElement(sendButton);
    }

    /**
     * Sends text to the "Chat Text Field" element on the chat page.
     *
     * @param message The message to be sent to the text field.
     */
    public void enterTextInChatField(String message) {
        clickElement(textField);
        sendTextToElement(textField, message);
    }

    /**
     * Retrieves and returns the text of the "First Message" if it is visible.
     *
     * @return The text of the first message, or null if the message is not visible or an error occurs.
     */
    public String getFirstMessage() {
        try {
            return getAccessibilityId(firstMessage);
        } catch (Exception e) {
            System.out.println("Error while retrieving the first message.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves and returns the text of the "First Reply" if it is visible.
     *
     * @return The text of the first reply, or null if the message is not visible or an error occurs.
     */
    public String getFirstReply() {
        try {
            return getAccessibilityId(firstReply);
        } catch (Exception e) {
            System.out.println("Error while retrieving the first reply.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves and returns the text of the "Second Message" if it is visible.
     *
     * @return The text of the second message, or null if the message is not visible or an error occurs.
     */
    public String getSecondMessage() {
        try {
            return getAccessibilityId(secondMessage);
        } catch (Exception e) {
            System.out.println("Error while retrieving the second message.");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Checks if the "First Reply" element is visible and its content is not empty by calling the original function.
     *
     * @return True if the first reply is visible and its content is not empty, false otherwise.
     */
    public boolean checkFirstReply() {
        return isFirstReplyVisibleAndNotEmpty(firstReply);
    }

    /**
     * Clicks the "Regenerate" button if it is visible and clickable.
     */
    public void clickRegenerate() {
        try {
            if (regenerate != null && regenerate.isDisplayed()) {
                regenerate.click();
            }
        } catch (Exception e) {
            System.out.println("Error while clicking the regenerate button.");
            e.printStackTrace();
        }
    }

    /**
     * Clicks the "Copy" button if it is visible and clickable.
     */
    public void clickCopy() {
        try {
            if (copy != null && copy.isDisplayed()) {
                copy.click();
            }
        } catch (Exception e) {
            System.out.println("Error while clicking the copy button.");
            e.printStackTrace();
        }
    }

    /**
     * Clicks the "Share" button if it is visible and clickable.
     */
    public void clickShare() {
        try {
            if (share != null && share.isDisplayed()) {
                share.click();
            }
        } catch (Exception e) {
            System.out.println("Error while clicking the share button.");
            e.printStackTrace();
        }
    }

    /**
     * Clicks the "More Options" button if it is visible and clickable.
     */
    public void clickMoreOptions() {
        try {
            if (moreOptions != null && moreOptions.isDisplayed()) {
                moreOptions.click();
            }
        } catch (Exception e) {
            System.out.println("Error while clicking the more options button.");
            e.printStackTrace();
        }
    }

    /**
     * Long presses on the provided element and then performs the paste action.
     */
    public void longPressAndPasteToReplyField() {
        clickElement(textField);
        longPressAndPaste(textField);
    }
}
