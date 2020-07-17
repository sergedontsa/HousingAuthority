package com.housing.authority.Controllers.User;

import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Repository.UserRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.UserModelAssembler;
import com.housing.authority.Tuples.User.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping(value = Constant.USER_CONTROLLER)
public class UsersController implements ServiceController<Users> {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserModelAssembler userModelAssembler;

    public UsersController(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    @Override
    @GetMapping(value = Constant.USER_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Users>> readAll() {
        List<EntityModel<Users>> users = this.userRepository.findAll().stream().map(this.userModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(users, linkTo(methodOn(UsersController.class).readAll()).withSelfRel());

    }

    @Override
    @CrossOrigin
    @GetMapping(value = Constant.USER_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Users> readOne(@PathVariable String id) {
        if(this.userRepository.findById(id).isPresent()) {
            return this.userModelAssembler.toModel(this.userRepository.findById(id).get());
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
    public EntityModel<Users> readOne(int id) {
        return null;
    }

    @Override
    @CrossOrigin
    @PostMapping(value = Constant.USER_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(Users newUser) {
        if (!this.userRepository.isUserExist(newUser.getUsername(), newUser.getEmail(), newUser.getPhonenumber())) {
            EntityModel<Users> entityModel = null;
            BCryptPasswordEncoder hasPasswordProvider = new BCryptPasswordEncoder();
            newUser.setPassword(hasPasswordProvider.encode(newUser.getPassword()));
            newUser.setLastUpdate(Constant.getCurrentDateAsString());
            newUser.setRegisterDate(Constant.getCurrentDateAsString());
            entityModel = this.userModelAssembler.toModel(this.userRepository.save(newUser));
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else {
            return null;
        }
    }

    @Override
    @PatchMapping(path = Constant.USER_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Users users) {
        if (this.userRepository.findById(id).isPresent()){
            Users existingUser = this.userRepository.findById(id).get();
            existingUser.setUsername(users.getUsername());
            existingUser.setPhonenumber(users.getPhonenumber());
            existingUser.setEmail(users.getEmail());
            existingUser.setPassword(users.getPassword());
            existingUser.setLastUpdate(Constant.getCurrentDateAsString());
            EntityModel<Users> entityModel = this.userModelAssembler.toModel(this.userRepository.save(existingUser));
            return ResponseEntity.created(userModelAssembler.toModel(this.userRepository.save(existingUser)).getRequiredLink(IanaLinkRelations.SELF)
            .toUri()).body(entityModel);

        }else {
            return null;
        }
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.USER_DELETE_WITH_ID)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if(this.userRepository.findById(id).isPresent()) {
            this.userRepository.delete(this.userRepository.findById(id).get());

        }
    }
}
