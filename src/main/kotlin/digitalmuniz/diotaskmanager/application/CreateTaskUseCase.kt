package digitalmuniz.diotaskmanager.application

import digitalmuniz.diotaskmanager.application.input.CreateTaskInput
import digitalmuniz.diotaskmanager.application.output.TaskOutput
import digitalmuniz.diotaskmanager.domain.Task
import digitalmuniz.diotaskmanager.domain.TaskRepository
import org.springframework.stereotype.Service

@Service
class CreateTaskUseCase(
    private val repository: TaskRepository
) {

    fun execute(input: CreateTaskInput): TaskOutput {
        val task = Task(input.title, input.description)
        val saved = repository.save(task)
        return TaskOutput.from(task)
    }
}