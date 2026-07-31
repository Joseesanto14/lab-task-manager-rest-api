package digitalmuniz.diotaskmanager.infrastructure.http

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import tools.jackson.databind.ObjectMapper

@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@SpringBootTest
class TaskControllerTest {

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp(
        webApplicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider
    ) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder>(documentationConfiguration(restDocumentationContextProvider))
            .build()
    }

    @Test
    fun `should save and retrieve task by id`() {
        val taskMap = mapOf(
            "title" to "Estudar Spring Boot",
            "description" to "Aprender a utilizar Spring REST Docs com Kotlin"
        )
        val jsonPayload = objectMapper.writeValueAsString(taskMap)

        val postResult = mockMvc.perform(
            post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)
        )
            .andExpect(status().isCreated)
            .andDo(
                document(
                    "create-task",
                    requestFields(
                        fieldWithPath("title").description("Título da tarefa"),
                        fieldWithPath("description").optional().description("Descrição da tarefa")
                    ),
                    responseFields(
                        fieldWithPath("id").description("ID único gerado para a tarefa"),
                        fieldWithPath("title").description("Título da tarefa"),
                        fieldWithPath("description").optional().description("Descrição da tarefa"),
                        fieldWithPath("status").description("Status inicial da tarefa")
                    )
                )
            )
            .andReturn()

        val responseContent = postResult.response.contentAsString
        val createdTask = objectMapper.readValue(responseContent, Map::class.java)
        val taskId = createdTask["id"] as String
        val expectedTitle = createdTask["title"] as String

        mockMvc.perform(get("/tasks/{id}", taskId))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(taskId))
            .andExpect(jsonPath("$.title").value(expectedTitle))
            .andDo(
                document(
                    "get-task-by-id",
                    pathParameters(
                        parameterWithName("id").description("ID único da tarefa")
                    ),
                    responseFields(
                        fieldWithPath("id").description("ID único da tarefa"),
                        fieldWithPath("title").description("Título da tarefa"),
                        fieldWithPath("description").optional().description("Descrição da tarefa"),
                        fieldWithPath("status").description("Status atual da tarefa")
                    )
                )
            )
    }
}