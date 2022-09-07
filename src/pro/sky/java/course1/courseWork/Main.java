package pro.sky.java.course1.courseWork;

public class Main {
    public static void main(String[] args) {

        minimalLevel();
        middleLevel();
    }

    private static Employee[] fillEmployees() {
        int employeesSize = 10;
        Employee[] employees = new Employee[employeesSize];

        employees[0] = new Employee("Иванов", "Иван", "Иванович", 5,
                90_000);
        employees[1] = new Employee("Сидоров", "Сергей", "Петрович", 4,
                110_000);
        employees[2] = new Employee("Петров", "Петр", "Петрович", 5,
                95_000);
        employees[3] = new Employee("Смирнова", "Мария", "Ивановна", 6,
                97_000);

        return employees;
    }

    /*
    1. Создать класс Employee, который содержит информацию о Ф.И.О., отделе и зарплате сотрудника. Отделы для простоты
    должны быть названы от 1 до 5.
    2. Добавить статическую переменную-счетчик, которая будет отвечать за id.
    3. Добавить в класс Employee поле id, которое проставляется из счетчика, а затем счетчик увеличивает свое значение.
    4. Добавить возможность получать значения полей из Employee (геттеры) для всех полей.
    5. Добавить возможность устанавливать значения полей отдела и зарплаты (сеттеры).
    6. По умолчанию все поля должны передавать через конструктор (кроме id) и заполняться в нем (включая id, который
    нужно получить из счетчика).
    7. Создать внутри класса с методом main поле типа Employee[10], которое будет выполнять роль «хранилища» для
    записей о сотрудниках.
    8. Создать статические методы, которые будут взаимодействовать с массивом и предоставлять результат:
        1. Получить список всех сотрудников со всеми имеющимися по ним данными (вывести в консоль значения всех полей
        (toString)).
        2. Посчитать сумму затрат на зарплаты в месяц.
        3. Найти сотрудника с минимальной зарплатой.
        4. Найти сотрудника с максимальной зарплатой.
        5. Подсчитать среднее значение зарплат (можно использовать для этого метод из пункта b).
        6. Получить Ф. И. О. всех сотрудников (вывести в консоль).
     */
    private static void minimalLevel() {

        Employee[] employees = fillEmployees();

        printEmployeesWithAllInfo(employees);
        System.out.printf("Сумма затрат в месяц: %.2f\n", getMothSpending(employees));
        System.out.println("Сотрудник с минимальной зарплатой: " + getEmployeeWithMinSalary(employees));
        System.out.println("Сотрудник с максимальной зарплатой: " + getEmployeeWithMaxSalary(employees));
        System.out.printf("Средняя зарплата: %.2f\n", getAverageSalary(employees));
        printEmployees(employees);

    }

    /*
    Создать дополнительные статические методы для решения следующих задач.
    1. Проиндексировать зарплату (вызвать изменение зарплат у всех сотрудников на величину аргумента в %).
    2. Получить в качестве параметра номер отдела (1–5) и найти (всего 6 методов):
        1. Сотрудника с минимальной зарплатой.
        2. Сотрудника с максимальной зарплатой.
        3. Сумму затрат на зарплату по отделу.
        4. Среднюю зарплату по отделу (учесть, что количество людей в отделе отличается от employees.length).
        5. Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра.
        6. Напечатать всех сотрудников отдела (все данные, кроме отдела).
    3. Получить в качестве параметра число и найти:
        1. Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
        2. Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
     */
    private static void middleLevel() {

        Employee[] employees = fillEmployees();

    }

    /*
    Выводит в консоль значения всех полей (toString)
     */
    private static void printEmployeesWithAllInfo(Employee[] employees) {

        System.out.println("Полная информация о сотрудниках: ");

        for (Employee employee : employees) {

            if (employee != null) {
                System.out.println(employee);
            }
        }
    }

    /*
    Считает сумму затрат на зарплаты в месяц.
     */
    private static double getMothSpending(Employee[] employees) {

        double sum = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                sum += employee.getSalary();
            }
        }

        return sum;
    }

    /*
    Находит сотрудника с минимальной зарплатой
     */
    private static Employee getEmployeeWithMinSalary(Employee[] employees) {

        int firstIndex = getFirstNotNullElement(employees);

        Employee employeeWithMinSalary;

        if (firstIndex >= 0) {
            employeeWithMinSalary = employees[firstIndex];
        } else {
            return null;
        }

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
    private static Employee getEmployeeWithMaxSalary(Employee[] employees) {

        int firstIndex = getFirstNotNullElement(employees);

        Employee employeeWithMaxSalary;

        if (firstIndex >= 0) {
            employeeWithMaxSalary = employees[firstIndex];
        } else {
            return null;
        }

        for (Employee employee : employees) {

            if (employee != null && employee.getSalary() > employeeWithMaxSalary.getSalary()) {
                employeeWithMaxSalary = employee;
            }
        }

        return employeeWithMaxSalary;
    }

    /*
    Считает среднее значение зарплат
     */
    private static double getAverageSalary(Employee[] employees) {

        int countEmployees = getCountEmployees(employees);

        if (countEmployees > 0) {
            return getMothSpending(employees) / countEmployees;
        } else {
            return 0;
        }

    }

    /*
    Выводит в консоль Ф. И. О. всех сотрудников
     */
    private static void printEmployees(Employee[] employees) {

        System.out.println("Список сотрудников:");

        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getLastName() + " " + employee.getFirstName() + " " + employee.getMiddleName());
            }
        }

    }

    /*
    Подсчитывает сколько сотрудников действительно в массиве
     */
    private static int getCountEmployees(Employee[] employees) {

        int count = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                count++;
            }
        }

        return count;
    }

    /*
    Находит индекс первой не пустой ячейки. Если все ячейки пусты - выводи '-1'
     */
    private static int getFirstNotNullElement(Employee[] employees) {

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                return i;
            }
        }

        return -1;
    }

    /*
    Индексирует зарплату на указанный процент
     */
    private static void indexingSalary(Employee[] employees, int percent) {

        for (Employee employee : employees) {

            if (employee != null) {
                employee.setSalary(employee.getSalary() * percent / 100);
            }
        }

    }

    /*
    Возвращает список сотрудников в указанном отделе
     */
    private static Employee[] getEmployeesFromDepartment(Employee[] employees, int departmentNumber) {

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
    Возвращает сотрудника с минимальной зарплатой в отделе
     */
    private static Employee getEmployeeWithMinSalary(Employee[] employees, int departmentNumber) {
        return getEmployeeWithMinSalary(getEmployeesFromDepartment(employees, departmentNumber));
    }

    /*
    Возвращает сотрудника с максимальной зарплатой в отделе
     */
    private static Employee getEmployeeWithMaxSalary(Employee[] employees, int departmentNumber) {
        return getEmployeeWithMaxSalary(getEmployeesFromDepartment(employees, departmentNumber));
    }

    /*
    Возвращает сумму затрат на месяц в указанном отделе
     */
    private static double getMothSpending(Employee[] employees, int departmentNumber) {
        return getMothSpending(getEmployeesFromDepartment(employees, departmentNumber));
    }

    /*
    Возвращает среднюю зарплату в месяц в указанном отделе
     */
    private static double getAverageSalary(Employee[] employees, int departmentNumber) {
        return  getAverageSalary(getEmployeesFromDepartment(employees, departmentNumber));
    }




}