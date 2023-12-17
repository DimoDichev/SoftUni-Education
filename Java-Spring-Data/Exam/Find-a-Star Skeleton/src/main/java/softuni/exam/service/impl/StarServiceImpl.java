package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

@Service
public class StarServiceImpl implements StarService {

    private StarRepository starRepository;
    private ConstellationRepository constellationRepository;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private Gson gson;

    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/stars.json"));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder output = new StringBuilder();

        StarSeedDto[] starSeedDtos =
                gson.fromJson(readStarsFileContent(), StarSeedDto[].class);

        for (StarSeedDto starSeedDto : starSeedDtos) {
            boolean isValid = validationUtil.isValid(starSeedDto);

            if (isValid && starRepository.findFirstByName(starSeedDto.getName()).isEmpty()) {
                output.append(String.format(Locale.US, "Successfully imported star %s - %.2f light years",
                                starSeedDto.getName(),
                                starSeedDto.getLightYears()))
                        .append(System.lineSeparator());

                Constellation constellation =
                        constellationRepository.findById(starSeedDto.getConstellationId()).orElse(null);

                Star star = modelMapper.map(starSeedDto, Star.class);
                star.setConstellation(constellation);

                starRepository.save(star);
            } else {
                output.append("Invalid star")
                        .append(System.lineSeparator());
            }
        }

        return output.toString().trim();
    }

    @Override
    public String exportStars() {
        StringBuilder output = new StringBuilder();

        List<Star> stars = starRepository.findAllRedGiantsWhoAreNeverBeenObserved();

        for (Star star : stars) {
            output.append(String.format("Star: %s", star.getName()))
                    .append(System.lineSeparator());

            output.append(String.format(Locale.US,"   *Distance: %.2f light years", star.getLightYears()))
                    .append(System.lineSeparator());

            output.append(String.format("   **Description: %s", star.getDescription()))
                    .append(System.lineSeparator());

            output.append(String.format("   ***Constellation: %s", star.getConstellation().getName()))
                    .append(System.lineSeparator());
        }

        return output.toString().trim();
    }

}
