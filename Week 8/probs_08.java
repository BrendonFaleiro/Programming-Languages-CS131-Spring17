import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Main {
  static Stream<Integer> quickSort(Stream<Integer> ints) {
    return ints.findAny()
               .map(pivot -> Stream.of(quickSort(ints.parallel().filter(i -> i < pivot)),
                                       Stream.of(pivot),
                                       quickSort(ints.parallel().filter(i -> i > pivot)))
                                    .flatMap(s->s))
               .orElse(Stream.empty());
  }

  static Stream<Integer> quickSort2(Supplier<Stream<Integer>> ints) {
    return ints.get().findAny()
               .map(pivot -> Stream.of(
                     quickSort2(() -> ints.get().parallel().filter(i -> i < pivot)),
                     Stream.of(pivot),
                     quickSort2(() -> ints.get().parallel().filter(i -> i > pivot)))
                  .flatMap(s->s))
               .orElse(Stream.empty());
  }

  public static void main(String[] args) {
    List<Integer> l = new LinkedList<Integer>();

    for (int i = 0; i < 32; ++i) l.add(i);

    System.out.println("Stream:");
    l.stream().forEach(e -> System.out.print(e + " "));
    System.out.println();

    System.out.println("Parallel Stream:");
    l.parallelStream().forEach(e -> System.out.print(e + " "));
    System.out.println();

    System.out.println("Parallel Stream (Ordered):");
    l.parallelStream().forEachOrdered(e -> System.out.print(e + " "));
    System.out.println();



    long seed = System.nanoTime();
    Collections.shuffle(l, new Random(seed));

    System.out.println("Shuffled list:");
    l.stream().forEach(e -> System.out.print(e + " "));
    System.out.println();

    System.out.println("QSorted list:");
    try { quickSort(l.stream()).forEach(e -> System.out.print(e + " ")); }
    catch(Exception e) { e.printStackTrace(); System.out.print("EXCEPTION"); }
    System.out.println();

    System.out.println("QSorted2 list:");
    try { quickSort2(l::stream).forEach(e -> System.out.print(e + " ")); }
    catch(Exception e) { System.out.println("EXCEPTION"); }
    System.out.println();
}}