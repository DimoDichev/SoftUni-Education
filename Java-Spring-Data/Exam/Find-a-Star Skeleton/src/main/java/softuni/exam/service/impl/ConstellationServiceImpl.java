package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private ConstellationRepository constellationRepository;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private Gson gson;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.constellationRepository = constellationRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/constellations.json"));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder output = new StringBuilder();

        ConstellationSeedDto[] constellationSeedDtos =
                gson.fromJson(readConstellationsFromFile(), ConstellationSeedDto[].class);

        for (ConstellationSeedDto constellationSeedDto : constellationSeedDtos) {
            boolean isValid = validationUtil.isValid(constellationSeedDto);

            if (isValid && constellationRepository.findFirstByName(constellationSeedDto.getName()).isEmpty()) {
                output.append(String.format("Successfully imported constellation %s - %s",
                        constellationSeedDto.getName(),
                        constellationSeedDto.getDescription()))
                        .append(System.lineSeparator());
                constellationRepository.save(modelMapper.map(constellationSeedDto, Constellation.class));
            } else {
                output.append("Invalid constellation")
                        .append(System.lineSeparator());
            }
        }

        return output.toString().trim();
    }

}






























