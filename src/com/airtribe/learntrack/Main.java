package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.airtribe.learntrack.constants.AppConstants.INVALID_OPTION;
import static com.airtribe.learntrack.constants.AppConstants.PLEASE_SELECT_OPT;
import static com.airtribe.learntrack.constants.MenuOptions.*;

/**
 * Main entry point for the LearnTrack application
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        logger.info("Welcome to LearnTrack!");
        int choiceNum = 0;
        int chooseOption = 0;
        do {
            displayMainMenu();
            try {
                choiceNum = readUserChoice();
                switch (choiceNum) {
                    case 1 -> {
                        displayStudentMenu();
                         chooseOption = readUserChoice();
                        studentMenu(chooseOption);
                    }
                    case 2 -> {
                        logger.info("Course Management selected - Feature coming soon!");
                        displayCourseMenu();
                        chooseOption = readUserChoice();
                        courseMenu(chooseOption);
                    }
                    case 3 -> {
                        logger.info("Enrollment Management selected - Feature coming soon!");
                    }
                    default -> {
                        logger.info("Exiting application...");
                    }
                }

            } catch (NumberFormatException e) {
                logger.error("Invalid input provided: {}", e.getMessage());
            } catch (EntityNotFoundException | IllegalArgumentException e) {
                logger.error("Error in main: {}", e.getMessage());
            }

        } while (choiceNum != MENU_EXIT);

        logger.info("Thank you for using LearnTrack!");
        scanner.close();
    }

    private static void courseMenu(int chooseOption) {

        switch (chooseOption) {

            case MENU_ADD_COURSE -> {
                logger.info("Add new course selected!");
                addCourse();
            }
            case MENU_VIEW_COURSE -> {
                logger.info("View all courses selected - Feature coming soon!");
                viewCourses();
            }
            case MENU_ACTIVATE_DEACTIVATE_COURSE -> {
                logger.info("Activate/Deactivate course selected - Feature coming soon!");
                activateDeactivateCourse();
            }
            default -> 
                logger.info("Exiting course menu...");
        }
    }

    private static void displayCourseMenu() {

        logger.info("\n======== Course Menu ========");
        logger.info("1. Add new course");
        logger.info("2. View all courses");
        logger.info("3. Activate/Deactivate course");
        logger.info(EXIT);
        logger.info(PLEASE_SELECT_OPT);

    }


    private static void displayMainMenu() {
        logger.info("\n======== LearnTrack Menu ========");
        logger.info("1. Student Management");
        logger.info("2. Course Management");
        logger.info("3. Enrollment Management");
        logger.info(EXIT);
        logger.info(PLEASE_SELECT_OPT);
    }




    /**
     * Display the main menu
     */
    private static void displayStudentMenu() {
       logger.info("\n======== Student Menu ========");
       logger.info("1. Add Student");
       logger.info("2. View All Students");
       logger.info("3. Search Student by ID");
       logger.info("4. Deactivate Student");
        logger.info(EXIT);
        logger.info(PLEASE_SELECT_OPT);
    }

    /**
     * Handle menu choice
     */
    private static void studentMenu(int choice) {
        switch (choice) {
            case MENU_ADD_STUDENT:
                addNewStudent();
                break;
            case MENU_VIEW_STUDENTS:
                viewAllStudents();
                break;
            case MENU_SEARCH_STUDENT:
                searchStudent();
                break;
            case MENU_DEACTIVATE_STUDENT:
                deactivateStudentMenu();
                break;
            case MENU_EXIT:
                logger.info("Exiting application...");
                break;
            default:
                logger.info("Invalid choice. Please select a valid option (1-5).");
        }
    }

    /**
     * Add a new student
     */
    private static void addNewStudent() {
        try {
            logger.info("Adding a new student...");
            Student student = new Student();

            logger.info("Enter first name: ");
            String firstName = scanner.nextLine();
            student.setFirstName(firstName);

            logger.info("Enter last name: ");
            String lastName = scanner.nextLine();
            student.setLastName(lastName);

            logger.info("Enter email: ");
            String email = scanner.nextLine();
            student.setEmail(email);

            logger.info("Enter batch: ");
            String batch = scanner.nextLine();
            student.setBatch(batch);

            logger.info("Is the student active? (true/false): ");
            boolean isActive = scanner.nextBoolean();
            student.setActive(isActive);

            studentService.addStudent(student);
           logger.info("✓ Student added successfully!");
        } catch (Exception e) {
            logger.error("Error adding student: {}", e.getMessage());
        }
    }

    /**
     * View all students
     */
    private static void viewAllStudents() {
        try {
            var students = studentService.getAllStudents();
            if (students.isEmpty()) {
               logger.info("No students found.");
                return;
            }

           logger.info("\n======== Student List ========");
            students.forEach(System.out::println);
           logger.info("================================\n");
        } catch (Exception e) {
            logger.error("Error retrieving students: {}", e.getMessage());
        }
    }

    /**
     * Search for a student by ID
     */
    private static void searchStudent() {
        try {
            logger.info("Enter student id to search: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Student student = studentService.getStudentById(id);
           logger.info("\n✓ Student found: {} " , student);
        } catch (EntityNotFoundException e) {
            logger.error("Error searching for student: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(INVALID_OPTION, e.getMessage());
        }
    }

    /**
     * Deactivate a student
     */
    private static void deactivateStudentMenu() {
        try {
            logger.info("Enter student id to deactivate: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            studentService.deactivateStudent(id);
           logger.info("✓ Student deactivated successfully!");
        } catch (EntityNotFoundException e) {
            logger.error("Error occurred while deactivating student: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(INVALID_OPTION, e.getMessage());
        }
    }

    private static int readUserChoice(){
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }


    private static void activateDeactivateCourse() {
      logger.info("Enter id of course to activate/deactivate: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean flag = false;
        logger.info("Enter true to activate or false to deactivate: ");
        try {
            flag = scanner.nextBoolean();
        }catch (Exception e){
            logger.error("Invalid input for activation flag: {}", e.getMessage());
            return;
        }
        courseService.enabledOrDisabledCourse(id, flag);
       logger.info("✓ Course with ID {} has been {} successfully!", id, flag ? "activated" : "deactivated");
    }

    private static void viewCourses() {
        logger.info("Fetching all courses...");
        courseService.getAllCourses()
                .forEach(course -> logger.info(course.toString()));
    }

    private static void addCourse() {

        try{
            Course course = new Course();
             logger.info("Enter course name: ");
             String courseName = scanner.nextLine();
             course.setCourseName(courseName);
             logger.info("Enter course description: ");
             String description = scanner.nextLine();
             course.setDescription(description);
             logger.info("Enter course duration in weeks: ");
             int durationInWeeks = scanner.nextInt();
             course.setDurationInWeeks(durationInWeeks);
             scanner.nextLine();

             courseService.addCourse(course);

        }catch (IllegalArgumentException e){
            logger.error("Error adding course: {}", e.getMessage());

        }

    }
}
