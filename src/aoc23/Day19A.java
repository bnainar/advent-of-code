package aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Day19A {
    static HashMap<String, HashMap<String, String>> workFlows = new HashMap<>();
    static HashMap<String, String> noMatchLocation = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String path = "C:/dev/advent-of-code/inputs/d";
        var input = (ArrayList<String>) Files.readAllLines(Paths.get(path));
        workFlows = new HashMap<>();
        noMatchLocation = new HashMap<>();
        int R = input.size(), i;
        for (i = 0; !input.get(i).isBlank(); i++) {
            var line = input.get(i);
            String wfName = line.substring(0, line.indexOf('{'));
            String[] rules = line.substring(line.indexOf('{') + 1, line.length() - 1).split(",");
            noMatchLocation.put(wfName, rules[rules.length - 1]);
            workFlows.computeIfAbsent(wfName, k -> new HashMap<>());
            for (int j = 0; j < rules.length - 1; j++) {
                var split = rules[j].split(":");
                workFlows.get(wfName).put(split[0], split[1]);
            }
        }
        long ans = 0L;
        for (i += 1; i < R; i++) {
            String line = input.get(i);
            String[] parts = line.substring(1, line.length() - 1).split(",");
            if(accept("in", parts))
                for (var xx : parts)
                    ans += Integer.parseInt(xx.substring(2));
        }
        System.out.println(ans);
    }

    private static boolean accept(String currWf, String[] parts) {
        if (currWf.equals("A"))
            return true;

        if (currWf.equals("R"))
            return false;

        for (var entry : workFlows.get(currWf).entrySet()) {
            String rule = entry.getKey();
            char ruleOp = rule.charAt(1);
            int ruleCompareNo = Integer.parseInt(rule.substring(2));
            for (var p : parts) {
                int partNo = Integer.parseInt(p.substring(2));
                if (rule.charAt(0) != p.charAt(0)) continue;
                if (ruleOp == '>' && partNo > ruleCompareNo) {
                    return accept(entry.getValue(), parts);
                } else if (ruleOp == '<' && partNo < ruleCompareNo) {
                    return accept(entry.getValue(), parts);
                }
            }
        }
        return accept(noMatchLocation.get(currWf), parts);
    }
}
