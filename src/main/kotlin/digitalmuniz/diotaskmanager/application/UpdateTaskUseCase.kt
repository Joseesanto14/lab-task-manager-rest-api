package digitalmuniz.diotaskmanager.application

import digitalmuniz.diotaskmanager.application.input.UpdateTaskInput
import digitalmuniz.diotaskmanager.application.output.TaskOutput
import digitalmuniz.diotaskmanager.domain.TaskId
import digitalmuniz.diotaskmanager.domain.TaskNotFoundException
import digitalmuniz.diotaskmanager.domain.TaskRepository
import org.springframework.stereotype.Service

@Service
class UpdateTaskUseCase(
    private val repository: TaskRepository
) {
    fun execute(id: TaskId, input: UpdateTaskInput): TaskOutput {
        val task = repository.findById(id) ?: throw TaskNotFoundException(id)

        val newTask = task.update(input.title, input.description, input.status)
        val updated = repository.save(newTask)
        return TaskOutput.from(updated)
    }
}