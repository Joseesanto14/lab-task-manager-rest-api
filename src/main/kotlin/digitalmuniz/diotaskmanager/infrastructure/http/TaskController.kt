package digitalmuniz.diotaskmanager.infrastructure.http

import digitalmuniz.diotaskmanager.application.CreateTaskUseCase
import digitalmuniz.diotaskmanager.infrastructure.http.request.CreateTaskRequest
import digitalmuniz.diotaskmanager.infrastructure.http.response.TaskResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(
    val createTaskUseCase: CreateTaskUseCase
) {
    @PostMapping
    fun create(@RequestBody request: CreateTaskRequest): TaskResponse {
        val input = request.toInput()
        val output = createTaskUseCase.execute(input)
        return TaskResponse.from(output)
    }
}