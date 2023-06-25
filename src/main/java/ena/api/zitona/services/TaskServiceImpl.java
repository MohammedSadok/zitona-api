package ena.api.zitona.services;

import com.google.firebase.messaging.FirebaseMessaging;
import ena.api.zitona.entitys.Task;
import ena.api.zitona.repositorys.ParcelleRepository;
import ena.api.zitona.repositorys.TaskRepository;
import ena.api.zitona.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;
    private final ParcelleRepository parcelleRepository;

    public TaskServiceImpl(TaskRepository taskRepository, FirebaseMessaging firebaseMessaging, UserRepository userRepository, ParcelleRepository parcelleRepository) {
        this.taskRepository = taskRepository;
        this.firebaseMessaging = firebaseMessaging;
        this.userRepository = userRepository;
        this.parcelleRepository = parcelleRepository;
    }

    @Override
    public List<Task> getTasksByParcelleId(Long id) {
        return taskRepository.findTasksByParcelleId(id);
    }

    @Override
    public List<Task> findTasksByParcelleIdAndDate(Long parcelle_id, String date) {
        return taskRepository.findTasksByParcelleIdAndDateLike(parcelle_id, date);
    }


    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
