package Lesson4.HW;

public class File {
    public long id;
    public String name;
    public String format;
    public long size;
    public Long storageId;

    public File(long id, String name, String format, long size, Long storageId) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
        this.storageId = storageId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public long getSize() {
        return size;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storage=" + storageId +
                '}';
    }
}
