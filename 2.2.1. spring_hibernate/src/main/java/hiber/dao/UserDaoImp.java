package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getOwner(String model, int series) {
      TypedQuery<Car> carQuery = sessionFactory.getCurrentSession().createQuery("from Car where model = :model and series = :series")
              .setParameter("model", model)
              .setParameter("series", series);
      List<Car> listCars = carQuery.getResultList();
      if (!listCars.isEmpty()) {
         Car car = listCars.get(0);
         List<User> listUser = listUsers();
         return listUser.stream()
                 .filter(user -> user.getCar().equals(car))
                 .findAny()
                 .orElse(null);
      }
      return null;
   }
}
