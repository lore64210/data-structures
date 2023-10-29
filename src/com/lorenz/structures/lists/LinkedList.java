package com.lorenz.structures.lists;

import com.lorenz.nodes.SimpleNode;

public class LinkedList<T> extends List<T> {

  private SimpleNode<T> first;
  private SimpleNode<T> last;
  protected Integer size;
  private final Iterator<T, LinkedList<T>, SimpleNode<T>> iterator;

  public LinkedList() {
    size = 0;
    first = null;
    last = null;
    iterator = new Iterator<>(this);
  }

  public LinkedList(T value) {
    size = 1;
    first = new SimpleNode<>(value);
    last = first;
    iterator = new Iterator<>(this);
  }

  public LinkedList(LinkedList<T> list) {
    size = list.getSize();
    first = list.getNode(0);
    last = list.getNode(size);
    iterator = new Iterator<>(this);
  }

  public Integer getPositionOf(T value) {
    this.iterator.setCurrentNode(this.first);
    int position = -1;
    int currentPosition = 0;
    do {
      SimpleNode<T> current = (SimpleNode<T>) iterator.next();
      if (current.getValue().equals(value)) {
        position = currentPosition;
        break;
      }
      currentPosition++;
    } while(this.iterator.hasNext());
    this.iterator.setCurrentNode(this.first);
    return position;
  }

  public void delete(T value) {
    this.iterator.setCurrentNode(this.first);
    int currentPosition = 0;
    do {
      SimpleNode<T> current = (SimpleNode<T>)iterator.next();
      if (current.getValue().equals(value)) {
        this.delete(currentPosition);
        break;
      }
      currentPosition++;
    } while(this.iterator.hasNext());
    this.iterator.setCurrentNode(this.first);
  }

  public void delete(int position) {
    SimpleNode<T> current = getNode(position);
    if (position == 0) {
      this.first = current.getRightNode();
      current.setRightNode(null);
    } else if (position == this.size - 1) {
      SimpleNode<T> leftNode = getNode(position - 1);
      leftNode.setRightNode(null);
      this.last = leftNode;
    } else {
      SimpleNode<T> leftNode = getNode(position - 1);
      leftNode.setRightNode(current.getRightNode());
    }
    this.size--;
  }

  protected SimpleNode<T> getNode(Integer position) {
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
    SimpleNode<T> newLast = getNode(size - 1);
    newLast.setRightNode(null);
    last = newLast;
    size--;
    return value;
  }

  public boolean hasNext() {
    return this.iterator.hasNext();
  }

  public T next() {
    return this.iterator.next().getValue();
  }


}
