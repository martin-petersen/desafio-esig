package br.com.example.desafioesig.restcontroller;

import br.com.example.desafioesig.form.TaskForm;
import br.com.example.desafioesig.model.Task;
import br.com.example.desafioesig.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "task")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> listagem() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Task> cadastro(@RequestBody @Valid TaskForm tarefa, UriComponentsBuilder uriComponentsBuilder) {
        Task task = new Task(false);
        task.setDescription(tarefa.getTarefa());
        taskRepository.save(task);
        URI uri = uriComponentsBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Task> atualizar(@RequestBody Task tarefa) {
        taskRepository.save(tarefa);
        return ResponseEntity.ok().body(tarefa);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        Task task;
        if (taskRepository.findById(id).isPresent()) {
            task = taskRepository.findById(id).get();
            taskRepository.delete(task);
        }
    }
}
