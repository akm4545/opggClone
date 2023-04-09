package kr.co.opgg.datasource.faq;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FAQRepository extends JpaRepository<FAQ, Integer> {
    Optional<List<FAQ>> findAllByFaqCategory(String faqCategory);
}
