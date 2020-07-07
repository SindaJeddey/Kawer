package kawer.tn.field;

import kawer.tn.field.dto.FieldDTO;
import kawer.tn.field.dto.FieldDTOToFieldConverter;
import kawer.tn.field.dto.FieldToFieldDTOConverter;
import kawer.tn.owner.Owner;
import kawer.tn.owner.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;
    private final OwnerRepository ownerRepository;
    private final FieldToFieldDTOConverter toFieldDTOConverter;
    private final FieldDTOToFieldConverter toFieldConverter;

    public FieldService(FieldRepository fieldRepository,
                        OwnerRepository ownerRepository,
                        FieldToFieldDTOConverter toFieldDTOConverter,
                        FieldDTOToFieldConverter toFieldConverter) {
        this.fieldRepository = fieldRepository;
        this.ownerRepository = ownerRepository;
        this.toFieldDTOConverter = toFieldDTOConverter;
        this.toFieldConverter = toFieldConverter;
    }

    public Field saveField (Field field){
        return this.fieldRepository.save(field);
    }

    public List<FieldDTO> getAllFields() {
        List<Field> fields = fieldRepository.findAll();
        List<FieldDTO> dtos = fieldRepository.findAll()
                .stream()
                .map(field -> toFieldDTOConverter.convert(field))
                .collect(Collectors.toList());
        return dtos;
    }

    public FieldDTO getFieldById(Long fieldId) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new NullPointerException("Field "+fieldId+" not found"));
        FieldDTO dto = toFieldDTOConverter.convert(field);
        return dto;
    }

    public FieldDTO addNewField(FieldDTO fieldDTO, Long ownerId) {
        Field fieldToSave = toFieldConverter.convert(fieldDTO);
        Owner fieldOwner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NullPointerException("Owner "+ownerId+" not found"));
        fieldToSave.setOwner(fieldOwner);
        Set<Field> fields = fieldOwner.getFields();
        fields.add(fieldToSave);
        fieldOwner.setFields(fields);
        ownerRepository.save(fieldOwner);
        Field savedField = fieldRepository.save(fieldToSave);
        FieldDTO savedFieldDto = toFieldDTOConverter.convert(savedField);
        return savedFieldDto;
    }

    public void deleteField(Long fieldId) {
        fieldRepository.deleteById(fieldId);
    }

    public FieldDTO modifyField(FieldDTO fieldDTO, Long fieldId) {
        Field fieldToModify = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new NullPointerException("Field "+fieldId+" not found"));
        Field updates = toFieldConverter.convert(fieldDTO);
        if(updates.getName() != null){
            fieldToModify.setName(updates.getName());
        }
        if(updates.getLocation() != null){
            fieldToModify.setLocation(updates.getLocation());
        }
        if(updates.getDescription() != null){
            fieldToModify.setDescription(updates.getDescription());
        }
        if(updates.getContactNumber() != null){
            fieldToModify.setContactNumber(updates.getContactNumber());
        }
        if(updates.getCapacity() != fieldToModify.getCapacity()){
            fieldToModify.setCapacity(updates.getCapacity());
        }
        if(updates.getPrice() != null){
            fieldToModify.setPrice(updates.getPrice());
        }
        if(updates.getAmenities() != null){
            fieldToModify.setAmenities(updates.getAmenities());
        }
        Field savedField = fieldRepository.save(fieldToModify);
        FieldDTO dto = toFieldDTOConverter.convert(savedField);
        return dto;
    }

    public List<FieldDTO> getFieldsByLocation(String location) {
        List<FieldDTO> dtos = fieldRepository.findAllByLocation(location)
                .stream()
                .map(field -> toFieldDTOConverter.convert(field))
                .collect(Collectors.toList());
        return dtos;
    }
}
