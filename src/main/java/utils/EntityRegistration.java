package utils;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;
import entities.Post;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Poiike Entities Registration
 */

public class EntityRegistration implements ServletContextListener {

    static {
        JodaTimeTranslators.add(ObjectifyService.factory());
        ObjectifyService.register(Post.class);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}