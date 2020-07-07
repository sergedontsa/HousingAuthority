package com.housing.authority.Controllers;

import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Repository.ScheduleRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.Utilities;
import com.housing.authority.TupleAssembler.ScheduleModelAssembler;
import com.housing.authority.Tuples.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.SCHEDULE_CONTROLLER)
public class ScheduleController implements ServiceController<Schedule> {

    private final ScheduleModelAssembler scheduleModelAssembler;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    /**
     * This method return the collection of all entity
     *
     * @return the collection
     */
    @Override
    @CrossOrigin
    @GetMapping(value = Constant.SCHEDULE_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Schedule>> readAll() {
        List<EntityModel<Schedule>> schedules = this.scheduleRepository.findAll().stream().map(this.scheduleModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(schedules, linkTo(methodOn(ScheduleController.class).readAll()).withSelfRel());
    }

    /**
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @CrossOrigin
    @GetMapping(value = Constant.SCHEDULE_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Schedule> readOne(@PathVariable String id) {
        if (this.scheduleRepository.findById(Integer.parseInt(id)).isPresent()){
            return this.scheduleModelAssembler.toModel(this.scheduleRepository.findById(Integer.parseInt(id)).get());
        }else {
            return null;
        }
    }

    /**
     * In case the entity is referred with the id with type integer
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @CrossOrigin
    public EntityModel<Schedule> readOne(int id) {
        return null;
    }

    /**
     * Create the new entity in server
     *
     * @param object the entity
     * @return the create entity
     */

    @Override
    @CrossOrigin
    @PostMapping(value = Constant.SCHEDULE_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Schedule object) {


        String numHours = Utilities.getNumHours(object.getFrom(), object.getTo());
        object.setHours(numHours);
        //System.out.println("Employee ID: " + object.getEmployeeid());

        System.out.println("Date: " + object.getDate());
        System.out.println("Start: " + object.getFrom());
        System.out.println("End: " + object.getTo());
        System.out.println("Hours: "+ object.getHours());
        System.out.println("Description: " + object.getDescription());
        System.out.println("Register date: " + object.getRegisterdate());
        System.out.println("Last Update: " + object.getLastupdate());

        System.out.println();
        System.out.println(object);
        System.out.println();
        EntityModel<Schedule> entityModel = this.scheduleModelAssembler
                .toModel(this.scheduleRepository.save(object));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);


    }


    /**
     * Update the entity in the server
     *
     * @param id       the id of the entity
     * @param schedule the updated data
     * @return the updated one
     */
    @Override
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Schedule schedule) {
        return null;
    }

    /**
     * Delete the value in the database
     *
     * @param id the id of the entity to updated
     */
    @Override
    @CrossOrigin
    public void delete(@PathVariable String id) {

    }
}
