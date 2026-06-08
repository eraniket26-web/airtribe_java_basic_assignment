# LearnTrack - Class Relationship Diagram

## 1. UML Class Diagram (Mermaid Format)

```mermaid
graph TD
    %% Entity Classes
    Person["Person (Abstract)"]
    Student["Student<br/>- id: int<br/>- firstName: String<br/>- lastName: String<br/>- email: String<br/>- batch: String<br/>- isActive: boolean"]
    Course["Course<br/>- id: int<br/>- courseName: String<br/>- description: String<br/>- durationInWeeks: int<br/>- isActive: boolean"]
    Enrollment["Enrollment<br/>- id: int<br/>- studentId: int<br/>- courseId: int<br/>- enrollmentDate: String<br/>- status: Status"]
    
    %% Service Classes
    StudentService["StudentService<br/>- repository: StudentRepository<br/>+ addStudent(Student)<br/>+ getAllStudents(): List<br/>+ getStudentById(int): Student<br/>+ deactivateStudent(int)"]
    CourseService["CourseService<br/>- repository: CourseRepository<br/>+ addCourse(Course)<br/>+ getAllCourses(): List<br/>+ getCourseById(int): Course<br/>+ enabledOrDisabledCourse(int, boolean)"]
    EnrollmentService["EnrollmentService<br/>- repository: EnrollmentRepository<br/>+ enrollStudent(int, int)<br/>+ getStudentEnrollments(int): List<br/>+ markEnrollmentAsCompleted(int, int)<br/>+ markEnrollmentAsCancelled(int, int)"]
    
    %% Repository Classes
    StudentRepository["StudentRepository<br/>- students: List<Student><br/>+ addStudent(Student)<br/>+ getAllStudents(): List<br/>+ getStudentById(int): Student<br/>+ deActivateStudent(int): Student"]
    CourseRepository["CourseRepository<br/>- courses: List<Course><br/>+ addCourse(Course)<br/>+ getAllCourses(): List<br/>+ getCourseById(int): Course<br/>+ enableOrDisableCourse(int, boolean)"]
    EnrollmentRepository["EnrollmentRepository<br/>- enrollments: List<Enrollment><br/>+ addEnrollment(Enrollment)<br/>+ checkEnrollment(int, int): Enrollment<br/>+ getEnrollmentsByStudentId(int): List<br/>+ markEnrollmentCompleted(int, int, Status)<br/>+ markEnrollmentCancelled(int, int, Status)"]
    
    %% Utility Classes
    IdGenerator["IdGenerator<br/>- studentIdCounter: int<br/>- courseIdCounter: int<br/>- enrollmentIdCounter: int<br/>+ generateNextStudentId(): int<br/>+ generateNextCourseId(): int<br/>+ generateNextEnrollmentId(): int"]
    InputValidator["InputValidator<br/>+ validateStudentFirstName(String): boolean<br/>+ validateStudentLastName(String): boolean<br/>+ validateEmail(String): boolean<br/>+ validateBatch(String): boolean<br/>+ validateCourseName(String): boolean<br/>+ validateCourseDescription(String): boolean<br/>+ validateDurationInWeeks(int): boolean<br/>+ validateAge(int): boolean"]
    
    %% Constants and Enums
    EnrollmentStatus["EnrollmentStatus<br/>enum Status<br/>- ACTIVE<br/>- COMPLETED<br/>- CANCELLED"]
    MenuOptions["MenuOptions<br/>- MENU_ADD_STUDENT: int<br/>- MENU_VIEW_STUDENTS: int<br/>- MENU_SEARCH_STUDENT: int<br/>- MENU_DEACTIVATE_STUDENT: int<br/>- MENU_EXIT: int"]
    AppConstants["AppConstants<br/>- INVALID_OPTION: String<br/>- PLEASE_SELECT_OPT: String<br/>- EXIT: String"]
    
    %% Exception Classes
    Exception1["EntityNotFoundException<br/>+ EntityNotFoundException(String)"]
    Exception2["CourseNotFoundException<br/>+ CourseNotFoundException(String)"]
    
    %% Main Application
    Main["Main<br/>- studentService: StudentService<br/>- courseService: CourseService<br/>- enrollmentService: EnrollmentService<br/>- scanner: Scanner<br/>+ main(String[])"]
    
    %% Relationships
    Student -->|extends| Person
    
    StudentService -->|uses| StudentRepository
    CourseService -->|uses| CourseRepository
    EnrollmentService -->|uses| EnrollmentRepository
    
    StudentService -->|uses| IdGenerator
    CourseService -->|uses| IdGenerator
    EnrollmentService -->|uses| IdGenerator
    
    StudentService -->|uses| InputValidator
    CourseService -->|uses| InputValidator
    
    StudentService -->|creates/manages| Student
    CourseService -->|creates/manages| Course
    EnrollmentService -->|creates/manages| Enrollment
    
    StudentRepository -->|stores| Student
    CourseRepository -->|stores| Course
    EnrollmentRepository -->|stores| Enrollment
    
    EnrollmentRepository -->|uses| EnrollmentStatus
    
    Main -->|uses| StudentService
    Main -->|uses| CourseService
    Main -->|uses| EnrollmentService
    
    Main -->|uses| MenuOptions
    Main -->|uses| AppConstants
    
    StudentService -->|throws| Exception1
    CourseService -->|throws| Exception2
    EnrollmentService -->|throws| Exception1
```

---

## 2. Architecture Layers Diagram

```
┌─────────────────────────────────────────────────────────┐
│                   PRESENTATION LAYER                    │
│                       (Main.java)                       │
│  - User Interaction                                     │
│  - Menu Display                                         │
│  - Input/Output Handling                                │
└────────────┬────────────────────────────────────────────┘
             │
┌────────────▼────────────────────────────────────────────┐
│                    SERVICE LAYER                        │
│  ┌──────────────────┬──────────────────┬──────────────┐ │
│  │StudentService    │CourseService     │EnrollmentSvc│ │
│  ├──────────────────┼──────────────────┼──────────────┤ │
│  │• addStudent()    │• addCourse()     │•enrollStudnt│ │
│  │• getStudentById()│• getCourseById() │•viewEnrollm │ │
│  │• getAllStudents()│• getAllCourses() │•markComplete│ │
│  │• deactivate()    │• enableDisable() │•markCancel  │ │
│  └──────────────────┴──────────────────┴──────────────┘ │
└────────────┬────────────────────────────────────────────┘
             │
┌────────────▼────────────────────────────────────────────┐
│                   REPOSITORY LAYER                      │
│  ┌──────────────────┬──────────────────┬──────────────┐ │
│  │StudentRepository │CourseRepository  │EnrollmentRep│ │
│  ├──────────────────┼──────────────────┼──────────────┤ │
│  │List<Student>     │List<Course>      │List<Enrollm│ │
│  │                  │                  │             │ │
│  │• CRUD Operations │• CRUD Operations │• CRUD Ops  │ │
│  │• Queries         │• Queries         │• Queries   │ │
│  └──────────────────┴──────────────────┴──────────────┘ │
└────────────┬────────────────────────────────────────────┘
             │
┌────────────▼────────────────────────────────────────────┐
│                    DATA LAYER                           │
│  ┌──────────────────┬──────────────────┬──────────────┐ │
│  │Student List      │Course List       │Enrollment    │ │
│  │(In-Memory)       │(In-Memory)       │List(In-Mem)  │ │
│  └──────────────────┴──────────────────┴──────────────┘ │
└─────────────────────────────────────────────────────────┘
```

---

## 3. Entity Relationships (ER Diagram)

```
┌───────────────┐
│    STUDENT    │
├───────────────┤
│ - id (PK)     │
│ - firstName   │
│ - lastName    │
│ - email       │
│ - batch       │
│ - isActive    │
└───────┬───────┘
        │
        │ 1:N
        │ (enrolls in)
        │
        ▼
┌───────────────┐        ┌──────────────┐
│  ENROLLMENT   │────────│    COURSE    │
├───────────────┤ N:M    ├──────────────┤
│ - id (PK)     │        │ - id (PK)    │
│ - studentId   │        │ - courseName │
│ - courseId    │        │ - description│
│ - enrollDate  │        │ - duration   │
│ - status      │        │ - isActive   │
└───────────────┘        └──────────────┘

Status: ACTIVE, COMPLETED, CANCELLED
```

---

## 4. Data Flow Diagram

```
USER INPUT
    │
    ▼
┌─────────────────────────────────────┐
│      Main Class (Menu Handler)      │
│  - displayMenu()                    │
│  - readUserChoice()                 │
│  - studentMenu()                    │
│  - courseMenu()                     │
│  - enrollmentMenu()                 │
└────────────┬────────────────────────┘
             │
             ▼
      ┌──────────────────┐
      │  Services Layer  │
      │  ┌────────────┐  │
      │  │Validate    │  │
      │  │Input       │  │
      │  └────────────┘  │
      │  ┌────────────┐  │
      │  │Process     │  │
      │  │Business    │  │
      │  │Logic       │  │
      │  └────────────┘  │
      └────────┬─────────┘
               │
               ▼
        ┌──────────────────┐
        │Repository Layer  │
        │  ┌────────────┐  │
        │  │Query       │  │
        │  │Update      │  │
        │  │Add/Delete  │  │
        │  └────────────┘  │
        └────────┬─────────┘
                 │
                 ▼
          ┌──────────────┐
          │ In-Memory    │
          │ Storage      │
          │ Lists        │
          └──────────────┘
```

---

## 5. Class Dependency Matrix

| Class | Depends On | Used By |
|-------|-----------|---------|
| **Student** | Person, EnrollmentStatus | StudentService, StudentRepository, Main |
| **Course** | - | CourseService, CourseRepository, Main |
| **Enrollment** | EnrollmentStatus | EnrollmentService, EnrollmentRepository, Main |
| **StudentService** | StudentRepository, IdGenerator, InputValidator, Student | Main |
| **CourseService** | CourseRepository, IdGenerator, InputValidator, Course | Main |
| **EnrollmentService** | EnrollmentRepository, IdGenerator, Enrollment, EnrollmentStatus | Main |
| **StudentRepository** | Student | StudentService |
| **CourseRepository** | Course | CourseService |
| **EnrollmentRepository** | Enrollment, EnrollmentStatus | EnrollmentService |
| **IdGenerator** | - | All Services |
| **InputValidator** | - | StudentService, CourseService |
| **Main** | All Services, All Entities | Entry Point |

---

## 6. Component Interaction Flow

### Student Management Flow:
```
User → Main.addNewStudent()
    ↓
Main.addNewStudent() validates input
    ↓
StudentService.addStudent(Student)
    ↓
IdGenerator.generateNextStudentId()
    ↓
StudentRepository.addStudent(Student)
    ↓
Student added to in-memory List
    ↓
Logger.info() - Success message
```

### Course Management Flow:
```
User → Main.addCourse()
    ↓
Main.addCourse() validates input
    ↓
CourseService.addCourse(Course)
    ↓
IdGenerator.generateNextCourseId()
    ↓
CourseRepository.addCourse(Course)
    ↓
Course added to in-memory List
    ↓
Logger.info() - Success message
```

### Enrollment Management Flow:
```
User → Main.enrollStudentMenu()
    ↓
Main.enrollStudentMenu() reads studentId & courseId
    ↓
EnrollmentService.enrollStudent(int, int)
    ↓
Check duplicate enrollment via Repository
    ↓
Create Enrollment object with ACTIVE status
    ↓
IdGenerator.generateNextEnrollmentId()
    ↓
EnrollmentRepository.addEnrollment(Enrollment)
    ↓
Enrollment added to in-memory List
    ↓
Logger.info() - Success message
```

---

## 7. Package Structure

```
com.airtribe.learntrack/
├── Main.java
├── entity/
│   ├── Person.java (Abstract Base Class)
│   ├── Student.java (extends Person)
│   ├── Course.java
│   └── Enrollment.java
├── service/
│   ├── StudentService.java
│   ├── CourseService.java
│   └── EnrollmentService.java
├── repository/
│   ├── StudentRepository.java
│   ├── CourseRepository.java
│   └── EnrollmentRepository.java
├── constants/
│   ├── AppConstants.java
│   ├── EnrollmentStatus.java
│   └── MenuOptions.java
├── exception/
│   ├── EntityNotFoundException.java
│   └── CourseNotFoundException.java
└── util/
    ├── IdGenerator.java
    └── InputValidator.java
```

---

## 8. Key Design Patterns Used

| Pattern | Where | Purpose |
|---------|-------|---------|
| **Layered Architecture** | Main → Service → Repository | Separation of concerns |
| **Repository Pattern** | Repository Classes | Data access abstraction |
| **Service Layer** | Service Classes | Business logic encapsulation |
| **Singleton (Logger)** | All Classes | Centralized logging |
| **Enum** | EnrollmentStatus | Type-safe status values |
| **Factory Method** | IdGenerator | ID generation |
| **Validator Pattern** | InputValidator | Input validation |

---

## 9. Relationships Summary

### Inheritance:
- **Student extends Person**

### Composition/Association:
- **Main uses StudentService, CourseService, EnrollmentService**
- **Services use Repositories**
- **Services use IdGenerator and InputValidator**
- **Repositories manage Entity Lists**

### Dependency:
- **All Services → IdGenerator (for ID generation)**
- **All Classes → Logger (SLF4J)**
- **Exception Classes → All Services**
- **EnrollmentStatus Enum → Enrollment, EnrollmentRepository, EnrollmentService**

---

## 10. Data Storage Structure

```
StudentRepository:
    private static List<Student> students = [
        Student{id=1, firstName="...", lastName="...", ...},
        Student{id=2, firstName="...", lastName="...", ...},
        ...
    ]

CourseRepository:
    private static List<Course> courses = [
        Course{id=1, courseName="Java", ...},
        Course{id=2, courseName="Python", ...},
        ...
    ]

EnrollmentRepository:
    private static List<Enrollment> enrollments = [
        Enrollment{id=1, studentId=1, courseId=1, status=ACTIVE, ...},
        Enrollment{id=2, studentId=2, courseId=2, status=COMPLETED, ...},
        ...
    ]
```

---

## Summary

✅ **3-Layer Architecture**: Presentation → Service → Repository → Data
✅ **Loose Coupling**: Services depend on abstractions (Repositories)
✅ **High Cohesion**: Each class has single responsibility
✅ **Reusability**: IdGenerator and InputValidator used across services
✅ **Exception Handling**: Custom exceptions for error management
✅ **Logging**: SLF4J integrated across all layers
✅ **In-Memory Storage**: Lists for persistence (can be replaced with DB)


