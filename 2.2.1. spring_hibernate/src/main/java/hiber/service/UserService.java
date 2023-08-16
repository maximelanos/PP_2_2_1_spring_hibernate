package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    @Transactional
    void add(Car car);

    @Transactional(readOnly = true)
    List<Car> listCars();

    @Transactional
    List<User> getOwner(String model, int series);
}
