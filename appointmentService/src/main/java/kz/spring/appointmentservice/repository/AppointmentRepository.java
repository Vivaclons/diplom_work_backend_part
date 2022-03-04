package kz.spring.appointmentservice.repository;

import kz.spring.appointmentservice.model.Appointment;
import kz.spring.appointmentservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment getAppointmentByAppointmentId(Long id);
    Appointment getAppointmentByDate(Date date);
    List<Appointment> findAppointmentByCustomer_CustomerName(String name);
    List<Appointment> findAppointmentByCustomer_CustomerId(Long id);
    Appointment addAppointment(Long medCenterId, Long appointmentId, Long doctorId, Long customerId);
}
