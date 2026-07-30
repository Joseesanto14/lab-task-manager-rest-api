package digitalmuniz.diotaskmanager.application

import digitalmuniz.diotaskmanager.application.output.TaskOutput
import digitalmuniz.diotaskmanager.domain.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetTasksUseCase(
    private val repository: TaskRepository
) {
    fun execute(): List<TaskOutput> = repository.findAll().map { TaskOutput.from(it) }
}