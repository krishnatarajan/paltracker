package io.pivotal.pal.tracker;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> inMemoryEntries = new HashMap<>();

    long id =1;


    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        timeEntry.setId(id++);

        inMemoryEntries.put(timeEntry.getId(), timeEntry);
        return inMemoryEntries.get(timeEntry.getId());
    }

    @Override
    public TimeEntry find(long id) {
        return inMemoryEntries.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        if (!inMemoryEntries.containsKey(id))
           return null;

        inMemoryEntries.remove(id);

        timeEntry.setId(id);
        inMemoryEntries.put(id, timeEntry);
        return inMemoryEntries.get(id);

    }

    @Override
    public void delete(long id) {

        if (!inMemoryEntries.containsKey(id))
            return;

        inMemoryEntries.remove(id);

    }

    @Override
    public Collection<TimeEntry> list() {

        return inMemoryEntries.values();
    }


}
