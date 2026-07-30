package digitalmuniz.diotaskmanager.domain

import lombok.Getter


data class Task(
    val id: TaskId = TaskId(),
    val title: String,
    val description: String? = null,
    val status: TaskStatus = TaskStatus.PENDING
) {
}