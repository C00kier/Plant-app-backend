package com.plantapp.plantapp.profile_image.repository;

import com.plantapp.plantapp.profile_image.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    Optional<ProfileImage> findByName(String name);
}
