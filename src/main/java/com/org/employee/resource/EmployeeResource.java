package com.org.employee.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.org.employee.domain.Address;
import com.org.employee.domain.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

	@Autowired
	private RestTemplate restTemplate;
	
	private String addressServiceId="address-service";
	//private String addressServicePort="8083";
	
	@GetMapping(value = "/{empId}")
	public Employee getEmployeeDetails(@PathVariable int empId) {

		List<Employee> employeeList = getAllEmployees();
		Employee emp = new Employee();		
		Address address = restTemplate.getForObject("http://"+addressServiceId+"/address/1", Address.class);

		for (Employee employee : employeeList) {
			if (employee.getEmpId() == empId) {
				emp = employee;
			}
		}
		emp.setAddress(address);
		return emp;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<Employee>();

		Employee emp1 = new Employee(1, "Satheesh");
		Employee emp2 = new Employee(2, "Chepuri");
		Employee emp3 = new Employee(3, "Anil");
		Employee emp4 = new Employee(4, "Santhosh");
		Employee emp5 = new Employee(5, "Jegadeep");

		employeeList.add(emp1);
		employeeList.add(emp2);
		employeeList.add(emp3);
		employeeList.add(emp4);
		employeeList.add(emp5);

		return employeeList;
	}

}
