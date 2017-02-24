public interface List<E> extends Collection<E>{
    /**
      返回List中元素的个数
    */
    int size();

    /**
      返回true如果列表中没有元素
    */
    boolean isEmpty();

    /**
      返回true如果列表中包含指定的元素
    */
    boolean contains(Object o);

    /**
      返回一个Iterator类型的实例
    */
    Iterator<E> iterator();

    /**
      返回一个包含list中所有元素的对象数组
    */
    Object[] toArray();

    /**
      返回一个包含list中元素的数组
    */
    <T> T[] toArray(T[] a);

    /**
      在list末尾添加一个元素，如果成功就返回true,失败就返回false
    */
    boolean add(E e);

    /**
      在list中删除指定的元素
    */
    boolean remove(Object o);

    /**
      返回true如果list中包含指定c集合中的所有的元素,否则返回false
    */
    boolean containsAll(Collection<?> c);

    /**
      返回true如果将集合c中的所有元素都成功添加到list中，否则返回false;
    */
    boolean addAll(Collection<? extends E> c);

    /**
      返回true如果成功的将指定的c集合插入到list中指定的位置，否则返回false
    */
    boolean addAll(int index, Collection<? extends E> c);

    /**
      返回true如果成功的将所有list中在c集合被包含的元素删除，否则返回false
    */
    boolean removeAll(Collection<?> c);
 
    /**
      返回treu如果成功的将所有list中不被c集合包含的元素删除，否则返回false
    */
    boolean retainAll(Collection<?> c);
    
    /**
      将list中的元素经过处理后所得的结果代替list中原有的元素
    */
    default void replaceAll(UnaryOperator<E> operator){
    	Objects.requireNonNull(operator);
    	final ListIterator<E> li = this.ListIterator();
    	while(li.hasNext()){
    		li.set(Operator.apply(li.next()));
    	}
    }
  
    /**
       对list中的元素进行排序
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super E> c){
    	Object[] a = this.toArray();
    	Arrays.sort(a, (Comparator) c);
    	ListIterator<E> i = this.ListIterator();
    	for(Object e : a){
    		i.next();
    		i.set((E) e);
    	}
    }

    /**
       清空list
    */
    void clear();

    /**
       返回true如果list和指定的对象相等（有相同的元素和相同的顺序）
    */
    boolean equals(Objects o);

    /**
       返回list的散列代码值
    */
    int hashCode();

    /**
       返回list中指定位置的元素
    */
    E get(int index);

    /**
       将list中被指定位置的元素替换为指定的元素
    */
    E set(int index, E element);

    /**
       在list中指定的位置插入指定的元素
    */
    void add(int index, E element);
 
    /**
       删除list中指定位置的元素
    */
    E remove(int index);
 
    /**
      返回指定的元素在list中第一次出现的位置，如果list中不存在指定的元素则返回-1
    */
    int indexOf(Object o);

    /**
      返回指定的元素在list中最后一次出现的位置，如果list中不存在指定的元素则返回-1
    */
    int lastIndexOf(Object o);

    /**
      返回list的列表迭代器
    */
    ListIterator<E> listIterator();

    /**
      返回从list指定位置开始的列表迭代器
    */
    ListIterator<E> listIterator(int index);

    /**
      返回list的子列表
    */
    List<E> subList(int fromIndex, int toIndex);

    default Spliterator<E> spliterator(){
    	 return Spliterators.spliterator(this, Spliterator.ORDERED);
    }
}