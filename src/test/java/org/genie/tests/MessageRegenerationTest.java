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

public class MessageRegenerationTest {
    /**
     * TEST CASE:
     * 1- Navigate to Alert Page.
     * 2- Text is entered into the chat conversation.
     * 3- User Clicks the 'Regenerate' Button to Refresh the Message.
     */

    private static final Logger logger = Logger.getLogger(MessageRegenerationTest.class);

    @Before
    public void setUp() throws MalformedURLException {
        DriverManager.getInstance().setUp();
        assertTrue(DriverManager.getInstance().checkOpenedApp("co.appnation.geniechat"));
    }

    @Test
    public void messageRegenerationTest()
    {
        System.out.println("Mobil Automation started.");
        System.out.println("1-Navigate to Alert Page.");
        AlertPage alertPage = new AlertPage(DriverManager.getInstance().getAppiumDriver());
        alertPage.clickContinue();
        System.out.println("Reached the Chat Page.");

        System.out.println("2- Text is entered into the chat conversation.");
        ChatPage chatPage = new ChatPage(DriverManager.getInstance().getAppiumDriver());
        chatPage.enterTextInChatField("How do you say \"How are you\" in Korean?");
        chatPage.clickSendButton();
        assertEquals("How do you say \"How are you\" in Korean? ", chatPage.getFirstMessage());
        assertTrue(chatPage.checkFirstReply());
        System.out.println("Message and output checked.");

        System.out.println("3- User Clicks the 'Regenerate' Button to Refresh the Message.");
        String firstReply = chatPage.getFirstReply();
        chatPage.clickRegenerate();
        assertNotEquals(chatPage.getFirstReply(), firstReply);
        System.out.println("The previous message and the next message are verified.");
    }

    @After
    public void tearDown() {
        DriverManager.getInstance().tearDown();
    }
}
