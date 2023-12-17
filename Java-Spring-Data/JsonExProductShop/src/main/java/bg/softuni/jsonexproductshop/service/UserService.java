package bg.softuni.jsonexproductshop.service;

import bg.softuni.jsonexproductshop.model.dto.UserSoldDto;
import bg.softuni.jsonexproductshop.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void seedUsers() throws IOException;

    User findRandomUser();

    List<UserSoldDto> findAllWithSoldItemsWithBuyer();
}
