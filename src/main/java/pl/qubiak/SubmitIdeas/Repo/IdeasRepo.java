package pl.qubiak.SubmitIdeas.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.qubiak.SubmitIdeas.Model.Ideas.Ideas;

import java.util.List;

@Repository
public interface IdeasRepo extends JpaRepository<Ideas, Long> {


    @Modifying(clearAutomatically = true)
    @Query("SELECT ideas i WHERE i.is+accepted = '1'") // true = 1
    List<Ideas> getAcceptedIdeas();

    @Modifying(clearAutomatically = true)
    @Query("SELECT ideas i WHERE i.is+accepted = '0'")
    List<Ideas> getUnAcceptedIdeas();
}
