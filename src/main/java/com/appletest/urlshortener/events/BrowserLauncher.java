package com.appletest.urlshortener.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

/**
 * @author Jagadeesh Prakash P
 * Below file to launch the Browser once after successful launch of application
 */

@Component
public class BrowserLauncher{

    @Value("${server.port}")
    private long port;

    @Value("${server.domain}")
    private String domain;


    @Value("${server.protocol}")
    private String protocol;

    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser(){
        System.setProperty("java.awt.headless", "false");
        Desktop desktop = Desktop.getDesktop();
        try{
            desktop.browse(new URI(protocol+"://"+domain+":"+port));
        }catch(Exception e){}

    }

}