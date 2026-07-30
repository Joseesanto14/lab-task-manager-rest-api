package digitalmuniz.diotaskmanager.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

abstract class TaskRepositoryTest {
    open var repository: TaskRepository? = null
    
    protected abstract fun createRepository(): TaskRepository
    
    @BeforeEach
    fun setUp() {
        repository = createRepository()
    }
    
    @Test
    fun `should save and retrieve task by id`() {
        val task = Task(title = "Passar na padaria", description = "Comprar pão")

        val saved = repository!!.save(task)
        val result = repository!!.findById(saved.id)

        assertNotNull(result)
        assertEquals(saved.id, result.id)
        assertEquals("Passar na padaria", result.title)
        assertEquals("Comprar pão", result.description)
        assertEquals(TaskStatus.PENDING, result.status)
    }

    @Test
    fun `should find all persisted tasks`() {
        val task1 = Task(title = "Task 1", description = "Desc 1")
        val task2 = Task(title = "Task 2", description = "Desc 2")
        repository!!.save(task1)
        repository!!.save(task2)

        val result = repository!!.findAll()

        assertEquals(2, result.size)
        assertTrue(result.containsAll(listOf(task1, task2)))
    }

    @Test
    fun `should delete task by id`() {
        val task = Task(title = "Task para deletar")
        val saved = repository!!.save(task)

        repository!!.delete(saved.id)

        val result = repository!!.findById(saved.id)
        assertNull(result)
    }

    @Test
    fun `should return empty when searching non existent task`() {
        val nonExistentId = TaskId()

        val result = repository!!.findById(nonExistentId)

        assertNull(result)
    }

    @Test
    fun `should update task status succesfully`() {
        val task = Task(title = "Task original", status = TaskStatus.PENDING)
        val saved = repository!!.save(task)

        val updatedTask = saved.copy(status = TaskStatus.COMPLETED)
        repository!!.save(updatedTask)

        val result = repository!!.findById(saved.id)
        assertNotNull(result)
        assertEquals(TaskStatus.COMPLETED, result.status)
    }

}