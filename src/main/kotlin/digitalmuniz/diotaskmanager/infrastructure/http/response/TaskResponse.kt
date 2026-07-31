package digitalmuniz.diotaskmanager.infrastructure.http.response

import com.fasterxml.jackson.annotation.JsonInclude
import digitalmuniz.diotaskmanager.application.output.TaskOutput

@JsonInclude(JsonInclude.Include.NON_ABSENT)
data class TaskResponse(
    val id: String,
    val title: String,
    val description: String?,
    val status: String
) {
    companion object {
        fun from(output: TaskOutput) = TaskResponse(
            output.id,
            output.title,
            output.description,
            output.status
        )
    }

}