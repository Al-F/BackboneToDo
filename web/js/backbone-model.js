/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var doAlert = function() {
    alert('change happened!');
}

//to create a model class with restful
var TodoItem = Backbone.Model.extend({
    urlRoot: 'api/todos',
    defaults: {
        description: 'Empty todo...',
        status: 'incomplete'
    }
});

//to create a model instance
//var todoItem = new TodoItem(
//    { description: 'Pick up milk', status: 'incomplete', id: 1 }
//);

//fetch todo with id = 1
var todoItem = new TodoItem({id: 1});

todoItem.fetch();

todoItem.set({description: 'Pick up cookies.'});

todoItem.save();

//create event
todoItem.on('event-name', function(){
    alert('event-name happened!');
});

//Run the event
todoItem.trigger('event-name');


var todoItem2 = new TodoItem();

//to listen for changes
todoItem2.on('change', doAlert);

//set without triggering event
//todoItem.set({description: 'Fill prescription.'}, {silent: true});

//event triggered on change
todoItem2.set({description: 'Fill prescription.'});

//remove event listener
todoItem2.off('change', doAlert);

todoItem2.save();

todoItem2.get('id');

todoItem2.destroy();

