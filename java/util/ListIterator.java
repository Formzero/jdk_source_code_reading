package util;

public interface ListIterator<E> extends Iterator<E>{

/**
	如果列表迭代器中有下一个元素则返回true，否则返回false
*/
	boolean hasNext();

/**
	返回列表迭代器中的下一个元素，如果没有则抛出NoSuchElementException异常
*/
	E next();
 
 /**
	如果列表迭代器中有前一个元素返回true，否则返回false
 */
    boolean hasPrevious();

/**
	返回列表迭代器中的前一个元素
*/
    E previous();

/**
	返回列表迭代器中的下一个元素的索引，如果列表迭代器位于列表的最后一个位置，
	则返回列表中元素的个数
*/
    int nextIndex();

/**
	返回列表迭代器中的上一个元素的索引，如果列表迭代器位于列表的第一个位置，
	则返回-1
*/
    int previousIndex();

/**
	删除列表迭代器中的最后一个元素
*/
    void remove();

/**
	将列表迭代器中的最后一个元素替换为指定的元素
*/
    void set(E e);

/**
	向列表迭代器中添加指定的元素
*/
    void add(E e);
}