package io.github.josephmtinangi.springbootuploadfiles.repositories;

import io.github.josephmtinangi.springbootuploadfiles.models.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
}
