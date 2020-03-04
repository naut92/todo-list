package com.consul_media.task_list.controller;

import com.consul_media.task_list.mappers.TaskMapper;
import com.consul_media.task_list.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskMapper service;

    public TaskController(TaskMapper service) {
        this.service = service;
    }

    @GetMapping("/task-list")
    public Collection<Task> getTodoList(){
        return service.getTaskList();
    }

    @GetMapping("/task/{taskId}")
    public Task getTodoById(@PathVariable("taskId") Long taskId) {
        log.info("Todo find: taskId={}", taskId);
        return service.getTaskById(taskId);
    }

    @PutMapping("/upd/{taskId}")
    public ResponseEntity<Task> updateEmployee(@PathVariable Long taskId, @Valid @RequestBody Task task) {
        log.warn("Request to update Task={}", task);
        Task result = service.updateTask(taskId, task);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/task/new")
    public ResponseEntity<Task> createTask
            (@Valid @RequestBody Task task) throws URISyntaxException {
        log.warn("Request to create Task={}", task);
        Task result = service.insertTask(task);
        return ResponseEntity.created(new URI("/task/" + result.getId()))
                .body(result);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        log.info("Request to delete Task={}", taskId);
        try {
            service.deleteTask(taskId);
        }catch (Exception e) {
            log.error("объект не удалён");
            e.getStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}
