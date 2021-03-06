package ua.kiev.prog;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AdvDAOImpl implements AdvDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Advertisement> list(String pattern) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Advertisement.class);
            criteria.add(Restrictions.like("shortDesc", "%" + pattern + "%"));
            List<Advertisement> result = criteria.list();
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public boolean add(Advertisement...adv) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            for (Advertisement advertisement : adv) {
                session.merge(advertisement);
            }
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Advertisement entity = (Advertisement) session.get(Advertisement.class, id);
            session.delete(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public byte[] getPhoto(long id) {
        Session session = sessionFactory.openSession();
        try {
            Photo photo = (Photo) session.get(Photo.class, id);
            return photo.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] getPhotoFromBacket(long id) {
        Session session = sessionFactory.openSession();
        try {
            PhotoDeleted photo = (PhotoDeleted) session.get(PhotoDeleted.class, id);
            return photo.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Advertisement> getAll() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Advertisement.class);
            List<Advertisement> result = criteria.list();
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public List<AdvertisementDeleted> getDeleted() {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(AdvertisementDeleted.class);
            List<AdvertisementDeleted> result = criteria.list();
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public boolean deleteToBacket(long...ids) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            for (long id : ids) {
                Advertisement entity = (Advertisement) session.get(Advertisement.class, id);
                AdvertisementDeleted entityDel = new AdvertisementDeleted(entity, new PhotoDeleted(entity.getPhoto()));
                session.merge(entityDel);
                session.delete(entity);
            }
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean restoreFromBacket(long...ids) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            for (long id : ids) {
                AdvertisementDeleted entityDel = (AdvertisementDeleted) session.get(AdvertisementDeleted.class, id);
                Advertisement entity = new Advertisement(entityDel, new Photo(entityDel.getPhoto()));
                session.merge(entity);
                session.delete(entityDel);
            }
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }
}
