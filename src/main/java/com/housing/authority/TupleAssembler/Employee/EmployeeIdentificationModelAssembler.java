package com.housing.authority.TupleAssembler.Employee;

import com.housing.authority.Controllers.Employee.EmployeeIdentificationController;
import com.housing.authority.Tuples.Employee.EmployeeIdentification;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;
@Component
public class EmployeeIdentificationModelAssembler implements RepresentationModelAssembler<EmployeeIdentification, EntityModel<EmployeeIdentification>> {
    @Override
    public @NotNull EntityModel<EmployeeIdentification> toModel(@NotNull EmployeeIdentification entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(EmployeeIdentificationController.class).readOne(entity.getEmployeeId())).withSelfRel(),
                linkTo(methodOn(EmployeeIdentificationController.class).readAll()).withRel("Identification")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<EmployeeIdentification>> toCollectionModel(@NotNull Iterable<? extends EmployeeIdentification> entities) {
        return null;
    }
}
