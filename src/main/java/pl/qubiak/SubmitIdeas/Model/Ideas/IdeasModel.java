package pl.qubiak.SubmitIdeas.Model.Ideas;

import javax.persistence.Entity;

@Entity
public class IdeasModel {

    private int id;
    private String idea;
    private String author;
    private boolean isAccepted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
