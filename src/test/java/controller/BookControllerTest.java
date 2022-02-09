package controller;

import com.nosql.mongolib.controller.BookController;
import com.nosql.mongolib.domain.BookDto;
import com.nosql.mongolib.service.BookService;
import lombok.val;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import(BookController.class)
class BookControllerTest {

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @Configuration
    static class Config {
    }

    @Test
    void bookList() throws Exception {
        List<BookDto> list = List.of(
                BookDto.builder().id("1").title("title1").build(),
                BookDto.builder().id("2").title("title2").build());
        when(bookService.findAllBooks()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("books", hasSize(2)));

        verify(bookService, times(1)).findAllBooks();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    void editPage() throws Exception {
        when(bookService.findById("1")).thenReturn(BookDto.builder().id("1").build());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/edit").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("book",
                        hasProperty("id", Is.is("1"))));
    }

    @Test
    void saveEditedPage() throws Exception {
        val newBook = BookDto.builder()
                .id("1")
                .title("title")
                .author("author")
                .genre("genre")
                .build();
        when(bookService.saveNewBook(newBook)).thenReturn(newBook);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/edit")
                        .content(newBook.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }

    @Test
    public void delete() throws Exception
    {
        doNothing().when(bookService).deleteBook(anyString());

        mockMvc
                .perform( MockMvcRequestBuilders.delete("/delete/{id}", 1) )
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }
}