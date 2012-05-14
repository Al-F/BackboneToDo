<%-- 
    Document   : index
    Created on : May 12, 2012, 3:56:16 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TODO</title>
        <link href="css/todo.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <script src="js/jscore/jquery-1.7.2.js"></script>
        <script src="js/jscore/underscore.js"></script>
        <script src="js/jscore/backbone.js"></script>
        <script src="js/backbone-model.js"></script>
        <script src="js/backbone-view.js"></script>
    </head>
    <body>         
        <div class="container">
            <h1>Todo list</h1>
            <form class="well form-inline">
                <input type="text" class="input-xxlarge" id="todo-desc" placeholder="What need to do?">                
                <div class="btn btn-primary" id="add-todo">Add todo</div>
            </form>
            <div id="todos" class="todo-list">
                <div></div>
            </div>
        </div>
    </body>
</html>
