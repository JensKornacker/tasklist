package at.phactum.tasklist.persistence;

import java.util.List;
import java.util.UUID;

import at.phactum.tasklist.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {
    Task findByTaskId(String taskId);
    List<Task> findAllByCompletedAtIsNullOrderByCreatedAtDesc();
}
