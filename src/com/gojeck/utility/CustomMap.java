package com.gojeck.utility;

import cm.gojeck.entity.Car;
import cm.gojeck.entity.Slot;

public class CustomMap<K, V> {

	private int size;
	private Entry<K, V>[] mapEntry;
	private int resevedSize;
	private int freeSize;
	@SuppressWarnings("unchecked")
	public CustomMap(int size) {
		this.size = size;
		resevedSize = 0;
		freeSize = size;
		mapEntry = new Entry[size];
	}

	public void put(K key, V value) {
		isKeyNull(key);
		int bucketIndex = getMapBucketIndex(key);
		Entry<K, V> entry = mapEntry[bucketIndex];
		if (null != entry) {
			while (entry != null) {
				if (key.equals(entry.getKey())) {
					entry.setValue(value);
					break;
				} else if (entry.getNext() == null) {
					entry.setNext(new Entry<K, V>(key, value));
					break;
				}
				entry = entry.getNext();
			}
		} else {
			resevedSize++;
			mapEntry[bucketIndex] = new Entry<K, V>(key, value);
		}
		// freeSize--;
	}

	public V get(K key) {
		isKeyNull(key);
		Entry<K, V> entry = mapEntry[getMapBucketIndex(key)];
		while (entry != null && !key.equals(entry.getKey()))
			entry = entry.getNext();
		return entry != null ? entry.getValue() : null;
	}

	public boolean contains(K key) {
		int hash = getMapBucketIndex(key);
		if (mapEntry[hash] == null) {
			return false;
		} else {
			Entry<K, V> temp = mapEntry[hash];
			while (temp != null) {
				if (temp.getKey().equals(key))
					return true;
				temp = temp.getNext();
			}
			return false;
		}
	}

	public String display() {
		StringBuilder sb = new StringBuilder("Slot No.		Registration.No				Colour").append("\n");
		for (int i = 0; i < size; i++) {
			if (mapEntry[i] != null) {
				Entry<K, V> entry = mapEntry[i];
				while (entry != null) {
					Slot s = (Slot) entry.getValue();
					Car c = (Car) s.getVehicle();
					/*System.out.print("{" + s.getSlotNo() + "," + c.getPlateNo() + " ,"
							+ c.getColor() + "}" + " ");*/
					sb = sb.append(s.getSlotNo()+"				"+ c.getPlateNo()+"				"+c.getColor()).append("\n");
					entry = entry.getNext();
				}
			}
		}
		return sb.toString();
	}

	public V remove(K deleteKey) {

		int hash = getMapBucketIndex(deleteKey);
		V value = null;
		if (mapEntry[hash] == null) {
			return null;
		} else {
			Entry<K, V> previous = null;
			Entry<K, V> current = mapEntry[hash];
			while (current != null) {
				if (current.getKey().equals(deleteKey)) {
					value = get(deleteKey);
					if (previous == null) {
						mapEntry[hash] = mapEntry[hash].getNext();
					} else {
						previous.setNext(current.getNext());
					}
					resevedSize--;
					return value;
				}
				previous = current;
				current = current.getNext();
			}
			resevedSize--;
			return null;
		}
	}

	private int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	private int indexFor(int h, int length) {
		return h & (length - 1);
	}

	private int getMapBucketIndex(K key) {
		int hash = hash(key.hashCode());
		int bucketIndex = indexFor(hash, size);
		return bucketIndex;
	}

	private void isKeyNull(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key may not be null");
		}
	}

	public int size() {
		return size;
	}

	public int getResevedSize() {
		return resevedSize;
	}

	public int getFreeSize() {
		return (size - resevedSize);
	}

}

class Entry<K, V> {
	private final K key;
	private V value;
	private Entry<K, V> next;

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Entry<K, V> getNext() {
		return next;
	}

	public void setNext(Entry<K, V> next) {
		this.next = next;
	}

	public K getKey() {
		return key;
	}

}
