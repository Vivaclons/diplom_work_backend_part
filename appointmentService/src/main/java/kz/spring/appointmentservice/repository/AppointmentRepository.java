package kz.spring.appointmentservice.repository;

import kz.spring.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment getAppointmentById(Long id);
//    Appointment getByAppointmentId(Long customerId);
//    Appointment getByDoctorId(Long doctorId);
//    Appointment getByMedCenterId(Long medCenterId);
    Appointment getAppointmentByDate(Date date);
}
