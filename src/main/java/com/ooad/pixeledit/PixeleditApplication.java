package com.ooad.pixeledit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.Resource;
import com.ooad.pixeledit.service.FilesStorageService;

@SpringBootApplication
public class PixeleditApplication {
	@Resource
	FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(PixeleditApplication.class, args);
	}
}

