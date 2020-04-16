package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private static String INSERT_SQL = "INSERT INTO time_entries (project_id, user_id, date, hours) VALUES (?, ?, ?, ?)";
    private static String RETRIEVE_SQL =  "SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?";
    private static String UPDATE_SQL = "UPDATE time_entries SET project_id = ?, user_id = ?, date = ?,  hours = ? WHERE id = ?";
    private static String DELETE_SQL = "DELETE FROM time_entries where id = ?";
    private static String RETRIEVE_ALL_SQL = "SELECT id, project_id, user_id, date, hours FROM time_entries";

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement( INSERT_SQL, RETURN_GENERATED_KEYS );
            statement.setLong(1, timeEntry.getProjectId());
            statement.setLong(2, timeEntry.getUserId());
            statement.setDate(3, Date.valueOf(timeEntry.getDate()));
            statement.setInt(4, timeEntry.getHours());

            return statement;
        }, keyHolder);

        return find(keyHolder.getKey().longValue());
    }

    @Override
    public TimeEntry find(long id) {

        return this.jdbcTemplate.query(RETRIEVE_SQL,
                new Object[]{id},
                this.extractor);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        jdbcTemplate.update("UPDATE time_entries " +
                        "SET project_id = ?, user_id = ?, date = ?,  hours = ? " +
                        "WHERE id = ?",
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                Date.valueOf(timeEntry.getDate()),
                timeEntry.getHours(),
                id);

        return find(id);

    }

    @Override
    public void delete(long id) {

        this.jdbcTemplate.update(DELETE_SQL, new Object[]{id});

    }

    @Override
    public List<TimeEntry> list() {
        return jdbcTemplate.query( RETRIEVE_ALL_SQL, rowMapper);
    }

    private final RowMapper<TimeEntry> rowMapper = (rs, rowNum) -> new TimeEntry(
            rs.getLong("id"),
            rs.getLong("project_id"),
            rs.getLong("user_id"),
            rs.getDate("date").toLocalDate(),
            rs.getInt("hours")
    );

    private final ResultSetExtractor<TimeEntry> extractor =
            (rs) -> rs.next() ? rowMapper.mapRow(rs, 1) : null;
}
