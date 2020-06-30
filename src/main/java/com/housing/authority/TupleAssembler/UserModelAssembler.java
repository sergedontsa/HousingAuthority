package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.UsersController;
import com.housing.authority.Tuples.Users;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;



import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<Users, EntityModel<Users>> {
    @Override
    public EntityModel<Users> toModel(Users entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(UsersController.class).readOne(entity.getUserId())).withSelfRel(),
                linkTo(methodOn(UsersController.class).readAll()).withRel("users"));
    }

    @Override
    public CollectionModel<EntityModel<Users>> toCollectionModel(Iterable<? extends Users> entities) {
        return null;
    }


}
