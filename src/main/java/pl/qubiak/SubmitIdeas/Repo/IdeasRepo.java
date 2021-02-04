package pl.qubiak.SubmitIdeas.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.qubiak.SubmitIdeas.Model.Ideas.Ideas;

@Repository
public interface IdeasRepo extends JpaRepository<Ideas, Long> {
}
