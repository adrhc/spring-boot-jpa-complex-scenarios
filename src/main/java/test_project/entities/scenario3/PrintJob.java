package test_project.entities.scenario3;

/**
 * Created by adr on 11/24/15.
 */

import javax.persistence.*;

@Entity
public class PrintJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private PrintQueue queue;

    public PrintJob() {
    }

    public PrintJob(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PrintQueue getQueue() {
        return queue;
    }

    public void setQueue(PrintQueue queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PrintJob{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}