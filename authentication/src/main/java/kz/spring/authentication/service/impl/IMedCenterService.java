package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.MedCenter;

import java.util.List;

public interface IMedCenterService {
    MedCenter getMedCenterById(Long medCenterId);

    MedCenter getByMedCenterName(String medCenterName);

    void updateMed(MedCenter medCenter);

    List<MedCenter> getAllMedCentersBy();

    void DeleteByIDMed(Long medCenterId);

    boolean addMedCenter(MedCenter medCenter);

    boolean activateMedCenter(String code);

    void updatePasswordMedCenter(String email, String password);

    String forgetPasswordMedCenter(String email);

}
