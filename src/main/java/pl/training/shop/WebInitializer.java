package pl.training.shop;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    private final String TEMP_FOLDER = "./";
    private final int MAX_UPLOAD_SIZE = 10 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MvcConfiguration.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
        var multipartConfigElement = new MultipartConfigElement(TEMP_FOLDER, MAX_UPLOAD_SIZE
                , MAX_UPLOAD_SIZE*2
                , MAX_UPLOAD_SIZE/2);
        dispatcher.setMultipartConfig(multipartConfigElement);
    }
}
