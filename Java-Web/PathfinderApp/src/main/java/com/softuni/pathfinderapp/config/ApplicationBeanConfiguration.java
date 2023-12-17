package com.softuni.pathfinderapp.config;

import com.softuni.pathfinderapp.model.binding.RouteAddBindingModel;
import com.softuni.pathfinderapp.model.binding.UserRegisterBindingModel;
import com.softuni.pathfinderapp.model.entity.CategoryEntity;
import com.softuni.pathfinderapp.model.entity.PictureEntity;
import com.softuni.pathfinderapp.model.entity.RouteEntity;
import com.softuni.pathfinderapp.model.entity.UserEntity;
import com.softuni.pathfinderapp.model.enums.CategoryNameEnum;
import com.softuni.pathfinderapp.model.enums.LevelEnum;
import com.softuni.pathfinderapp.model.enums.RoleTypeEnum;
import com.softuni.pathfinderapp.model.view.RouteDetailsViewModel;
import com.softuni.pathfinderapp.model.view.RouteViewModel;
import com.softuni.pathfinderapp.repository.CategoryRepository;
import com.softuni.pathfinderapp.repository.RoleRepository;
import com.softuni.pathfinderapp.repository.UserRepository;
import com.softuni.pathfinderapp.service.impl.PathfinderUserDetailsService;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ApplicationBeanConfiguration {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationBeanConfiguration(RoleRepository roleRepository, UserRepository userRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // UserRegisterBindingModel -> User
        Provider<UserEntity> newUserProvider = req -> new UserEntity()
                .setRole(Set.of(roleRepository.findByName(RoleTypeEnum.USER)))
                .setLevel(LevelEnum.BEGINNER);

        Converter<String, String> passwordConverter
                = ctx -> (ctx.getSource() == null)
                ? null
                : passwordEncoder.encode(ctx.getSource());

        modelMapper
                .createTypeMap(UserRegisterBindingModel.class, UserEntity.class)
                .setProvider(newUserProvider)
                .addMappings(mapper -> mapper.using(passwordConverter)
                        .map(UserRegisterBindingModel::getPassword, UserEntity::setPassword));


        //RouteAddBindingModel -> RouteEntity

        Converter<Set<CategoryNameEnum>, Set<CategoryEntity>> toCategoryEntitySet
                = ctx -> (ctx.getSource() == null)
                ? null
                : categoryRepository.getAllByNameIn(ctx.getSource());

        modelMapper
                .createTypeMap(RouteAddBindingModel.class, RouteEntity.class)
                .addMappings(mapper -> mapper
                        .using(toCategoryEntitySet)
                        .map(RouteAddBindingModel::getCategories, RouteEntity::setCategories));


        //RouteEntity -> RouteViewModel
        Converter<Set<PictureEntity>, String> setPictureUrl
                = ctx -> (ctx.getSource().isEmpty())
                ? "/images/pic4.jpg"
                : ctx.getSource().stream().findFirst().get().getUrl();

        modelMapper
                .createTypeMap(RouteEntity.class, RouteViewModel.class)
                .addMappings(mapper -> mapper.using(setPictureUrl)
                        .map(RouteEntity::getPictures, RouteViewModel::setPictureUrl));


        //RouteEntity -> RouteDetailsViewModel
        Converter<Set<PictureEntity>, Set<String>> setSetOfPictureUrl
                = ctx -> (ctx.getSource().isEmpty())
                ? new HashSet<>()
                : ctx.getSource().stream().map(PictureEntity::getUrl).collect(Collectors.toSet());

        modelMapper
                .createTypeMap(RouteEntity.class, RouteDetailsViewModel.class)
                .addMappings(mapper -> mapper.using(setSetOfPictureUrl)
                        .map(RouteEntity::getPictures, RouteDetailsViewModel::setPictureUrl));



        return modelMapper;
    }

}
