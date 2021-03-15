import java.util.*;
import java.lang.*;

class Range {
    int start, end;

    Range(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

//Comparator to sort in ascending order based on range start date
class CustomComparator implements Comparator<Range> {
    public int compare(Range a, Range b) {
        return a.start - b.start;
    }
}

public class MergeRanges {
    public static void mergeRangeList(List<Range> ranges) {
        Collections.sort(ranges, new CustomComparator());

        int index = 0;

        for (int i = 1; i < ranges.size(); i++) {
            //merge the ranges if it overlaps
            if (ranges.get(index).end >= ranges.get(i).start) {
                ranges.get(index).end = Math.max(ranges.get(index).end, ranges.get(i).end);
                ranges.get(index).start = Math.min(ranges.get(index).start, ranges.get(i).start);
            } else {
                index++;
                ranges.get(index).start = ranges.get(i).start;
                ranges.get(index).end = ranges.get(i).end;
            }
        }
        // remove the unwanted remaining elements from list
        int remElements = ranges.size() - index + 1;
        index++;
        while (remElements > 1 && index < ranges.size()) {
            ranges.remove(index);
            remElements--;
        }

    }

    public static void main(String[] args) {
        List<Range> ranges = new ArrayList<>();
        // Uncomment below lines fro test case-1
        System.out.println("Test case-1 result");
        ranges.add(new Range(94133,94133));
        ranges.add(new Range(94200,94299));
        ranges.add(new Range(94600,94699));

        // Uncomment below lines fro test case-2
        /*System.out.println("Test case-2 result");
        ranges.add(new Range(94133,94133));
        ranges.add(new Range(94200,94299));
        ranges.add(new Range(94226,94399));*/

        mergeRangeList(ranges);

        for (int i = 0; i < ranges.size(); i++) {
            System.out.print("[" + ranges.get(i).start + "," + ranges.get(i).end + "]");
        }
    }
}