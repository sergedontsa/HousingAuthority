package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.EmployeeController;
import com.housing.authority.Tuples.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {



    @Override
    public   EntityModel<Employee> toModel( Employee employee) {
      return new EntityModel<>(employee,
              linkTo(methodOn(EmployeeController.class).readOne(employee.getEmployeeId())).withSelfRel(),
              linkTo(methodOn(EmployeeController.class).readAll()).withRel("employee"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Employee>> toCollectionModel(@NotNull Iterable<? extends Employee> entities) {
        return null;
    }
}
