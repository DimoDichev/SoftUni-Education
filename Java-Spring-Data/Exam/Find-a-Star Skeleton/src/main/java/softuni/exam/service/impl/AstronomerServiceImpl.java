package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerRootSeedDto;
import softuni.exam.models.dto.AstronomerSeedDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private AstronomerRepository astronomerRepository;
    private StarRepository starRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private XmlParser xmlParser;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/astronomers.xml"));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder output = new StringBuilder();

        List<AstronomerSeedDto> astronomers =
                xmlParser.fromFile("src/main/resources/files/xml/astronomers.xml"
                        , AstronomerRootSeedDto.class).getAstronomers();

        for (AstronomerSeedDto astronomer : astronomers) {
            boolean isValid = validationUtil.isValid(astronomer);

            if (isValid && astronomerRepository
                    .findFirstByFirstNameAndLastName(astronomer.getFirstName(), astronomer.getLastName()).isEmpty()
            && starRepository.findById(astronomer.getObservingStar()).isPresent()) {

                output.append(String.format(Locale.US, "Successfully imported astronomer %s %s - %.2f",
                        astronomer.getFirstName(),
                        astronomer.getLastName(),
                        astronomer.getAverageObservationHours()))
                        .append(System.lineSeparator());

                Star star = starRepository.findById(astronomer.getObservingStar()).orElse(null);

                Astronomer mapAstronomer = modelMapper.map(astronomer, Astronomer.class);
                mapAstronomer.setObservingStar(star);
                astronomerRepository.save(mapAstronomer);
            } else {
                output.append("Invalid astronomer")
                        .append(System.lineSeparator());
            }

        }

        return output.toString().trim();
    }

}
