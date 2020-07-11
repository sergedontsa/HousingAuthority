package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.EmployeeDetailController;
import com.housing.authority.Tuples.EmployeeDetail;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EmployeeDetailModelAssembler implements RepresentationModelAssembler<EmployeeDetail, EntityModel<EmployeeDetail>> {
    @Override
    public  EntityModel<EmployeeDetail> toModel( EmployeeDetail entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(EmployeeDetailController.class).readOne(entity.getEmployee().getEmployeeId())).withSelfRel(),
                linkTo(methodOn(EmployeeDetailController.class).readAll()).withRel("detail"));

    }

    @Override
    public @NotNull CollectionModel<EntityModel<EmployeeDetail>> toCollectionModel(@NotNull Iterable<? extends EmployeeDetail> entities) {
        return null;
    }
}
