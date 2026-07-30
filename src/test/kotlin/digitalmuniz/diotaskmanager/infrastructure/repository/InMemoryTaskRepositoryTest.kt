package digitalmuniz.diotaskmanager.infrastructure.repository

import digitalmuniz.diotaskmanager.domain.TaskRepository
import digitalmuniz.diotaskmanager.domain.TaskRepositoryTest

class InMemoryTaskRepositoryTest : TaskRepositoryTest() {
    override fun createRepository(): TaskRepository {
        return InMemoryTaskRepository()
    }


}
