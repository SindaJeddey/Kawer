package kawer.tn.field;


import kawer.tn.field.dto.FieldDTO;
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
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping("/fields/{location}")
    public List<FieldDTO> getFieldsByLocation(@PathVariable String location) {
        return fieldService.getFieldsByLocation(location);
    }

    @GetMapping("/{fieldId}")
    public FieldDTO getField(@PathVariable Long fieldId) {
        return fieldService.getFieldById(fieldId);
    }

    @PostMapping("/{ownerId}/new")
    public FieldDTO newField(@RequestBody FieldDTO fieldDTO,@PathVariable Long ownerId) {
        return fieldService.addNewField(fieldDTO,ownerId);
    }

    @DeleteMapping("/{fieldId}")
    public void deleteOwner(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
    }

    @PutMapping("/{fieldId}")
    public FieldDTO modifyOwner(@RequestBody FieldDTO fieldDTO, @PathVariable Long fieldId) {
        return fieldService.modifyField(fieldDTO, fieldId);
    }
}
