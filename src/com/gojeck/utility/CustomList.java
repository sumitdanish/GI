
package com.gojeck.utility;




public class CustomList<V> {

	private V value;
	private CustomList<V> next;
	public CustomList() {
		
	}
	public CustomList(V value) {
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public CustomList<V> getNext() {
		return next;
	}

	public void setNext(CustomList<V> next) {
		this.next = next;
	}

	
}
