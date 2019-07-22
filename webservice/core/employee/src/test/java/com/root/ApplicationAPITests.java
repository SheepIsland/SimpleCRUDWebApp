package com.root;

import com.root.entity.Employee;
import com.root.entity.Salary;
import com.root.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ApplicationAPITests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Test
    public void findAllEmployees() throws Exception {
        final Salary salary = new Salary(1);
        salary.setId(1L);
        final Employee employee = new Employee("Test",salary);
        employee.setId(1L);

        final List<Employee> employees = Arrays.asList(employee);
        given(applicationService.findAllEmployee()).willReturn(employees);

        this.mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Test\",\n" +
                        "        \"salary\": {\n" +
                        "            \"id\": 1,\n" +
                        "            \"summary\": 1\n" +
                        "        }\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void findAllEmployeesWhenEployeesIsEmpty() throws Exception {
        final List<Employee> employees = Collections.emptyList();
        given(applicationService.findAllEmployee()).willReturn(employees);

        this.mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}
