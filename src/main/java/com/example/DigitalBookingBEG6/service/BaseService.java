package com.example.DigitalBookingBEG6.service;

import java.util.List;

public interface BaseService<T> {
    public T save(T element);
    public List<T> getAll();
    public boolean delete(Integer id);
    public boolean exist(Integer id);
    public T modify(T element);
    public T getById(Integer id);
}
