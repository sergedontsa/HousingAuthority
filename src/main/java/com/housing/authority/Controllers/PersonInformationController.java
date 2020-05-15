package com.housing.authority.Controllers;

import com.housing.authority.Repository.PersonInformationRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.PersonInformationModelAssembler;
import com.housing.authority.Tuples.Personinformation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.HOUSING_CONTROLLER)
public class PersonInformationController {
    private final PersonInformationRepository personInformationRepository;
    private final PersonInformationModelAssembler personInformationModelAssembler;

    @GetMapping(value = Constant.PERSONAL_INFORMATION_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Personinformation>> readAllRecord(){
        List<EntityModel<Personinformation>> personalInformation = this.personInformationRepository.findAll().stream().map(
                this.personInformationModelAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(personalInformation, linkTo(methodOn(PersonInformationController.class).readAllRecord()).withSelfRel());
    }

    @GetMapping(value = Constant.PERSONAL_INFORMATION_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<?> readOneRecord(@PathVariable String id) {
        if (this.personInformationRepository.findById(id).isPresent()){
            return this.personInformationModelAssembler.toModel(this.personInformationRepository.findById(id).get());
        }else {
            return null;
        }
    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.PERSONAL_INFORMATION_DELETE_WITH_ID)
    @CrossOrigin
    public void deleteRecord(@PathVariable String id){
        if (this.personInformationRepository.findById(id).isPresent()){
            this.personInformationRepository.delete(this.personInformationRepository.findById(id).get());
        }
    }
    @PatchMapping(path = Constant.PERSONAL_INFORMATION_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object updateRecord(@PathVariable String id, @RequestBody Personinformation personinformation){
        if (this.personInformationRepository.findById(id).isPresent()){
            Personinformation existingRecord = this.personInformationRepository.findById(id).get();
            existingRecord.setPersonid(personinformation.getPersonid());
            existingRecord.setPersonstatus(personinformation.getPersonstatus());
            existingRecord.setPhonenumber(personinformation.getPhonenumber());
            existingRecord.setEmail(personinformation.getEmail());
            existingRecord.setIdtype(personinformation.getIdtype());
            existingRecord.setIdnumber(personinformation.getIdnumber());
            existingRecord.setIdissuedate(personinformation.getIdissuedate());
            existingRecord.setIdexpireddate(personinformation.getIdexpireddate());
            existingRecord.setAddress(personinformation.getAddress());
            existingRecord.setCountry(personinformation.getCountry());
            existingRecord.setLastupdate(Constant.getCurrentDateAsString());
            EntityModel<Personinformation> entityModel = this.personInformationModelAssembler.toModel(this.personInformationRepository.save(existingRecord));
            return ResponseEntity.created(this.personInformationModelAssembler.toModel(this.personInformationRepository.save(existingRecord))
            .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }
    @PostMapping(value = Constant.PERSONAL_INFORMATION_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createRecord(@RequestBody Personinformation personinformation){
        personinformation.setDataid(IDGenerator.RECORD_ID());
        personinformation.setRegisterdate(Constant.getCurrentDateAsString());
        personinformation.setLastupdate(Constant.getCurrentDateAsString());
        EntityModel<Personinformation> entityModel = this.personInformationModelAssembler.toModel(this.personInformationRepository.save(personinformation));
        return ResponseEntity.created(this.personInformationModelAssembler.toModel(this.personInformationRepository.save(personinformation))
        .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }
}
