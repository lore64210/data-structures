package com.lorenz.structures.lists;

import com.lorenz.nodes.SimpleNode;

import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {

  private SimpleNode<T> first;
  private SimpleNode<T> last;
  private Integer size;
  private Iterator iterator;

  public LinkedList() {
    size = 0;
    first = null;
    last = null;
    iterator = new Iterator(this);
  }

  public LinkedList(T value) {
    size = 1;
    first = new SimpleNode<>(value);
    last = first;
    iterator = new Iterator(this);
  }

  public LinkedList(LinkedList<T> list) {
    size = list.getSize();
    first = list.getNode(0);
    last = list.getNode(size);
    iterator = new Iterator(this);
  }

  private SimpleNode<T> getNode(Integer position) {
    if (isEmpty() || position < 0) {
      throw new IllegalArgumentException("La posicion no existe");
    }
    try {
      SimpleNode<T> currentNode = first;
      for (int i = 0; i < position; i++) {
        currentNode = currentNode.getRightNode();
      }
      return currentNode;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("La posicion no existe");
    }
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Integer getSize() {
    return size;
  }

  public T getFirst() {
    return first.getValue();
  }

  public T getLast() {
    return last.getValue();
  }

  public void add(T value) {
    if (first == null) {
      first = last = new SimpleNode<>(value);
      iterator.setCurrentNode(first);
    } else {
      last.setRightNode(new SimpleNode<>(value));
      last = last.getRightNode();
    }
    size++;
  }

  public void add(T value, Integer position) {
    SimpleNode<T> nodeToAdd = new SimpleNode<>(value);
    if (isEmpty()) {
      if (position == 0) {
        push(value);
      } else {
        throw new IllegalArgumentException("Posicion incorrecta");
      }
    } else if (position.equals(size + 1)) {
      last.setRightNode(nodeToAdd);
      last = nodeToAdd;
    } else if (position > size + 1) {
      throw new IllegalArgumentException("Posicion incorrecta");
    } else if (position == 0) {
      nodeToAdd.setRightNode(getNode(0));
      first = nodeToAdd;
    } else {
      SimpleNode<T> node = getNode(position - 1);
      nodeToAdd.setRightNode(node.getRightNode());
      node.setRightNode(nodeToAdd);
    }
    size++;
  }

  public T get(Integer position) {
    return getNode(position).getValue();
  }

  @Override
  public void push(T value) {
    if (isEmpty()) {
      first = new SimpleNode<>(value);
      last = first;
    } else {
      last.setRightNode(new SimpleNode<>(value));
      last = last.getRightNode();
    }
    size++;
  }

  @Override
  public T pop() {
    if (isEmpty()) {
      throw new IllegalArgumentException("La lista esta vacia");
    }
    T value = last.getValue();
    last = getNode(size - 1);
    size--;
    return value;
  }

  public boolean hasNext() {
    return iterator.hasNext();
  }

  public T next() {
    return iterator.next().getValue();
  }

  private class Iterator {
    private SimpleNode<T> currentNode;

    public Iterator(LinkedList<T> linkedList) {
      currentNode = linkedList.first;
    }

    public void setCurrentNode(SimpleNode<T> currentNode) {
      this.currentNode = currentNode;
    }

    public boolean hasNext() {
      return currentNode != null;
    }

    public SimpleNode<T> next() {
      if (!hasNext()) throw new NoSuchElementException("La lista no contiene mas elementos");
      SimpleNode<T> node = currentNode;
      currentNode = currentNode.getRightNode();
      return node;
    }
  }

}
