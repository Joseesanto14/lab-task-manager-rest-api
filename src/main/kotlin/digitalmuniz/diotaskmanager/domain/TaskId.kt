package digitalmuniz.diotaskmanager.domain

import org.springframework.util.Assert
import java.util.UUID

data class TaskId(
    val id: UUID = UUID.randomUUID()
) {
    init {
        requireNotNull(id) { "id must be not null" }
    }
}