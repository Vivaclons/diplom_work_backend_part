package kz.spring.appointmentservice.service;

import kz.spring.appointmentservice.model.*;
import kz.spring.appointmentservice.repository.AppointmentRepository;
import kz.spring.appointmentservice.repository.CustomerRepository;
import kz.spring.appointmentservice.repository.DoctorRepository;
import kz.spring.appointmentservice.repository.MedCenterRepository;
import kz.spring.appointmentservice.service.impl.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    public void create(Appointment appointment, Long medCenterId, Long doctorId, Long customerId) {

        MedCenter medCenter = medCenterRepository.getMedCenterByMedCenterId(medCenterId);

        Doctor doctor = doctorRepository.getDoctorByDoctorId(doctorId);

        Customer customer = customerRepository.getCustomerByCustomerId(customerId);

//        Customer customer = restTemplate.getForObject("http://localhost:8083/customer/" + customerId, Customer.class);
//
//        Doctor doctor = restTemplate.getForObject("http://localhost:8082/doctor/" + doctorId, Doctor.class);
//
//        MedCenter medCenter = restTemplate.getForObject("http://localhost:8082/medCenter/" + medCenterId, MedCenter.class);

        boolean apCheck = false;

        if(doctor != null && medCenter != null && customer != null){
            appointment.setCustomer(customer);
            appointment.setDoctor(doctor);
            appointment.setMedCenter(medCenter);
            appointment.setStatus(String.valueOf(AppointmentStatus.WAITING_TO_ARRIVAL));
            apCheck = checkAppointment(appointment);
            if(apCheck){
                appointmentRepository.saveAndFlush(appointment);
            } else {
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

//        Customer customer1 = restTemplate.getForObject("http://client-service/customer/" + customerId, Customer.class);

//        Doctor doctor1 = restTemplate.getForObject("http://med-service/doctor" + doctorId, Doctor.class);

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

    public boolean checkAppointment(Appointment appointment){

        List<Appointment> appointment1 = appointmentRepository.findAll();

        int timeFrom = Integer.parseInt(appointment.getDoctor().getWorkTimeFrom().replace(":", "0"));
        int timeTo = Integer.parseInt(appointment.getDoctor().getWorkTimeTo().replace(":", "0"));

        int time = Integer.parseInt(appointment.getTime().replace(":", "0"));

        int times = time + 10000;

        for(int i = 0; i < appointment1.size(); i++){
            if(appointment.getCustomer().getCustomerId().equals(appointment1.get(i).getCustomer().getCustomerId()) &&
                    (appointment1.get(i).getTime().equals(appointment.getTime()) && appointment1.get(i).getDate().getTime() == appointment.getDate().getTime())){
                if(times < Integer.parseInt(appointment1.get(i).getTime().replace(":", "0"))){
                    System.out.println("Customer have appointment (+1 hour)");
                    return false;
                }
                System.out.println("Customer have appointment!");
                return false;
            }

            if(appointment.getDoctor().getDoctorId().equals(appointment1.get(i).getDoctor().getDoctorId()) &&
                    (appointment1.get(i).getTime().equals(appointment.getTime()) && appointment1.get(i).getDate().getTime() == appointment.getDate().getTime())){
                if(times < Integer.parseInt(appointment1.get(i).getTime().replace(":", "0"))){
                    System.out.println("Doctor has appointment (+1 hour)");
                    return false;
                }
                System.out.println("Doctor have appointment!");
                return false;
            }

            if(time < timeTo && time > timeFrom && times > timeFrom){
                System.out.println("Doctor time is " + appointment.getDoctor().getWorkTimeFrom() + " and " + appointment.getDoctor().getWorkTimeTo());
                return false;
            }

            if(times > timeFrom){
                System.out.println("Doctor time is " + appointment.getDoctor().getWorkTimeFrom() + " and " + appointment.getDoctor().getWorkTimeTo() + " (+1 hour)");
                return false;
            }

        }

        System.out.println("Appointment validation is success");
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

    @Override
    public Appointment updateStatus(Long appointmentId, String status){

        Appointment appointment = appointmentRepository.getById(appointmentId);

        if(appointment == null){
            System.out.println("Appointment is empty!");
            return null;
        }

        if(status.equals(AppointmentStatus.WAITING_TO_ARRIVAL)){
            appointment.setStatus(String.valueOf(AppointmentStatus.WAITING_TO_ARRIVAL));
        } else if(status.equals(AppointmentStatus.APPOINTMENT_ACCEPTED)){
            appointment.setStatus(String.valueOf(AppointmentStatus.APPOINTMENT_ACCEPTED));
        } else{
            appointment.setStatus(String.valueOf(AppointmentStatus.PATIENT_DID_NOT_ARRIVE));
        }

        return appointmentRepository.saveAndFlush(appointment);

    }

    @Override
    public List<Appointment> getAllCustomerAppointment(Long customerId){

        List<Appointment> appointment = appointmentRepository.findAll();

        List<Appointment> appointments = new ArrayList<>();

        for(int i = 0; i < appointment.size(); i++){
            if(appointment.get(i).getCustomer().getCustomerId() == customerId){
                appointments.add(appointment.get(i));
            }
        }

        return appointments;
    }

    @Override
    public List<Appointment> getAllDoctorAppointment(Long doctorId){

        List<Appointment> appointment = appointmentRepository.findAll();

        List<Appointment> appointments = new ArrayList<>();

        for(int i = 0; i < appointment.size(); i++){
            if(appointment.get(i).getDoctor().getDoctorId() == doctorId){
                appointments.add(appointment.get(i));
            }
        }

        return appointments;
    }

    @Override
    public List<Appointment> getAllMedCenterAppointment(Long medCenterId){

        List<Appointment> appointment = appointmentRepository.findAll();

        List<Appointment> appointments = new ArrayList<>();

        for(int i = 0; i < appointment.size(); i++){
            if(appointment.get(i).getMedCenter().getMedCenterId() == medCenterId){
                appointments.add(appointment.get(i));
            }
        }

        return appointments;

    }

    @Override
    public List<Appointment> searchAppointmentStatus(String status){

        List<Appointment> appointment = appointmentRepository.findAll();

        List<Appointment> appointmentStatus = new ArrayList<>();

        for(int i = 0; i < appointment.size(); i++){
            if(appointment.get(i).getStatus().equals(status)){
                appointmentStatus.add(appointment.get(i));
            }
        }

        return appointmentStatus;
    }
}
