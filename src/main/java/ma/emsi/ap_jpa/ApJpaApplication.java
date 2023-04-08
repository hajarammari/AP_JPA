package ma.emsi.ap_jpa;

import ma.emsi.ap_jpa.entities.Patient;
import ma.emsi.ap_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ApJpaApplication implements CommandLineRunner {


    @Autowired //injection des dependances
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(ApJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i =0 ; i<100; i++){
            patientRepository.save(
                    new Patient(null,"toto", new Date(),Math.random()>0.5?true:false, (int)(Math.random()*100)));
        }

//        patientRepository.save(new Patient(null, "toto",new Date(),true,50));
//        patientRepository.save(new Patient(null, "Lucas",new Date(),false,70));
//        patientRepository.save(new Patient(null, "Ethan",new Date(),false,10));


        //List<Patient> patients= patientRepository.findAll();
        Page<Patient> patients= patientRepository.findAll(PageRequest.of(0,5));

        System.out.println("Total pages: "+patients.getTotalPages());
        System.out.println("Total elements: "+ patients.getTotalElements());
        System.out.println("Num page: "+ patients.getNumber());
        List<Patient> content = patients.getContent();
        Page<Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(0,4));
        List<Patient> patientList = patientRepository.chercherPatients("%h%",40);


        System.out.println("Listes des Patients");
        byMalade.forEach(p->{
            System.out.println("___________________");
            System.out.println(p.getId()+" " + p.getNom()+" "+p.getScore()+" "+p.isMalade());
        });

        System.out.println("Patient par id");
        Patient patient= patientRepository.findById(1L).orElse(null);
        if(patient != null ){
            System.out.println(patient.getNom()+" "  +patient.isMalade());
        }

        patient.setScore(870);
        patientRepository.save(patient);
        //patientRepository.deleteById(1L);

    }
}
