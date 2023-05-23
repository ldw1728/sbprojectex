package com.example.sbprojectex.Listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RequestListener implements ServletRequestListener{

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        log.info("this is requestListener---------------------------------");

        ServletRequestListener.super.requestDestroyed(sre);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequestListener.super.requestInitialized(sre);
    }

    

}
