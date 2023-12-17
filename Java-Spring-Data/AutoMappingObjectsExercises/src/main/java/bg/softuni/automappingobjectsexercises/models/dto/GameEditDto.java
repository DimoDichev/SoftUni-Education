package bg.softuni.automappingobjectsexercises.models.dto;

import bg.softuni.automappingobjectsexercises.common.ConstantMessages;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class GameEditDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String thumbnailUrl;
    private String description;
    private LocalDate releaseDate;

    public GameEditDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Pattern(regexp = "^[A-Z][a-z\\d].*", message = ConstantMessages.INCORRECT_TITLE_INSERT)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Positive(message = ConstantMessages.NEGATIVE_PRICE_INSERT)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Positive(message = ConstantMessages.NEGATIVE_SIZE_INSERT)
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Size(min = 11, max = 11, message = ConstantMessages.INCORRECT_TRAILER_URL)
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(https?://).*", message = ConstantMessages.INCORRECT_THUMBNAIL_URL)
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Size(min = 20, message = ConstantMessages.INCORRECT_DESCRIPTION_SIZE)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void updateField(Map<String, String> fieldArgs) {
        for (String key : fieldArgs.keySet()) {
            switch (key) {
                case "title" -> setTitle(fieldArgs.get(key));
                case "price" -> setPrice(new BigDecimal(fieldArgs.get(key)));
                case "size" -> setSize(Double.parseDouble(fieldArgs.get(key)));
                case "trailer" -> setTrailer(fieldArgs.get(key));
                case "thumbnailUrl" -> setThumbnailUrl(fieldArgs.get(key));
                case "description" -> setDescription(fieldArgs.get(key));
            }
        }
    }

}
