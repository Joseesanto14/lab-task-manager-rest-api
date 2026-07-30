package digitalmuniz.diotaskmanager.infrastructure.repository

import digitalmuniz.diotaskmanager.domain.Task
import digitalmuniz.diotaskmanager.domain.TaskId
import digitalmuniz.diotaskmanager.domain.TaskRepository

class InMemoryTaskRepository : TaskRepository {
    val storage = HashMap<TaskId, Task>()

    override fun save(task: Task): Task {
        storage[task.id] = task
        return task
    }

    override fun findAll(): List<Task> {
        return storage.values.toList()
    }

    override fun findById(id: TaskId): Task? {
        return storage[id]
    }

    override fun delete(id: TaskId) {
        storage.remove(id)
    }
}