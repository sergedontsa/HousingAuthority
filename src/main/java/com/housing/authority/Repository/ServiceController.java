package com.housing.authority.Repository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface ServiceController<E>
{
    public CollectionModel<EntityModel<E>> readAll();
    public EntityModel<E> readOne(String id);
    public ResponseEntity<?> create(@RequestBody E object);
    Object update(String id, E e);
    void delete(String id);
}
