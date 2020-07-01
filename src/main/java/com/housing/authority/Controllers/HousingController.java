package com.housing.authority.Controllers;

import com.housing.authority.Repository.*;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.ApartmentModelAssembler;
import com.housing.authority.TupleAssembler.BuildingModelAssembler;
import com.housing.authority.TupleAssembler.ComplainDoneModelAssembler;
import com.housing.authority.TupleAssembler.ComplainModelAssembler;
import com.housing.authority.TupleAssembler.EmployeeModelAssembler;
import com.housing.authority.TupleAssembler.ListeningAssembler;
import com.housing.authority.TupleAssembler.TenantModelAssembler;
import com.housing.authority.TupleAssembler.UserModelAssembler;
import com.housing.authority.Tuples.Complain;
import com.housing.authority.Tuples.Complaindone;
import com.housing.authority.Tuples.Listening;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 */
@RestController
@RequestMapping(value = Constant.HOUSING_CONTROLLER)
@RequiredArgsConstructor
public class HousingController {

    private final ApartmentRepository apartmentTupleRepository;
    private final EmployeeRepository employeeRepository;
    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final ComplainRepository complainRepository;
    private final ComplainDoneRepository complainDoneRepository;
    private final ListeningRepository listeningRepository;
    private final EmployeeModelAssembler employeeModelAssembler;
    private final BuildingModelAssembler buildingModelAssembler;
    private final UserModelAssembler userModelAssembler;
    private final ApartmentModelAssembler apartmentModelAssembler;
    private final TenantModelAssembler tenantModelAssembler;
    private final ComplainModelAssembler complainModelAssembler;
    private final ComplainDoneModelAssembler complainDoneModelAssembler;
    private final ListeningAssembler listeningAssembler;

    @CrossOrigin
    @GetMapping(value = "/email")
    public static void sendEmail(){
        //need to fix the domaine email
        Email from = new Email("dontsa00serge@gmail.com");
        String subject = "Sending with Twilio SendGrid is fun";
        Email to = new Email("dontsaserge@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere even with java");
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println("+ "+response.getStatusCode());
            System.out.println("+ "+response.getBody());
            System.out.println("+ "+response.getHeaders());

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //.................................COMPLAIN
    @GetMapping(value = Constant.COMPLAIN_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Complain>> readAllComplain(){
        List<EntityModel<Complain>> complains = this.complainRepository.findAll().stream().map(this.complainModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(complains, linkTo(methodOn(HousingController.class).readAllComplain()).withSelfRel());
   }
    @GetMapping(value = Constant.COMPLAIN_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Complain> readOneComplain(@PathVariable String id){
        if (this.complainRepository.findById(id).isPresent()){
            return this.complainModelAssembler.toModel(this.complainRepository.findById(id).get());
        }else {
            return null;
        }
    }
    @PostMapping(value = Constant.COMPLAIN_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> createComplain(@RequestBody Complain complain){
        complain.setRegisterdate(Constant.getCurrentDateAsString());
        complain.setLastupdate(Constant.getCurrentDateAsString());
        complain.setStatus("Under Review");
        complain.setComplainid(IDGenerator.COMPLAIN_ID());
        EntityModel<Complain> entityModel = this.complainModelAssembler.toModel(this.complainRepository.save(complain));
        assert entityModel != null;
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    @PatchMapping(path = Constant.COMPLAIN_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<?> updateComplain(@PathVariable String id, @RequestBody Complain complain){
        if (this.complainRepository.findById(id).isPresent()){
            Complain existingComplain = this.complainRepository.findById(id).get();
            existingComplain.setPersonid(complain.getPersonid());
            existingComplain.setType(complain.getType());
            existingComplain.setTitle(complain.getTitle());
            existingComplain.setBody(complain.getBody());
            existingComplain.setPhonenumber(complain.getPhonenumber());
            existingComplain.setSeverity(complain.getSeverity());
            existingComplain.setAddress(complain.getAddress());
            if (complain.getStatus() != null){
                existingComplain.setStatus(complain.getStatus());
            }else {
                existingComplain.setStatus("Pending");
            }
            existingComplain.setAssignto(complain.getAssignto());
            existingComplain.setLastupdate(Constant.getCurrentDateAsString());

            EntityModel<Complain> entityModel = this.complainModelAssembler.toModel(this.complainRepository.save(existingComplain));
            assert entityModel != null;
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);



        }else {
            return null;
        }
    }

    @DeleteMapping(value = Constant.COMPLAIN_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public HttpStatus deleteComplain(@PathVariable String id){
        if (this.complainRepository.findById(id).isPresent()){
            this.complainRepository.delete(this.complainRepository.findById(id).get());
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }

    //.................................COMPLAIN DONE
    @GetMapping(value = Constant.COMPLAIN_DONE_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Complaindone>> readAllComplainDone(){
        List<EntityModel<Complaindone>> complains = this.complainDoneRepository.findAll().stream().map(this.complainDoneModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(complains, linkTo(methodOn(HousingController.class).readAllComplain()).withSelfRel());
    }
    @GetMapping(value = Constant.COMPLAIN_DONE_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Complaindone> readOneComplainDone(@PathVariable int id){
        if (this.complainDoneRepository.findById(id).isPresent()){
            return this.complainDoneModelAssembler.toModel(this.complainDoneRepository.findById(id).get());
        }else {
            return null;
        }
    }
    @PostMapping(value = Constant.COMPLAIN_DONE_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> createComplainDone(@RequestBody Complaindone complaindone){

        if (this.employeeRepository.findById(complaindone.getEmployeeid()).isPresent() && this.complainRepository.findById(complaindone.getComplainId()).isPresent()){
            complaindone.setConfirmationid(IDGenerator.COMPLAIN_DONE_CONFIRMATION());
            complaindone.setRegisterdate(Constant.getCurrentDateAsString());
            complaindone.setLastupdate(Constant.getCurrentDateAsString());
            setComplainStatus("DONE", complaindone.getComplainId());
            EntityModel<Complaindone> entityModel = this.complainDoneModelAssembler.toModel(this.complainDoneRepository.save(complaindone));
            assert entityModel != null;
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

        }else {
            return null;
        }
    }


    @PatchMapping(path = Constant.COMPLAIN_DONE_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public void updateComplainDone(@PathVariable int id, @RequestBody Complaindone complaindone){
        if (this.complainDoneRepository.findById(id).isPresent()){
            Complaindone existingComplainDone = this.complainDoneRepository.findById(id).get();
            existingComplainDone.setLastupdate(Constant.getCurrentDateAsString());
            existingComplainDone.setCost(complaindone.getCost());
            existingComplainDone.setEmployeeid(complaindone.getEmployeeid());
            existingComplainDone.setTimespend(complaindone.getTimespend());
        }
    }

    @DeleteMapping(value = Constant.COMPLAIN_DONE_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.CREATED)
    @CrossOrigin
    public HttpStatus deleteComplainDone(@PathVariable int id){
        if (this.complainDoneRepository.findById(id).isPresent()){
            this.complainDoneRepository.delete(this.complainDoneRepository.findById(id).get());
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }

    //    @Modifying
//    @Query("UPDATE Complain SET Complain.status=:status where Complain.complainid=:complainid")
    private void setComplainStatus(@Param("status") String status, @Param("complainid") String complainid){
        if (this.complainRepository.findById(complainid).isPresent()){
            Complain complain = this.complainRepository.findById(complainid).get();
            complain.setStatus(status);
            this.complainRepository.save(complain);
        }
    }

    //listening
    @GetMapping(value = Constant.LISTENING_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Listening>> readAllListening(){

       List<EntityModel<Listening>> listening = this.listeningRepository.findAll().stream()
               .map(this.listeningAssembler::toModel).collect(Collectors.toList());

       return new CollectionModel<>(listening, linkTo(methodOn(HousingController.class).readAllListening()).withSelfRel());


    }
    @GetMapping(value = Constant.LISTENING_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Listening> readOneListening(@PathVariable String id){
        if (this.listeningRepository.findById(id).isPresent()){
            return this.listeningAssembler.toModel(this.listeningRepository.findById(id).get());
        }else {
            return null;
        }
    }

}
