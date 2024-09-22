package org.genie.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.genie.pages.chat.ChatPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.genie.pages.alert.AlertPage;
import org.genie.utils.DriverManager;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;

public class MessageSendAndVerify {
    /**
     * TEST CASE - CHATBOT:
     * 1- Navigate to Alert Page.
     * 2- Text is entered into the chat conversation.
     */

    private static final Logger logger = Logger.getLogger(MessageSendAndVerify.class);

    @Before
    public void setUp() throws MalformedURLException {
        DriverManager.getInstance().installApp("src/main/java/org/genie/utils/apk/Genie.apk");
        DriverManager.getInstance().setUp();
        assertTrue(DriverManager.getInstance().checkOpenedApp("co.appnation.geniechat"));
    }

    @Test
    public void messageSendAndVerify()
    {
        System.out.println("Mobil Automation started.");
        System.out.println("1-Navigate to Alert Page.");
        AlertPage alertPage = new AlertPage(DriverManager.getInstance().getAppiumDriver());
        alertPage.clickContinue();
        System.out.println("Reached the Chat Page.");

        System.out.println("2- Text is entered into the chat conversation.");
        ChatPage chatPage = new ChatPage(DriverManager.getInstance().getAppiumDriver());
        chatPage.enterTextInChatField("Test Message!");
        chatPage.clickSendButton();
        assertEquals("Test Message! ", chatPage.getFirstMessage());
        assertTrue(chatPage.checkFirstReply());
        System.out.println("Message and output checked.");
    }

    @After
    public void tearDown() {
        DriverManager.getInstance().tearDown();
    }
}
