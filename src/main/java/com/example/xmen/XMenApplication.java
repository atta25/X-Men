package com.example.xmen;

import com.example.xmen.detector.Detector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class XMenApplication {
	private static Detector detector;

	public XMenApplication(Detector detector) {
		this.detector = detector;
	}

	public static void main(String[] args) {
		SpringApplication.run(XMenApplication.class, args);
		detector.loadCriteria();
	}
}
