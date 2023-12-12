package edu.spring.mall.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class ImageServiceImple implements ImageService {
	private final Logger logger = LoggerFactory.getLogger(ImageServiceImple.class);
	    
    private final String BUCKETNAME = "edu-mall-img";
    
    private Storage storage;
    
    public ImageServiceImple() throws IOException {
        // 서비스 계정 키 파일 경로
    	String jsonPath = "C:\\jsonKey\\pure-heuristic-404308-b6290aeba841.json";

        // 스토리지 클라이언트 초기화
        storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(jsonPath)))
                .build()
                .getService();
    }
    
    
    @Override
    public String uploadFile(MultipartFile file, String imagePath) throws IOException {
    	logger.info("uploadFile 호출");
            BlobId blobId = BlobId.of(BUCKETNAME, imagePath);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
            try (WriteChannel writer = storage.writer(blobInfo)) {
                try (InputStream input = file.getInputStream()) {
                    byte[] buffer = new byte[1024];
                    int limit;
                    while ((limit = input.read(buffer)) >= 0) {
                        writer.write(ByteBuffer.wrap(buffer, 0, limit));
                    }
                }
            
            return String.format("File %s uploaded to bucket %s as %s", imagePath, BUCKETNAME, imagePath);
        }
    }
    
	@Override
	public String noticeImg(MultipartFile file, String imagePath) throws IOException {
		logger.info("noticeImg 호출");
	    
	    BlobId blobId = BlobId.of(BUCKETNAME, imagePath);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
	    try (WriteChannel writer = storage.writer(blobInfo)) {
	        try (InputStream input = file.getInputStream()) {
	            byte[] buffer = new byte[1024];
	            int limit;
	            while ((limit = input.read(buffer)) >= 0) {
	                writer.write(ByteBuffer.wrap(buffer, 0, limit));
	            }
	        }
	    }
	    
	    // 업로드된 이미지에 대한 URL을 반환해야 합니다.
	    // 여기에서는 Cloud Storage의 객체 URL을 반환해야 합니다.
	    // 예: "https://storage.googleapis.com/edu-mall-img/" + imagePath
	    String imageUrl = "https://storage.googleapis.com/" + BUCKETNAME + "/" + imagePath;
	    return imageUrl;
	}
	



}
