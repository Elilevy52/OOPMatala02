package ID_206946790_ELI_LEVY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class NewSet<T extends MultipeChoiseAnswer> implements Set<T>, Serializable{

	ArrayList<T> data = new ArrayList<T>();

	@Override
	public int size() {
		return data.size();

	}

	@Override
	public boolean isEmpty() {
		if (data.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		if(data.contains(o)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> dataIterator = data.iterator();
		if(dataIterator.hasNext()) {
			return dataIterator;
		}
		return data.iterator();
	}

	@Override
	public Object[] toArray() {;
		Object[] arr = new Object[data.size()];
		for (int i = 0; i < data.size(); i++) {
			arr[i] = data.get(i);
		}
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		
		return null;
	}

	@Override
	public boolean add(T e) {
		if(data.contains(e)) {
			System.out.println("Object already exists in data.");
			return false;
		}
		else {
			data.add(e);
			System.out.println("Object added Successfully");
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		if(!data.contains(o)) {
			System.out.println("Object doesn't exists.");
			return false;
		}else {
			data.remove(o);
			System.out.println("Object removed Successfully.");
			return true;
		}	
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if(data.containsAll(c)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if(data.addAll(c)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if(data.retainAll(c)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if(data.removeAll(c)) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		data.clear();
	}

	public T get(int index) {
		return data.get(index);

	}

	public void set(int i, Object object) {
		data.set(i, (T) object);
		System.out.println("Object changed Successfully.");
	}

	public void add(int i, Object answer) {
		data.add(i , (T) answer);
		System.out.println("Object added Successfully.");
	}

}
