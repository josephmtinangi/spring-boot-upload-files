package io.github.josephmtinangi.springbootuploadfiles.repositories;

import io.github.josephmtinangi.springbootuploadfiles.models.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {

    @Query("SELECT uf FROM UploadedFile uf ORDER BY uf.createdAt")
    public List<UploadedFile> findAll();
}
