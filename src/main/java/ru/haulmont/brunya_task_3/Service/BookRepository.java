package ru.haulmont.brunya_task_3.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.haulmont.brunya_task_3.Book;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
