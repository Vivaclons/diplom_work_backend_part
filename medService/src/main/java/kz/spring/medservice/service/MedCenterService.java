package kz.spring.medservice.service;

import kz.spring.medservice.model.Doctor;
import kz.spring.medservice.model.MedCenter;
import kz.spring.medservice.model.Specialty;
import kz.spring.medservice.repository.DoctorRepository;
import kz.spring.medservice.repository.MedCenterRepository;
import kz.spring.medservice.service.impl.IMedCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

@Service
public class MedCenterService implements IMedCenterService {

    @Autowired
    private MedCenterRepository medCenterRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<MedCenter> searchMedCenterByMedCenterName(String medCenterName) {
        return medCenterRepository.getMedCenterByMedCenterNameIsLike("%" + medCenterName + "%");
    }

    @Override
    public List<MedCenter> searchMedCenterByMedCenterAddress(String medCenterAddress) {
        return medCenterRepository.getMedCenterByMedCenterAddressIsLike("%" + medCenterAddress + "%");
    }

    @Override
    public MedCenter removeDoctor(String medCenterEmail, String doctorEmail) {

        MedCenter medCenter = medCenterRepository.findMedCenterByMedCenterEmail(medCenterEmail);

        boolean check = false;

        for(int i = 0; i < medCenter.getDoctors().size(); i++){
            if(medCenter.getDoctors().get(i).getDoctorEmail().equals(doctorEmail)){
                medCenter.getDoctors().remove(i);
                check = true;
                break;
            }
        }

        if(check){
            return medCenterRepository.saveAndFlush(medCenter);
        }
        return null;
    }

    @Override
    public MedCenter addDoctor(String medCenterEmail, String doctorEmail) {
        MedCenter medCenter = medCenterRepository.findMedCenterByMedCenterEmail(medCenterEmail);

        Doctor doctor = doctorRepository.findDoctorByDoctorEmail(doctorEmail);

        boolean check = false;

        if(doctor != null && medCenter != null && checkDoctor(medCenter, doctorEmail)){
            medCenter.getDoctors().add(doctor);
            if(!check){
                return medCenterRepository.saveAndFlush(medCenter);
            }
        }
        return null;
    }

    @Override
    public boolean checkDoctor(MedCenter medCenter, String doctorEmail){
        for(int i = 0; i < medCenter.getDoctors().size(); i++){
            if(medCenter.getDoctors().get(i).getDoctorEmail() == doctorEmail){
                System.out.println("This medical center has this doctor with id " + doctorEmail);
                return false;
            }
        }
        return true;
    }

    @Override
    public List<MedCenter> getAllFilter(String lat, String lon, int distancefrom, int distanceTo, int ratingFrom, int ratingTo, int priceFrom, int priceTo, String time) {
        List<MedCenter> medCenters = medCenterRepository.findAll();

        List<MedCenter> medCenterList = new ArrayList<>();

        double latd = Double.parseDouble(lat);
        double lond = Double.parseDouble(lon);
        double raduis = 6371.01;

        boolean sort = true;

        for(int i = 0; i < medCenters.size(); i++){
            double latd2 = Double.parseDouble(medCenters.get(i).getLatitude());
            double lond2 = Double.parseDouble(medCenters.get(i).getLongitude());

            double latDistance = Math.toRadians(latd - latd2);
            double lonDistance = Math.toRadians(lond - lond2);

            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(latd)) * Math.cos(Math.toRadians(latd2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            //exchange to meter
            double distance = raduis * c * 1000;

            distance = Math.pow(distance, 2);

            medCenters.get(i).setDistance(Math.sqrt(distance));
            medCenterRepository.saveAndFlush(medCenters.get(i));
        }

        while(sort){
            sort = false;
            for(int i = 1; i < medCenters.size(); i++){
                if(medCenters.get(i).getDistance() < medCenters.get(i-1).getDistance()){
                    swap(medCenters, i, i-1);
                    sort = true;
                }
            }
        }


        int timeF = Integer.parseInt(time.replace(':', '0'));
        int tFrom = 0;
        int tTo = 0;

        for(int i = 0; i < medCenters.size(); i++){
            if(distancefrom <= medCenters.get(i).getDistance() && distanceTo >= medCenters.get(i).getDistance()){
                if(priceFrom <= medCenters.get(i).getFees() && priceTo >= medCenters.get(i).getFees()){
                    if(ratingFrom <= medCenters.get(i).getRating() && ratingTo >= medCenters.get(i).getRating()){
                        tFrom = Integer.parseInt(medCenters.get(i).getWorkTimeFrom().replace(':', '0'));
                        tTo = Integer.parseInt(medCenters.get(i).getWorkTimeTo().replace(':', '0'));
                        if(timeF > tFrom && timeF < tTo){
                            medCenterList.add(medCenters.get(i));
                        }
                    }
                }
            }
        }
        return medCenterList;
    }

    @Override
    public List<MedCenter> getNearMedCenter(String lat, String lon) {
        double latd = Double.parseDouble(lat);
        double lond = Double.parseDouble(lon);
        double raduis = 6371.01;

        List<MedCenter> medCenters = medCenterRepository.findAll();

        boolean sort = true;

        for(int i = 0; i < medCenters.size(); i++){
            double latd2 = Double.parseDouble(medCenters.get(i).getLatitude());
            double lond2 = Double.parseDouble(medCenters.get(i).getLongitude());

            double latDistance = Math.toRadians(latd - latd2);
            double lonDistance = Math.toRadians(lond - lond2);

            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(latd)) * Math.cos(Math.toRadians(latd2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            //exchange to meter
            double distance = raduis * c * 1000;

            distance = Math.pow(distance, 2);

            medCenters.get(i).setDistance(Math.sqrt(distance));
            medCenterRepository.saveAndFlush(medCenters.get(i));
        }

        while(sort){
            sort = false;
            for(int i = 1; i < medCenters.size(); i++){
                if(medCenters.get(i).getDistance() < medCenters.get(i-1).getDistance()){
                    swap(medCenters, i, i-1);
                    sort = true;
                }
            }
        }

        return medCenters;
    }

    @Override
    public MedCenter updateDoctor(String medCenterEmail, String doctorEmail) {
        MedCenter medCenter = medCenterRepository.findMedCenterByMedCenterEmail(medCenterEmail);

        if(medCenter != null && medCenter.getMedCenterId() != null && medCenter.getMedCenterId() != 0L) {
            for (int i = 0; i < medCenter.getDoctors().size(); i++) {
                medCenter.getDoctors().get(i).setDoctorEmail(doctorEmail);
                return medCenterRepository.saveAndFlush(medCenter);
            }
        }
        return null;
    }

    @Override
    public MedCenter getById(Long id) {
        return medCenterRepository.getByMedCenterId(id);
    }

    @Override
    public MedCenter getByMedCenterName(String medCenterName) {
        return medCenterRepository.getMedCenterByMedCenterName(medCenterName);
    }

    @Override
    public MedCenter getByMedCenterAddress(String address){
        return medCenterRepository.getMedCenterByMedCenterAddress(address);
    }

    @Override
    public void update(MedCenter medCenter) {

        MedCenter updateMedCenter = medCenterRepository.findMedCenterByMedCenterEmail(medCenter.getMedCenterEmail());

        if(updateMedCenter != null){

            updateMedCenter.setMedCenterName(medCenter.getMedCenterName());
            updateMedCenter.setMedCenterAddress(medCenter.getMedCenterAddress());
            updateMedCenter.setMedCenterEmail(medCenter.getMedCenterEmail());
            updateMedCenter.setAbout(medCenter.getAbout());
            updateMedCenter.setFees(medCenter.getFees());
            updateMedCenter.setMedCenterNumber(medCenter.getMedCenterNumber());
            updateMedCenter.setMedCenterTelNumber(medCenter.getMedCenterTelNumber());
            updateMedCenter.setWorkTimeFrom(medCenter.getWorkTimeFrom());
            updateMedCenter.setWorkTimeTo(medCenter.getWorkTimeTo());

            medCenterRepository.saveAndFlush(medCenter);

        }else{
            System.out.println("Medical center is empty!");
        }
    }

    @Override
    public List<MedCenter> getAllMedCenter() {
        return medCenterRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        medCenterRepository.deleteById(id);
    }

    @Override
    public List<MedCenter> getAllMedCentersWorkTime(String date){

        List<MedCenter> medCenters = medCenterRepository.findAll();

        List<MedCenter> medCentersTime = new ArrayList<>();

        int timeNow = Integer.parseInt(date.replace(':', '0'));
        int timeFrom = 0;
        int timeTo = 0;

        for(int i = 0; i < medCenters.size(); i++){
            timeFrom = Integer.parseInt(medCenters.get(i).getWorkTimeFrom().replace(':', '0'));
            timeTo = Integer.parseInt(medCenters.get(i).getWorkTimeTo().replace(':', '0'));

            if(timeNow > timeFrom && timeNow < timeTo){
                medCentersTime.add(medCenters.get(i));
            }

        }
        return medCentersTime;
    }

    @Override
    public void rating(Long medCenterId, double rating){
        MedCenter medCenter = medCenterRepository.getByMedCenterId(medCenterId);

        if(medCenter.getPeopleCount() < 75 && rating < 4){
            rating += 1;
        }

        medCenter.setRating((medCenter.getRating() * medCenter.getPeopleCount() + rating)/(medCenter.getPeopleCount() + 1));
        medCenter.setPeopleCount(medCenter.getPeopleCount() + 1);

        medCenterRepository.saveAndFlush(medCenter);
    }
}
