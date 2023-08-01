package javaInAction.chapter11;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class chapter11Main {

    public static void main(String[] args) {
//        Person person = new Person();
//        Optional<Person> optPerson = Optional.of(person);
//        // 컴파일에러 Optional<Optional<Car>> 으로 반환 되어서
////        optPerson.map(Person::getCar)
////                .map(Car::getInsurance)
////                .map(Insurance::getName);
//        optPerson.flatMap(Person::getCar)
//                .flatMap(Car::getInsurance)
//                .map(Insurance::getName)
//                .orElse("Unknow");

//        int[] rank = {0}; // 랭킹2: 동일스코어 동일 랭킹
//        AtomicInteger rank = new AtomicInteger(0);

        List<PlayerRank> playerList = List.of(new PlayerRank(1L, 0), new PlayerRank(2L,0));

//        Map<Long, PlayerRank> playerRankMap = playerList.stream()
//                .sorted(Comparator.comparing(PlayerRank::getRank).reversed())
//                .map(p -> new PlayerRank(p.getPlyrIdx(), ++rank[0])) // 2023.03.13 랭킹 동점자 룰로 인한 수정 (조수빈)
//                .distinct() // 플레이어 중복 제거
//                .collect(Collectors.toMap(PlayerRank::getPlyrIdx, o -> o));
        Map<Long, PlayerRank> playerRankMap = playerList.stream()
                .sorted(Comparator.comparing(PlayerRank::getRank).reversed())
//                .map(p -> new PlayerRank(p.getPlyrIdx(), rank.incrementAndGet())) // 2023.03.13 랭킹 동점자 룰로 인한 수정 (조수빈)
                .distinct() // 플레이어 중복 제거
                .map(PlayerRank::calculateRank)
                .peek(System.out::println)
                .collect(Collectors.toMap(
                        PlayerRank::getPlyrIdx,
                        rank -> rank));
//                .collect(Collectors.toMap(
//                        PlayerRank::getPlyrIdx,
//                        PlayerRank::calculateRank));

        playerRankMap.forEach((idx , playerRank) -> {
            System.out.println("idx = " + idx +", playerRank = " + playerRank);
        });
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }
}
