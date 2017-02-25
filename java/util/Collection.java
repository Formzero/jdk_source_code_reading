package java.util;

import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<E> extends Iterable<E>{

/**
	返回Collection中元素的个数
*/
	int size();

/**
    返回true如果Collection中没有元素 
*/
	boolean isEmpty();

/**
	返回true如果Collection中包含指定的元素
*/
	boolean contains(Object o);

/**
	返回一个迭代器
*/
	Iterator<E> iterator();

/**
	返回包含Collection中所有元素的对象数组
*/
	Object[] toArray();

/**
	返回数组
*/
	<T> T[] toArray(T[] a);

/**
	向Collection中添加元素，如何添加成功则返回true,否则返回false
*/
	boolean add(E e);

/**
	从Collection中删除指定的元素
*/
	boolean remove(Object o);

/**
	如果Collection中包含指定Collection中的全部元素则返回true,否则返回false
*/
	boolean containsAll(Collection<?> c);

/**
	如果成功将指定Collection中的元素全部添加到Collection中则返回true,否则返回false
*/
	boolean addAll(Collection<?> c);

/**
	如果成功将Collection中被包含在指定Collection中的元素删除则返回true,否则返回false
*/
	boolean removeAll(Collection<?> c);

/**
 	Removes all of the elements of this collection that satisfy the given
    predicate.  Errors or runtime exceptions thrown during iteration or by
    the predicate are relayed to the caller.
*/
	default boolean removeIf(Predicate<? super E> filter){
		Objects.requireNonNull(filter);
		boolean removed = false;
		final Iterator<E> each = iterator();
		while(each.hasNext()){
			if(filter.test(each.next())){
				each.remove();
				removed = true;
			}
		}
		return removed;
	}

/**
	如果成功删除Collection中不被包含在指定Collection的元素则返回true,否则返回false
*/
	boolean retainAll(Collection<?> c);

/**
	删除Collection中所有的元素
*/
	void clear();

/**
	判断Collection和指定的对象是否相等，相等则返回true,否则返回false
*/
	boolean equals(Objects o);

/**
	获取Collection的散列代码值
*/
	int hashCode();

/**
	返回一个Spliterator类型的对象 
*/
	@Override
	default Spliterator<E> spliterator(){
		return Spliterator.spliterator(this, 0);
	}

/**
	返回一个Stream类型的对象
*/
	default Stream<E> stream(){
		return StreamSupport.stream(spliterator(), fales);
	}

	default Stream<E> parallelStream(){
		return StreamSupport.stream(spliterator(), true);
	}
}