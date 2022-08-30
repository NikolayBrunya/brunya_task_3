package ru.haulmont.brunya_task_3.Service;

import ru.haulmont.brunya_task_3.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    void createBook(Book book);
}
