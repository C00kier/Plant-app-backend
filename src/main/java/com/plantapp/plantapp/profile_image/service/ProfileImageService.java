package com.plantapp.plantapp.profile_image.service;

import com.plantapp.plantapp.profile_image.model.ProfileImage;
import com.plantapp.plantapp.profile_image.repository.ProfileImageRepository;
import com.plantapp.plantapp.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ProfileImageService implements IProfileImageService{

    private final ProfileImageRepository profileImageRepository;

    public ProfileImageService(ProfileImageRepository profileImageRepository) {
        this.profileImageRepository = profileImageRepository;
    }

    @Transactional
    public String uploadImage(MultipartFile file, User user) throws IOException {
        Optional<ProfileImage> optionalProfileImage = profileImageRepository.findByName(user.getUsername());

        if(optionalProfileImage.isPresent()){
            ProfileImage profileImage = optionalProfileImage.get();
            profileImage.setType(file.getContentType());
            profileImage.setImageData(compressImage(file.getBytes()));
            profileImageRepository.save(profileImage);
            return "Profile image updated.";
        }

        profileImageRepository.save(ProfileImage.builder()
                .name(user.getUsername())
                .type(file.getContentType())
                .imageData(compressImage(file.getBytes()))
                .user(user)
                .build());

        return "Profile image uploaded successfully.";
    }

    @Transactional
    public byte[] getImage(String username) {
        Optional<ProfileImage> optionalProfileImage = profileImageRepository.findByName(username);
        byte[] image = null;
        if (optionalProfileImage.isPresent()) {
            image = decompressImage(optionalProfileImage.get().getImageData());
        }
        return image;
    }

    private byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    private byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}

