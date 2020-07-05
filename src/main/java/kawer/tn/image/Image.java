package kawer.tn.image;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    private Long id;
    private String name;
    private byte[] image;

}
