package ena.api.zitona.services;

import ena.api.zitona.entitys.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {
        List<Task> getTasksByParcelleId(Long id);
        List<Task> findTasksByParcelleIdAndDate (Long parcelle_id, String date);
        Task saveTask(Task task);
        Task getTaskById(Long id);
        List<Task> getAllTasks();
        Task updateTask(Task task);
        void deleteTask(Long id);
}
