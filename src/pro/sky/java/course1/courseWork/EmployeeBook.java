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
    Выводит в консоль Ф. И. О. всех сотрудников
     */
    public void printEmployees() {

        System.out.println("Список сотрудников:");

        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getLastName() + " " + employee.getFirstName() + " " + employee.getMiddleName());
            }
        }

    }

    /*
    Индексирует зарплату на указанный процент
     */
    public void indexingSalary(int percent) {

        for (Employee employee : employees) {

            if (employee != null) {
                employee.setSalary(employee.getSalary() + employee.getSalary() * percent / 100);
            }
        }

    }

    /*
    Выводит в консоль список сотрудников (id, Ф. И. О. и зарплата)
     */
    public void printEmployeesWithIdAndSalary() {

        for (Employee employee : employees) {

            if (employee != null) {
                System.out.println(employee.getId() + ", " + employee.getLastName() + " " + employee.getFirstName() + " " +
                        employee.getMiddleName() + " получает " + employee.getSalary());
            }

        }
    }

    /*
    Добавляет сотрудника в список
     */
    public void addEmployee(Employee employee) {

        try {

            employees[getFirstFreeElement()] = employee;

        } catch (ArrayIsFullExceptions e) {

            System.out.println(e.getMessage());
            System.out.println("Увеличиваем массив");
            increaseEmployeesArray();
            addEmployee(employee);

        }
    }

    /*
    Удаляет сотрудника по имени и фамилии
     */
    public void deleteEmployee(String lastName, String firstName, String middleName) {

        try {
            employees[findIndexEmployee(lastName, firstName, middleName)] = null;
        } catch (EmployeeNotFoundException e) {
            System.out.println("Не удается удалить сотрудника \n" + e.getMessage());
        }

    }

    /*
    Удаляет сотрудника по имени и фамилии
     */
    public void deleteEmployee(int id) {

        try {
            employees[findIndexEmployee(id)] = null;
        } catch (EmployeeNotFoundException e) {
            System.out.println("Не удается удалить сотрудника \n" + e.getMessage());
        }

    }

    /*
    Устанавливает зарплату сотруднику
     */
    public void setEmployeeSalary(String lastName, String firstName, String middleName, double salary) {

        try {
            employees[findIndexEmployee(lastName, firstName, middleName)].setSalary(salary);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Не удается установить зарплату сотруднику \n" + e.getMessage());
        }
    }

    public void setEmployeeDepartment(String lastName, String firstName, String middleName, int departmentNumber) {

        try {
            employees[findIndexEmployee(lastName, firstName, middleName)].setDepartmentNumber(departmentNumber);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Не удается изменить отдел сотруднику \n" + e.getMessage());
        }
    }

    /*
    Находит сотрудника по Фамилии Имени Отчеству
     */
    private int findIndexEmployee(String lastName, String firstName, String middleName)
            throws EmployeeNotFoundException {

        for (int i = 0; i < employees.length; i++) {

            if(employees[i] != null && employees[i].getLastName().equals(lastName) &&
                    employees[i].getFirstName().equals(firstName) &&
                    employees[i].getMiddleName().equals(middleName)) {

                return i;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник " + lastName + " " + firstName + " " + middleName +
                " не найден");

    }

    /*
    Находит сотрудника по id
     */
    private int findIndexEmployee(int id)
            throws EmployeeNotFoundException {

        for (int i = 0; i < employees.length; i++) {

            if(employees[i] != null && employees[i].getId() == id) {

                return i;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник с id " + id +
                " не найден");

    }

    /*
    Находит индекс первой пустой ячейки.
     */
    private int getFirstFreeElement() throws ArrayIsFullExceptions {

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
