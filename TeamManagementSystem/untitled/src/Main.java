
/*
 Begin

    Abstract class Employee with attributes name, employeeId, address, phoneNumber
    - Employee constructor initializes attributes
    - Methods: getName, setName, getEmployeeId, setEmployeeId, getAddress, setAddress, getPhoneNumber, setPhoneNumber, abstract calculateWages, toString

    Class SalariedEmployee extends Employee with attribute salary
    - SalariedEmployee constructor initializes attributes including superclass attributes
    - Methods: getSalary, setSalary, calculateWages (returns salary), toString (includes superclass toString plus salary info)

    Class CommissionEmployee extends Employee with attributes commissionPerSale, numberOfSales
    - CommissionEmployee constructor initializes attributes including superclass attributes
    - Methods: getCommissionPerSale, setCommissionPerSale, getNumberOfSales, setNumberOfSales, calculateWages (returns commissionPerSale * numberOfSales), toString (includes superclass toString plus commission info)

    Class BasePlusCommissionEmployee extends CommissionEmployee with attribute basePay
    - BasePlusCommissionEmployee constructor initializes attributes including superclass attributes
    - Methods: getBasePay, setBasePay, calculateWages (returns super.calculateWages() + basePay), toString (includes superclass toString plus base pay info)

    Class HourlyEmployee extends Employee with attributes hourlyRate, hoursWorked
    - HourlyEmployee constructor initializes attributes including superclass attributes
    - Methods: getHourlyRate, setHourlyRate, getHoursWorked, setHoursWorked, calculateWages (returns hourlyRate * hoursWorked), toString (includes superclass toString plus hourly info)

    Class Team with attributes teamName, members (list of Employee)
    - Team constructor initializes teamName and instantiates members list
    - Methods: addMember (adds Employee to members), removeMember (removes Employee from members), printTeamInfo (prints teamName and details of each member including wages)

    Main function:
    - Create Team object
    - Create SalariedEmployee, CommissionEmployee, BasePlusCommissionEmployee, and HourlyEmployee objects
    - Add all employee objects to the team
    - Call printTeamInfo on the team object to display all members and their wage details

 End
*/


import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    private String name;
    private String employeeId;
    private String address;
    private String phoneNumber;

    public Employee(String name, String employeeId, String address, String phoneNumber) {
        this.name = name;
        this.employeeId = employeeId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public abstract double calculateWages();

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

class SalariedEmployee extends Employee {
    private double salary;

    public SalariedEmployee(String name, String employeeId, String address, String phoneNumber, double salary) {
        super(name, employeeId, address, phoneNumber);
        this.salary = salary;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public double calculateWages() {
        return getSalary();
    }

    @Override
    public String toString() {
        return super.toString() + ", salary=" + salary;
    }
}

class CommissionEmployee extends Employee {
    private double commissionPerSale;
    private int numberOfSales;

    public CommissionEmployee(String name, String employeeId, String address, String phoneNumber, double commissionPerSale, int numberOfSales) {
        super(name, employeeId, address, phoneNumber);
        this.commissionPerSale = commissionPerSale;
        this.numberOfSales = numberOfSales;
    }

    public double getCommissionPerSale() { return commissionPerSale; }
    public void setCommissionPerSale(double commissionPerSale) { this.commissionPerSale = commissionPerSale; }

    public int getNumberOfSales() { return numberOfSales; }
    public void setNumberOfSales(int numberOfSales) { this.numberOfSales = numberOfSales; }

    @Override
    public double calculateWages() {
        return getCommissionPerSale() * getNumberOfSales();
    }

    @Override
    public String toString() {
        return super.toString() + ", commissionPerSale=" + commissionPerSale + ", numberOfSales=" + numberOfSales;
    }
}

class BasePlusCommissionEmployee extends CommissionEmployee {
    private double basePay;

    public BasePlusCommissionEmployee(String name, String employeeId, String address, String phoneNumber, double commissionPerSale, int numberOfSales, double basePay) {
        super(name, employeeId, address, phoneNumber, commissionPerSale, numberOfSales);
        this.basePay = basePay;
    }

    public double getBasePay() { return basePay; }
    public void setBasePay(double basePay) { this.basePay = basePay; }

    @Override
    public double calculateWages() {
        return super.calculateWages() + getBasePay();
    }

    @Override
    public String toString() {
        return super.toString() + ", basePay=" + basePay;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(String name, String employeeId, String address, String phoneNumber, double hourlyRate, double hoursWorked) {
        super(name, employeeId, address, phoneNumber);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    @Override
    public double calculateWages() {
        return getHourlyRate() * getHoursWorked();
    }

    @Override
    public String toString() {
        return super.toString() + ", hourlyRate=" + hourlyRate + ", hoursWorked=" + hoursWorked;
    }
}

class Team {
    private String teamName;
    private List<Employee> members;

    public Team(String teamName) {
        this.teamName = teamName;
        this.members = new ArrayList<>();
    }

    public void addMember(Employee employee) {
        members.add(employee);
    }

    public void removeMember(Employee employee) {
        members.remove(employee);
    }

    public void printTeamInfo() {
        System.out.println("Team: " + teamName);
        for (Employee member : members) {
            System.out.println(member + ", Wages: " + member.calculateWages());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Alpha");

        SalariedEmployee salariedEmployee = new SalariedEmployee("John Doe", "E001", "123 Maple Street", "555-1010", 50000);
        CommissionEmployee commissionEmployee = new CommissionEmployee("Jane Roe", "E002", "456 Oak Avenue", "555-2020", 10.0, 200);
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Jim Poe", "E003", "789 Pine Road", "555-3030", 15.0, 100, 30000);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Julia Doe", "E004", "321 Birch Blvd", "555-4040", 20.0, 40);

        team.addMember(salariedEmployee);
        team.addMember(commissionEmployee);
        team.addMember(basePlusCommissionEmployee);
        team.addMember(hourlyEmployee);

        team.printTeamInfo();
    }
}



/*
Team: Alpha
Employee{name='John Doe', employeeId='E001', address='123 Maple Street', phoneNumber='555-1010'}, salary=50000.0, Wages: 50000.0
Employee{name='Jane Roe', employeeId='E002', address='456 Oak Avenue', phoneNumber='555-2020'}, commissionPerSale=10.0, numberOfSales=200, Wages: 2000.0
Employee{name='Jim Poe', employeeId='E003', address='789 Pine Road', phoneNumber='555-3030'}, commissionPerSale=15.0, numberOfSales=100, basePay=30000.0, Wages: 31500.0
Employee{name='Julia Doe', employeeId='E004', address='321 Birch Blvd', phoneNumber='555-4040'}, hourlyRate=20.0, hoursWorked=40.0, Wages: 800.0

Process finished with exit code 0
 */