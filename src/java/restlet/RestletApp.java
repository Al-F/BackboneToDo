/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import restlet.resources.HelloWorldResourse;

/**
 *
 * @author isakov
 */
public class RestletApp extends Application {
    
    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/hello", HelloWorldResourse.class);        
        
        return router;
    }
}
