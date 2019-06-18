package sequencepattern.pattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ElementSequence<E> implements List<E> {

	private final List<E> elements;

	public ElementSequence(final List<E> list) {
		elements = list;
	}

	public ElementSequence(final ElementSequence<E> sequence) {
		elements = new ArrayList<>();
		for (int i = 0; i < sequence.size(); i++) {
			elements.add(sequence.get(i));
		}
	}

	@SafeVarargs
	public ElementSequence(final E... list) {
		elements = new ArrayList<>();
		for (E e : list) {
			elements.add(e);
		}
	}

	public E get(int index) {
		return elements.get(index);
	}

	public void add(final ElementSequence<E> sequence) {
		elements.addAll(sequence.elements);
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return size() <= 0;
	}

	public ElementSequence<E> subSequence(int start, int end) {
		return new ElementSequence<>(elements.subList(start, end));
	}

	public ElementSequence<E> subSequence(int start) {
		return subSequence(start, elements.size());
	}

	public List<ElementSequence<E>> match(Pattern<E> pattern) {
		return pattern.execute(this);
	}

	public boolean matchesExactly(Pattern<E> pattern) {
		List<ElementSequence<E>> result = pattern.execute(this);
		for (ElementSequence<E> r : result) {
			if(r.size() == size()) {
				return true;
			}
		}
		return false;
	}

	public boolean startsWith(Pattern<E> pattern) {
		List<ElementSequence<E>> result = pattern.execute(this);
		for (ElementSequence<E> r : result) {
			boolean starts = true;
			for (int j = 0; j < r.elements.size(); j++) {
				if (!r.get(j).equals(get(j))) {
					starts = false;
					break;
				}
			}
			if (starts) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return elements.toString();
	}

	@Override
	public boolean add(E e) {
		return elements.add(e);
	}

	@Override
	public void add(int index, E element) {
		elements.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return elements.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return elements.addAll(index, c);
	}

	@Override
	public void clear() {
		elements.clear();
	}

	@Override
	public boolean contains(Object o) {
		return elements.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	@Override
	public int indexOf(Object o) {
		return elements.indexOf(o);
	}

	@Override
	public Iterator<E> iterator() {
		return elements.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return elements.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return elements.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return elements.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return elements.remove(o);
	}

	@Override
	public E remove(int index) {
		return elements.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return elements.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return elements.retainAll(c);
	}

	@Override
	public E set(int index, E element) {
		return elements.set(index, element);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return elements.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return elements.toArray(a);
	}

}
