abstract class EmployeeType {
    abstract int payAmount(Employee emp);
}

class Salesman extends EmployeeType {
    int payAmount(Employee emp) {
        return emp.getMonthlySalary() + emp.getCommission();
    }
}

class Manager extends EmployeeType {
    int payAmount(Employee emp) {
        return emp.getMonthlySalary() + emp.getBonus();
    }
}