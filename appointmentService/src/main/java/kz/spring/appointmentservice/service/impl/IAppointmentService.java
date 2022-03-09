package kz.spring.appointmentservice.service.impl;

import kz.spring.appointmentservice.model.Appointment;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {
    Appointment getById(Long id);
    Appointment addAppointment(Long medCenterId, Long appointmentId, Long doctorId, Long customerId);
    Appointment updateDoctor(Long appointmentId, Long doctorId);
    Appointment updateMedCenter(Long appointmentId, Long medCenterId);
    Appointment updateCustomer(Long appointmentId, Long customerId);
    Appointment getByDate(Date date);
    void create(Appointment appointment, Long medCenterId, Long appointmentId, Long doctorId, Long customerId);
    void update(Appointment appointment, Long medCenterId, Long appointmentId, Long doctorId, Long customerId);
    List<Appointment> getAllAppointment();
    void DeleteById(Long id);
}
