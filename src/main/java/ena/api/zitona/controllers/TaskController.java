package ena.api.zitona.controllers;

import ena.api.zitona.entitys.Task;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/parcelle/{id}")
    public ResponseEntity<ResponseData<List<Task>>> getTasksByParcelleId(@PathVariable Long id) {
        List<Task> tasks = taskService.getTasksByParcelleId(id);
        ResponseData<List<Task>> responseData = new ResponseData<>(tasks, HttpStatus.OK, "Tasks retrieved successfully.");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/parcelle/{id}/date/{date}")
    public ResponseEntity<ResponseData<List<Task>>> getTasksByParcelleIdAndDate(@PathVariable Long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Task> tasks = taskService.findTasksByParcelleIdAndDate(id, date);
        ResponseData<List<Task>> responseData = new ResponseData<>(tasks, HttpStatus.OK, "Tasks retrieved successfully.");
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Task>> saveTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        ResponseData<Task> responseData = new ResponseData<>(savedTask, HttpStatus.CREATED, "Task saved successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Task>> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            ResponseData<Task> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Task not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        ResponseData<Task> responseData = new ResponseData<>(task, HttpStatus.OK, "Task retrieved successfully.");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Task>>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        ResponseData<List<Task>> responseData = new ResponseData<>(tasks, HttpStatus.OK, "Tasks retrieved successfully.");
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Task>> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            ResponseData<Task> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Task not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        task.setId(id);
        Task updatedTask = taskService.updateTask(task);
        ResponseData<Task> responseData = new ResponseData<>(updatedTask, HttpStatus.OK, "Task updated successfully.");
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteTask(@PathVariable Long id) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            ResponseData<Void> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Task not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        taskService.deleteTask(id);
        ResponseData<Void> responseData = new ResponseData<>(null, HttpStatus.NO_CONTENT, "Task deleted successfully.");
        return ResponseEntity.ok(responseData);
    }
}
