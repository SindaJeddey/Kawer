package kawer.tn.field;


import kawer.tn.field.dto.FieldDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {
    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping("/fields/{location}")
    @PreAuthorize("permitAll()")
    public List<FieldDTO> getFieldsByLocation(@PathVariable String location) {
        return fieldService.getFieldsByLocation(location);
    }

    @GetMapping("/{fieldId}")
    @PreAuthorize("permitAll()")
    public FieldDTO getField(@PathVariable Long fieldId) {
        return fieldService.getFieldById(fieldId);
    }

    @PostMapping("/{ownerId}/new")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public FieldDTO newField(@RequestBody FieldDTO fieldDTO,@PathVariable Long ownerId) {
        return fieldService.addNewField(fieldDTO,ownerId);
    }

    @DeleteMapping("/{fieldId}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public void deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
    }

    @PutMapping("/{fieldId}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN')")
    public FieldDTO modifyField(@RequestBody FieldDTO fieldDTO, @PathVariable Long fieldId) {
        return fieldService.modifyField(fieldDTO, fieldId);
    }
}
