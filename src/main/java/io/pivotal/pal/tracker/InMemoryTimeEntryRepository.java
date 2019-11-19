package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{


    private Map<Long,TimeEntry> timeEntryMap = new HashMap<>();
    private Long counter = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry copy = new TimeEntry(++counter, timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());
        timeEntryMap.put(copy.getId(), copy);

        return copy;
    }

    @Override
    public void delete(long id) {

        timeEntryMap.remove(id);


    }

    @Override
    public List<TimeEntry> list() {
        return timeEntryMap.values().stream().collect(Collectors.toList());
    }


    @Override
    public TimeEntry update(long l, TimeEntry timeEntry) {
        if (timeEntryMap.containsKey(l) == false) {
            return null;
        }
        TimeEntry copy = new TimeEntry(l,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        timeEntryMap.put(l, copy);


        return  copy;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }


}
