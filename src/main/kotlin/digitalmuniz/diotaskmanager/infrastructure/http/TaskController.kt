package digitalmuniz.diotaskmanager.infrastructure.http

import digitalmuniz.diotaskmanager.application.CreateTaskUseCase
import digitalmuniz.diotaskmanager.application.DeleteTaskUseCase
import digitalmuniz.diotaskmanager.application.GetTaskByIdUseCase
import digitalmuniz.diotaskmanager.application.GetTasksUseCase
import digitalmuniz.diotaskmanager.application.UpdateTaskUseCase
import digitalmuniz.diotaskmanager.domain.TaskId
import digitalmuniz.diotaskmanager.infrastructure.http.request.CreateTaskRequest
import digitalmuniz.diotaskmanager.infrastructure.http.request.UpdateTaskRequest
import digitalmuniz.diotaskmanager.infrastructure.http.response.TaskResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    val createTaskUseCase: CreateTaskUseCase,
    val getTasksUseCase: GetTasksUseCase,
    val getTaskByIdUseCase: GetTaskByIdUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase
) {
    @PostMapping
    fun create(@RequestBody request: CreateTaskRequest): TaskResponse {
        val input = request.toInput()
        val output = createTaskUseCase.execute(input)
        return TaskResponse.from(output)
    }

    @GetMapping
    fun list(): List<TaskResponse> = getTasksUseCase.execute().map { TaskResponse.from(it) }

    @GetMapping("/{id}")
    fun read(@PathVariable id: UUID): TaskResponse {
        val taskId = TaskId(id)
        val output = getTaskByIdUseCase.execute(taskId)
        return TaskResponse.from(output)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        val uuid = TaskId(id)
        deleteTaskUseCase.execute(uuid)
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: UpdateTaskRequest): TaskResponse {
        val input = request.toInput()
        val output = updateTaskUseCase.execute(TaskId(id), input)
        return TaskResponse.from(output)
    }
}