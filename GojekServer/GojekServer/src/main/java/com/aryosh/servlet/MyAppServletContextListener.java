/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aryosh.servlet;

import com.aryosh.bl.GeographicInit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author AryoSH XLINK
 */
public class MyAppServletContextListener implements ServletContextListener
{

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("AryoSH ::: Initializing servlet Start...");
        GeographicInit init = new GeographicInit();
        init.generateDriverLoc();
        System.out.println("AryoSH ::: Initializing servlet Done...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("AryoSH ::: Destroying servlet");
    }
    
}
