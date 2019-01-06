package students;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentsDifferentImplimentation implements ElementCollection<Student, String> {
	private Map<String, Student> map;
	
	public StudentsDifferentImplimentation() {
		this.map = new HashMap<>();
	}
	
	@Override
	public void add(Student student) {
		this.map.put(student.getKey(), student);
	}

	@Override
	public Optional<Student> getByKey(String key) {
		Student rv = this.map.get(key);
		
		if (rv != null) {
			return Optional.of(rv);
		}else {
			return Optional.empty();
		}
	}

	@Override
	public Collection<Student> getAll() {
		return this.map.values();
	}

	@Override
	public Collection<Student> getByCriteria(Predicate<Student> criteria) {
//		List<Student> rv = new ArrayList<>();
//		for (Student s : this.map.values()) {
//			if (criteria.test(s)) {
//				rv.add(s);
//			}
//		}
//		return rv;
		
		return 
			this.map.values()
				.stream()
				.filter(criteria)
				.collect(Collectors.toList());
	}

	@Override
	public void remove(Student student) {
		this.map.remove(student.getKey());
	}

	@Override
	public void clear() {
		this.map.clear();
	}

}
