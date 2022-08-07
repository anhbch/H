package com.application.puranh.service.implement;

import com.application.puranh.model.Event;
import com.application.puranh.model.MediaUpload;
import com.application.puranh.model.User;
import com.application.puranh.repository.EventRepository;
import com.application.puranh.repository.MediaUploadRepository;
import com.application.puranh.repository.UserRepository;
import com.application.puranh.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private final Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MediaUploadRepository mediaUploadRepository;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile image) {
        try {
            File uploadedFile = convertMultiPartToFile(image);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            } else
                System.out.println("File doesn't exist");
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAvatarToDB(String url, User user) {
        user.setAvaUrl(url);
        userRepository.save(user);
    }


    @Override
    public void saveMediaToDB(String url, String title, User user, Long evenId) {
        Event event = eventRepository.findByEventId(evenId);
        MediaUpload media = new MediaUpload();
        media.setEvent(event);
        media.setTitle(title);
        media.setUrl(url);
        eventRepository.save(event);
        mediaUploadRepository.save(media);
    }

    @Override
    public void delete(Long id) {
        MediaUpload media = mediaUploadRepository.findByMediaId(id);
        mediaUploadRepository.delete(media);
    }

    @Override
    public List<MediaUpload> getAll(Long id) {
        Event event = eventRepository.findByEventId(id);
        return mediaUploadRepository.findByEvent(event);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
