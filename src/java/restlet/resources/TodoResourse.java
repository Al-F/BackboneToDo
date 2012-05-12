/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restlet.resources;

import classes.Todo;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
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
        this.todo = getTodos().get(todoId);
  
        setExisting(this.todo != null);
    }

    @Put("json")
    public void storeItem(Representation entity) throws JSONException {  
        // The PUT request updates or creates the resource.  
        if (todo == null) {
            todo = new Todo(todoId);
        }
        try {
            JSONObject req = (new JsonRepresentation(entity)).getJsonObject();
            
            todo.setDescription(req.getString("description"));
            
            if (getTodos().putIfAbsent(todo.getId(), todo) == null) {
                setStatus(Status.SUCCESS_CREATED);
            } else {  
                setStatus(Status.SUCCESS_OK);  
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }            
    }  
    
    @Get("json")
    public String represent() {
        return todo.toJson();
    }    
    
    private ConcurrentMap<Integer, Todo> getTodos(){
         return ((RestletApp) getApplication()).getTodos();
    }
}
