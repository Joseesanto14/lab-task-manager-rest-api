package digitalmuniz.diotaskmanager.application.input

data class CreateTaskInput(
    val title: String,
    val description: String? = null,
)