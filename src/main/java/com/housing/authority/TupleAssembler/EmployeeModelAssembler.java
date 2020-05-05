package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.HousingController;
import com.housing.authority.Tuples.Employees;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employees, EntityModel<Employees>> {



    @Override
    public EntityModel<Employees> toModel(Employees employees) {
      return new EntityModel<>(employees,
              linkTo(methodOn(HousingController.class).readOneEmployee(employees.getEmployeeId())).withSelfRel(),
              linkTo(methodOn(HousingController.class).readAllEmployees()).withRel("employees"));
    }

    @Override
    public CollectionModel<EntityModel<Employees>> toCollectionModel(Iterable<? extends Employees> entities) {
        return null;
    }
}
