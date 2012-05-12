/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restlet;

import classes.Todo;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import restlet.resources.HelloWorldResourse;
import restlet.resources.TodoResourse;
import restlet.resources.TodosResourse;

/**
 *
 * @author isakov
 */
public class RestletApp extends Application {
    
    /** The list of items is persisted in memory. */  
    private static final ConcurrentMap<Integer, Todo> todos =   
            new ConcurrentHashMap<Integer, Todo>();
    private static int id = 1;
    
    static
    {        
        //insert todo for test
        Todo todo = new Todo(id++);        
        todo.setDescription("Pick up milk");
        todo.setStatus("incomplete");        
        todos.put(todo.getId(), todo);
    }
    
    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/hello", HelloWorldResourse.class);        
        
        // Defines a route for the resource "list of items"  
        router.attach("/todos", TodosResourse.class);  
        
        // Defines a route for the resource "item"  
        router.attach("/todos/{todoID}", TodoResourse.class);  

        return router; 
    }
    
    /** 
     * Returns the list of registered todo. 
     *  
     * @return the list of registered todo. 
     */  
    public ConcurrentMap<Integer, Todo> getTodos() {  
        return todos;  
    }
    
     /** 
     * Create new todo with unique id
     *  
     * @return the created todo
     */  
    public Todo createTodo(){
        Todo todo = new Todo(id++);
        todos.put(todo.getId(), todo);
        return todo;
    }
}
