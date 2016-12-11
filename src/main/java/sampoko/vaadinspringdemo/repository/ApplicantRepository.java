package sampoko.vaadinspringdemo.repository;


import sampoko.vaadinspringdemo.model.jpa.hibernate.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {



}
