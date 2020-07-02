package com.housing.authority.Controllers;

import com.housing.authority.Repository.ListeningRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.ListeningAssembler;
import com.housing.authority.Tuples.Listening;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.LISTENING_CONTROLLER)
public class ListeningController implements ServiceController<Listening> {

    private final ListeningRepository listeningRepository;
    private final ListeningAssembler listeningAssembler;

    /**
     * This method return the collection of all entity
     *
     * @return the collection
     */
    @Override
    @CrossOrigin
    @GetMapping(value = Constant.LISTENING_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Listening>> readAll() {
        List<EntityModel<Listening>> listening = this.listeningRepository.findAll().stream()
                .map(this.listeningAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(listening, linkTo(methodOn(ListeningController.class).readAll()).withSelfRel());

    }

    /**
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @CrossOrigin
    @GetMapping(value = Constant.LISTENING_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Listening> readOne(@PathVariable String id) {
        if (this.listeningRepository.findById(id).isPresent()){
            return this.listeningAssembler.toModel(this.listeningRepository.findById(id).get());
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
    public EntityModel<Listening> readOne(int id) {
        return null;
    }

    /**
     * Create the new entity in server
     *
     * @param object the entity
     * @return the create entity
     */
    @Override
    public ResponseEntity<?> create(Listening object) {
        return null;
    }

    /**
     * Update the entity in the server
     *
     * @param id        the id of the entity
     * @param listening the updated data
     * @return the updated one
     */
    @Override
    public Object update(String id, Listening listening) {
        return null;
    }

    /**
     * Delete the value in the database
     *
     * @param id the id of the entity to updated
     */
    @Override
    public void delete(String id) {

    }
}
