package com.android.common.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class CircularQueue<T> implements Queue<T> {
	//==========================================================================
	// Constants
	//==========================================================================

	//==========================================================================
	// Fields
	//==========================================================================
	private Object[] mArray;
	
	private int mCapacity;

	private int mSize;
	
	private int mFront;
	
	private int mRear;
	
	//==========================================================================
	// Constructors
	//==========================================================================
	public CircularQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
		mArray = new Object[capacity];
		mCapacity = capacity;
		mSize = 0;
		mFront = 0;
		mRear = 0;
	}

	//==========================================================================
	// Getters
	//==========================================================================

	//==========================================================================
	// Setters
	//==========================================================================

	//==========================================================================
	// Methods
	//==========================================================================
    private int nextPosition(int position) {
    	return  ++position >= mCapacity ? position - mCapacity : position;
    }
    
    private int previousPosition(int position) {
    	return  --position < 0 ? position + mCapacity : position;
    }

	@Override
    public int size() {
    	return mSize;
    }

	@Override
    @SuppressWarnings("unchecked")
    public T poll(){
    	if (mSize > 0) {
    		Object obj = mArray[mFront];
        	mArray[mFront] = null;
        	mFront = nextPosition(mFront);
        	mSize--;
        	return (T) obj;
    	} else {
    		return null;
    	}
    }

	@Override
    public boolean offer(T obj) {
    	if (null == obj) {
    		return false;
    	}
        mArray[mRear] = obj;
    	if (mSize == mCapacity) {
            // The header element is dropped
            mFront = nextPosition(mFront);
            mRear = mFront;
        } else {
        	mSize++;
        	mRear = nextPosition(mRear);
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
	public T get(int position) {
    	if (position < 0 || position >= mSize) {
    		throw new ArrayIndexOutOfBoundsException();
    	}
    	int index = mFront + position;
    	index = index >= mCapacity ? index - mCapacity : index;
    	return (T) mArray[index];
    }

	@Override
    public boolean remove(Object obj) {
    	if (null == obj || mSize == 0) {
    		return false;
    	}
    	boolean res = false;
        Object[] a = mArray;
        int s = mCapacity;
    	int i = mFront;
    	for (int j = 0; j < mSize; j++) {
        	if (obj.equals(a[i])) {
        		if (mFront < mRear) {
                    System.arraycopy(a, i + 1, a, i, mRear - 1 - i);
        		} else if (i >= mFront) {
        			int l = --s - i;
        			if (l > 0) {
        				System.arraycopy(a, i + 1, a, i, l);
        			}
        			if (mRear > 0) {
        				mArray[s] = mArray[0];
            			if (mRear > 1) {
                            System.arraycopy(a, 1, a, 0, mRear - 1);
            			}
        			}
        		} else {
        			int l = mRear - 1 - i;
        			if (l > 0) {
                        System.arraycopy(a, i + 1, a, i, l);
        			}
        		}
        		mRear = previousPosition(mRear);
        		mArray[mRear] = null;
        		mSize--;
        		res = true;
        	}
        	i = nextPosition(i);
    	}
    	return res;
    }

	@Override
    public void clear() {
    	for (; mSize > 0; mSize--) {
    		mArray[mFront] = null;
    		mFront = nextPosition(mFront);
    	}
    }

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		// TODO Not supported yet
		return false;
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return mSize == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Not supported yet
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		// TODO Not supported yet
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		// TODO Not supported yet
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Not supported yet
		return null;
	}

	@Override
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] array) {
		// TODO Not supported yet
		return null;
	}

	@Override
	public boolean add(T arg0) {
		// TODO Not supported yet
		return false;
	}

	@Override
	public T remove() {
		// TODO Not supported yet
		return null;
	}

	@Override
	public T peek() {
		// TODO Not supported yet
		return null;
	}

	@Override
	public T element() {
		// TODO Not supported yet
		return null;
	}
    
	//==========================================================================
	// Inner/Nested Classes
	//==========================================================================
}
