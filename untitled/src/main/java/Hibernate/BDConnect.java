package Hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BDConnect {
    public static void main(String[] args) {
        ShopProducer producer = new ShopProducer("Some Producer");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(producer);

        session.getTransaction().commit();
        session.close();
    }
}
