package com.examen.examen.models
import javax.persistence.*

@Entity
@Table
data class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?=null,
    val name: String,
    val salary: Float
)
