package pro.sky.java.course1.courseWork;

public class EmployeeBook {

    //Список сотрудников
    private Employee[] employees;
    //Счетчик
    private int counter = 0;

    /*
    Находит индекс первой пустой ячейки.
     */
    private static int getFirstFreeElement(Employee[] employees) throws ArrayIsFullExceptions {

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                return i;
            }
        }

        throw new ArrayIsFullExceptions("Нет свободных ячеек в массиве");

    }
}
