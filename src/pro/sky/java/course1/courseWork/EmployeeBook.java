package pro.sky.java.course1.courseWork;

import java.util.Arrays;

public class EmployeeBook {

    //Список сотрудников
    private Employee[] employees;
    //Счетчик
    private int counter = 0;

    /*
    Считает сумму затрат на зарплаты в месяц.
     */
    public double getMothSpending() {

        double sum = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                sum += employee.getSalary();
            }
        }

        return sum;
    }

    /*
    Считает среднее значение зарплат
     */
    public double getAverageSalary() {

        int countEmployees = getCountEmployees();

        if (countEmployees > 0) {
            return getMothSpending() / countEmployees;
        } else {
            return 0;
        }

    }

    /*
    Находит сотрудника с минимальной зарплатой
     */
    public Employee getEmployeeWithMinSalary() throws EmployeeNotFoundException {

        Employee employeeWithMinSalary = employees[getFirstNotNullElement()];

        for (Employee employee : employees) {

            if (employee != null &&  employee.getSalary() < employeeWithMinSalary.getSalary()) {
                employeeWithMinSalary = employee;
            }
        }

        return employeeWithMinSalary;
    }

    /*
    Находит сотрудника с максимальной зарплатой
     */
    public Employee getEmployeeWithMaxSalary() throws EmployeeNotFoundException {

        Employee employeeWithMaxSalary = employees[getFirstNotNullElement()];

        for (Employee employee : employees) {

            if (employee != null && employee.getSalary() > employeeWithMaxSalary.getSalary()) {
                employeeWithMaxSalary = employee;
            }
        }

        return employeeWithMaxSalary;
    }

    /*
    Находит индекс первой пустой ячейки.
     */
    private int getFirstFreeElement(Employee[] employees) throws ArrayIsFullExceptions {

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                return i;
            }
        }

        throw new ArrayIsFullExceptions("Нет свободных ячеек в массиве");

    }

    /*
    Увеличивает размер массива на указанное число
     */
    private void increaseEmployeesArray(int sizeToIncrease) {
        employees = Arrays.copyOf(employees,employees.length + sizeToIncrease);
    }

    /*
    Увеличивает размер массива на 1
     */
    private void increaseEmployeesArray() {
        employees = Arrays.copyOf(employees,employees.length + 1);
    }

    /*
    Находит индекс первой не пустой ячейки. Если все ячейки пусты - выводи '-1'
     */
    private int getFirstNotNullElement() throws EmployeeNotFoundException {

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                return i;
            }
        }

        throw new EmployeeNotFoundException("Нет сотрудников в массиве");
    }

    /*
    Подсчитывает сколько сотрудников действительно в массиве
     */
    public int getCountEmployees() {

        int count = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString() {

        StringBuilder resultString = new StringBuilder("Список сотрудников:");

        for (Employee employee : employees) {

            if (employee != null) {
                resultString.append(employee);
            }

        }

        return resultString.toString();
    }
}
