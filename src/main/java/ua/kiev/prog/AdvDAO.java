package ua.kiev.prog;

import java.util.List;

public interface AdvDAO {
    List<Advertisement> list(String pattern);
	boolean add(Advertisement...adv);
    boolean delete(long id);
    byte[] getPhoto(long id);
    byte[] getPhotoFromBacket(long id);
    List<Advertisement> getAll();
    List<AdvertisementDeleted> getDeleted();
    boolean deleteToBacket(long...id);
    boolean restoreFromBacket(long...id);
}
