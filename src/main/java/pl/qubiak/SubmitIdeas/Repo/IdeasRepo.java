package pl.qubiak.SubmitIdeas.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.qubiak.SubmitIdeas.Model.Ideas.Ideas;

import java.util.List;

@Repository
public interface IdeasRepo extends JpaRepository<Ideas, Long> {

    List<Ideas> findByAcceptedTrue();

    List<Ideas> findByAcceptedFalse();
}
