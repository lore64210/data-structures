package com.lorenz.structures.lists;

public interface List<T> {

  boolean isEmpty();

  Integer getSize();

  void push(T value);

  T pop();

}
