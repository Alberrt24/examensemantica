package com.examen.examen.controllers

import com.examen.examen.models.Employee
import com.examen.examen.repositories.EmployeeRepo
import org.apache.jena.rdf.model.ModelFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.io.StringWriter

@Controller
class HomeControler {
    @Autowired
    lateinit var employeeRepo: EmployeeRepo
    @RequestMapping("/")
    fun home(model: Model): String
    {
        employeeRepo.save(Employee(salary= 1000f, name="Abogado"))
        employeeRepo.save(Employee(salary= 5000f, name="Arquitecto"))
        employeeRepo.save(Employee(salary= 8000f, name="Programador"))
        val empleo= employeeRepo.findAll()
        model.addAttribute("employee", empleo)
        model.addAttribute("name", "Abogado")
        model.addAttribute("jsonld","{\"\"}")
        return "home.html"
    }

    @RequestMapping("/employee/{id}")
    fun detail(model: Model, @PathVariable(value="id") id: Long) : String {
        val empleado = employeeRepo.findByIdOrNull(id)
        model.addAttribute("employee", empleado)


        val m = ModelFactory.createDefaultModel()

        val ns = "https://schema.org/"

        val productResource = m.createResource(ns + "JobPosting")
        val productNameProperty = m.createProperty(ns + "title")
        val offersNameProperty = m.createProperty(ns + "baseSalary")

        // offerResource.addProperty(priceProperty, product?.price.toString())

        m.add(productResource, productNameProperty, empleado?.name)



        val jsonLdWriter = StringWriter()
        m.write(jsonLdWriter, "JSON-LD")
        model.addAttribute("jsonld" , jsonLdWriter)

        return "employes/detail.html"
    }


}