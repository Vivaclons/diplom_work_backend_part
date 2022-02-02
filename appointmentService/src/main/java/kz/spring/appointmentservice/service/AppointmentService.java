package kz.spring.appointmentservice.service;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.appointmentservice.repository.AppointmentRepository;
import kz.spring.appointmentservice.service.impl.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.getById(id);
    }

    @Override
    public Appointment getByDoctorId(Long doctorId) {
        return appointmentRepository.getByDoctorId(doctorId);
    }

    @Override
    public Appointment getByCustomerId(Long customerId) {
        return appointmentRepository.getByCustomerId(customerId);
    }

    @Override
    public Appointment getByMedCenterId(Long medCenterId) {
        return appointmentRepository.getByMedCenterId(medCenterId);
    }

    @Override
    public Appointment getByDate(Date date) {
        return appointmentRepository.getByDate(date);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentRepository.saveAndFlush(appointment);
    }

    @Override
    public List<Appointment> getAllAppointmentBy() {
        return appointmentRepository.getAppointmentBy();
    }

    @Override
    public void DeleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
