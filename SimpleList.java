package com.uptc.pgr3.serverWarShip.dataStruct.list;

import java.util.Comparator;
import java.util.Iterator;

/**
 * 
 * Crea una lista simplemente enlazada<br>
 * Fecha de creacion: 25/05/2019
 * 
 * @author Camilo Hurtado
 *
 * @param <T> Cualquier objeto
 */

public class SimpleList<T> implements Iterable<T> {

	private Node<T> head;
	private Comparator<T> comparator;
	private int size;

	/**
	 * Crea la una lista simplemente enlazada vacia y que agrega los items en
	 * desorden
	 */
	public SimpleList() {
		this.head = null;
		this.comparator = null;
		this.size = 0;
	}

	public SimpleList(T[] datas) {
		this.head = null;
		this.comparator = null;
		this.size = 0;
		this.add(datas);
	}

	/**
	 * Crea la una lista simplemente enlazada vacia y que agrega los items en un
	 * orden especifico
	 * 
	 * @param comparator especifica como se va a ordenar la lista
	 */
	public SimpleList(Comparator<T> comparator) {
		this.head = null;
		this.comparator = comparator;
		this.size = 0;
	}

	/**
	 * Añade un elemento a la lista, dependiendo si esta tiene ordenador o no
	 * 
	 * @param item El objeto que se va a agregar
	 */

	public void add(T item) {
		if (comparator == null) {
			addUnsort(item);
		} else {
			addSort(item);
		}
	}

	/**
	 * Añade un objeto al inicio de la lista
	 * 
	 * @param item El objeto que se va a añadir
	 */
	public void insert(T item) {
		this.head = new Node<>(item, this.head);
		size++;
	}

	/**
	 * Obtiene el tamaño de la lista
	 * 
	 * @return El tamaño de la lista
	 */
	public int size() {
		return size;
	}

	/**
	 * Busca un objeto en la lista y muestra si existe
	 * 
	 * @param item El objeto que se va a buscar
	 * @return true si el objeto existe en la lista
	 */
	public boolean contains(T item) {
		for (Node<T> i = this.head; i != null; i = i.next) {
			if (i.info == item) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Añade un objeto a la lista en un orden especifico
	 * 
	 * @param item El objeto que se va a añadir
	 */
	private void addSort(T item) {
		if (isEmpty()) {
			insert(item);
		} else if (comparator.compare(this.head.info, item) > 0) {
			insert(item);
		} else {
			Node<T> previous = this.head;
			for (Node<T> aux = this.head.next; aux != null; aux = aux.next, previous = previous.next) {
				if (comparator.compare(aux.info, item) > 0) {
					previous.next = new Node<>(item, aux);
					return;
				}
			}
			previous.next = new Node<>(item);
			size++;
		}
	}

	/**
	 * Añade un objeto al final de la lista
	 * 
	 * @param item El objeto que se va a añadir
	 */
	private void addUnsort(T item) {
		if (isEmpty()) {
			insert(item);
		} else {
			Node<T> aux = getLastNode(this.head);
			aux.next = new Node<>(item);
			size++;
		}
	}

	/**
	 * Verifica si la lista es nula
	 * 
	 * @return true si la lista es nula
	 */
	public boolean isEmpty() {
		return this.head == null;
	}

	public void add(SimpleList<T> otherList) {
		Node<T> aux = getLastNode(this.head);
		aux.next = otherList.head;
		size += otherList.size;
	}

	public void insert(SimpleList<T> otherList) {

		Node<T> aux = getLastNode(otherList.head);
		aux.next = this.head;
		this.head = otherList.head;
		size += otherList.size;
	}

	public T get(int index) {
		return getNode(index).info;
	}

	private Node<T> getNode(int index) {
		Node<T> aux = this.head;
		int count = 0;
		for (Node<T> n = this.head; count <= index; n = n.next, count++) {
			aux = n;
		}
		return aux;
	}

	public T getLastItem() {
		return getLastNode(this.head).info;
	}

	public T getFirstItem() {
		return this.head.info;
	}

	private Node<T> getLastNode(Node<T> head) {
		Node<T> aux = null;
		for (aux = head; aux.next != null; aux = aux.next)
			;
		return aux;
	}

	public boolean isExist(T reference) {
		Node<T> aux = this.head;
		while (aux.next != null) {
			if (aux.info == reference) {
				return true;
			}
			aux = aux.next;
		}
		return false;
	}
	
	


	public void add(T[] datas) {
		for (T t : datas) {
			add(t);
		}
	}

	public void removeAt(int index) {

		if (index == 0) {
			this.head = this.head.next;
		} else {
			getNode(index - 1).next = getNode(index + 1);
		}
		this.size--;
	}

	public void remove(T reference) {
		if (this.head.info.equals(reference)) {
			this.head = this.head.next;
			this.size--;
		} else {
			Node<T> previous = this.head;
			Node<T> actual = this.head.next;
			try {
				while (!actual.info.equals(reference)) {
					previous = actual;
					actual = actual.next;
				}
				previous.next = actual.next;
				this.size--;
			} catch (NullPointerException e) {
				Exception a = new Exception("Not Found");
				a.printStackTrace();
			}
		}
	}

	public int indexOf(T reference) {
		int index = 1;
		if (reference.equals(this.head.info)) {
			return 0;
		} else {
			Node<T> aux = this.head.next;
			while (!aux.info.equals(reference)) {
				index++;
				aux = aux.next;
			}
		}
		return index;
	}

	public void setAt(int index, T info) {
		getNode(index).info = info;
	}

	public void set(T reference, T newElement) {
		Node<T> aux = this.head;
		while (!aux.equals(reference) && aux != null) {
			aux = aux.next;
		}
		if (aux == null) {
			System.out.println("no encontrado");
		} else {
			aux.info = newElement;
		}
	}

	
	public void clear() {
		this.head=null;
	}
	
	public T get(T reference, Comparator<T> comparator) {
		for (T t : this) {
			if (comparator.compare(t, reference) == 0) {
				return t;
			}
		}
		return null;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> aux = head;
			@Override
			public boolean hasNext() {
				return aux != null;
			}

			@Override
			public T next() {
				T data = aux.info;
				aux = aux.next;
				return data;
			}
		};
	}

}
