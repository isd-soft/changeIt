package com.internship.changeit.model;

import java.util.List;

public interface Dao<T> {

    List<T> getAll();

    void save(T t);

    void delete(T t);

}
