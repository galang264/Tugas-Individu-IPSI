import java.util.Vector;
import java.util.Enumeration;

public class Course {
    private String title;
    private Vector courseRecords;

    Course() {
        courseRecords = new Vector();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
    }

    public void addCourseRecord(CourseRecord cr) {
        courseRecords.addElement(cr);
    }

    public Enumeration getCourseRecords() {
        return courseRecords.elements();
    }

    public void printCourseStudents() {
        Enumeration records = getCourseRecords();

        while (records.hasMoreElements()) {
            CourseRecord cr = (CourseRecord) records.nextElement();
            Student student = cr.getStudent();

            if (student != null) {
                System.out.println(student.getName());
            }
        }
    }

    private double calculateAverage(CourseRecord cr) {
        Enumeration assignments = cr.getAssignments();

        double total = 0;
        int count = 0;

        while (assignments.hasMoreElements()) {
            Assignment a = (Assignment) assignments.nextElement();

            try {
                total += a.getMark();
                count++;
            } catch (NotYetSetException e) {
                // Nilai tugas belum diisi.
            }
        }

        if (count == 0) {
            return -1;
        }

        return total / count;
    }

    public void printBestStudent() {
        Enumeration records = getCourseRecords();

        Student bestStudent = null;
        double bestAverage = -1;

        while (records.hasMoreElements()) {
            CourseRecord cr = (CourseRecord) records.nextElement();
            Student student = cr.getStudent();

            if (student == null) {
                continue;
            }

            double average = calculateAverage(cr);

            if (average > bestAverage) {
                bestAverage = average;
                bestStudent = student;
            }
        }

        if (bestStudent != null) {
            System.out.println(
                "Mahasiswa terbaik: " + bestStudent.getName()
            );
            System.out.println(
                "Nilai rata-rata: " + bestAverage
            );
        } else {
            System.out.println("Belum ada nilai yang bisa dihitung.");
        }
    }

    public void printFinalExamStudents() {
        Enumeration records = getCourseRecords();

        while (records.hasMoreElements()) {
            CourseRecord cr = (CourseRecord) records.nextElement();
            Student student = cr.getStudent();

            if (student == null) {
                continue;
            }

            double average = calculateAverage(cr);

            // Asumsi sementara: rata-rata minimal 60.
            if (average >= 60) {
                System.out.println(student.getName());
            }
        }
    }
}