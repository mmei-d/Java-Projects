class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> courses = new HashMap<>();
        Queue<Integer> noPrereqs = new LinkedList<>();
        int classes = 0;
        
        for(int i = 0; i < numCourses; i++){
            courses.put(i, new ArrayList<>());
        }
        
        for(int[] prereq : prerequisites){
            ArrayList<Integer> pre = courses.get(prereq[0]);
            pre.add(prereq[1]);
            courses.put(prereq[0], pre);
        }
        
        for(Map.Entry<Integer, ArrayList<Integer>> course : courses.entrySet()){
            if(course.getValue().size() == 0) {
                noPrereqs.add(course.getKey());
                classes++;
            }
        }
        
        while(!noPrereqs.isEmpty()){
            int taken = noPrereqs.poll();
            for(Map.Entry<Integer, ArrayList<Integer>> course : courses.entrySet()){
                ArrayList<Integer> pre = course.getValue();
                if(pre.contains(taken)){
                    pre.remove(Integer.valueOf(taken));
                    courses.put(course.getKey(), pre);
                    if(course.getValue().size() == 0) {
                        noPrereqs.add(course.getKey()); 
                        classes++;
                    }
                } 
            }
        }
        
        return classes == numCourses;
    }
}