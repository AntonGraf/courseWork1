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
