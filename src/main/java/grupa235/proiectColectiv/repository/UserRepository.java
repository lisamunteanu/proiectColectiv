package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.RepoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<RepoUser, Integer> {
    RepoUser findByUsername(String username);
    RepoUser findByResetToken(String resetToken);
}
