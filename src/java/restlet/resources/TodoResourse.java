/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restlet.resources;

import classes.Todo;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import restlet.RestletApp;

/**
 *
 * @author isakov
 */
public class TodoResourse  extends ServerResource{
    
    /** The underlying Item object. */  
    Todo todo;  
  
    /** The sequence of characters that identifies the resource. */  
    Integer todoId;  
  
    @Override  
    protected void doInit() throws ResourceException {  
        // Get the "itemName" attribute value taken from the URI template  
        // /items/{itemName}.  
        this.todoId = Integer.parseInt((String)getRequest().getAttributes().get("todoID"));  
  
        // Get the item directly from the "persistence layer".  
        this.todo = ((RestletApp) getApplication()).getTodos().get(todoId);
  
        setExisting(this.todo != null);
    }
    
    @Get("json")
    public String represent() {
        return todo.toJson();
    }    
}
