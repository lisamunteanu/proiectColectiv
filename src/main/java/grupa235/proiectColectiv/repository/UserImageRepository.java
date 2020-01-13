package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage,Long> {

    @Query(value ="select * from userimage u where u.id_user= :idUser limit 1" ,nativeQuery = true)
    Optional<UserImage> getImage(@Param("idUser") Long idUser);

    @Modifying
    @Query(value = "update userimage set image = :iMage where userimage.id_user = :userId", nativeQuery = true)
    void updateImage(@Param("iMage") String iMage,@Param("userId") Long userId);
}
