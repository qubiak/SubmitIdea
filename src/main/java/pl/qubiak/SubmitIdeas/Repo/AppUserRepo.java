package pl.qubiak.SubmitIdeas.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.qubiak.SubmitIdeas.Model.Users.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

  //  @Modifying(clearAutomatically = true)
  //  @Query("UPDATE app_user a SET a.role = 'ROLE_MOD' WHERE a.id = :id")
  //  void update(@Param("id") Long id);
}

