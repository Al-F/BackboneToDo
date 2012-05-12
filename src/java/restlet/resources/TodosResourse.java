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
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import restlet.RestletApp;

/**
 *
 * @author isakov
 */
public class TodosResourse  extends ServerResource{
        
    
     /** 
     * Handle POST requests: create a new item. 
     */  
    @Post ("json")
    public Representation acceptItem(Representation entity) throws JSONException {  
        Representation result = null;        
        try {
            // Parse the given representation   
            JSONObject req = (new JsonRepresentation(entity)).getJsonObject();
            
            Todo todo = createTodo();            
            todo.setDescription(req.getString("description"));
            todo.setStatus(req.getString("status"));
            
            getTodos().put(todo.getId(), todo);
                
            setStatus(Status.SUCCESS_CREATED);
            Representation rep = new StringRepresentation(todo.toJson(),  MediaType.APPLICATION_JSON);
                //Indicates where is located the new resource.
                //rep.setIdentifier(getRequest().getResourceRef().getIdentifier() + "/" + itemName);
            result = rep;                
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return result;  
    }  
    
    @Get("json")
    public String represent() {        
        return "collection";
    }
    
    private Todo createTodo(){
         return ((RestletApp) getApplication()).createTodo();
    }
    
    private ConcurrentMap<Integer, Todo> getTodos(){
         return ((RestletApp) getApplication()).getTodos();
    }
}
