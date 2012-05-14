
//load when dom is ready (jquery)
$(function(){
    //create a view class
    var TodoView = Backbone.View.extend({
        tagName: 'article',
        id: 'todo-view',
        className: 'todo',
        //configure underscore template
        template: _.template('<h3 class="<%= status %>"><input type=checkbox ' +
            '<% if(status === "complete") print("checked") %>/>' + '<%= description %> <i class="icon-remove-sign"></i></h3>'),
        events: {            
            'click .icon-remove-sign' : 'onRemove',
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

    //create a instance of list view class
    var todoListView = new TodoListView({
        collection: todoList,
        //bind instance to #todos element
        el : $('#todos')
    });
});