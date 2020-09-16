package br.com.example.desafioesig.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @NotNull
	private String description;
	private boolean done;

	public Task() {
	}

	public Task(boolean done) {
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}
}
