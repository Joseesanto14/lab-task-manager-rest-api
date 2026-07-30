package digitalmuniz.diotaskmanager.domain

class TaskNotFoundException(id: TaskId) : RuntimeException("Task with id $id not found") {

}