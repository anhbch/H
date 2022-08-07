package com.application.puranh.repository;

import com.application.puranh.model.Event;
import com.application.puranh.model.MediaUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaUploadRepository extends JpaRepository<MediaUpload, Long> {

    List<MediaUpload> findByEvent(Event event);

    MediaUpload findByMediaId(Long id);
}
