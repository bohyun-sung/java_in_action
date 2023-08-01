package javaInAction.chapter11;


import java.util.Objects;
import java.util.Optional;

public class PlayerRank {
    private Long plyrIdx;
    private Integer rank;

    public PlayerRank(Long plyrIdx, Integer rank) {
        this.plyrIdx = plyrIdx;
        this.rank = rank;
    }

    public Long getPlyrIdx() {
        return plyrIdx;
    }

    public Integer getRank() {
        return rank;
    }

    public PlayerRank calculateRank() {
        int newRank = Optional.ofNullable(this.rank)
                .map(rank -> rank + 1)
                .orElse(1);
//        int newRank = Optional.ofNullable(this.rank).orElse(0) + 1;
//        this.rank = Optional.ofNullable(this.rank).orElse(0);
//        ++ this.rank;
        return new PlayerRank(this.plyrIdx, newRank);
    }

    private void initRank(Long idx) {
        new PlayerRank(idx, null);
    }

    @Override
    public String toString() {
        return "PlayerRank{" +
                "plyrIdx=" + plyrIdx +
                ", rank=" + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerRank that = (PlayerRank) o;
        return plyrIdx.equals(that.plyrIdx) && rank.equals(that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plyrIdx, rank);
    }
}
