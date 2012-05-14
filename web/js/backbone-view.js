
    //create a view class
    var TodoView = Backbone.View.extend({
        tagName: 'article',
        id: 'todo-view',
        className: 'todo',
        //configure underscore template
        template: _.template('<div class="todo-item"><button class="close">×</button><strong class="checbox <%= status %>"><input type=checkbox ' +
            '<% if(status === "complete") print("checked") %>/>' + ' <%= description %></strong></div>'),
        events: {            
            'click button.close' : 'onRemove',            
            'change input': 'toggleStatus'
        },
        initialize: function(){
            //subscribe on events when initialized
            this.model.on('change', this.render, this);
            this.model.on('destroy', this.remove, this);
        },
        render: function(){
            //use template and assign to html
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        onRemove: function(e){
            this.model.destroy();
        },
        toggleStatus: function(){
            this.model.toggleStatus();
        }
    });

    //create a list view class
    var TodoListView = Backbone.View.extend({        
        initialize: function(){
            //subscribe on events when initialized
            this.collection.on('add', this.addOne, this);
            this.collection.on('reset', this.addAll, this);        
        },  
        render: function(){
            this.addAll();
            return this;
        },
        addOne: function(todoItem){
            var todoView = new TodoView({
                model: todoItem
            });        
            this.$el.append(todoView.render().el);
        },
        addAll: function() {
            this.collection.forEach(this.addOne, this);
        }
    });
    
    //create a  view class
    var TopView = Backbone.View.extend({    
        events: {            
            'click #add-todo' : 'addTodo'           
        },        
        addTodo: function(){
            var desc = $('#todo-desc').val();
            $('#todo-desc').val("");
            var todo = new TodoItem();            
            todo.set({"description": desc});
            this.collection.add(todo);
        }
    });

    //create a instance of list view class
    var todoListView;
    var topView;
    
//load when dom is ready (jquery)
$(function(){   
    topView = new TopView({
        collection: todoList,
        el: $('.form-inline')
    })
    todoListView = new TodoListView({
        collection: todoList,
        el: $('#todos')
    });    
});

