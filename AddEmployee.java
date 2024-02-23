package net.texala.employee.impl;

import java.util.Scanner;
import net.texala.employee.manager.EmployeeManager;
import net.texala.employee.model.Employee;
import net.texala.employee.service.EmployeeOperation;
import net.texala.employee.exceptions.EmployeeOperationException;
import static net.texala.employee.constants.Constants.ADD_EMPLOYEE_HEADER_TOP;
import static net.texala.employee.constants.Constants.ADD_EMPLOYEE_HEADER_MIDDLE;
import static net.texala.employee.constants.Constants.ADD_EMPLOYEE_HEADER_BOTTOM;
import static net.texala.employee.constants.Constants.ENTER_EMPID;
import static net.texala.employee.constants.Constants.ENTER_FIRST_NAME;
import static net.texala.employee.constants.Constants.ENTER_LAST_NAME;
import static net.texala.employee.constants.Constants.ERROR_ADD_EMPLOYEE;
import static net.texala.employee.constants.Constants.ERROR;

public class AddEmployee implements EmployeeOperation {
	 
    @Override
    public void execute(EmployeeManager manager, Scanner scanner) throws EmployeeOperationException {
        try {
        	System.out.println(ADD_EMPLOYEE_HEADER_TOP);
        	System.out.println(ADD_EMPLOYEE_HEADER_MIDDLE);
        	System.out.println(ADD_EMPLOYEE_HEADER_BOTTOM);
        	manager.loadEmployees();

            System.out.print(ENTER_EMPID);
            int empId = manager.getValidEmpId(scanner);
            
            if (manager.isEmployeeIdExists(manager, empId)) {
            	System.out.println("Error: Employee with ID " + empId + " already exists.");
                manager.temporaryVector.clear();  
                return;
            }
            
            System.out.print(ENTER_FIRST_NAME);
            String firstName = scanner.nextLine();

            System.out.print(ENTER_LAST_NAME);
            String lastName = scanner.nextLine();

            String department = manager.selectDepartment(scanner);

            System.out.println();
            manager.temporaryVector.clear();
            manager.temporaryVector.add(new Employee(empId, firstName, lastName, department));
            manager.addEmployee(empId, firstName, lastName, department);
            
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage());
        } catch (Exception e) {
            EmployeeOperationException.throwAddEmployeeException(ERROR_ADD_EMPLOYEE + e.getMessage());
        }
    }
 
}
