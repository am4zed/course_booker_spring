package com.codeclan.coursebooker.coursebooker.repositories.CustomerRepository;

import com.codeclan.coursebooker.coursebooker.models.Customer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Customer> getCustomersByCourseId(Long courseId){
        List<Customer> results = null;

        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Customer.class);
            cr.createAlias("bookings", "bookingAlias");
            cr.add(Restrictions.eq("bookingAlias.course.id", courseId));
            results = cr.list();
        }
        catch(HibernateException ex) {
            ex.printStackTrace();
        }
        return results;
    }

    @Transactional
    public List<Customer> getCustomersByTownAndCourseId(String town, Long courseId){
        List<Customer> results = null;

        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Customer.class);
            cr.createAlias("bookings", "bookingAlias");
            cr.add(Restrictions.eq("bookingAlias.course.id", courseId));
            cr.add(Restrictions.eq("town", town).ignoreCase());
            results = cr.list();
        }
        catch(HibernateException ex) {
            ex.printStackTrace();
        }
        return results;
    }

    @Transactional
    public  List<Customer> getCustomersOverCertainAgeByTownAndCourseId(int age, String town, Long course_id){
        List<Customer> results = null;

        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Customer.class);
            cr.createAlias("bookings", "bookingAlias");
            cr.add(Restrictions.gt("age", age));
            cr.add(Restrictions.eq("town", town).ignoreCase());
            cr.add(Restrictions.eq("bookingAlias.course.id", course_id));

            results = cr.list();
        }
        catch(HibernateException ex) {
            ex.printStackTrace();
        }
        return results;
    }
}
