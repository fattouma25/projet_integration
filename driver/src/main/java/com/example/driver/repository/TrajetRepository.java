package com.example.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.driver.models.Trajet;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
}
