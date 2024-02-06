package com.plantapp.plantapp.profile_image.service;

import com.plantapp.plantapp.user.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IProfileImageService {
    String uploadImage(MultipartFile file, User user) throws IOException;
    byte[] getImage(User user);
}
