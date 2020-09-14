package br.com.example.desafioesig.controller;

import br.com.example.desafioesig.model.Task;
import br.com.example.desafioesig.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component("taskController")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    private Task tarefa = new Task(false);

    public void novaTask() {
        taskRepository.save(tarefa);
        tarefa = new Task();
    }

    public List<Task> getLista() {
        return taskRepository.findAll();
    }

    public void remover(Task tarefa) {
        taskRepository.delete(tarefa);
    }

    public void atualizarStatus(Task tarefa) {
        tarefa.setDone(!tarefa.isDone());
        taskRepository.save(tarefa);
    }

    public void atualizarDescription(Task tarefa) {
        taskRepository.save(tarefa);
    }

    public Task getTarefa() {
        return tarefa;
    }
}
