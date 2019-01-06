package students;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface ElementCollection<E extends KeyOwner<K>, K> {
	public void add (E e);
	public Optional<E> getByKey (K key);
	public Collection<E> getAll();
	public Collection<E> getByCriteria(Predicate<E> criteria);
	public void remove (E e);
	public void clear();	
}
