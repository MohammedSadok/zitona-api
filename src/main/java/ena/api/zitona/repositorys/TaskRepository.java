package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findTasksByParcelleId (Long id);
    List<Task> findTasksByParcelleIdAndDateLike (Long parcelle_id, String date);

}
