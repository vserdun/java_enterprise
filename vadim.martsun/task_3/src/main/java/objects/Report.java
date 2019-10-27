package objects;

import java.time.LocalDate;
import java.util.Objects;

public class Report {
    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private Status status;

    public Report(int id, LocalDate date, String name, String description, Status status) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id &&
                date.equals(report.date) &&
                name.equals(report.name) &&
                description.equals(report.description) &&
                status == report.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, name, description, status);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
