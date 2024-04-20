package com.example.demo.repository;

import com.example.demo.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CompanyRepositoryTest {
@Autowired
    private CompanyRepository companyRepository;
    private Company company;

    @BeforeEach
    public void setup(){
        company = new Company("FlyBondi","aca iria banner", "urlPage");
    }

    @Test
    void saveCompanyTest(){
      Company company1 = companyRepository.save(company);
     assertThat(company1).isNotNull();
     assertThat(company1.getName()).isEqualTo("FlyBondi");
    }
    @Test
    void findAllCompaniesTest(){
        Company company1 = new Company("nombrePrueba", "banner", "url");
        //guardo 2 companias
        companyRepository.save(company);
        companyRepository.save(company1);
        List<Company> companyList = companyRepository.findAll();

        assertThat(companyList).isNotNull();
        assertThat(companyList.size()).isEqualTo(2);
    }
    @Test
    void companyFindById(){
        companyRepository.save(company);
        //del repository traeme
        Company companyTest = companyRepository.findById(company.getId()).get();
        assertThat(companyTest).isNotNull();
    }
    @Test
    void companyUpdateTest(){
        //primero guardo el vuelo
        companyRepository.save(company);

        //busco por id la compania recién guardada en el repositorio
        Company company1 = companyRepository.findById(company.getId()).get();

        //Seteo los nuevos valores, que actualizaria
        company1.setBanner("Banner prueba1");
        company1.setName("Compania prueba");
        //vuelvo a guardarlo, actualizado
        Company companyUpdated = companyRepository.save(company1);

        assertThat(companyUpdated.getName()).isEqualTo("Compania prueba");
        assertThat(companyUpdated.getBanner()).isEqualTo("Banner prueba1");
    }

    @Test
    void companyDeleted(){
        //guardo compania y la borro por id
        companyRepository.save(company);
        companyRepository.deleteById(company.getId());

        //busco si hay una compania con ese id y corroboro que este vacio
        Optional<Company> deletedCompany = companyRepository.findById(company.getId());
        // Verificar que la compañía haya sido eliminada correctamente
       assertTrue(deletedCompany.isEmpty());
          }


}