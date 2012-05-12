/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//to create a model class with restful
var TodoItem = Backbone.Model.extend({urlRoot: '/todos'});

//to create a model instance
//var todoItem = new TodoItem(
//    { description: 'Pick up milk', status: 'incomplete', id: 1 }
//);

//fetch todo with id = 1
var todoItem = new TodoItem({id: 1});

todoItem.fetch();



