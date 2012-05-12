/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *
 * @author isakov
 */
public class Todo {     
     private int id;
     private String description;
     private String status;
     
     public Todo(int id){
         this.id = id;
     }
     
     /**
     * @return json string
     */
     public String toJson(){
         JsonObject object = new JsonObject();
         object.addProperty("id", id);
         object.addProperty("description", description);
         object.addProperty("status", status);
         
         Gson gson = new Gson();
         return gson.toJson(object);
     }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
