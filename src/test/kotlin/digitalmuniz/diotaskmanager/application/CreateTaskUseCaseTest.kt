package digitalmuniz.diotaskmanager.application

import digitalmuniz.diotaskmanager.application.input.CreateTaskInput
import digitalmuniz.diotaskmanager.domain.TaskRepository
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class CreateTaskUseCaseTest {
    @Mock
    private lateinit var repository: TaskRepository
    @InjectMocks
    private lateinit var useCase: CreateTaskUseCase

    @Test
    fun `should create task successfully`() {
        val input = CreateTaskInput("Estudar Kotlin", "Finalizar o módulo de data classes")

        val dummy = digitalmuniz.diotaskmanager.domain.Task("dummy", "dummy")
        `when`(repository.save(org.mockito.Mockito.any() ?: dummy)).thenAnswer { invocation -> invocation.getArgument(0) as digitalmuniz.diotaskmanager.domain.Task }

        val output = useCase.execute(input)

        assertNotNull(output)
        assertNotNull(output.id)
        assertEquals("Estudar Kotlin", output.title)
        assertEquals("Finalizar o módulo de data classes", output.description)

        verify(repository, times(1)).save(org.mockito.Mockito.any() ?: dummy)
    }
}