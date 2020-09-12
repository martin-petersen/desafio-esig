package br.com.example.desafioesig.repository;


import br.com.example.desafioesig.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
