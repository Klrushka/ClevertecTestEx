package ru.clevertec.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFileByURLPlugin implements Plugin<Project> {

    String srcUrl = "https://github.com/Klrushka/ClevertecTestEx/raw/gradle-task/bills/bill.pdf";

    File target = new File("bills/downloaded/DownloadedBill.pdf");

    @Override
    public void apply(Project project) {

        project.task("downloadFilePlugin").doLast(
                task -> {
                    try {
                        URL url = new URL(srcUrl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                             FileOutputStream fileOutputStream = new FileOutputStream(target);
                             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024)) {


                            byte[] buffer = new byte[1024];
                            int read = 0;

                            while ((read = bufferedInputStream.read(buffer, 0, 1024)) >= 0) {
                                bufferedOutputStream.write(buffer, 0, read);
                            }

                            System.out.println("Complete");
                        }


                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    project.getTasks().getAt("downloadFilePlugin").setDescription("Download file by URL");
                    project.getTasks().getAt("downloadFilePlugin").setGroup("Download by URL");
                }
        );

    }

}
