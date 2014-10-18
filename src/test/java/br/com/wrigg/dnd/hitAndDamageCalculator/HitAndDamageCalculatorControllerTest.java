package br.com.wrigg.dnd.hitAndDamageCalculator;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.wrigg.dnd.hitAndDamage.Character;
import br.com.wrigg.dnd.hitAndDamage.Weapon;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:baseControllerContextTest.xml",
		"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
@WebAppConfiguration
public class HitAndDamageCalculatorControllerTest {

	private MockMvc mockMvc;
	//private Character characterMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();

	}

	@Test
	public void welcome_ShouldShowWelcomeMessageTest() throws Exception {
		mockMvc.perform(get("/hitAndDamageCalculator"))
				.andExpect(status().isOk())
				.andExpect(view().name("hitAndDamageCalculator"))
				.andExpect(
						forwardedUrl("/WEB-INF/pages/hitAndDamageCalculator.jsp"));
	}

	@Test
	public void welcome_ShouldShowDiceTypeDamageTest() throws Exception {
		Weapon weapon = new Weapon();
		weapon.setName("Kukri");
		Character character = new Character();
		character.equip(weapon);
		
		//when(todoServiceMock.add(isA(TodoDTO.class))).thenReturn(added);
		
		mockMvc.perform(get("/hitAndDamageCalculator"))
			.andExpect(status().isOk())
			.andExpect(view().name("hitAndDamageCalculator"))
			.andExpect(forwardedUrl("/WEB-INF/pages/hitAndDamageCalculator.jsp"));

		mockMvc.perform(post("/hitAndDamageCalculator")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("weaponSelect", "Kukri")
	                .sessionAttr("character", character))
//	      .andExpect(status().)
	      .andExpect(view().name("redirect:hitAndDamageCalculator"))
	      .andExpect(redirectedUrl("/hitAndDamageCalculator"))
	      .andExpect(model().attribute("character", is(character)));
		
	}
	// @Test
	// public void
	// calculateDamageRollShouldadd_NewTodoEntry_ShouldAddTodoEntryAndRenderViewTodoEntryView()
	// throws Exception {
	// Todo added = new TodoBuilder()
	// .id(1L)
	// .description("description")
	// .title("title")
	// .build();
	//
	// when(todoServiceMock.add(isA(TodoDTO.class))).thenReturn(added);
	//
	// mockMvc.perform(post("/todo/add")
	// .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	// .param("description", "description")
	// .param("title", "title")
	// .sessionAttr("todo", new TodoDTO())
	// )
	// .andExpect(status().isMovedTemporarily())
	// .andExpect(view().name("redirect:todo/{id}"))
	// .andExpect(redirectedUrl("/todo/1"))
	// .andExpect(model().attribute("id", is("1")))
	// .andExpect(flash().attribute("feedbackMessage",
	// is("Todo entry: title was added.")));
	//
	// ArgumentCaptor<TodoDTO> formObjectArgument =
	// ArgumentCaptor.forClass(TodoDTO.class);
	// verify(todoServiceMock, times(1)).add(formObjectArgument.capture());
	// verifyNoMoreInteractions(todoServiceMock);
	//
	// TodoDTO formObject = formObjectArgument.getValue();
	//
	// assertThat(formObject.getDescription(), is("description"));
	// assertNull(formObject.getId());
	// assertThat(formObject.getTitle(), is("title"));
	// }

}
