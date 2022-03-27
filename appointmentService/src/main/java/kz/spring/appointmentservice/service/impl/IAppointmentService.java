package kz.spring.appointmentservice.service.impl;

import kz.spring.appointmentservice.model.Appointment;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {
    Appointment getById(Long id);
    Appointment updateDoctor(Long appointmentId, Long doctorId);
    Appointment updateMedCenter(Long appointmentId, Long medCenterId);
    Appointment updateCustomer(Long appointmentId, Long customerId);
    Appointment getByDate(Date date);
    void create(Appointment appointment, Long medCenterId, Long doctorId, Long customerId);
    void update(Appointment appointment, Long medCenterId, Long appointmentId, Long doctorId, Long customerId);
    List<Appointment> getAllAppointment();
    void DeleteById(Long id);
    Appointment updateStatus(Long appointmentId, String status);
    List<Appointment> getAllCustomerAppointment(Long customerId);
    List<Appointment> getAllDoctorAppointment(Long doctorId);
    List<Appointment> getAllMedCenterAppointment(Long medCenterId);
    List<Appointment> searchAppointmentStatus(String status);
}
