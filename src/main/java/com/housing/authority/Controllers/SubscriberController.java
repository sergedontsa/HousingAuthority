package com.housing.authority.Controllers;

import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Repository.SubscriberRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.SubscriberModelAssembler;
import com.housing.authority.Tuples.Subscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.SUBSCRIBER_CONTROLLER)
public class SubscriberController implements ServiceController<Subscriber> {

    private final SubscriberRepository subscriberRepository;
    private final SubscriberModelAssembler subscriberModelAssembler;

    @Override
    @GetMapping(value = Constant.SUBSCRIBER_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Subscriber>> readAll() {
        List<EntityModel<Subscriber>> subscribers = this.subscriberRepository.findAll().stream()
                .map(this.subscriberModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(subscribers, linkTo(methodOn(SubscriberController.class).readAll()).withSelfRel());
    }

    @Override
    public EntityModel<Subscriber> readOne(String id) {
        return null;
    }

    @Override
    @CrossOrigin
    @GetMapping(value = Constant.SUBSCRIBER_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Subscriber> readOne(@PathVariable int id) {
        if (this.subscriberRepository.findById(id).isPresent()){
            return subscriberModelAssembler.toModel(this.subscriberRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @Override
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Constant.SUBSCRIBER_SAVE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(Subscriber object) {
        if (object.getName() == null || object.getEmail() == null){
            return null;
        }else {
            object.setLastupdate(Constant.getCurrentDateAsString());
            object.setRegisterdate(Constant.getCurrentDateAsString());
         EntityModel<Subscriber> entityModel = subscriberModelAssembler.toModel(this.subscriberRepository.save(object));
         return ResponseEntity.created(subscriberModelAssembler.toModel(this.subscriberRepository.save(object))
         .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }
    }

    @Override
    @CrossOrigin
    @PatchMapping(path = Constant.SUBSCRIBER_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    public Object update(@PathVariable String id, @RequestBody Subscriber subscriber) {
        int idInt = Integer.parseInt(id);
        if (this.subscriberRepository.findById(idInt).isPresent()){
            Subscriber existingSubscriber = this.subscriberRepository.findById(idInt).get();
            existingSubscriber.setLastupdate(Constant.getCurrentDateAsString());
            if (subscriber.getEmail() != null) {
                existingSubscriber.setEmail(subscriber.getEmail());
            }
            if (subscriber.getName() != null) {
                existingSubscriber.setName(subscriber.getName());
            }
           EntityModel<Subscriber> entityModel = this.subscriberModelAssembler.toModel(this.subscriberRepository.save(existingSubscriber));
                return ResponseEntity.created(subscriberModelAssembler.toModel(this.subscriberRepository.save(existingSubscriber)).getRequiredLink(IanaLinkRelations.SELF)
                .toUri()).body(entityModel);

        }
        return null;
    }

    @Override
    @CrossOrigin
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.SUBSCRIBER_DELETE_WITH_ID)
    public void delete(@PathVariable String id) {
        if (this.subscriberRepository.findById(Integer.parseInt(id)).isPresent()){
            this.subscriberRepository.delete(this.subscriberRepository.findById(Integer.parseInt(id)).get());
        }
    }
}
