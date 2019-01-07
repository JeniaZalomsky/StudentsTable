package students;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentsSortedImpl implements ElementCollection<Student, String> {

	private Map<String, Student> map;

	public StudentsSortedImpl() {
		this.map=new TreeMap<String,Student>(new Comparator<String>() {
			@Override
			public int compare(String id1, String id2) {
				return id1.compareTo(id2);
			}
		});
	}

	@Override
	public void add(Student s) {
		this.map.put(s.getKey(), s);
	}

	@Override
	public Optional<Student> getByKey(String key) {
		Student sd = this.map.get(key);

		if (sd != null) {
			return Optional.of(sd);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Collection<Student> getAll() {
		return this.map.values();
	}

	@Override
	public Collection<Student> getByCriteria(Predicate<Student> criteria) {
		return this.map.values().stream().filter(criteria).collect(Collectors.toList());
	}

	@Override
	public void remove(Student s) {
		this.map.remove(s.getKey());

	}

	@Override
	public void clear() {
		this.map.clear();

	}

}
