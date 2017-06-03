package pack;

import pack.AccountManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//https://www.mkyong.com/servlet/what-is-listener-servletcontextlistener-example/

@WebListener()
public class Listener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //System.out.println("ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        AccountManager manager=new AccountManager ();

        ServletContext sc = arg0.getServletContext();
        sc.setAttribute ( "data", manager );
    }
}