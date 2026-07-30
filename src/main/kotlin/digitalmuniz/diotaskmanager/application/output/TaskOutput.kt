package digitalmuniz.diotaskmanager.application.output

import digitalmuniz.diotaskmanager.domain.Task

data class TaskOutput(
    val id: String,
    val title: String,
    val description: String?,
    val status: String
) {
    companion object {
        fun from(task: Task): TaskOutput {
            return TaskOutput(
                task.id.toString(),
                task.title,
                task.description,
                task.status.name
            )
        }
    }
}