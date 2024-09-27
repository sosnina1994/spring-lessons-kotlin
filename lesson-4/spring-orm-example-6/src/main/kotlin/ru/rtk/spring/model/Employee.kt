package ru.rtk.spring.model

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "employees")
@Entity
@NamedEntityGraph(
    name = "contact-graph",
    attributeNodes = [NamedAttributeNode("contact")]
)
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    // Задает имя и некоторые свойства поля таблицы, на которое будет отображаться поле сущности
    @Column(name = "full_name", nullable = false)
    var fullName: String? = null,

    // Задает имя и некоторые свойства поля таблицы, на которое будет отображаться поле сущности
    @Column(name = "birth_date", nullable = false)
    var birthDate: LocalDate? = null,

    // Указывает на связь между таблицами "один к одному"
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = true)
    val contact: Contact? = null
)