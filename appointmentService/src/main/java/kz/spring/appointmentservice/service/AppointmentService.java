package kz.spring.appointmentservice.service;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.appointmentservice.model.Customer;
import kz.spring.appointmentservice.model.Doctor;
import kz.spring.appointmentservice.model.MedCenter;
import kz.spring.appointmentservice.repository.AppointmentRepository;
import kz.spring.appointmentservice.repository.CustomerRepository;
import kz.spring.appointmentservice.repository.DoctorRepository;
import kz.spring.appointmentservice.repository.MedCenterRepository;
import kz.spring.appointmentservice.service.impl.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.getAppointmentByAppointmentId(id);
    }

    @Override
    public Appointment getByDate(Date date) {
        return appointmentRepository.getAppointmentByDate(date);
    }

    @Override
    public void create(Appointment appointment, Long medCenterId, Long doctorId, Long customerId) {

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

//        Customer customer = restTemplate.getForObject("http://localhost:8083/customer/" + customerId, Customer.class);
//
//        Doctor doctor = restTemplate.getForObject("http://localhost:8082/doctor/" + doctorId, Doctor.class);
//
//        MedCenter medCenter = restTemplate.getForObject("http://localhost:8082/medCenter/" + medCenterId, MedCenter.class);

        boolean apCheck = true;

        if(doctor != null && medCenter != null && customer != null){
            appointment.setCustomer(customer);
            appointment.setDoctor(doctor);
            appointment.setMedCenter(medCenter);
            apCheck = checkAppointment(appointment);
            if(apCheck){
                appointmentRepository.saveAndFlush(appointment);
            } else {
                deleteApp(appointment);
                System.out.println("Error with appointment date: you have appointment in this time");
            }
        } else {
            System.out.println("entity is empty!");
        }
    }

    @Override
    public void update(Appointment appointment, Long medCenterId, Long appointmentId, Long doctorId, Long customerId){

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

//        Customer customer = restTemplate.getForObject("http://localhost:8083/customer/" + customerId, Customer.class);
//
//        Doctor doctor = restTemplate.getForObject("http://localhost:8082/doctor/" + doctorId, Doctor.class);
//
//        MedCenter medCenter = restTemplate.getForObject("http://localhost:8082/medCenter/" + medCenterId, MedCenter.class);

        boolean apCheck = true;

        if(doctor != null && medCenter != null && customer != null){
            appointment.setCustomer(customer);
            appointment.setDoctor(doctor);
            appointment.setMedCenter(medCenter);
            apCheck = checkAppointment(appointment);
            if(apCheck){
                appointmentRepository.saveAndFlush(appointment);
            } else {
                deleteApp(appointment);
                System.out.println("Error with appointment date: you have appointment in this time");
            }
        } else {
            System.out.println("entity is empty!");
        }
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void deleteApp(Appointment appointment){
        appointmentRepository.delete(appointment);
    }

    public boolean checkAppointment(Appointment appointment){

        List<Appointment> appointment1 = appointmentRepository.findAll();

        int timeFrom = Integer.parseInt(appointment.getDoctor().getWorkTimeFrom().replace(":", "0"));
        int timeTo = Integer.parseInt(appointment.getDoctor().getWorkTimeTo().replace(":", "0"));

        int time = Integer.parseInt(appointment.getTime().replace(":", "0"));

        for(int i = 0; i < appointment1.size(); i++){
            if(appointment.getCustomer().getCustomerId() == appointment1.get(i).getCustomer().getCustomerId() && appointment1.get(i).getDate().getTime() == appointment.getDate().getTime()){
                if(time > timeTo && time > timeFrom){
                    System.out.println("Doctor time is " + appointment.getDoctor().getWorkTimeFrom() + " and " + appointment.getDoctor().getWorkTimeTo());
                    return false;
                }
            }else{
                System.out.println("You have same appointment with time");
            }
        }

        return true;
    }

    @Override
    public Appointment updateDoctor(Long appointmentId, Long doctorId) {

        Appointment appointment = appointmentRepository.getById(appointmentId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

//        Doctor doctor = restTemplate.getForObject("http://localhost:8082/doctor/" + doctorId, Doctor.class);

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

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

//        MedCenter medCenter = restTemplate.getForObject("http://localhost:8082/medCenter/" + medCenterId, MedCenter.class);

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

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

//        Customer customer = restTemplate.getForObject("http://localhost:8083/customer/" + customerId, Customer.class);

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
