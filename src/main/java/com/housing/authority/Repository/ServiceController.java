package com.housing.authority.Repository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface ServiceController<E>
{
    /**
     * This method return the collection of all entity
     * @return the collection
     */
    public CollectionModel<EntityModel<E>> readAll();

    /**
     * Return the entity with id if found in the  server
     * @param id the id of the entity
     * @return the entity
     */
    public EntityModel<E> readOne(String id);
    /**
     * In case the entity is referred with the id with type integer
     * Return the entity with id if found in the  server
     * @param id the id of the entity
     * @return the entity
     */
    public EntityModel<E> readOne(int id);

    /**
     * Create the new entity in server
     * @param object the entity
     * @return the create entity
     */
    public ResponseEntity<?> create(@RequestBody E object);

    /**
     * Update the entity in the server
     * @param id the id of the entity
     * @param e the updated data
     * @return the updated one
     */
    Object update(String id, E e);

    /**
     * Delete the value in the database
     * @param id the id of the entity to updated
     */
    void delete(String id);

}
