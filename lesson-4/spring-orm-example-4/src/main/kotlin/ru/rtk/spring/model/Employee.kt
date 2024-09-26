package ru.rtk.spring.model

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "employees")
@Entity
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
    val contact: Contact? = null,

    // Указывает на связь между таблицами "один к многим"
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "employee_id", nullable = true)
    var equipments: Set<Equipment>? = null,

    // Указывает на связь между таблицами "многие к одному"
    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var status: EmployeeStatus? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinTable(name = "employee_skills",
        joinColumns = [JoinColumn(name = "employee_id")],
        inverseJoinColumns = [JoinColumn(name = "skill_id")]
    )
    var skils: List<Skill>? = null,
)