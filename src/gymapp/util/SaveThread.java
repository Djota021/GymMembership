package gymapp.util;

import gymapp.repository.FileRepository;

public class SaveThread<T> extends Thread {

    private FileRepository<T> repository;
    private T object;

    public SaveThread(FileRepository<T> repository, T object) {
        this.repository = repository;
        this.object = object;
    }

    @Override
    public void run() {
        repository.save(object);
    }
}
