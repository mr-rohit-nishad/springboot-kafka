package net.sprinboot.consumer.db.repository;

import net.sprinboot.consumer.db.entity.WikiMediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiMediaDataRepository extends JpaRepository<WikiMediaData,Long> {
}
