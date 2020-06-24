package jdbc.Lesson4.HW;

import java.util.Arrays;

public class Storage {
    public long id;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageMaxSize;

    public Storage(long id, String[] formatsSupported, String storageCountry, long storageMaxSize) {
        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public long getId() {
        return id;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public long getStorageMaxSize() {
        return storageMaxSize;
    }

    public String formatsSupportedToString() {
        String formats = Arrays.toString(formatsSupported);
        return formats.substring(1, formats.length() - 2);
    }

    public boolean isFormatSupported(String format) {
        for (String f : formatsSupported) {
            if (f.equals(format))
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize='" + storageMaxSize + '\'' +
                '}';
    }
}
