package com.example.mockmohan;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.mockmohan.controller.BookController;
import com.example.mockmohan.model.Book;
import com.example.mockmohan.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

  private MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();
  ObjectWriter objectWriter = objectMapper.writer();

  @Mock
  BookRepository bookRepository;

  @InjectMocks
  BookController bookController;

  Book book1 = new Book(1L, "Book1", "Book1 summary", 1);
  Book book2 = new Book(2L, "Book2", "Book2 summary", 2);


  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
  }

  @Test
  public void getAllBooks_Success() throws Exception {
    List<Book> books = new ArrayList<>(Arrays.asList(book1, book2));

    when(bookRepository.findAll()).thenReturn(books);
    mockMvc.perform(MockMvcRequestBuilders
            .get("/book/all")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", Matchers.is("Book1")));

  }
}
