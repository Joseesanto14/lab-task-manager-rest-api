package digitalmuniz.diotaskmanager.application.input

import digitalmuniz.diotaskmanager.domain.TaskStatus

data class UpdateTaskInput(
    val title: String?,
    val description: String?,
    val status: TaskStatus?
) {

}