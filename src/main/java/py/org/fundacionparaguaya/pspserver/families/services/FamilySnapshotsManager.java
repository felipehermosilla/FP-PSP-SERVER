package py.org.fundacionparaguaya.pspserver.families.services;

import java.util.List;

import py.org.fundacionparaguaya.pspserver.families.dtos.FamilyFilterDTO;
import py.org.fundacionparaguaya.pspserver.families.dtos.FamilyMapDTO;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;

public interface FamilySnapshotsManager {

	FamilyMapDTO getFamilyMapById(Long familyId);
	
	void deleteSnapshotByFamily(Long familyId);

	List<FamilyMapDTO> getAllFamyliesMapData(FamilyFilterDTO filter, UserDetailsDTO user);
	
}
