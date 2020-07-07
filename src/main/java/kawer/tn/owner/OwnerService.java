package kawer.tn.owner;

import kawer.tn.owner.dto.OwnerDTO;
import kawer.tn.owner.dto.OwnerDTOToOwnerConverter;
import kawer.tn.owner.dto.OwnerToOwnerDTOConverter;
import kawer.tn.security.auth.ApplicationUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerDTOToOwnerConverter toOwnerConverter;
    private final OwnerToOwnerDTOConverter toOwnerDTOConverter;
    private final ApplicationUserRepository applicationUserRepository;

    public OwnerService(OwnerRepository ownerRepository,
                        OwnerDTOToOwnerConverter toOwnerConverter,
                        OwnerToOwnerDTOConverter toOwnerDTOConverter,
                        ApplicationUserRepository applicationUserRepository) {
        this.ownerRepository = ownerRepository;
        this.toOwnerConverter = toOwnerConverter;
        this.toOwnerDTOConverter = toOwnerDTOConverter;
        this.applicationUserRepository = applicationUserRepository;
    }

    public Owner saveOwner(Owner owner) {
        return this.ownerRepository.save(owner);
    }

    public OwnerDTO addNewOwner(OwnerDTO ownerDTO) {
        Owner ownerToAdd = toOwnerConverter.convert(ownerDTO);
        Owner savedOwner = ownerRepository.save(ownerToAdd);
        applicationUserRepository.saveApplicationUser(savedOwner.getUsername(),savedOwner.getPassword(),"OWNER");
        OwnerDTO savedOwnerDTO = toOwnerDTOConverter.convert(savedOwner);
        return savedOwnerDTO;
    }

    public OwnerDTO getOwnerById(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NullPointerException("Owner " + ownerId + " not found"));
        OwnerDTO dto = toOwnerDTOConverter.convert(owner);
        return dto;

    }

    public void deleteOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(()->new NullPointerException("Owner "+ownerId+" not found"));
        applicationUserRepository.deleteApplicationUser(owner.getUsername(),"OWNER");
        ownerRepository.deleteById(ownerId);
    }

    public OwnerDTO modifyOwner(OwnerDTO ownerDTO, Long ownerId) {
        Owner ownerToUpdate = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NullPointerException("Owner "+ownerId+" not found"));
        Owner update = toOwnerConverter.convert(ownerDTO);
        if(update.getFirstName() != null)
            ownerToUpdate.setFirstName(update.getFirstName());
        if(update.getLastName() != null)
            ownerToUpdate.setLastName(update.getLastName());
        if(update.getUsername() != null)
            ownerToUpdate.setNumber(update.getNumber());
        if(update.getEmail() != null)
            ownerToUpdate.setEmail(update.getEmail());
        Owner savedOwner= ownerRepository.save(ownerToUpdate);
        OwnerDTO savedOwnerDto = toOwnerDTOConverter.convert(savedOwner);
        return savedOwnerDto;
    }

    public List<OwnerDTO> getAllOwners(){
        List<OwnerDTO> dtos = ownerRepository.findAll()
                .stream()
                .map(owner -> toOwnerDTOConverter.convert(owner))
                .collect(Collectors.toList());
        return dtos;
    }

}
