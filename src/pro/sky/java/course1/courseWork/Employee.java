package pro.sky.java.course1.courseWork;

public class Employee {

    //Имя
    private final String firstName;
    //Фамилия
    private final String lastName;
    //Отчество
    private final String middleName;
    //Номер отдела
    private int departmentNumber;
    //Зарплата
    private double salary;
    //ID
    private final int id;
    //счетчик
    private static int counter = 0;

    public Employee(String lastName, String firstName, String middleName, int departmentNumber, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.departmentNumber = departmentNumber;
        this.salary = salary;
        this.id = counter++;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (departmentNumber != employee.departmentNumber) return false;
        if (Double.compare(employee.salary, salary) != 0) return false;
        if (id != employee.id) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;
        return middleName != null ? middleName.equals(employee.middleName) : employee.middleName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + departmentNumber;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Сотрудник: " + "id " + id + ", " + lastName + " " + firstName + " " + middleName + ", отдел №" + departmentNumber + ", " +
                ", получает " + salary;
    }
}
