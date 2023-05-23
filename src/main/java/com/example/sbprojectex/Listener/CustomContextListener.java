package com.example.sbprojectex.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomContextListener implements ServletContextListener{

    private final Logger logger = LogManager.getLogger(CustomContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("-----------    context Initialized    -----------");

        ServletContextListener.super.contextInitialized(sce);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        logger.info("-----------    context Destroyed    -----------");

        ServletContextListener.super.contextDestroyed(sce);
    }

   
    
    
}
