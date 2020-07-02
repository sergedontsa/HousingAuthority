package com.housing.authority.Controllers;

import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Repository.SubscriberRepository;
import com.housing.authority.TupleAssembler.SubscriberModelAssembler;
import com.housing.authority.Tuples.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public class SubscriberController implements ServiceController<Subscriber> {

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private SubscriberModelAssembler subscriberModelAssembler;
    @Override
    public CollectionModel<EntityModel<Subscriber>> readAll() {
        return null;
    }

    @Override
    public EntityModel<Subscriber> readOne(String id) {
        return null;
    }

    @Override
    public EntityModel<Subscriber> readOne(int id) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(Subscriber object) {
        return null;
    }

    @Override
    public Object update(String id, Subscriber subscriber) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
