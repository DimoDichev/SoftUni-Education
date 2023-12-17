package bg.softuni.jsonexproductshop.service.impl;

import bg.softuni.jsonexproductshop.model.dto.UserSeedDto;
import bg.softuni.jsonexproductshop.model.dto.UserSoldDto;
import bg.softuni.jsonexproductshop.model.entity.User;
import bg.softuni.jsonexproductshop.repository.UserRepository;
import bg.softuni.jsonexproductshop.service.UserService;
import bg.softuni.jsonexproductshop.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static bg.softuni.jsonexproductshop.common.ConstantFilePath.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() > 0) return;

        String fileContent = Files.readString(Path.of(FILE_READ_PATH + USER_PATH));

        UserSeedDto[] userSeedDtos = gson.fromJson(fileContent, UserSeedDto[].class);

        Arrays.stream(userSeedDtos)
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);

    }

    @Override
    public User findRandomUser() {
        long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);
        return userRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<UserSoldDto> findAllWithSoldItemsWithBuyer() {
        return userRepository.findAllUserWhoHaveSoldItemsWhitBuyerOrderByLastAndFirstName()
                .stream()
                .map(user -> modelMapper.map(user, UserSoldDto.class))
                .collect(Collectors.toList());
    }

}
