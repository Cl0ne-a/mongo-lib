package com.nosql.mongolib;

import com.nosql.mongolib.domain.BookDto;
import com.nosql.mongolib.service.BookService;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String bookList(Model model) {
        val books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        val book = bookService.findById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(BookDto bookDto) {
        bookService.saveNewBook(bookDto);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        val book = BookDto.builder().build();
        model.addAttribute("book", book);
        return "create";
    }

    @PostMapping("/create")
    public String saveNewBook(BookDto bookDto) {
        bookService.saveNewBook(bookDto);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")String id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }
}
