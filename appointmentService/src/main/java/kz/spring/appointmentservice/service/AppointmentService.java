package kz.spring.appointmentservice.service;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.appointmentservice.model.Customer;
import kz.spring.appointmentservice.model.Doctor;
import kz.spring.appointmentservice.model.MedCenter;
import kz.spring.appointmentservice.repository.AppointmentRepository;
import kz.spring.appointmentservice.service.impl.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.getAppointmentByAppointmentId(id);
    }

    @Override
    public Appointment getByDate(Date date) {
        return appointmentRepository.getAppointmentByDate(date);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentRepository.saveAndFlush(appointment);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment addAppointment(Long medCenterId, Long appointmentId, Long doctorId, Long customerId){

        Customer customer = restTemplate.getForObject("http://localhost:8083/client-service/customer/" + customerId, Customer.class);

        Doctor doctor = restTemplate.getForObject("http://localhost:8082/med-service/doctor/" + doctorId, Doctor.class);

        MedCenter medCenter = restTemplate.getForObject("http://localhost:8082/med-service/medCenter/" + medCenterId, MedCenter.class);

        Appointment appointment = appointmentRepository.getById(appointmentId);

        boolean apCheck = checkAppointment(customer, appointment);

        boolean check = false;

        if(doctor != null && medCenter != null && customer != null && appointment != null && apCheck){
            appointment.getCustomer().setCustomerId(customerId);
            appointment.getDoctor().setDoctorId(doctorId);
            appointment.getMedCenter().setMedCenterId(medCenterId);
            if(!check){
                return appointmentRepository.saveAndFlush(appointment);
            }
        }
        return null;
    }

    public boolean checkAppointment(Customer customer, Appointment appointment){

        List<Appointment> appointment1 = appointmentRepository.findAll();

        for(int i = 0; i < appointment1.size(); i++){
            if(customer.getCustomerId().equals(appointment1.get(i).getCustomer().getCustomerId()) && appointment1.get(i).getDate().getTime() == appointment.getDate().getTime()){
                return false;
            }
        }

        return true;
    }

    @Override
    public Appointment updateDoctor(Long appointmentId, Long doctorId) {

        Appointment appointment = appointmentRepository.getById(appointmentId);

        Doctor doctor = restTemplate.getForObject("http://localhost:8082/med-service/doctor/" + doctorId, Doctor.class);

        boolean check = false;

        if(appointment.getDoctor().getDoctorId().equals(doctorId)){
            appointment.setDoctor(doctor);
            check = true;
        }

        if(check){
            return appointmentRepository.saveAndFlush(appointment);
        }
        return null;
    }

    @Override
    public Appointment updateMedCenter(Long appointmentId, Long medCenterId) {

        Appointment appointment = appointmentRepository.getById(appointmentId);

        MedCenter medCenter = restTemplate.getForObject("http://localhost:8082/med-service/medCenter/" + medCenterId, MedCenter.class);

        boolean check = false;

        if(appointment.getMedCenter().getMedCenterId().equals(medCenterId)){
            appointment.setMedCenter(medCenter);
            check = true;
        }

        if(check){
            return appointmentRepository.saveAndFlush(appointment);
        }
        return null;
    }

    @Override
    public Appointment updateCustomer(Long appointmentId, Long customerId) {

        Appointment appointment = appointmentRepository.getById(appointmentId);

        Customer customer = restTemplate.getForObject("http://localhost:8083/client-service/customer/" + customerId, Customer.class);

        boolean check = false;

        if(appointment.getCustomer().getCustomerId().equals(customerId)){
            appointment.setCustomer(customer);
            check = true;
        }

        if(check){
            return appointmentRepository.saveAndFlush(appointment);
        }
        return null;
    }
}
