package digitalmuniz.diotaskmanager.domain

data class Task(
    val id: TaskId = TaskId(),
    val title: String,
    val description: String? = null,
    val status: TaskStatus = TaskStatus.PENDING
) {
    constructor(
        title: String,
        description: String?
    ): this(id = TaskId(), title = title, description = description)

    fun update(title: String?, description: String?, status: TaskStatus?): Task {
        if (listOf(title, description, status).all { it == null }) return this

        return this.copy(
            title = title ?: this.title,
            description = description ?: this.description,
            status = status ?: this.status
        )
    }
}