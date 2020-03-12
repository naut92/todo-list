package com.consul_media.server.controller;

import com.consul_media.server.mappers.TaskMapper;
import com.consul_media.server.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Task> updateEmployee(@Valid @RequestBody Task task/*, @PathVariable Long taskId*/) {
        log.warn("Request to update Task={}", task);
        service.updateTask(task/*, taskId*/);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/task/new")
    public ResponseEntity<Task> createTask
            (@Valid @RequestBody Task task) throws URISyntaxException {
        log.warn("Request to create Task={}", task);
        service.insertTask(task);
        return ResponseEntity.ok().build();
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
