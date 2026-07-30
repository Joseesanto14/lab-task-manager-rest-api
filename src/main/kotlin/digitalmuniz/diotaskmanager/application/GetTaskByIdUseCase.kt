package digitalmuniz.diotaskmanager.application

import digitalmuniz.diotaskmanager.application.output.TaskOutput
import digitalmuniz.diotaskmanager.domain.TaskId
import digitalmuniz.diotaskmanager.domain.TaskNotFoundException
import digitalmuniz.diotaskmanager.domain.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetTaskByIdUseCase(
    private val repository: TaskRepository
) {
    fun execute(id: TaskId): TaskOutput {
        return repository.findById(id)?.let { TaskOutput.from(it) } ?: throw TaskNotFoundException(id)
    }
}