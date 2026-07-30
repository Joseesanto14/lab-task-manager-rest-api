package digitalmuniz.diotaskmanager.domain

interface TaskRepository {
    fun save(task: Task) : Task

    fun findAll() : List<Task>

    fun findById(id: TaskId) : Task?

    fun delete(id: TaskId)
}