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

    public void setEmployeeDepartmentAndSalary(String lastName, String firstName, String middleName,
                                               int departmentNumber, double salary) {

        setEmployeeDepartment(lastName, firstName, middleName, departmentNumber);
        setEmployeeSalary(lastName, firstName, middleName, salary);

    }

    public int generateId() {
        return counter++;
    }

    /*
    Возвращает сотрудника с минимальной зарплатой в отделе
     */
    public Employee getEmployeeWithMinSalary(int departmentNumber) throws EmployeeNotFoundException {

        EmployeeBook departmentEmployeeBook = this.createDepartmentEmployeeBook(departmentNumber);

        return departmentEmployeeBook.getEmployeeWithMinSalary();
    }

    /*
    Возвращает сотрудника с максимальной зарплатой в отделе
     */
    public Employee getEmployeeWithMaxSalary(int departmentNumber) throws EmployeeNotFoundException {

        EmployeeBook departmentEmployeeBook = this.createDepartmentEmployeeBook(departmentNumber);

        return departmentEmployeeBook.getEmployeeWithMaxSalary();
    }

    /*
    Возвращает сумму затрат на месяц в указанном отделе
     */
    public double getMothSpending(int departmentNumber) {

        EmployeeBook departmentEmployeeBook = this.createDepartmentEmployeeBook(departmentNumber);

        return departmentEmployeeBook.getMothSpending();
    }

    /*
    Возвращает среднюю зарплату в месяц в указанном отделе
     */
    public double getAverageSalary(int departmentNumber) {

        EmployeeBook departmentEmployeeBook = this.createDepartmentEmployeeBook(departmentNumber);

        return  departmentEmployeeBook.getAverageSalary();
    }

    /*
    Индексирует зарплату на указанный процент
     */
    public void indexingSalary(int percent, int departmentNumber) {


        for (Employee employee : employees) {

            if (employee != null && employee.getDepartmentNumber() == departmentNumber) {
                employee.setSalary(employee.getSalary() + (employee.getSalary() * percent / 100));
            }
        }

    }

    /*
    Печатает всю информацию, кроме номера отдела, о сотрудниках отдела
     */
    public void printEmployeesWithAllInfo(int departmentNumber) {

        System.out.println("Сотрудники отела №" + departmentNumber);
        for (Employee employee : getEmployeesFromDepartment(departmentNumber)) {

            if (employee != null) {

                System.out.println("Сотрудник: " + "id " + employee.getId() + ", " + employee.getLastName() + " " +
                        employee.getFirstName() + " " + employee.getMiddleName() + ", " +
                        ", получает " + employee.getSalary());

            }

        }
    }

    public void printEmployees(int departmentNumber) {

        System.out.println("Сотрудники отела №" + departmentNumber);
        for (Employee employee : getEmployeesFromDepartment(departmentNumber)) {

            if (employee != null) {

                System.out.println("Сотрудник: " + employee.getLastName() + " " +
                        employee.getFirstName() + " " + employee.getMiddleName());

            }

        }

    }

    public void printEmployeesByDepartments() {

        for (int departmentNumber : getDepartmentsArray()) {
            printEmployees(departmentNumber);
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
    private int getCountEmployees() {

        int count = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                count++;
            }
        }

        return count;
    }

    /*
    Возвращает список сотрудников в указанном отделе
     */
    private Employee[] getEmployeesFromDepartment(int departmentNumber) {

        Employee[] employeesDepartment = new Employee[employees.length];
        int indexEmployeesDepartment = 0;

        for (Employee employee : employees) {

            if (employee != null && employee.getDepartmentNumber() == departmentNumber) {

                employeesDepartment[indexEmployeesDepartment] = employee;
                indexEmployeesDepartment++;

            }
        }

        return  employeesDepartment;
    }

    /*
    Создает хранилище сотрудников только указанного отдела
     */
    private EmployeeBook createDepartmentEmployeeBook(int departmentNumber) {

        EmployeeBook departmentEmployeeBook = new EmployeeBook();

        for (Employee employee : getEmployeesFromDepartment(departmentNumber)) {

            if (employee.getDepartmentNumber() == departmentNumber) {
                departmentEmployeeBook.addEmployee(employee);
            }

        }

        return departmentEmployeeBook;
    }

    /*
    Возвращает список всех номеров отделов
     */
    private int[] getDepartmentsArray() {

        int departmentsCount = 10;
        int[] departments = new int[departmentsCount];
        int indexDepartments = 0;

        for (Employee employee : employees) {

            if (employee != null && !isDepartmentNumberInArray(employee.getDepartmentNumber(),departments)) {

                departments[indexDepartments] = employee.getDepartmentNumber();
                indexDepartments++;

            }
        }
        return departments;
    }

    private boolean isDepartmentNumberInArray(int searchNumber, int[] departments) {

        for (int number : departments) {

            if (searchNumber == number) {
                return true;
            }

        }

        return false;
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
