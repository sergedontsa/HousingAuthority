package com.housing.authority.Controllers.ContactInformaiton;

import com.housing.authority.Repository.ContactInformationRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.ContactInformation.ContactInformationAssembler;
import com.housing.authority.Tuples.ContactInformation.ContactInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value = Constant.CONTACT_INFORMATION_CONTROLLER)
public class ContactInformationController {

    @Autowired
    private final ContactInformationAssembler contactInformationAssembler;
    @Autowired
    private final ContactInformationRepository contactInformationRepository;

    public ContactInformationController(ContactInformationRepository contactInformationRepository, ContactInformationAssembler contactInformationAssembler) {
        this.contactInformationAssembler = contactInformationAssembler;
        this.contactInformationRepository = contactInformationRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.CONTACT_INFORMATION_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<ContactInformation>> readAll(){
        return null;
    }
    @CrossOrigin
    @GetMapping(value = Constant.CONTACT_INFORMATION_GET_ONE)
    public EntityModel<ContactInformation> readOne(@PathVariable String phoneNumber){
        return null;
    }
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Constant.CONTACT_INFORMATION_SAVE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String phoneNumber, @RequestBody ContactInformation contactInformation){
        return null;
    }
    @CrossOrigin
    @DeleteMapping(value = Constant.CONTACT_INFORMATION_DELETE, produces = Constant.PRODUCE)
    public ResponseEntity<?> delete(@PathVariable String phoneNumber){
        return null;
    }
    @CrossOrigin
    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = Constant.CONTACT_INFORMATION_UPDATE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> update(@PathVariable String phoneNumber, @RequestBody ContactInformation contactInformation){
        return null;
    }

}
