package digitalmuniz.diotaskmanager.application

import digitalmuniz.diotaskmanager.domain.TaskId
import digitalmuniz.diotaskmanager.domain.TaskNotFoundException
import digitalmuniz.diotaskmanager.domain.TaskRepository
import org.springframework.stereotype.Service

@Service
class DeleteTaskUseCase(
    private val repository: TaskRepository
) {
    fun execute(id: TaskId) = repository.findById(id)
        ?.let { repository.delete(id) }
        ?: throw TaskNotFoundException(id)
}