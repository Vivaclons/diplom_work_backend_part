package kz.spring.medservice.service.impl;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.MedCenter;

import java.util.List;

public interface IMedCenterService {
    List<MedCenter> searchMedCenterByMedCenterName(String medCenterName);
    List<MedCenter> searchMedCenterByMedCenterAddress(String medCenterAddress);
    MedCenter removeDoctor(Long medCenterId, Long doctorId);
    MedCenter addDoctor(Long medCenterId, Long doctorId);
    MedCenter updateDoctor(Long medCenterId, Long doctorId);
    List<MedCenter> getAllMedCentersWorkTime(String date);
    MedCenter getById(Long id);
    MedCenter getByMedCenterName(String medCenterName);
    MedCenter getByMedCenterAddress(String address);
    void update(MedCenter medCenter);
    List<MedCenter> getAllMedCenter();
    void DeleteById(Long id);
    void rating(Long medCenterId, double rating);
    boolean checkDoctor(MedCenter medCenter, Long doctorId);

    List<MedCenter> getAllFilter(String lat, String lon, int distancefrom, int distanceTo, int ratingFrom, int ratingTo, int priceFrom, int priceTo, String time);

    List<MedCenter> getNearMedCenter(String lat, String lon);
}
