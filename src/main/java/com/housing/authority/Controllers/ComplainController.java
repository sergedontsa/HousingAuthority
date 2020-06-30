package com.housing.authority.Controllers;

import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Tuples.Complain;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public class ComplainController implements ServiceController<Complain> {


    @Override
    public CollectionModel<EntityModel<Complain>> readAll() {
        return null;
    }

    @Override
    public EntityModel<Complain> readOne(String id) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(Complain object) {
        return null;
    }

    @Override
    public Object update(String id, Complain complain) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
