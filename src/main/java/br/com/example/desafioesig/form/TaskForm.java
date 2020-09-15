package br.com.example.desafioesig.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TaskForm {
    @NotEmpty
    @NotNull
    @Length(max = 15)
    private String tarefa;

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
