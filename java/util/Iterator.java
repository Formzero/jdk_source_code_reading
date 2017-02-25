package java.util;

import java.util.function.Consumer;

public interface Iterator<E>{

/**
	如果迭代器还有下一个元素则返回true，否则返回false
*/
	boolean hasNext();

/**
	返回迭代器中的下一个元素，如果没有元素则抛出NoSuchElementException异常
*/
	E next();

/**
	Removes from the underlying collection the last element returned
    by this iterator (optional operation).
*/
	default void remove(){
		throw new UnsupportedOperationException("remove");
	}

/**
	Performs the given action for each remaining element until all elements
    have been processed or the action throws an exception.
*/
	default void forEachRemaining(Consumer<? super E> action){
		Objects.requireNonNull(action);
		while(hasNext())
			action.accept(next());
	}
}