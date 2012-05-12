/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//to create a view class
var TodoView = Backbone.View.extend({
    tagName: 'article',
    id: 'todo-view',
    className: 'todo',
    template: _.template('<h3><%= description %></h3>'),
    events: {
        "click h3": "alertStatus"
    },
    render: function(){
        var attributes = this.model.toJSON();
        this.$el.html(this.template(attributes));
    },      
    alertStatus: function(e) {
        alert('Hey you clicked the h3!');
    }
});

//???
//this.$el.delegate('h3', 'click', alertStatus);


//to create a view instance
var todoView = new TodoView(
    {model: todoItem}
);

todoView.render();


//just visualize, not backbone :

window.onload = onLoad;

function onLoad() {
    
    $('#test').html(todoView.el);    
}

