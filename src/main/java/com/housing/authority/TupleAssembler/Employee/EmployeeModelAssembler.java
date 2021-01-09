package com.housing.authority.TupleAssembler.Employee;

import com.housing.authority.Controllers.Employee.EmployeeController;
import com.housing.authority.Tuples.Employee.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>>{


    @Override
    public @NotNull EntityModel<Employee> toModel(@NotNull Employee entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(EmployeeController.class).readOneEmployee(entity.getEmployeeId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).readAllEmployee()).withRel("employees")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Employee>> toCollectionModel(@NotNull Iterable<? extends Employee> entities) {
        return null;
    }
}

