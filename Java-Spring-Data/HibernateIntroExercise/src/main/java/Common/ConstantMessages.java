package Common;

public class ConstantMessages {
    public static final String CHOSE_TASKS_NUMBER = String.format("%d. %s%n%d. %s%n%d. %s%n%d. %s%n%d. %s%n%d. %s%n" +
            "%d. %s%n%d. %s%n%d. %s%n%d. %s%n%d. %s%n%d. %s",
            2, "Change casing",
            3, "Contains Employee",
            4, "Employees with a Salary Over 50 000",
            5, "Employees from Department",
            6, "Adding a New Address and Updating the Employee",
            7, "Addresses with Employee Count",
            8, "Get Employees with Project",
            9, "Find the Latest 10 Projects",
            10, "Increase Salaries",
            11, "Find Employees by First Name",
            12, "Employees Maximum Salaries",
            13, "Remove Towns");
    public static final String CHOSE_TASK_MASSAGES = "Please enter exercises number:";
    public static final String CHOSE_ANOTHER_TASK_OR_END = "Please select another task number, press Enter to read exercises list or End to end the program:";
    public static final String ENTER_FULL_NAME = "Please enter full name to check if it exists in the database";
    public static final String ENTER_LAST_NAME = "Please enter employee last name to change the address";
    public static final String ENTER_EMPLOYEE_ID = "Please enter employee ID";
    public static final String ENTER_VALID_NAME = "Please enter valid name";
    public static final String ENTER_FIRST_NAME_PATTERN = "Please enter first name pattern:";
    public static final String ENTER_TOWN_NAME_FOR_DELETE = "Please enter town name, that you want to delete";
    public static final String AFFECTED_ENTITY = "Affected entity: ";
    public static final String NO_SUCH_EMPLOYEES_EXIST = "No such employee exist";
    public static final String NO_SUCH_TOWN_EXIST = "No such town exist";
    public static final String NO_SUCH_EXERCISE = "No such exercise. Please chose valid exercise number";
    public static final String INCORRECT_NAME_FORMAT = "Incorrect input name format! Please enter a valid name format";

}
