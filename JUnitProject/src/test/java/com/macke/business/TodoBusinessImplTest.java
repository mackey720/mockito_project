package com.macke.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Matchers.anyString;

import com.macke.data.api.TodoService;


public class TodoBusinessImplTest {
	
	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	 @Rule
	 public ExpectedException thrown= ExpectedException.none();

	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	@Mock
	TodoService todoService;

	@Test
	public void shoulRetrieveTodosRelatedToSpring() {
		List<String> todos = Arrays.asList("Learn MVC Spring", "Do Spring Cleaning", "clean the house");
		when(todoService.retrieveTodos("Dummy")).thenReturn(todos);
		
		List<String> todoService = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(2, todoService.size());
	}
	
	@Test
	public void usingMockito_UsingBDD() {
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		//given
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");

		//then
		assertThat(todos.size(), is(2));
	}

	
	@Test
	public void shoulDeleteTodosNotRelatedToSpring() {
		List<String> todos = Arrays.asList("Learn MVC Spring", "Do Spring Cleaning", "clean the house");
		when(todoService.retrieveTodos("Dummy")).thenReturn(todos);
		
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		verify(todoService).deleteTodo("clean the house");
		verify(todoService, Mockito.never()).deleteTodo("todoService");
	}
	
	@Test(expected=NullPointerException.class)
	public void shoulDeleteTodosNotRelatedToSpring_IsNull() {
		List<String> todos = null;
		when(todoService.retrieveTodos("Dummy")).thenReturn(todos);
		
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
	}

}
