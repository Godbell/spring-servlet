package fileupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    private static final String FILE_UPLOAD_DIR = "/Users/jjongha134/Dev/etc";

    public String restore(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        File fileUploadDir = new File(FILE_UPLOAD_DIR);

        if (!fileUploadDir.exists() && !fileUploadDir.mkdirs()) {
            return null;
        }

        String filename = Optional.ofNullable(file.getOriginalFilename()).orElse("");
        int extnameIndex = Optional.ofNullable(filename.lastIndexOf('.')).orElse(-100) + 1;

        if (extnameIndex < 0) {
            return null;
        }

        System.out.println(filename);

        String savedFilename = createFilename(filename.substring(extnameIndex));
        byte[] data = file.getBytes();

        FileOutputStream fileOutputStream = new FileOutputStream(
            FILE_UPLOAD_DIR + "/" + savedFilename
        );
        fileOutputStream.write(data);
        fileOutputStream.close();

        return "images/" + savedFilename;
    }

    private String createFilename(String extName) {
        Calendar calendar = Calendar.getInstance();
        return "" + calendar.get(Calendar.YEAR)
               + calendar.get(Calendar.MONTH)
               + calendar.get(Calendar.DATE)
               + calendar.get(Calendar.HOUR)
               + calendar.get(Calendar.MINUTE)
               + calendar.get(Calendar.SECOND)
               + calendar.get(Calendar.MILLISECOND)
               + "." + extName;
    }
}
