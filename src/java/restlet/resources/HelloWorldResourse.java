/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restlet.resources;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author isakov
 */
public class HelloWorldResourse extends ServerResource{
    
    @Get
    public String represent() {        
        return "hello, world"; //Handler of GET requests        
    }    
    
}
