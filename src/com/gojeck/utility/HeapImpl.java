package com.gojeck.utility;

public class HeapImpl {
	  protected int[] data;
	  public HeapImpl(int size) {
		 data  = new int[2*size+1];
	    this.data[0] = 0;
	  }

	  public HeapImpl insert(int value) {
	    ++this.data[0];
	    this.data[this.data[0]] = value;
	    this.bubbleUp(this.data[0]);
	    return this;
	  }

	  /*
	  *
	  * This method is used to create initialise the minHeap Tree
	  * */

	  protected void bubbleUp(int index) {
	    int parentIndex =  index / 2;
	    if (index == 1 || this.data[parentIndex] < this.data[index]){
	    	return;
	    }
	    int parentValue = this.data[parentIndex];
	    this.data[parentIndex] = this.data[index];
	    this.data[index]       = parentValue;
	    bubbleUp(parentIndex);
	  }

	  public int remove() {
	    int minValue = this.data[1];
	    int lastValue = this.data[this.data[0]--];
	    this.data[1] = lastValue;
	    this.sinkDown(1);
	    return minValue;
	  }

	  protected boolean isLeaf(int index) {
	    return (index * 2) > this.data[0];
	  }

	  protected void sinkDown(int index) {
	    if (this.isLeaf(index)){
	    	return;
	    }
	    int leftChildIndex  = index * 2;
	    int rightChildIndex = (index * 2) + 1;
	    int minChildIndex;
	    if (rightChildIndex > this.length()) {
	      minChildIndex = leftChildIndex;
	    }
	    else {

	      minChildIndex = (this.data[leftChildIndex] <
	                       this.data[rightChildIndex]) ? leftChildIndex : rightChildIndex;
	    }
	    if (this.data[index] < this.data[minChildIndex]) return;

	    int childValue = this.data[minChildIndex];
	    this.data[minChildIndex] = this.data[index];
	    this.data[index]         = childValue;

	    sinkDown(minChildIndex);
	  }

	  public int minValue() {
	    return this.data[1];
	  }

	  public int length() {
	    return this.data[0];
	  }

}
