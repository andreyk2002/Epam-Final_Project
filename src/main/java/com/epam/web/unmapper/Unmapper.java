package com.epam.web.unmapper;

import com.epam.web.entity.Identifiable;

import java.util.Map;

public interface Unmapper<T extends Identifiable> {

    Map<String, Object> unmap(T item);
}
