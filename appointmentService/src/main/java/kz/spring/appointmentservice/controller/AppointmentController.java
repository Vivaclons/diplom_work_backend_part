package kz.spring.appointmentservice.controller;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.appointmentservice.service.impl.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @GetMapping("/all")
    public List<Appointment> getAllAppointment(){
        return iAppointmentService.getAllAppointment();
    }

    @GetMapping("/id}")
    public Appointment getById(@PathVariable("id") Long id){
        return iAppointmentService.getById(id);
    }

    @GetMapping("/date/{date}")
    public Appointment getByDate(@PathVariable("date") Date date){
        return iAppointmentService.getByDate(date);
    }

    @DeleteMapping("delete/{appointment_id}")
    public void deleteAppointmentByID(@PathVariable("id") Long id){
        iAppointmentService.DeleteById(id);
    }

    @PostMapping("/create")
    public void createAppointment(@RequestBody Appointment appointment){
        iAppointmentService.update(appointment);
    }

    @PutMapping("/update")
    public void updateAppointment(@RequestBody Appointment appointment){
        iAppointmentService.update(appointment);
    }

    @PatchMapping("/add-appointment/{medCenterId}/{appointmentId}/{doctorId}/{customerId}")
    public ResponseEntity<?> addCustomerAnalysis(@PathVariable("medCenterId") Long medCenterId, @PathVariable("appointmentId") Long appointmentId, @PathVariable("doctorId") Long doctorId, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(iAppointmentService.addAppointment(medCenterId, appointmentId, doctorId, customerId));
    }

    @PatchMapping("/update-customer/{appointmentId}/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("appointmentId") Long appointmentId, @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(iAppointmentService.updateCustomer(appointmentId, customerId));
    }

    @PatchMapping("/update-doctor/{appointmentId}/{doctorId}")
    public ResponseEntity<?> updateDoctor(@PathVariable("appointmentId") Long appointmentId, @PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(iAppointmentService.updateDoctor(appointmentId, doctorId));
    }

    @PatchMapping("/update-medCenter/{appointmentId}/{medCenterId}")
    public ResponseEntity<?> updateMedCenter(@PathVariable("appointmentId") Long appointmentId, @PathVariable("medCenterId") Long medCenterId) {
        return ResponseEntity.ok(iAppointmentService.updateMedCenter(appointmentId, medCenterId));
    }
}
