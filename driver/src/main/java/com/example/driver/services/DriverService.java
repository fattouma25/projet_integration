package com.example.driver.services;


import com.example.driver.models.Driver;
import com.example.driver.repository.DriverRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.application.model.Offre;
// import com.application.repository.OffreRepository;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRegistrationRepo;

    // @Autowired
    // private OffreRepository offreRepository;

    public Driver saveDriver(Driver driver) {
        return driverRegistrationRepo.save(driver);
    }

    public Driver updateDriverProfile(Driver driver) {
        return driverRegistrationRepo.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return (List<Driver>) driverRegistrationRepo.findAll();
    }

    public void updateStatus(String email) {
        driverRegistrationRepo.updateStatus(email);
    }

    public void rejectStatus(String email) {
        driverRegistrationRepo.rejectStatus(email);
        System.out.print("rejected");
    }


    public List<Driver> getDriverListByEmail(String email) {
        return (List<Driver>) driverRegistrationRepo.findDriverListByEmail(email);
    }

    public Driver fetchDriverByEmail(String email) {
        return driverRegistrationRepo.findByEmail(email);
    }

    public Driver fetchDriverByDrivername(String drivername) {
        return driverRegistrationRepo.findByDrivername(drivername);
    }

    public Driver fetchDriverByEmailAndPassword(String email, String password) {
        return driverRegistrationRepo.findByEmailAndPassword(email, password);
    }

    public List<Driver> fetchProfileByEmail(String email) {
        return (List<Driver>) driverRegistrationRepo.findProfileByEmail(email);
    }

    public List<Driver> getDriversByNameandAddress(String drivername, String address) {
        return (List<Driver>) driverRegistrationRepo.findDriversByNameAndAddress(drivername, address);
    }

    // public List<Offre> getAllOffres() {
    //     return offreRepository.findAll();
    // }

    // public Offre addOffre(Offre offre) {
    //     return offreRepository.save(offre);
    // }

    // public Offre updateOffre(Long id, Offre offre) {
    //     Offre existingOffre = offreRepository.findById(id).orElseThrow(() -> new RuntimeException("Offre not found"));
    //     existingOffre.setDepart(offre.getDepart());
    //     existingOffre.setDestination(offre.getDestination());
    //     existingOffre.setDate(offre.getDate());
    //     existingOffre.setPrix(offre.getPrix());
    //     existingOffre.setPlacesDisponibles(offre.getPlacesDisponibles());
    //     return offreRepository.save(existingOffre);
    // }

    // public void deleteOffre(Long id) {
    //     offreRepository.deleteById(id);
    // }

}
