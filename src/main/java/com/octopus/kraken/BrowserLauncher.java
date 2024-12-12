package com.octopus.kraken;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;
@Component
public class BrowserLauncher {
    /**
     * So that I don't have to hit the url on startup
     */
    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser(){
        System.setProperty("java.awt.headless", "false");
        Desktop desktop = Desktop.getDesktop();
        try{
            desktop.browse(new URI("http://localhost:8080/swagger-ui.html"));
        }catch(Exception e){
            System.out.println(e);
        }

    }

}
