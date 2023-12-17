package bg.softuni.automappingobjectsexercises.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameViewDto {

    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String thumbnailUrl;
    private String description;
    private LocalDate releaseDate;

    public GameViewDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

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

    @Override
    public String toString() {
        return String.format("%s %.2f", this.title, this.price);
    }

    public String allInfo() {
        StringBuilder output = new StringBuilder();

        output.append("Title: ").append(this.title).append(System.lineSeparator());
        output.append("Price: ").append(String.format("%.2f", this.price)).append(System.lineSeparator());
        output.append("Description: ").append(this.description).append(System.lineSeparator());
        output.append("Release date: ").append(this.releaseDate).append(System.lineSeparator());

        return output.toString();
    }
}
