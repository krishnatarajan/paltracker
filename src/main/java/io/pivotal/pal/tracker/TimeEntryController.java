package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    TimeEntryRepository  timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(path = "/time-entries", consumes = "application/json", produces = "application/json")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = this.timeEntryRepository.create(timeEntryToCreate);
        if (timeEntry != null)
          return ResponseEntity.created(URI.create("")).body(timeEntry);

        return (ResponseEntity) ResponseEntity.badRequest();

    }

    @GetMapping(path = "/time-entries/{timeEntryId}", produces = "application/json")
    public ResponseEntity read(@PathVariable long timeEntryId) {

        TimeEntry timeEntry = this.timeEntryRepository.find(timeEntryId);
        if (timeEntry != null)
            return ResponseEntity.ok(timeEntry);

        return (ResponseEntity) ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/time-entries", produces = "application/json")
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> timeEntries = new ArrayList<>(this.timeEntryRepository.list());

        return ResponseEntity.ok(timeEntries);

    }

    @PutMapping(path = "/time-entries/{timeEntryId}", produces = "application/json")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {

        TimeEntry timeEntry = this.timeEntryRepository.update(timeEntryId, expected);

        if (timeEntry != null)
            return ResponseEntity.ok(timeEntry);

        return (ResponseEntity) ResponseEntity.notFound().build();


    }

    @DeleteMapping(path = "/time-entries/{timeEntryId}", produces = "application/json")
    public ResponseEntity delete(@PathVariable long timeEntryId) {

        this.timeEntryRepository.delete(timeEntryId);

        return (ResponseEntity) ResponseEntity.noContent().build();

    }
}
