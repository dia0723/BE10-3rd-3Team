package com.example.place_data.repository;

import com.example.place_data.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByLocation(String location);

    List<Place> findByTitleContainingAndCategoryAndLocation(String title, String category, String location);

    List<Place> findByTitleContainingAndCategory(String title, String category);

    List<Place> findByTitleContainingAndLocation(String title, String location);

    List<Place> findByTitleContaining(String title);

    List<Place> findByCategoryAndLocation(String category, String location);

    List<Place> findByCategory(String category);
}
