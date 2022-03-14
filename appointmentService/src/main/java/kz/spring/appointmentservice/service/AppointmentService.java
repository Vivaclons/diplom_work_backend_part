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
    public void create(Appointment appointment, Long medCenterId, Long appointmentId, Long doctorId, Long customerId) {
        appointmentRepository.saveAndFlush(appointment);
        addAppointment(medCenterId, appointmentId, doctorId, customerId);
    }

    @Override
    public void update(Appointment appointment, Long medCenterId, Long appointmentId, Long doctorId, Long customerId){
        appointmentRepository.saveAndFlush(appointment);
        addAppointment(medCenterId, appointmentId, doctorId, customerId);
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

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

//        Customer customer = restTemplate.getForObject("http://localhost:8083/customer/" + customerId, Customer.class);
//
//        Doctor doctor = restTemplate.getForObject("http://localhost:8082/doctor/" + doctorId, Doctor.class);
//
//        MedCenter medCenter = restTemplate.getForObject("http://localhost:8082/medCenter/" + medCenterId, MedCenter.class);

        Appointment appointment = appointmentRepository.getAppointmentByAppointmentId(appointmentId);

        boolean apCheck = true;

        boolean check = false;


        if(apCheck){
            if(doctor != null && medCenter != null && customer != null && appointment != null){
                appointment.setCustomer(customer);
                appointment.setDoctor(doctor);
                appointment.setMedCenter(medCenter);
                if(!check){
                    appointmentRepository.saveAndFlush(appointment);
                    apCheck = checkAppointment(customer, appointment);
                    if(!apCheck){
                        DeleteById(appointmentId);
                    }
                }
            } else {
                System.out.println("entity is empty!");
            }
        } else {
            System.out.println("Error with appointment date: you have appointment in this time");
        }
        return null;
    }

    public boolean checkAppointment(Customer customer, Appointment appointment){

        List<Appointment> appointment1 = appointmentRepository.findAll();

        for(int i = 0; i < appointment1.size(); i++){
            System.out.println(customer.getCustomerId());
            System.out.println(appointment.toString());
            System.out.println(appointment1.get(i).getAppointmentId());
            System.out.println(customer.getCustomerId());
            System.out.println(appointment1.get(i).getCustomer().getCustomerId());
            if(customer.getCustomerId().equals(appointment1.get(i).getCustomer().getCustomerId()) && appointment1.get(i).getDate().getTime() == appointment.getDate().getTime()){
                return false;
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
