package test_project.entities.scenario3;

/**
 * Created by adr on 11/24/15.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PrintQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "queue")
    @OrderColumn(name = "PRINT_ORDER")
    private List<PrintJob> jobs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PrintJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<PrintJob> jobs) {
        this.jobs = jobs;
    }

    public void addJob(PrintJob job) {
        this.jobs.add(job);
        job.setQueue(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PrintQueue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}