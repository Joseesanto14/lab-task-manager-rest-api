package digitalmuniz.diotaskmanager.infrastructure.http.request

import digitalmuniz.diotaskmanager.application.input.UpdateTaskInput
import digitalmuniz.diotaskmanager.domain.TaskStatus

data class UpdateTaskRequest(
    val title: String?,
    val description: String?,
    val status: String?
) {
    fun toInput(): UpdateTaskInput {
        return UpdateTaskInput(title, description, TaskStatus.valueOf(status ?: ""))
    }
}