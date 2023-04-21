package com.ooad.pixeledit.repository;
import com.ooad.pixeledit.models.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
    Filter findByFilterName(String filter_name);
    Filter findByFilterType(String filter_type);
}
