package kz.spring.appointmentservice.repository;

import kz.spring.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment getById(Long id);
//    Appointment getByAppointmentId(Long customerId);
//    Appointment getByDoctorId(Long doctorId);
//    Appointment getByMedCenterId(Long medCenterId);
    Appointment getByDate(Date date);
}
