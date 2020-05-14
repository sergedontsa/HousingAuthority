package com.housing.authority.Controllers;

import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Repository.ComplainDoneRepository;
import com.housing.authority.Repository.ComplainRepository;
import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Repository.ListeningRepository;
import com.housing.authority.Repository.TenantRepository;
import com.housing.authority.Repository.UserRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.ID_Utils;
import com.housing.authority.TupleAssembler.ApartmentModelAssembler;
import com.housing.authority.TupleAssembler.BuildingModelAssembler;
import com.housing.authority.TupleAssembler.ComplainDoneModelAssembler;
import com.housing.authority.TupleAssembler.ComplainModelAssembler;
import com.housing.authority.TupleAssembler.EmployeeModelAssembler;
import com.housing.authority.TupleAssembler.ListeningAssembler;
import com.housing.authority.TupleAssembler.TenantModelAssembler;
import com.housing.authority.TupleAssembler.UserModelAssembler;
import com.housing.authority.Tuples.Apartment;
import com.housing.authority.Tuples.Building;
import com.housing.authority.Tuples.Complain;
import com.housing.authority.Tuples.Complaindone;
import com.housing.authority.Tuples.Employees;
import com.housing.authority.Tuples.Listening;
import com.housing.authority.Tuples.Tenant;
import com.housing.authority.Tuples.Users;
import lombok.RequiredArgsConstructor;


import org.springframework.data.repository.query.Param;
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


    @GetMapping(value = Constant.EMPLOYEE_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Employees>> readAllEmployees(){

        List<EntityModel<Employees>> employees = this.employeeRepository.findAll().stream()
                .map(this.employeeModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(employees, linkTo(methodOn(HousingController.class).readAllEmployees()).withSelfRel());
    }

    @GetMapping(value = Constant.EMPLOYEE_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Employees> readOneEmployee(@PathVariable String id){
        if (this.employeeRepository.findById(id).isPresent()){
            return employeeModelAssembler.toModel(this.employeeRepository.findById(id).get());
        }else {
            return null;
        }


    }

    @PostMapping(value = Constant.EMPLOYEE_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createEmployee(@RequestBody Employees newEmployee) {
        newEmployee.setEmployeeId(ID_Utils.EMPLOYEE_ID());
        newEmployee.setRegisterDate(Constant.getCurrentDateAsString());
        newEmployee.setLastupdate(Constant.getCurrentDateAsString());
        EntityModel<Employees> entityModel = employeeModelAssembler.toModel(this.employeeRepository.save(newEmployee));
        return ResponseEntity.created(employeeModelAssembler.toModel(this.employeeRepository.save(newEmployee)).getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.EMPLOYEE_DELETE_WITH_ID)
    @CrossOrigin
    public void deleteEmployee(@PathVariable String id){
        if (this.employeeRepository.findById(id).isPresent()){
            this.employeeRepository.delete(this.employeeRepository.findById(id).get());
        }

    }
    @PatchMapping(path = Constant.EMPLOYEE_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object updateEmployee(@PathVariable String id, @RequestBody Employees employees){
        if (this.employeeRepository.findById(id).isPresent()){
            Employees existingEmployee = this.employeeRepository.findById(id).get();

            existingEmployee.setFirstName(employees.getFirstName());
            existingEmployee.setMiddleName(employees.getMiddleName());
            existingEmployee.setLastName(employees.getLastName());

             EntityModel<Employees> entityModel = this.employeeModelAssembler.toModel(this.employeeRepository.save(existingEmployee));
            return ResponseEntity.created(employeeModelAssembler.toModel(this.employeeRepository.save(existingEmployee)).getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);


        }else {
            return HttpStatus.NOT_FOUND;
        }

    }

//    ----------------Begin------Building

    @GetMapping(value = Constant.BUILDING_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Building>> readAllBuilding(){
        List<EntityModel<Building>> buildings = this.buildingRepository.findAll().stream().map(this.buildingModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(buildings, linkTo(methodOn(HousingController.class).readAllBuilding()).withSelfRel());
    }

    @GetMapping(value = Constant.BUILDING_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public Object readOneBuilding(@PathVariable String id){
        if (this.buildingRepository.findById(id).isPresent()) {
            return this.buildingModelAssembler.toModel(this.buildingRepository.findById(id).get());
        }else {
            return HttpStatus.NO_CONTENT;
        }
    }

    @PostMapping(value = Constant.BUILDING_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    ResponseEntity<?> createBuilding(@RequestBody Building newBuilding){
        newBuilding.setBuildingId(ID_Utils.BUILDING_ID());
        return ResponseEntity.created(this.buildingModelAssembler.toModel(this.buildingRepository.save(newBuilding))
                .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newBuilding);

    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.BUILDING_DELETE_WITH_ID)
    @CrossOrigin
    public HttpStatus deleteBuilding(@PathVariable String id){
        if (this.buildingRepository.findById(id).isPresent()) {
            this.buildingRepository.delete(this.buildingRepository.findById(id).get());
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }

    }


    @PatchMapping(path = Constant.BUILDING_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public HttpStatus updateBuilding(@PathVariable String id, @RequestBody Building building){

        if (this.buildingRepository.findById(id).isPresent()){
            Building existingBuilding = this.buildingRepository.findById(id).get();
            existingBuilding.setBuildingNumber(building.getBuildingNumber());
            existingBuilding.setCity(building.getCity());
            existingBuilding.setProvince(building.getProvince());
            existingBuilding.setZipCode(building.getZipCode());
            existingBuilding.setCountry(building.getCountry());
            existingBuilding.setNumLevel(building.getNumLevel());
            existingBuilding.setNumBedRoom(building.getNumBedRoom());
            existingBuilding.setNumBathRoom(building.getNumBathRoom());
            existingBuilding.setNumLivingRoom(building.getNumLivingRoom());
            existingBuilding.setNumKitchen(building.getNumKitchen());
            existingBuilding.setNumDoor(building.getNumDoor());
            existingBuilding.setNumWindows(building.getNumWindows());
            existingBuilding.setNumApartment(building.getNumApartment());
            existingBuilding.setNumStudio(building.getNumStudio());
            existingBuilding.setNumParkingSpace(building.getNumParkingSpace());
            existingBuilding.setWithElevator(building.isWithElevator());
            existingBuilding.setWithPool(building.isWithPool());
            this.buildingRepository.save(existingBuilding);
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }

    }

//    ----------------USERS--------
    @GetMapping(value = Constant.USER_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Users>> readAllUsers(){
        List<EntityModel<Users>> users = this.userRepository.findAll().stream().map(this.userModelAssembler::toModel)
                .collect(Collectors.toList());
       return new CollectionModel<>(users, linkTo(methodOn(HousingController.class).readAllUsers()).withSelfRel());
    }
    @GetMapping(value = Constant.USER_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Users> readOneUser(@PathVariable String id){

        if(this.userRepository.findById(id).isPresent()) {
           return this.userModelAssembler.toModel(this.userRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @PostMapping(value = Constant.USER_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> createUser(@RequestBody Users newUser){
        if (!this.userRepository.isUserExist(newUser.getUsername(), newUser.getEmail(), newUser.getPhonenumber())) {
            EntityModel<Users> entityModel = null;
            BCryptPasswordEncoder hasPasswordProvider = new BCryptPasswordEncoder();
            newUser.setPassword(hasPasswordProvider.encode(newUser.getPassword()));
            newUser.setLastUpdate(Constant.getCurrentDateAsString());
            newUser.setRegisterDate(Constant.getCurrentDateAsString());
            entityModel = this.userModelAssembler.toModel(this.userRepository.save(newUser));
            assert entityModel != null;
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else {
            return null;
        }
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.USER_DELETE_WITH_ID)
    @CrossOrigin
    public HttpStatus deleteUser(@PathVariable String id){
        if(this.userRepository.findById(id).isPresent()) {
            this.userRepository.delete(this.userRepository.findById(id).get());
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }

    }
    @PatchMapping(path = Constant.USER_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public void updateUser(@RequestBody Users users){
        users.setLastUpdate(Constant.getCurrentDateAsString());
        if(this.userRepository.findById(users.getUserId()).isPresent()) {

            this.userRepository.save(users);
        }

    }

    //-------------------------------APARTMENT
    @GetMapping(value = Constant.APARTMENT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Apartment>> readAllApartment(){
        List<EntityModel<Apartment>> apartments = this.apartmentTupleRepository.findAll().stream().map(this.apartmentModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(apartments, linkTo(methodOn(HousingController.class).readAllApartment()).withSelfRel());
    }

    @GetMapping(value = Constant.APARTMENT_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Apartment> readOneApartment(@PathVariable String id){

        if (this.apartmentTupleRepository.findById(id).isPresent()) {
            return this.apartmentModelAssembler.toModel(this.apartmentTupleRepository.findById(id).get());
        }else {
            return null;
        }
    }
    @PostMapping(value = Constant.APARTMENT_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> createApartment(@RequestBody Apartment apartment){
        apartment.setApartmentID(ID_Utils.APARTMENT_ID());
        apartment.setRegisterdate(Constant.getCurrentDateAsString());
        apartment.setLastupdate(Constant.getCurrentDateAsString());
        apartment.setStatus("Available");
        EntityModel<Apartment> entityModel = this.apartmentModelAssembler.toModel(this.apartmentTupleRepository.save(apartment));
        assert entityModel != null;
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }
    @DeleteMapping(value = Constant.APARTMENT_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public HttpStatus deleteApartment(@PathVariable String id){
        if (this.apartmentTupleRepository.findById(id).isPresent()){
            this.apartmentTupleRepository.delete(this.apartmentTupleRepository.findById(id).get());
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }

    }
    @PatchMapping(path = Constant.APARTMENT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public void updateApartment(@PathVariable String id, @RequestBody Apartment apartment){
        if (this.apartmentTupleRepository.findById(id).isPresent()){

            Apartment existingApartment = this.apartmentTupleRepository.findById(id).get();

            existingApartment.setNumBedRoom(apartment.getNumBedRoom());
            existingApartment.setBuildingid(apartment.getBuildingid());
            existingApartment.setNumLivingRoom(apartment.getNumLivingRoom());
            existingApartment.setNumBathRoom(apartment.getNumBathRoom());
            existingApartment.setNumKitchen(apartment.getNumKitchen());
            existingApartment.setNumCloset(apartment.getNumCloset());
            existingApartment.setNumWindows(apartment.getNumWindows());
            existingApartment.setWithBath(apartment.isWithBath());
            existingApartment.setWithWaterBoiler(apartment.isWithWaterBoiler());
            existingApartment.setLastupdate(Constant.getCurrentDateAsString());
            if (apartment.getStatus() == null){
                existingApartment.setStatus("Available");
            }else {
                existingApartment.setStatus(apartment.getStatus());
            }

            this.apartmentTupleRepository.save(existingApartment);


        }
    }
//.................................TENANT
    @GetMapping(value = Constant.TENANT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Tenant>> readAllTenant(){
        List<EntityModel<Tenant>> tenants = this.tenantRepository.findAll().stream()
                .map(this.tenantModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(tenants, linkTo(methodOn(HousingController.class).readAllTenant()).withSelfRel());
    }

    @GetMapping(value = Constant.TENANT_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public Object readOneTenant(@PathVariable String id){
        if (this.tenantRepository.findById(id).isPresent()){
            return this.tenantModelAssembler.toModel(this.tenantRepository.findById(id).get());
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }
    @PostMapping(value = Constant.TENANT_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createTenant(@RequestBody Tenant tenant){
        tenant.setTenantid(ID_Utils.TENANT_ID());
        tenant.setRegisterdate(Constant.getCurrentDateAsString());
        tenant.setLastupdate(Constant.getCurrentDateAsString());
        EntityModel<Tenant> entityModel = this.tenantModelAssembler.toModel(this.tenantRepository.save(tenant));
        return ResponseEntity.created(this.tenantModelAssembler.toModel(this.tenantRepository.save(tenant)).getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
    }
    @DeleteMapping(value = Constant.TENANT_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public HttpStatus deleteTenant(@PathVariable String id){
        if (this.tenantRepository.findById(id).isPresent()){
            this.tenantRepository.delete(this.tenantRepository.findById(id).get());
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PatchMapping(path = Constant.TENANT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public HttpStatus updateTenant(@PathVariable String id, @RequestBody Tenant tenant){
        if (this.tenantRepository.findById(id).isPresent()){

            if (!this.buildingRepository.findById(tenant.getBuildingid()).isPresent() || !this.apartmentTupleRepository.findById(tenant.getApartmentid()).isPresent()){
                return HttpStatus.NOT_FOUND;
            }else {
                Tenant existingTenant = this.tenantRepository.findById(id).get();
                existingTenant.setApartmentid(tenant.getApartmentid());
                existingTenant.setBuildingid(tenant.getBuildingid());
                existingTenant.setFirstname(tenant.getFirstname());
                existingTenant.setMiddlename(tenant.getMiddlename());
                existingTenant.setLastname(tenant.getLastname());
                existingTenant.setEmail(tenant.getEmail());
                existingTenant.setPhonenumber(tenant.getPhonenumber());
                existingTenant.setProfession(tenant.getProfession());
                existingTenant.setDeposite(tenant.getDeposite());
                existingTenant.setLastupdate(Constant.getCurrentDateAsString());
                this.tenantRepository.save(existingTenant);
                return HttpStatus.OK;
            }

        }else {
            return HttpStatus.NOT_FOUND;
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
        complain.setComplainid(ID_Utils.COMPLAIN_ID());
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
            complaindone.setConfirmationid(ID_Utils.COMPLAIN_DONE_CONFIRMATION());
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
