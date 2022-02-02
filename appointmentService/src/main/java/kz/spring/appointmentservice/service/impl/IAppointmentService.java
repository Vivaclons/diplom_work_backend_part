package kz.spring.appointmentservice.service.impl;

import kz.spring.appointmentservice.model.Appointment;

import java.util.Date;
import java.util.List;

public interface IAppointmentService {
    Appointment getById(Long id);
    Appointment getByDoctorId(Long doctorId);
    Appointment getByCustomerId(Long customerId);
    Appointment getByMedCenterId(Long medCenterId);
    Appointment getByDate(Date date);
    void update(Appointment appointment);
    List<Appointment> getAllAppointmentBy();
    void DeleteById(Long id);
}
