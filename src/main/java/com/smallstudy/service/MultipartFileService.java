package com.smallstudy.service;

import com.smallstudy.dto.FileDTO;
import com.smallstudy.error.UnSupportedImageFileExtensionException;
import com.smallstudy.error.UnSupportedImageFileTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MultipartFileService {

    @Value("${file.dir}")
    private String fileDir;
    private static final List<String> ALLOWED_CONTENT_TYPE = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpeg", "jpg", "png", "gif");

    public FileDTO saveImgFile(MultipartFile file) throws UnSupportedImageFileTypeException, IOException {

        if(file.isEmpty())
            throw new FileNotFoundException("파일이 존재하지 않습니다.");

        if(!ALLOWED_CONTENT_TYPE.contains(file.getContentType()))
            throw new UnSupportedImageFileTypeException("   The file type " + file.getContentType() + " is not supported");

        String originalFilename = file.getOriginalFilename();
        log.info("file Name {}", originalFilename);

        int pos = originalFilename.lastIndexOf(".");

        if(pos == -1)
            throw new UnSupportedImageFileExtensionException("UnSupportedImageFileExtensionException");

        String ext = originalFilename.substring(pos + 1);

        if(!ALLOWED_EXTENSIONS.contains(ext))
            throw new UnSupportedImageFileExtensionException("UnSupportedImageFileExtensionException");

        LocalDateTime now = LocalDateTime.now();
        Path base = Paths.get(fileDir);
        Path path = Paths.get(String.valueOf(now.getYear()), String.valueOf(now.getMonthValue()), String.valueOf(now.getDayOfMonth()));
        Path fullPath = base.resolve(path);

        if(!Files.exists(fullPath))
            Files.createDirectories(fullPath);

        String uuid = UUID.randomUUID() + "." + ext;
        Path fullPathAndFile = fullPath.resolve(uuid);
        file.transferTo(fullPathAndFile.toFile());

        return new FileDTO(originalFilename, uuid, path.toString());
    }


}
