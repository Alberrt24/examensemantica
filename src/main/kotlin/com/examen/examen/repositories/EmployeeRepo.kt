package com.examen.examen.repositories

import org.springframework.data.repository.CrudRepository
import com.examen.examen.models.Employee

interface EmployeeRepo: CrudRepository<Employee, Long>