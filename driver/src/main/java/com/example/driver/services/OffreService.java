package com.example.driver.services;

import com.example.driver.models.Offre;
import com.example.driver.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public Offre addOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    public Offre updateOffre(Long id, Offre offre) {
        Offre existingOffre = offreRepository.findById(id).orElseThrow(() -> new RuntimeException("Offre not found"));
        existingOffre.setDepart(offre.getDepart());
        existingOffre.setDestination(offre.getDestination());
        existingOffre.setDate(offre.getDate());
        existingOffre.setPrix(offre.getPrix());
        existingOffre.setPlacesDisponibles(offre.getPlacesDisponibles());
        return offreRepository.save(existingOffre);
    }

    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);
    }
}
