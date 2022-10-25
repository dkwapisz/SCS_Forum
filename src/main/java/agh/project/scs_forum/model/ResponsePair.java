package agh.project.scs_forum.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class ResponsePair<T1, T2> {

    private T1 list;
    private T2 nrOfPages;

}
