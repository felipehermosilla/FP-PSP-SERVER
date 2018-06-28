package py.org.fundacionparaguaya.pspserver.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import py.org.fundacionparaguaya.pspserver.families.dtos.FamilyDTO;
import py.org.fundacionparaguaya.pspserver.families.dtos.FamilyFilterDTO;
import py.org.fundacionparaguaya.pspserver.families.dtos.FamilyMapDTO;
import py.org.fundacionparaguaya.pspserver.families.services.FamilyService;
import py.org.fundacionparaguaya.pspserver.families.services.FamilySnapshotsManager;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/families")
public class FamilyController {

    private static final Logger LOG = LoggerFactory
            .getLogger(FamilyController.class);

    private FamilyService familyService;

    private FamilySnapshotsManager familyMapService;

    public FamilyController(FamilyService familyService,
                            FamilySnapshotsManager familyMapService) {
        this.familyService = familyService;
        this.familyMapService = familyMapService;
    }

    @PostMapping()
    public ResponseEntity<FamilyDTO> addFamily(
            @Valid @RequestBody FamilyDTO familyDTO) throws URISyntaxException {
        FamilyDTO result = familyService.addFamily(familyDTO);
        return ResponseEntity
                .created(new URI("/api/v1/families/" + result.getFamilyId()))
                .body(result);
    }

    @PutMapping("/{familyId}/image")
    public String uploadFamilyPhoto(@PathVariable("familyId") Long familyId,
                                    @RequestParam("file") MultipartFile file) throws IOException {

        String url=familyService.imageUpload(familyId, file);
        return url;
    }

    @PutMapping("/{familyId}")
    public ResponseEntity<FamilyDTO> updateFamily(
            @PathVariable("familyId") Long familyId,
            @RequestBody FamilyDTO familyDTO) {

        FamilyDTO result = familyService.updateFamily(familyId, familyDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{familyId}")
    public ResponseEntity<FamilyMapDTO> getFamilyMapById(
            @PathVariable("familyId") Long familyId) {
        FamilyMapDTO dto = familyMapService.getFamilyMapById(familyId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{familyId}")
    public ResponseEntity<?> deleteFamily(
            @PathVariable("familyId") Long familyId) {
        LOG.debug("REST request to delete Family: {}", familyId);
        familyMapService.deleteSnapshotByFamily(familyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<FamilyDTO>> getAllFamilies(
            @RequestParam(value = "organization_id", required = false)
                    Long organizationId,
            @RequestParam(value = "country_id", required = false)
                    Long countryId,
            @RequestParam(value = "city_id", required = false)
                    Long cityId,
            @RequestParam(value = "free_text", required = false)
                    String name,
            @RequestParam(value = "application_id", required = false)
                    Long applicationId,
            @RequestParam(value = "last_modified_gt", required = false) String lastModifiedGt,
            @AuthenticationPrincipal UserDetailsDTO user) {
        FamilyFilterDTO filter = FamilyFilterDTO.builder()
                .applicationId(applicationId)
                .organizationId(organizationId)
                .countryId(countryId)
                .cityId(cityId)
                .name(name)
                .isActive(true)
                .lastModifiedGt(lastModifiedGt)
                .build();
        List<FamilyDTO> families = familyService.listFamilies(filter, user);
        return ResponseEntity.ok(families);
    }

    @GetMapping("/counter")
    public ResponseEntity<Long> getFamiliesByFilter(
            @AuthenticationPrincipal UserDetailsDTO details) {
        Long count = familyService.countFamiliesByDetails(details);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/user")
    public ResponseEntity<List<FamilyDTO>> getFamiliesByUserAndNameOrCode(
            @AuthenticationPrincipal UserDetailsDTO details,
            @RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(familyService
                .listDistinctFamiliesByUser(details, name));
    }

    @GetMapping("/map-info")
    public ResponseEntity<List<FamilyMapDTO>> getFamilyMapData(
            @RequestParam(value = "organization_id", required = false)
            Long organizationId,
    @RequestParam(value = "country_id", required = false)
            Long countryId,
    @RequestParam(value = "city_id", required = false)
            Long cityId,
    @RequestParam(value = "free_text", required = false)
            String name,
    @RequestParam(value = "application_id", required = false)
            Long applicationId,
    @AuthenticationPrincipal UserDetailsDTO user) {
        FamilyFilterDTO filter = FamilyFilterDTO.builder()
                .applicationId(applicationId)
                .organizationId(organizationId)
                .countryId(countryId)
                .cityId(cityId)
                .name(name)
                .isActive(true)
                .build();
        List<FamilyMapDTO> dto = familyMapService.getAllFamyliesMapData(filter, user);
        return ResponseEntity.ok(dto);
    }

}