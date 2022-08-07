package com.application.puranh.controller;

import com.application.puranh.model.MediaUpload;
import com.application.puranh.model.User;
import com.application.puranh.service.CloudinaryService;
import com.application.puranh.service.EventService;
import com.application.puranh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/media")
public class CloudinaryController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final CloudinaryService cloudinaryService;

    @Autowired
    private final EventService eventService;


    private final Logger logger = LoggerFactory.getLogger(CloudinaryController.class);

    public CloudinaryController(UserService userService, CloudinaryService cloudinaryService, EventService eventService) {
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
        this.eventService = eventService;
    }

    /*
     * User's avatar controller
     */

    @PostMapping(path = "/avatar")
    public ResponseEntity<LinkedHashMap<String, Object>> uploadAvatar
            (@RequestParam("avaFile") MultipartFile file, Authentication authentication) throws IOException {
        logger.debug("Request to upload user's avatar");
        try {
            User user = userService.getByUserName(authentication.getName());
            String url = cloudinaryService.uploadFile(file);
            cloudinaryService.saveAvatarToDB(url, user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /*
     * Uploaded file controller
     */

    @PostMapping(path = "/image/{eventId}")
    public ResponseEntity<LinkedHashMap<String, Object>> uploadImage
            (@RequestParam("imageFile") MultipartFile file, @RequestParam("title") String title, Authentication authentication,@PathVariable("eventId")
            Long eventId) throws IOException {
        logger.debug("Request to upload image");
        User user = userService.getByUserName(authentication.getName());
        String url = cloudinaryService.uploadFile(file);
        try {
            cloudinaryService.saveMediaToDB(url, title, user, eventId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path ="/image/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") Long id) {
        logger.debug("Request to delete media");
        try {
            cloudinaryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(path = "/event-images/{eventId}")
    public ResponseEntity<List<MediaUpload>> getAllImages (@PathVariable("eventId") Long id) {
        logger.debug("Request to get all images");
        return ResponseEntity.ok(cloudinaryService.getAll(id));
    }
}
