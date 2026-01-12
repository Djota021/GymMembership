package gymapp.repository;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class FileRepository<T> {

    private String fileName;

    public FileRepository(String fileName) {
        this.fileName = fileName;
    }

    public void save(T object) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName, true))) {

            oos.writeObject(object);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
