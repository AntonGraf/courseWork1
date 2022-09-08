package pro.sky.java.course1.courseWork;

public class Main {
    public static void main(String[] args) {

        maximalLevel();
    }

    /*
    Привести структуру проекта к ООП.
    1. Создать класс EmployeeBook.
    2. Перенести хранилище сотрудников в него (массив), закрыть к нему доступ извне (сделать приватным).
    3. Все статические методы по работе с массивом перенести в этот класс и сделать нестатическими.
    4. Добавить несколько новых методов:
        1. Добавить нового сотрудника (создаем объект, заполняем поля, кладем в массив).
        Нужно найти свободную ячейку в массиве и добавить нового сотрудника в нее. Искать нужно всегда с начала, так как
         возможно добавление в ячейку удаленных ранее сотрудников.
        2. Удалить сотрудника (находим сотрудника по Ф. И. О. и/или id, обнуляем его ячейку в массиве).
    5. Изменить сотрудника (получить сотрудника по Ф. И. О., модернизировать его запись):
        1. Изменить зарплату.
        2. Изменить отдел.
        Придумать архитектуру. Сделать или два метода, или один, но продумать его.
    6. Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
     */
    private static void maximalLevel() {

        EmployeeBook employeeBook = new EmployeeBook();

        employeeBook.addEmployee(new Employee("Иванов", "Иван", "Иванович",
                5,90_000, employeeBook.generateId()));
        employeeBook.addEmployee(new Employee("Сидоров", "Сергей", "Петрович",
                4, 110_000, employeeBook.generateId()));
        employeeBook.addEmployee(new Employee("Петров", "Петр", "Петрович",
                5, 95_000, employeeBook.generateId()));
        employeeBook.addEmployee(new Employee("Смирнова", "Мария", "Ивановна",
                6, 97_000, employeeBook.generateId()));

        middleLevel(employeeBook);
        printSeparator();

        System.out.println("Курсовая сложного уровня");

        System.out.println("Пункт 4.1 Добавление сотрудника");
        employeeBook.addEmployee(new Employee("Семенов", "Семен", "Семенович",
                5, 195_000, employeeBook.generateId()));
        System.out.println(employeeBook);
        printSeparator();

        System.out.println("Пункт 4.2а Удаление сотрудника по Ф. И. О.");
        employeeBook.deleteEmployee("Иванов", "Иван", "Иванович");
        System.out.println(employeeBook);
        printSeparator();

        System.out.println("Пункт 4.2б Удаление сотрудника по id");
        employeeBook.deleteEmployee(2);
        System.out.println(employeeBook);
        printSeparator();

        System.out.println("Пункт 5 Изменить зарплату и отдел сотруднику");
        employeeBook.setEmployeeSalary("Семенов", "Семен", "Семенович", 130_000);
        employeeBook.setEmployeeDepartment("Семенов", "Семен", "Семенович",
                6);
        System.out.println(employeeBook);
        System.out.println("Второй вариант изменения зарплаты и отдела");
        employeeBook.setEmployeeDepartmentAndSalary("Семенов", "Семен", "Семенович",
                4,140_000);
        System.out.println(employeeBook);
        printSeparator();

        System.out.println("Пункт 6 Вывести список сотрудников по отделам");
        employeeBook.printEmployeesByDepartments();
        printSeparator();

        employeeBook.printEmployees();
    }

    private static void middleLevel(EmployeeBook employeeBook) {

        System.out.println("Из курсовой повышенного уровня.");
        System.out.println("Пункт 1");

        System.out.println("Данные сотрудников до повышения зарплат");
        System.out.println(employeeBook);

        System.out.println("Данные сотрудников после повышения зарплат");
        employeeBook.indexingSalary(10);
        System.out.println(employeeBook);
        printSeparator();

        System.out.println("Пункт 2");

        int departmentNumber = 5;
        System.out.println("Данные по отделу №" + departmentNumber);
        employeeBook.printEmployeesWithAllInfo(departmentNumber);

        try {
            System.out.printf("Сотрудник с минимальной зарплатой: %s \n",
                    employeeBook.getEmployeeWithMinSalary(departmentNumber));
            System.out.printf("Сотрудник с максимальной зарплатой: %s \n",
                    employeeBook.getEmployeeWithMaxSalary(departmentNumber));
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf("Сумма затрат в месяц: %.2f\n", employeeBook.getMothSpending(departmentNumber));
        System.out.printf("Средняя зарплата: %.2f\n", employeeBook.getAverageSalary(departmentNumber));
        System.out.println("Данные сотрудников после повышения зарплат");
        employeeBook.indexingSalary(5, departmentNumber);
        employeeBook.printEmployees(departmentNumber);
        printSeparator();

        System.out.println("Пункт 3");

        double salary = 106_000;
        System.out.printf("Список сотрудников с зарплатой ниже %.2f:\n", salary);
        employeeBook.printEmployeesWithLessSalary(salary);

        System.out.printf("Список сотрудников с зарплатой выше %.2f:\n", salary);
        employeeBook.printEmployeesWithMoreSalary(salary);
    }

    private static void printSeparator() {
        System.out.println("===========================");
    }
}