package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findFirstByName(String name);

    @Query("SELECT s FROM Star s " +
            "LEFT JOIN Astronomer a ON a.observingStar.id = s.id " +
            "WHERE s.starType = softuni.exam.models.entity.StarType.RED_GIANT " +
            "AND a.observingStar.id = null " +
            "ORDER BY s.lightYears")
    List<Star> findAllRedGiantsWhoAreNeverBeenObserved();

}
