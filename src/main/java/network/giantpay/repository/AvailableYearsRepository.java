package network.giantpay.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

/**
 * Return a sorted by asc list of {@link network.giantpay.model.RoadMap} years
 */
@Component
@AllArgsConstructor
public class AvailableYearsRepository implements Supplier<List<Integer>> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Integer> get() {
        return this.jdbcTemplate.queryForList("SELECT DISTINCT date_part('year',date) AS year FROM roadmap ORDER BY year ASC ", Integer.class);
    }
}
