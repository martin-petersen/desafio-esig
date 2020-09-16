package br.com.example.desafioesig;

import br.com.example.desafioesig.model.Task;
import br.com.example.desafioesig.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskRestControlerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void GetTestShouldReturn200() {
        ResponseEntity<?> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/task",String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void GetTestShouldReturn404() {
        ResponseEntity<?> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/tak",String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void PostTestShouldReturn200() {
        Task task = new Task();
        task.setDescription("Teste");
        BDDMockito.when(taskRepository.save(task)).thenReturn(task);
        ResponseEntity<?> responseEntity = restTemplate.getForEntity("http://localhost:"+port+"/task",String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void PostTestShouldReturn400() {
        Task task = new Task();
        BDDMockito.when(taskRepository.save(task)).thenReturn(task);
        ResponseEntity<?> responseEntity = restTemplate.postForEntity("http://localhost:"+port+"/task",task,String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void DeleteTestShouldReturn200() {
        Task task = new Task();
        task.setDescription("Teste");
        BDDMockito.when(taskRepository.save(task)).thenReturn(task);
        BDDMockito.doNothing().when(taskRepository).deleteById((long) 1);
        ResponseEntity<?> responseEntity = restTemplate.exchange("http://localhost:"+port+"/task/1", HttpMethod.DELETE, null,String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}
