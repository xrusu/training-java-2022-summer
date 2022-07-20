package hw3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@RequiredArgsConstructor
public class Pair implements Comparable<Pair> {
    private final UUID left;
    private final Card right;

    @Override
    public int compareTo(Pair o) {
        return left.compareTo(o.left);
    }
}
