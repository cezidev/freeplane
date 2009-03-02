/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2009 Dimitry Polivaev
 *
 *  This file author is Dimitry Polivaev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.core.extension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SmallExtensionMap implements Map<Class<? extends IExtension>, IExtension>{


	private static final int INITIAL_CAPACITY = 5;
	private List<IExtension> collection;
	
	private List<IExtension> createCollection(){
		if(collection == null){
			collection = new ArrayList<IExtension>(INITIAL_CAPACITY);
		}
		return collection;
	}

	private int find(final Class<? extends IExtension> clazz) {
		if(collection == null){
			return -1;
		}
		for (int i = 0; i < collection.size(); i++) {
			if (clazz.isAssignableFrom(collection.get(i).getClass())) {
				return i;
			}
		}
		return -1;
	}


	private void removeEmptyCollection() {
	    if(collection.size() == 0){
	    	collection = null;
	    }
	    
    }

	
	public void clear() {
	    collection = null;
	    
    }

	public boolean containsKey(Object key) {
		if(collection == null){
			return false;
		}
		if(! (key instanceof Class)){
			return false;
		}
	    for (int i = 0; i < collection.size(); i++) {
        	final Object extension = collection.get(i);
        	if (((Class) key).isAssignableFrom(extension.getClass())) {
        		return true;
        	}
        }
        return false;
    }

	public boolean containsValue(Object value) {
	    if(collection == null){
        	return false;
        }
		if(! (value instanceof IExtension)){
			return false;
		}
        for (int i = 0; i < collection.size(); i++) {
        	if (((IExtension)value).equals(collection.get(i))) {
        		return true;
        	}
        }
        return false;
    }

	public Set<java.util.Map.Entry<Class<? extends IExtension>, IExtension>> entrySet() {
	    throw new NoSuchMethodError();
    }

	public IExtension get(Object key) {
		if(! (key instanceof Class)){
			return null;
		}
		final int index = find(((Class)key));
        if (index >= 0) {
        	return collection.get(index);
        }
        return null;
    }

	public boolean isEmpty() {
	    return collection == null;
    }

	public Set<Class<? extends IExtension>> keySet() {
		throw new NoSuchMethodError();
    }

	public IExtension put(Class<? extends IExtension> key, IExtension value) {
	    final int index = find(key);
        if (index >= 0) {
        	final IExtension oldValue = collection.get(index);
        	collection.set(index, value);
			return oldValue;
        }
        else {
			if(! key.isAssignableFrom(value.getClass())){
	    		throw new ClassCastException();
	    	}
        	createCollection().add(value);
        	return null;
        }	    
    }

	public void putAll(Map<? extends Class<? extends IExtension>, ? extends IExtension> source) {
	    for(Entry<? extends Class<? extends IExtension>, ? extends IExtension> entry : source.entrySet()){
			final Class<? extends IExtension> key = entry.getKey();
	    	final IExtension value = entry.getValue();
	    	put(key, value);
	    }
	    
    }

	public IExtension remove(Object key) {
		if(collection == null || ! (key instanceof Class)){
			return null;
		}
		final int index = find((Class)key);
		if(index == -1){
			return null;
		}
		final IExtension remove = collection.remove(index);
		removeEmptyCollection();
		return remove;
		
    }

	public int size() {
	    return collection ==  null ? 0 : collection.size();
    }

	public Collection<IExtension> values() {
		Collection<IExtension> emptyList = Collections.emptyList();
	    return collection == null ? emptyList : collection;
    }
	
}

