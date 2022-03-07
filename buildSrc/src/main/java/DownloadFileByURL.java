import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Download file by url
 */
public class DownloadFileByURL extends DefaultTask {

    @Input
    String srcUrl;


    @OutputFile
    File target;

    @TaskAction
    void download(){
        try{
            URL url = new URL(srcUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            double fileSize = connection.getContentLength();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);

            byte[] buffer = new byte[1024];
            int read = 0;

            while ((read = bufferedInputStream.read(buffer, 0, 1024)) >= 0){
                bufferedOutputStream.write(buffer, 0, read);
            }

            bufferedInputStream.close();
            bufferedOutputStream.close();
            fileOutputStream.close();

            System.out.println("Complete");


        } catch (Exception e){
            System.out.println(e);
        }
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public File getTarget() {
        return target;
    }
}