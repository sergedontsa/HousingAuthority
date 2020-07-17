package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.Employee.EmployeeAddressController;

import com.housing.authority.Tuples.Employee.EmployeeAddress;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EmployeeAddressModelAssembler implements RepresentationModelAssembler<EmployeeAddress, EntityModel<EmployeeAddress>> {
    @Override
    public  EntityModel<EmployeeAddress> toModel(EmployeeAddress entity) {
        return new EntityModel<>(entity, linkTo(methodOn(EmployeeAddressController.class).readOne(entity.getEmployeeId())).withSelfRel(),
                linkTo(methodOn(EmployeeAddressController.class).readAll()).withRel("Address"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<EmployeeAddress>> toCollectionModel(@NotNull Iterable<? extends EmployeeAddress> entities) {
        return null;
    }
}
