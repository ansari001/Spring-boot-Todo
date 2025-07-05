package com.ansari.todo.repository.todo;

import com.ansari.todo.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    Optional<List<Todo>> findByUsername(String username);
}
