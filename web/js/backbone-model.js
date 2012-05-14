
//create a model class with restful
var TodoItem = Backbone.Model.extend({
    urlRoot: 'api/todos',
    defaults: {
        description: 'Empty todo...',
        status: 'incomplete'
    },
    toggleStatus: function(){
        if(this.get('status') === 'incomplete'){
            this.set({
                'status': 'complete'
            });
        }else{
            this.set({
                'status': 'incomplete'
            });
        }
        this.save();
    }
});

//create a model list class with restful
var TodoList = Backbone.Collection.extend({
    url: 'api/todos',
    model: TodoItem
});

//create a instance of list
var todoList = new TodoList();

//fetch with data
todoList.fetch();