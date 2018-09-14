package com.wholegroup.dbtest;

import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ObjectifyWebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectifyService.init();
        ObjectifyService.register(TestServlet.TestCounter.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
