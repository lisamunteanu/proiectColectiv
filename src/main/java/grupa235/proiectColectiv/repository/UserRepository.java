package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.RepoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<RepoUser, Integer> {
    Optional<RepoUser> findByUsername(String username);
    Optional<RepoUser> findByResetToken(String resetToken);
}
