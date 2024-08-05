package pj.backend.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pj.backend.service.FileService;

@Service
public class FileServiceImplement implements FileService {

  @Value("${file.path}")
  private String filePath;

  @Value("${file.url}")
  private String fileUrl;
  
  @Override
  public String upload(MultipartFile file) {

    if (file.isEmpty())
      return null;
      
    String originalFileName = file.getOriginalFilename();
    String extention = originalFileName.substring(originalFileName.lastIndexOf("."));
    String uuid = UUID.randomUUID().toString();
    String saveFileName = uuid + extention;
    String savePath = filePath + saveFileName;

    try {
      file.transferTo(new File(savePath));
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }

    String url = fileUrl + saveFileName;
    return url;
  } // upload

  @Override
  public Resource getImage(String fileName) {

    Resource resource = null;

    try {
      resource = new UrlResource("file:" + filePath + fileName);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }

    return resource;
  } // getImage
} // FileServiceImplement
