package com.ooad.pixeledit.repository;
import com.ooad.pixeledit.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByIsApprovedIsNull();
}
