package com.example.DigitalBookingBEG6.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    public T save(T element);
    public List<T> getAll();
    public boolean delete(Integer id);
    public T modify(Integer id, T element);
    public Optional<T> getById(Integer id);
}
