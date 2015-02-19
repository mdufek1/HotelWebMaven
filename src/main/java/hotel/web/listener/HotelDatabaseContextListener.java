/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.listener;

import hotel.web.model.HotelService;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author mdufek1
 */
public class HotelDatabaseContextListener  implements ServletContextListener{
    
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        HotelService hs = (HotelService)ctx.getBean("HotelService");
        sc.setAttribute("hotelService", hs);
    }
    
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }
}
