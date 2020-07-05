package kawer.tn.field;

import org.springframework.stereotype.Service;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field saveField (Field field){
        return this.fieldRepository.save(field);
    }
}
