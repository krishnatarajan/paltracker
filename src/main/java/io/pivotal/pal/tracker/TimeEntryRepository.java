package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry);

    public TimeEntry find(long id);

    public TimeEntry update(long id, TimeEntry timeEntry);

    public void delete (long id);

    public Collection<TimeEntry> list();
}
