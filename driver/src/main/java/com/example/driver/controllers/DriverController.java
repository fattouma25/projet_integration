package com.example.driver.controllers;

import com.example.driver.models.BookingRequest;
import com.example.driver.models.Driver;
import com.example.driver.models.Offre;
import com.example.driver.services.DriverService;
import com.example.driver.services.OffreService;
import com.example.driver.repository.DriverRepository;
import com.example.driver.repository.OffreRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 import com.example.driver.repository.PaiementRepository;
import com.example.driver.repository.TrajetRepository;
import com.example.driver.models.Paiement;
import com.example.driver.models.Trajet;


// import com.application.model.BookingRequest;

// import com.application.model.Trajet;
// import com.application.repository.TrajetRepository;

@RestController
@RequestMapping("/driver")
@CrossOrigin(origins = "http://localhost:4200") // Permet uniquement localhost:4200
public class DriverController {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private DriverService driverRegisterService;

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OffreService offreService;

    @Autowired
    private TrajetRepository trajetRepository;

    @PostMapping("/registerdriver")
    public Driver registerDriver(@RequestBody Driver driver) throws Exception {
        String currEmail = driver.getEmail();
        if (currEmail != null || !"".equals(currEmail)) {
            Driver driverObj = driverRegisterService.fetchDriverByEmail(currEmail);
            if (driverObj != null) {
                throw new Exception("Driver with " + currEmail + " already exists !!!");
            }
        }
        Driver driverObj = null;
        driverObj = driverRegisterService.saveDriver(driver);
        return driverObj;
    }

    @PostMapping("/logindriver")
    public Driver loginDriver(@RequestBody Driver driver) throws Exception {
        String currEmail = driver.getEmail();
        String currPassword = driver.getPassword();

        Driver driverObj = null;
        if (currEmail != null && currPassword != null) {
            driverObj = driverRegisterService.fetchDriverByEmailAndPassword(currEmail, currPassword);
        }
        if (driverObj == null) {
            throw new Exception("User does not exists!!! Please enter valid credentials...");
        }
        return driverObj;
    }

    @PostMapping("/addDriver")
    public Driver addNewDriver(@RequestBody Driver driver) throws Exception {
        Driver driverObj = null;
        driverObj = driverRegisterService.saveDriver(driver);
        return driverObj;
    }

    @GetMapping("/driverlist")
    public ResponseEntity<List<Driver>> getDriversWithAcceptOrFalseStatus() throws Exception {
        // Fetch all drivers from the service
        List<Driver> drivers = driverRegisterService.getAllDrivers();

        // Return the filtered list
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @PostMapping("/searchDriversByNameandAddress")
    public ResponseEntity<List<Driver>> searchDriversByNameandAddress(
            @RequestBody Map<String, String> obj) throws Exception {
        List<Driver> drivers = driverRegisterService.getDriversByNameandAddress(obj.get("drivername"),
                obj.get("address"));
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    @GetMapping("/gettotaldrivers")
    public ResponseEntity<List<Integer>> getTotalDrivers() throws Exception {
        List<Driver> drivers = driverRegisterService.getAllDrivers();
        List<Integer> al = new ArrayList<>();
        al.add(drivers.size());
        return new ResponseEntity<List<Integer>>(al, HttpStatus.OK);
    }

    @GetMapping("/driverlistbyemail/{email}")
    public ResponseEntity<List<Driver>> getRequestHistoryByEmail(@PathVariable String email) throws Exception {
        System.out.print("requesting");
        List<Driver> history = driverRegisterService.getDriverListByEmail(email);
        return new ResponseEntity<List<Driver>>(history, HttpStatus.OK);
    }

    @GetMapping("/driverProfileDetails/{email}")
    public ResponseEntity<List<Driver>> getDriverProfileDetails(@PathVariable String email) throws Exception {
        List<Driver> drivers = driverRegisterService.fetchProfileByEmail(email);
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    @PutMapping("/updatedriver")
    public ResponseEntity<Driver> updateDriverProfile(@RequestBody Driver driver) throws Exception {
        Driver driverobj = driverRegisterService.updateDriverProfile(driver);
        return new ResponseEntity<Driver>(driverobj, HttpStatus.OK);
    }

    @GetMapping("/getAllOffres")
    public ResponseEntity<List<Offre>> getAllOffres() {
        return ResponseEntity.ok(offreService.getAllOffres());
    }

    @PostMapping("/addOffre")
    public ResponseEntity<Map<String, String>> addOffre(@RequestBody Offre offre) {
        Map<String, String> response = new HashMap<>();

        if (offre.getDriver() == null || offre.getDriver().getEmail() == null) {
            response.put("message", "Driver information is required.");
            return ResponseEntity.badRequest().body(response);
        }

        Driver driverOptional = driverRepository.findByEmail(offre.getDriver().getEmail());
        if (driverOptional == null) {
            response.put("message", "Driver not found.");
            return ResponseEntity.badRequest().body(response);
        }

        // Associer le driver existant à l'offre
        offre.setDriver(driverOptional);
        offreRepository.save(offre);

        response.put("message", "Offre added successfully.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateOffre/{id}")
    public ResponseEntity<String> updateOffre(@PathVariable Long id, @RequestBody Offre offreDetails) {
        return offreRepository.findById(id).map(existingOffre -> {
            // Mettre à jour les champs principaux de l'offre
            existingOffre.setDepart(offreDetails.getDepart());
            existingOffre.setDestination(offreDetails.getDestination());
            existingOffre.setPrix(offreDetails.getPrix());
            existingOffre.setDate(offreDetails.getDate());
            existingOffre.setPlacesDisponibles(offreDetails.getPlacesDisponibles());

            // Vérifier et associer le driver si présent dans les détails
            if (offreDetails.getDriver() != null && offreDetails.getDriver().getEmail() != null) {
                Driver driverOptional = driverRepository.findByEmail(offreDetails.getDriver().getEmail());
                if (driverOptional != null) {
                    existingOffre.setDriver(driverOptional);
                } else {
                    return ResponseEntity.badRequest().body("Driver not found for the provided email.");
                }
            }

            offreRepository.save(existingOffre);
            return ResponseEntity.ok("Offre updated successfully.");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("deleteOffre/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        offreService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listOffres")
    public List<Offre> listAllOffres() {
    List<Offre> offres = offreRepository.findAll();

    return offres;
    }

    @GetMapping("/offre/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable Long id) {
        Optional<Offre> offre = offreRepository.findById(id);
        if (offre.isPresent()) {
            return ResponseEntity.ok(offre.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/driverPaiments/{email}")
    public ResponseEntity<List<Paiement>> getPaiementsByDriver(@PathVariable String email) {
        List<Paiement> paiements = paiementRepository.findByDriverEmail(email);
        return ResponseEntity.ok(paiements);
    }

    @PostMapping("/addPaiement")
    public ResponseEntity<Map<String, String>> addPaiement(@RequestBody Map<String, Object> paiementData) {
        // Vérification des paramètres
        if (!paiementData.containsKey("driver") || !paiementData.containsKey("montant")) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "L'email du conducteur et le montant sont requis."));
        }

        String driverEmail = (String) paiementData.get("driver");
        String nomUtilisateur = (String) paiementData.get("nomUtilisateur");
        Double montant = Double.parseDouble(paiementData.get("montant").toString());
        String datePaiement = (String) paiementData.get("datePaiement"); // Format attendu : YYYY-MM-DD

        // Vérifier si le Driver existe par email
        Driver driver = driverRepository.findByEmail(driverEmail);
        if (driver == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Le conducteur avec cet email n'existe pas."));
        }

        // Créer un nouvel objet Paiement
        Paiement paiement = new Paiement();
        paiement.setDriver(driver);
        paiement.setNomUtilisateur(nomUtilisateur);
        paiement.setMontant(montant);
        paiement.setDatePaiement(LocalDate.parse(datePaiement)); // Convertir la date si nécessaire

        // Sauvegarder le paiement
        paiementRepository.save(paiement);

        return ResponseEntity.ok(Map.of("message", "Paiement effectué avec succès."));
    }

    // Endpoint to fetch offer details by offreId

    @PostMapping("/choisirOffre/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> bookTrajet(@PathVariable Long id, @RequestBody BookingRequest bookingRequest) {
        Optional<Offre> offreOptional = offreRepository.findById(id);
        if (!offreOptional.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "L'offre n'existe pas."));
        }

        Offre offre = offreOptional.get();

        // Check if enough places are available
        if (offre.getPlacesDisponibles() < bookingRequest.getNombreDePassagers()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "Il n'y a pas assez de places disponibles."));
        }

        // Create a new Trajet (booking)
        Trajet trajet = new Trajet();
        trajet.setOffre(offre);
        trajet.setNomUtilisateur(bookingRequest.getNomUtilisateur());
        trajet.setEmailUtilisateur(bookingRequest.getEmailUtilisateur());
        trajet.setNombreDePassagers(bookingRequest.getNombreDePassagers());
        trajet.setDateTrajet(bookingRequest.getDateTrajet());
        trajet.setMessage(bookingRequest.getMessage());

        // Save the trajet to the database
        trajetRepository.save(trajet);

        // Update the available places in the offer
        offre.setPlacesDisponibles(offre.getPlacesDisponibles() - bookingRequest.getNombreDePassagers());
        offreRepository.save(offre);

        // Return a success response as JSON
        return ResponseEntity.ok(Collections.singletonMap("message", "Réservation effectuée avec succès."));
    }


}
