package kawer.tn.owner;

import kawer.tn.owner.dto.OwnerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDTO> getAllOwners(){
        return ownerService.getAllOwners();
    }

    @GetMapping("/{ownerId}")
    public OwnerDTO getOwner(@PathVariable Long ownerId){
        return ownerService.getOwnerById(ownerId);
    }

    @PostMapping("/new")
    public OwnerDTO newOwner(@RequestBody OwnerDTO ownerDTO){
        return ownerService.addNewOwner(ownerDTO);
    }

    @DeleteMapping("/{ownerId}")
    public void deleteOwner(@PathVariable Long ownerId){
        ownerService.deleteOwner(ownerId);
    }

    @PutMapping("/{ownerId}")
    public OwnerDTO modifyOwner(@RequestBody OwnerDTO ownerDTO, @PathVariable Long ownerId){
        return ownerService.modifyOwner(ownerDTO,ownerId);
    }

}
