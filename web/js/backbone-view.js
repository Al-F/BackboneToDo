/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//to create a view class
var TodoView = Backbone.View.extend({
    render: function(){
        var html = '<h3>' + this.model.get('description') + '</h3>';
        $(this.el).html(html);
    }
});

//to create a view instance
var todoView = new TodoView(
    { model: todoItem }
);

todoView.render();

console.log(todoView.el);


