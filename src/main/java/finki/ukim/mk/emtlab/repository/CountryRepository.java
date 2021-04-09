package finki.ukim.mk.emtlab.repository;

import finki.ukim.mk.emtlab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
