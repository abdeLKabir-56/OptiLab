package com.optilab.laboratoire;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LaboratoireRepositoryTest {

    @Autowired
    private LaboratoireRepository laboratoireRepository; // Injecting the repository


    // Create Labo Test
    @Test
    public void createLaboratoire() {
        // Arrange
        Laboratoire laboratoire = Laboratoire.builder()
                .nom("Lab1_Test")
                .build();


        // Act
        Laboratoire createdLaboratoire = laboratoireRepository.save(laboratoire);

        // Assert
        assertThat(createdLaboratoire).isNotNull();
        assertThat(createdLaboratoire.getId()).isNotNull();
        assertThat(createdLaboratoire.getNom()).isEqualTo("Lab1_Test");
    }

    // Find All Labos Test
    @Test
    public void GetAll(){
        Laboratoire laboratoire1 = Laboratoire.builder()
                .nom("Lab1_Test")
                .build();
        Laboratoire laboratoire2 = Laboratoire.builder()
                .nom("Lab2_Test")
                .build();

        laboratoireRepository.save(laboratoire1);
        laboratoireRepository.save(laboratoire2);

        List<Laboratoire> laboratoireList = laboratoireRepository.findAll();

        Assertions.assertThat(laboratoireList).isNotNull();
        assertThat(laboratoireList.size()).isEqualTo(2);
    }

    // Find By ID Test
    @Test
    public void GetLaboratoireById(){
        Laboratoire laboratoire = Laboratoire.builder()
                .nom("Lab1_Test")
                .build();

        laboratoireRepository.save(laboratoire);

        Laboratoire laboratoire1 = laboratoireRepository.findById(laboratoire.getId()).orElse(null);

        Assertions.assertThat(laboratoire1).isNotNull();
    }
}
