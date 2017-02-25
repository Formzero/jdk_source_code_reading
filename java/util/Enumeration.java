package java.util;

public interface Enumeration<E>{
/**
	如果枚举中有更多的元素则返回true，否则返回false
*/
	boolean hasMoreElements();

/**
	返回枚举中的下一个元素
*/
	E nextElement();
}