package kz.spring.medservice.service.impl;

import kz.spring.medservice.model.MedCenter;

import java.util.List;

public interface IMedCenterService {
    MedCenter getById(Long id);
    MedCenter getByMedCenterName(String medCenterName);
    MedCenter getByMedCenterAddress(String address);
    void update(MedCenter medCenter);
    List<MedCenter> getAllMedCenterBy();
    void DeleteById(Long id);
}
