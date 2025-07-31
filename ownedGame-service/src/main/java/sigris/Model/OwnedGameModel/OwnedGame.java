package sigris.Model.OwnedGameModel;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OwnedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerNickName;
    private String gameName;
    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    private Condition condition;
    private boolean isLocalized;
    private boolean isExpansion;
    private int editionNumber;
}
