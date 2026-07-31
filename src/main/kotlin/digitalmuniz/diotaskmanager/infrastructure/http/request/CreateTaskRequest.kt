package digitalmuniz.diotaskmanager.infrastructure.http.request

import digitalmuniz.diotaskmanager.application.input.CreateTaskInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTaskRequest(
    @NotBlank
    @Size(min = 3, max = 100)
    val title: String,
    @Size(max = 500)
    val description: String?
) {
    fun toInput(): CreateTaskInput = CreateTaskInput(title, description)
}