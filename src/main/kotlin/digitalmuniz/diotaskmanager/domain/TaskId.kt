package digitalmuniz.diotaskmanager.domain

import java.util.*

@JvmInline
value class TaskId(
    val id: UUID = UUID.randomUUID()
) {
    init {
        requireNotNull(id) { "id must be not null" }
    }
}