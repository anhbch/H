package com.application.puranh.service;

import com.application.puranh.model.MediaUpload;
import com.application.puranh.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CloudinaryService {
    String uploadFile(MultipartFile gifFile);

    void saveAvatarToDB(String url, User user);


    void saveMediaToDB(String url, String title, User user, Long eventId);

    void delete(Long id);

    List<MediaUpload> getAll(Long id);
}
