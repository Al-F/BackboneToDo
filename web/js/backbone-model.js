/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//to create a model class
var TodoItem = Backbone.Model.extend({});

//to create a model instance
var todoItem = new TodoItem(
    { description: 'Pick up milk', status: 'incomplete', id: 1 }
);

