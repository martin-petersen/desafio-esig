package br.com.example.desafioesig.controller;

import br.com.example.desafioesig.model.Task;
import br.com.example.desafioesig.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component("taskController")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    private Task novaTarefa = new Task();

    public void novaTask() {
        taskRepository.save(novaTarefa);
        novaTarefa = new Task();
    }

    public Task getNovaTarefa() {
        return novaTarefa;
    }
}
