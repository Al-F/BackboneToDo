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
public class TodosResourse  extends ServerResource{
        
    @Get("json")
    public String represent() {        
        return "collection";
    }    
}
