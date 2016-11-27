package com.gojeck.utility;

import cm.gojeck.entity.Slot;

public class CustomListUtil<V> {
	public CustomList<V> addEntry(V value, CustomList<V> node) {
		CustomList<V> customList = new CustomList<V>();
		customList.setValue(value);
		if (node == null) {
			node = customList;
			return node;
		}
		node.setNext(addEntry(value, node.getNext()));
		return node;
	}

	
	
	@SuppressWarnings("unchecked")
	public CustomList<V> deleteEntry(V v,CustomList<V> emptySlotList){
		Slot deleteSlot = (Slot)v;
		CustomList<V> temp = emptySlotList;
		//System.out.println(getSize(emptySlotList));
		Slot filledSlot = (Slot)temp.getValue();
		if(filledSlot.getSlotNo() == deleteSlot.getSlotNo() && temp != null &&  temp.getNext() != null){
			emptySlotList = emptySlotList.getNext();
			filledSlot = new Slot(deleteSlot.getSlotNo(),null,false,false);
		}else if(filledSlot.getSlotNo() == deleteSlot.getSlotNo() && temp.getNext() == null){
			emptySlotList = null;
			filledSlot = new Slot(deleteSlot.getSlotNo(),null,false,false);
		}else{
			while(temp.getNext() != null){
				//CustomList<V> v1 = temp.getNext();
				filledSlot = (Slot)temp.getNext().getValue();
				if(filledSlot.getSlotNo() == deleteSlot.getSlotNo()){
					@SuppressWarnings("unused")
					CustomList<V> v1 = temp.getNext();
					temp.setNext(temp.getNext().getNext());
					v1 = null;
					filledSlot = new Slot(deleteSlot.getSlotNo(),null,false,false);
					break;
				}
				temp = temp.getNext();
			}
		}
		return emptySlotList;
	}

	/*public String display(CustomList<V> list){
		if(list == null){
			return "";
		}
		Slot s = (Slot)list.getValue();
		Car c = (Car)s.getVehicle();
		System.out.println(c.getColor().getColorName());
		return display(list.getNext());
	}*/
	
	public int getSize(CustomList<V> list){
		if(list == null){
			return 0;
		}
		//System.out.println(list.getValue().getClass().getName());
		//System.out.println(((Slot)list.getValue()).getSlotNo());
		return 1 + getSize(list.getNext());
	}
}
