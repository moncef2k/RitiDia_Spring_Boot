package ensaf.pfa.projet.RitiDia.services.interfaces;

import ensaf.pfa.projet.RitiDia.shared.dto.PatientDto;

public interface IPatientService {


    PatientDto createPatient(Long id, PatientDto patientDto);
}
