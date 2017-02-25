package java.util;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.funcition.UnaryOperator;

public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{

    private static final long serialVersionUID = 8683452581122892189L;

/**
	默认的初始化容量设置为10
*/
    private static final int DEFAULT_CAPACITY = 10;

/**
	用空实例共享空数组实例
*/
    private static final Object[] EMPTY_ELEMENTDATA = {};

/**
	用默认大小的而空实例共享数组实例
*/
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
/**
	The array buffer into which the elements of the ArrayList are stored.
    The capacity of the ArrayList is the length of this array buffer. Any
    empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
    will be expanded to DEFAULT_CAPACITY when the first element is added.
*/
    transient Object[] elementData;

/**
	数组列表中包含的元素的个数
*/
    private int size;

/**
	构造一个指定初始化容量的空列表
*/
    public ArrayList(int initialCapacity){
    	if(initialCapacity > 0){
    		this.elementData = new Object[initialCapacity];
    	}
    	else if(initialCapacity == 0){
    		this.elementData = EMPTY_ELEMENTDATA;
    	}
    	else{
    		throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
    	}
    }

/**
	构造一个初始化容量为10的空列表
*/
    public ArrayList(){
    	this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

/**
	用指定的集合构造一个列表
*/
    public ArrayList(Collection<? extends E> c){
    	elementData = c.toArray();
    	if((size = elementData.length) ! = 0){
    		//c.toArray might(incorrectly) not return Object[] 
    		if(elementData.getClass() != Object[].class)
    			elementData = Arrays.copyOf(elementData, size, Object[].class);
    	}
    	else{
    		//replace with empty array.
    		this.elementData = EMPTY_ELEMENTDATA;
    	}
    }

/**
	Trims the capacity of this ArrayList instance to be the
    list's current size.  An application can use this operation to minimize
    the storage of an ArrayList instance.
*/
    public void trimToSize(){
    	modCount++;
    	if(size < elementData.length){
    		elementData = (size = 0)
    		? EMPTY_ELEMENTDATA
    		: Arrays.copyOf(elementData, size);
    	}
    }

    public void ensureCapacity(int minCapacity){
    	int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
    	//any size if not default element table
    	? 0
    	//large than default for default empty table. It's already
    	//supposed to be at default size.
    	: DEFAULT_CAPACITY;
    	if(minCapacity > minExpand){
    		ensureExplicitCapacity(minCapacity);
    	}
    }

    private void ensureCapacityInternal(int minCapacity){
    	if(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
    		minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    	}
    	ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity){
    	modCount++;
    	//overflow-conscious code
    	if(minCapacity - elementData.length > 0)
    		grow(minCapacity);
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity){
    	//overflow-conscious code
    	int oldCapacity = elementData.length;
    	int newCapacity = oldCapacity + (oldCapacity >> 1);
    	if(newCapacity - minCapacity < 0)
    		newCapacity = minCapacity;
    	if(newCapacity - MAX_ARRAY_SIZE > 0)
    		newCapacity = hugeCapacity(minCapacity);
    	//minCapacity is usually close to size, so this is a win
    	elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity){
    	if(minCapacity < 0)  //overflow
    	   throw new OutOfMemoryError();
    	return (minCapacity > MAX_ARRAY_SIZE) ?
    	   Integer.MAX_VALUE :
    	   MAX_ARRAY_SIZE;
    }

    public int size(){
    	return size;
    }

    public boolean isEmpty(){
    	return size == 0;
    }

    public boolean contains(Object o){
    	return indexOf(o) >= 0;
    }

    public int indexOf(Object o){
    	if(o == null){
    		for(int i = 0; i < size; i++)
    			if(elementData[i] == null)
    				return i;
        }
        else{
        	for(int i = 0; i < size; i++)
        		if(o.equals(elementData[i]))
        			return i;
        }
        return -1
    }

    public int lastIndexOf(Object o){
    	if(o == null){
    		for(int i = size - 1; i >= 0; i--)
    			if(elementData[i] == null)
    				return i;
    	}
    	else{
    		for(int i = size -1; i >= 0; i--)
    			if(o.equals(elementData[i]))
    				return i;
    	}
    	return -1;
    }

    public Object clone(){
    	try{
    		ArrayList<?> v = (ArrayList<?>) super.clone();
    		v.elementData = Arrays.copyOf(elementData, size);
    		v.modCount = 0;
    		return v;
    	} catch(CloneNotSupportedException e){
    		//this shouldn't happen, since we are Cloneable
    		throw new InternalError(e);
    	}
    }

    public Object[] toArray(){
    	return Arrays.copyOf(elementData, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a){
    	if(a.length < size)
    		//Make a new array of a's runtime type, but my contents
    		return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    	System.arraycopy(elementData, 0, a, 0, size);
    	if(a.length > size)
    		a[size] = null;
    	return a;

    	@SuppressWarnings("unchecked")
    	E elementData(int index){
    		return (E) elementData[index];
    	}
    }

    public E get(int index){
    	rangeCheck(index);
    	return elementData(index);
    }

    public E set(int index, E element){
    	rangeCheck(index);
    	E oldValue = elementData(index);
    	elementData[index] = element;
    	return oldValue;
    }

    public boolean add(E e){
    	ensureCapacityInternal(size + 1);
    	elementData[size++] = e;
    	return true;
    }

    public void add(int index, E element){
    	rangeCheckForAdd(index);
    	ensureCapacityInternal(size + 1);
    	System.arraycopy(elementData, index, elementData, index + 1, size - index);
    	elementData[index] = element;
    	size++;
    }

    public E remove(int index){
    	rangeCheck(index);
    	modCount++;
    	E oldValue = elementData(index);
    	int numMoved = size - index -1;
    	if(numMoved > 0){
    		System.arraycopy(elementData, index + 1, elementData, index, numMoved);
    		elementData[--size] = null;  //clear to let GC do its work
    		return oldValue;
    	}
    }

    /**
		未完待续...
    */
}