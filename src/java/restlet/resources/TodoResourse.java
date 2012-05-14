/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restlet.resources;

import classes.Todo;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
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
        // The PUT request updates
        if (todo == null) {
            setStatus(Status.CLIENT_ERROR_NOT_FOUND);
            return;
        }
        try {
            JSONObject req = (new JsonRepresentation(entity)).getJsonObject();
            
            todo.setDescription(req.getString("description"));
            todo.setStatus(req.getString("status"));
            
            if (getTodos().putIfAbsent(todo.getId(), todo) == null) {
                setStatus(Status.SUCCESS_CREATED);
            } else {  
                setStatus(Status.SUCCESS_OK);  
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }            
    } 
    
    @Delete("json")
    public void removeTodo(Representation entity) throws JSONException {  
        // The DELETE request remove existing resourse
        if (todo != null) {  
            // Remove the item from the list.  
            getTodos().remove(todo.getId());  
        }  
        // Tells the client that the request has been successfully fulfilled.  
        setStatus(Status.SUCCESS_NO_CONTENT);  
    }
    
    @Get("json")
    public String represent() {
        // The GET request return resourse JSON        
        return todo.toJson();
    }    
    
    private ConcurrentMap<Integer, Todo> getTodos(){
         return ((RestletApp) getApplication()).getTodos();
    }
}
