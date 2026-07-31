package digitalmuniz.diotaskmanager.infrastructure.http.request

import digitalmuniz.diotaskmanager.application.input.CreateTaskInput

data class CreateTaskRequest(
    val title: String,
    val description: String?
) {
    fun toInput(): CreateTaskInput = CreateTaskInput(title, description)
}