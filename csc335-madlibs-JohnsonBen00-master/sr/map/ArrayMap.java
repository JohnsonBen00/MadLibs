package map;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArrayMap<K, V> extends AbstractMap<K, V>{
	
	private Object[] key;
	private Object[] value;
	
	public ArrayMap()
	{
		this.key = new Object[0];
		this.value = new Object[0];
	}
	
	@Override
	public V put(K key, V value) {
		Object val;
		for( int i = 0; i < this.key.length; i++) {
			if( this.key[i].toString().equals(key.toString())) {
				val = this.value[i];
				this.value[i] = (Object) value;
				return (V) val;
			}
		}
		Object[] newKey = new Object[this.key.length + 1];
		Object[] newVal = new Object[this.key.length + 1];
		for( int i = 0; i < this.key.length; i++) {
			newKey[i] = this.key[i];
		}
		newKey[this.key.length + 1] = key;
		for( int i = 0; i < this.value.length; i++) {
			newVal[i] = this.value[i];
		}
		newVal[this.value.length + 1] = value;
		this.key = newKey;
		this.value = newVal;
		return null;
	}

	@Override
	public int size() {
		return this.key.length;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> hash_Set = new HashSet<Entry<K, V>>();
		for( int i = 0; i < this.key.length; i++) {
			hash_Set.add(new AbstractMap.SimpleEntry(this.key[i], this.value[i]));
		}
		return hash_Set;
	}

	private class ArrayMapEntrySet extends AbstractSet<Entry<K,V>> {
		
		private Set<Entry<K, V>> set;
		
		public ArrayMapEntrySet() {
			this.set = entrySet();
		}
	
		@Override
		public int size() {
			return this.set.size();
			
		}
	
		@Override
		public boolean contains(Object o) {
			Iterator<Entry<K, V>> it = iterator();
			while(it.hasNext()) {
				if(o.equals(it.next())) {
					return true;
				}
			}
			return false;
			
		}
	
		@Override
		public Iterator<Entry<K,V>> iterator() {
			Iterator<Entry<K, V>> it = this.set.iterator();
			return it;
		}
		
	}
	
	private class ArrayMapEntrySetIterator<T> implements Iterator<T> {
		
		private Iterator<Entry<K, V>> it;
		private ArrayMapEntrySet set;
		
		public ArrayMapEntrySetIterator() {
			this.it = this.set.iterator();
		}
	
		@Override
		public boolean hasNext() {
			if( this.it.hasNext()) {
				return true;
			}
			return false;
		}
		
		@Override
		public T next() {
			if(hasNext()) {
				return (T) this.it.next();
			}
			return null;
		}
			
	}
	
}
