package pl.qubiak.SubmitIdeas.Model.Ideas;

import javax.persistence.Entity;

@Entity
public class IdeasModel {

    private int id;
    private String ideas;
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdeas() {
        return ideas;
    }

    public void setIdeas(String ideas) {
        this.ideas = ideas;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
