package com.bdrecruit.springbootmsbdrc;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author salomao.junior 2022.03.15 20:38
 * Spring application starter
 *
 */

@SpringBootApplication
public class SpringbootMsBdrcApplication {

    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = SpringbootMsBdrcApplication.class.getClassLoader();

        // get firebase secret key file on resources
        File file = new File(classLoader.getResource("serviceAccountKey.json").getFile());
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

        // get firebase options
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                //.setDatabaseUrl("s")
                .build();

        // firebase application initialization
        FirebaseApp.initializeApp(options);

        // runs our spring application
        SpringApplication.run(SpringbootMsBdrcApplication.class, args);
    }

}
