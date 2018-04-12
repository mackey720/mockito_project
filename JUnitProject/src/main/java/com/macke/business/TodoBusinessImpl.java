package com.macke.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.macke.data.api.TodoService;


public class TodoBusinessImpl {

	private TodoService todoService;

	TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retrieveTodosRelatedToSpring(String user) {
		List<String> filteredTodos = new ArrayList<String>();
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todo : allTodos) {
			if (todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}

	public void deleteTodosNotRelatedToSpring(String user) {
		List<String> allTodos = todoService.retrieveTodos(user);
		if(allTodos == null) {
			throw new NullPointerException();
		} else {
			for (String todo : allTodos) {
				if (!todo.contains("Spring")) {
					todoService.deleteTodo(todo);
				}
			}
		}
	}
}