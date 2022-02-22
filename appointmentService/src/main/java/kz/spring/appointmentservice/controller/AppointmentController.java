package kz.spring.appointmentservice.controller;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.appointmentservice.service.impl.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("/{customerId}")
//    public Appointment getByCustomerId(@PathVariable("customerId") Long customerId){
//        return iAppointmentService.getByCustomerId(customerId);
//    }

    @GetMapping("/id}")
    public Appointment getById(@PathVariable("id") Long id){
        return iAppointmentService.getById(id);
    }

//    @GetMapping("/{doctorId}")
//    public Appointment getByDoctorId(@PathVariable("doctorId") Long doctorId){
//        return iAppointmentService.getByDoctorId(doctorId);
//    }

//    @GetMapping("/{medCenterId}")
//    public Appointment getByMedCenterId(@PathVariable("medCenterId") Long medCenterId){
//        return iAppointmentService.getByMedCenterId(medCenterId);
//    }

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
}
