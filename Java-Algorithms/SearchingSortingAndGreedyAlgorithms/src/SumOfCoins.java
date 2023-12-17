import java.util.*;
import java.util.stream.Collectors;

public class SumOfCoins {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));


        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

        for (Map.Entry<Integer, Integer> usedCoin : usedCoins.entrySet()) {
            System.out.println(usedCoin.getKey() + " -> " + usedCoin.getValue());
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
       Map<Integer, Integer> result = new LinkedHashMap<>();
       int currentSum = 0;
       int coinIndex = 0;

       Arrays.sort(coins);
       List<Integer> sortedCoins = Arrays.stream(coins).boxed().collect(Collectors.toList());
       Collections.reverse(sortedCoins);

       while (currentSum != targetSum && coinIndex < sortedCoins.size()) {
           int currentCoinValue = sortedCoins.get(coinIndex);
           int remainingSum = targetSum - currentSum;
           int numberOfCoinsToTake = remainingSum / currentCoinValue;

           if (numberOfCoinsToTake > 0) {
               result.put(currentCoinValue, numberOfCoinsToTake);
               currentSum += numberOfCoinsToTake * currentCoinValue;
           }
           coinIndex++;
       }

       return result;
    }
}