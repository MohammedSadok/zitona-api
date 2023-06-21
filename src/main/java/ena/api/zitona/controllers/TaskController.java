package ena.api.zitona.controllers;

import ena.api.zitona.entitys.NotificationMessage;
import ena.api.zitona.entitys.Task;
import ena.api.zitona.repositorys.ParcelleRepository;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.PushNotificationService;
import ena.api.zitona.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final PushNotificationService pushNotificationService;
    private final ParcelleRepository parcelleRepository;

    public TaskController(TaskService taskService, PushNotificationService pushNotificationService, ParcelleRepository parcelleRepository) {
        this.taskService = taskService;
        this.pushNotificationService = pushNotificationService;
        this.parcelleRepository = parcelleRepository;
    }


    @GetMapping("/parcelle/{id}")
    public ResponseEntity<ResponseData<List<Task>>> getTasksByParcelleId(@PathVariable Long id) {
        List<Task> tasks = taskService.getTasksByParcelleId(id);
        ResponseData<List<Task>> responseData = new ResponseData<>(tasks, HttpStatus.OK, "Tasks retrieved successfully.");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/parcelle/{id}/today")
    public ResponseEntity<ResponseData<List<Task>>> getTasksByParcelleIdAndDate(
            @PathVariable Long id) {
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = DateFor.format(date);
        System.out.println(stringDate);
        System.out.println(stringDate);
        List<Task> tasks = taskService.findTasksByParcelleIdAndDate(id, stringDate);
        ResponseData<List<Task>> responseData = new ResponseData<>(tasks, HttpStatus.OK, "Tasks retrieved successfully.");
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Task>> saveTask(@RequestBody Task task) {
        long id = parcelleRepository.findUserIdByParcelleId(task.getParcelle().getId());
        NotificationMessage notificationMessage = new NotificationMessage(id,task.getObject(),task.getContent());
        pushNotificationService.sendNotificationByToken(notificationMessage);
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
