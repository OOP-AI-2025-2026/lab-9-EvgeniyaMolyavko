package ua.opnu;

import java.util.*;

public class Task {
    public static void main(String[] args) {
    }

    public void removeShorterStrings(List<String> list) {
        for(int i=0; i<list.size()-1; i++){
            if (list.get(i).length() < list.get(i+1).length()){
                list.remove(list.get(i));
            } else if(list.get(i).length() > list.get(i+1).length()){
                list.remove(list.get(i+1));
            } else {
                list.remove(list.get(i));
            }
        }
    }

    public void stutter(List<String> list) {
        for(int i=0; i<list.size(); i+=2){
            list.add(i+1, list.get(i));
        }
    }

    public void switchPairs(List<String> list) {
        String str = "";
        for(int i=0; i<list.size()-1; i+=2){
            str = list.get(i);
            list.set(i, list.get(i+1));
            list.set(i+1, str);
        }
    }

    public void removeDuplicates(List<String> list) {
        int counter1 = 1;
        while (counter1 > 0){
            counter1 = 0;
            for(int i=0; i<list.size()-1; i++) {
                if(list.get(i).equals(list.get(i+1))){
                    list.remove(i+1);
                    counter1++;
                }
            }
        }
    }

    public void markLength4(List<String> list) {
        String stars = "****";
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).length() == 4){
                if(i==0){
                    list.add(0, stars);
                    i++;
                } else {
                    list.add(i, stars);
                    i++;
                }
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        while (!queue.isEmpty()){
            deque.addLast(queue.peek());
            queue.poll();
        }
        while(!deque.isEmpty()){
            if (deque.getFirst()==deque.getLast()){
                if(deque.size()>1){
                    deque.removeFirst();
                    deque.removeLast();
                } else {
                    deque.removeFirst();
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public void reorder(Queue<Integer> queue) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int n = queue.size();

        for (int i = 0; i < n; i++) {
            int val = queue.remove();
            if (val < 0) {
                deque.push(val);
            } else {
                queue.add(val);
            }
        }

        int negCount = deque.size();
        while (!deque.isEmpty()) {
            queue.add(deque.pop());
        }

        for (int i = 0; i < n - negCount; i++) {
            queue.add(queue.remove());
        }


    }

    public void rearrange(Queue<Integer> queue) {
            ArrayDeque<Integer> buffer = new ArrayDeque<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int val = queue.remove();
                if (val % 2 == 0) {
                    buffer.add(val);
                }
                queue.add(val);
            }
            for (int i = 0; i < size; i++) {
                int val = queue.remove();
                if (val % 2 != 0) {
                    buffer.add(val);
                }
                queue.add(val);
            }

        queue.clear();
        queue.addAll(buffer);
    }

    public int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.length() % 2 == 0) {
                iterator.remove();
            }
        }
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        HashSet<Integer> hshSet = new HashSet<>(list1);
        Iterator<Integer> iterator = hshSet.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            if (list2.indexOf(iterator.next())>=0) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isUnique(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if(list.indexOf(map.get(key))<0){
                list.add(map.get(key));
            } else {
                return false;
            }
        }
        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> mapFinal = new HashMap<>();
        for (Map.Entry<String, Integer> mapPair : map1.entrySet()) {
            if(map2.containsKey(mapPair.getKey())){
                if(map2.get(mapPair.getKey()).equals(mapPair.getValue())){
                    mapFinal.put(mapPair.getKey(), mapPair.getValue());
                }
            }
        }
        return mapFinal;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> mapFinal = new HashMap<>();
        for (Map.Entry<Integer, String> mapPair : map.entrySet()) {
            mapFinal.put(mapPair.getValue(), mapPair.getKey());
        }
        return mapFinal;
    }

    public int rarest(Map<String, Integer> map) {
        Map<Integer, Integer> mapFinal = new HashMap<>();
        ArrayList<Integer> values = new ArrayList<>(map.values());
        for (int i = 0; i < values.size(); i++) {
            if (!mapFinal.containsKey(values.get(i))) {
                mapFinal.put(values.get(i), 1);
            } else {
                mapFinal.put(values.get(i), mapFinal.get(values.get(i)) + 1);
            }
        }
        Optional<Integer> minValue = mapFinal.values().stream().min(Integer::compareTo);
        Optional<Integer> key = Optional.empty();
        if (minValue.isPresent()) {
            key = mapFinal.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(minValue.get()))
                    .map(Map.Entry::getKey)
                    .min(Integer::compareTo);

            
    
        }
        return key.get();
    }

    public int maxOccurrences(List<Integer> list) {
        Map<Integer, Integer> mapFinal = new HashMap<>();
        list.stream().forEach(el -> {
            if(mapFinal.containsKey(el)){
                mapFinal.put(el, mapFinal.get(el)+1);
            } else {
                mapFinal.put(el, 1);
            }
        });
        Optional<Integer> op = mapFinal.values().stream().max(Integer::compareTo);

        return op.isPresent()?op.get():0;
    }

}
