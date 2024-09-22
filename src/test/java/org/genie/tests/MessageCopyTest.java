package org.genie.tests;

import org.genie.pages.alert.AlertPage;
import org.genie.pages.chat.ChatPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.genie.utils.DriverManager;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import static org.junit.Assert.*;

public class MessageCopyTest {
    /**
     * TEST CASE:
     * 1- Navigate to Alert Page.
     * 2- Text is entered into the chat conversation.
     * 3- User Selects the Previous Message for Copying
     * 4- User Verifies the Message was Copied
     */

    private static final Logger logger = Logger.getLogger(MessageCopyTest.class);

    @Before
    public void setUp() throws MalformedURLException {
        DriverManager.getInstance().setUp();
        assertTrue(DriverManager.getInstance().checkOpenedApp("co.appnation.geniechat"));
    }

    @Test
    public void messageCopyTest()
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

        System.out.println("3- User Selects the Previous Message for Copying");
        chatPage.clickCopy();
        System.out.println("The previous message is selected for copying.");

        System.out.println("4- User Verifies the Message was Copied");
        chatPage.longPressAndPasteToReplyField();
        chatPage.clickSendButton();
        assertNotEquals(chatPage.getFirstReply(), chatPage.getSecondMessage());
        System.out.println("The previous and the next messages are verified for correctness.");
    }

    @After
    public void tearDown() {
        DriverManager.getInstance().tearDown();
    }
}
