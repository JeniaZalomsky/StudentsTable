package students;

public class StudentsFactory {
	public ElementCollection<Student, String> createStudents() {
		return new StudentsDifferentImplimentation();
	}
}
