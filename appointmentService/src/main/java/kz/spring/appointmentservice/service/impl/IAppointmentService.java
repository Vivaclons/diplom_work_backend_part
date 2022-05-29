package kz.spring.appointmentservice.service.impl;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.appointmentservice.model.Customer;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {
    Appointment getById(Long id);
    Appointment updateDoctor(Long appointmentId, Long doctorId);
    Appointment updateMedCenter(Long appointmentId, Long medCenterId);
    Appointment updateCustomer(Long appointmentId, Long customerId);
    Appointment getByDate(Date date);
    void create(Appointment appointment, Long doctorId, String username);
    void update(Appointment appointment, Long medCenterId, Long appointmentId, Long doctorId, Long customerId);
    List<Appointment> getAllAppointment();
    void DeleteById(Long id);
    Appointment updateStatus(Long appointmentId, String status);
    List<Appointment> getAllCustomerAppointment(String email);
    List<Appointment> getAllDoctorAppointment(String email);
    List<Appointment> getAllMedCenterAppointment(String email);
    List<Appointment> searchAppointmentStatus(String status);
    void appointmentNotification(Appointment appointment);
}
