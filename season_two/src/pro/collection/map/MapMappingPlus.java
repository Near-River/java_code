package pro.collection.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分拣存储:
 * --计算班级的考试总分和平均分
 * Created by Near on 2015/11/29.
 */
public class MapMappingPlus {
    @Test
    public void testPlus() {
        Map<String, ClassRoom> map = new HashMap<String, ClassRoom>();

        List<Student> students = new ArrayList<Student>();
        students.add(new Student("小明", "a", 85.0));
        students.add(new Student("小红", "b", 86.0));
        students.add(new Student("小高", "a", 89.0));
        students.add(new Student("小刚", "b", 80.0));
        students.add(new Student("小李", "b", 98.0));

        for (Student student : students) {
            String classId = student.getClassId();
            ClassRoom classRoom = null;
            if (!map.containsKey(classId)) {
                classRoom = new ClassRoom(classId);
                map.put(classId, classRoom);
            } else {
                classRoom = map.get(classId);
            }
            classRoom.getStudents().add(student);
            classRoom.setTotal(classRoom.getTotal() + student.getScore());
        }

        for (String classId : map.keySet()) {
            ClassRoom classRoom = map.get(classId);
            System.out.println(classRoom.getClassId() + " --> " + classRoom.getTotal() +
                    " --> " + classRoom.getTotal() / classRoom.getStudents().size());
        }
    }

    class Student {
        private String name;
        private String classId;
        private double score;

        public Student() {
        }

        public Student(String name, String classId, double score) {
            this.name = name;
            this.classId = classId;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }

    class ClassRoom {
        private String classId;
        private List<Student> students;
        private double total;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public ClassRoom() {
            students = new ArrayList<Student>();
        }

        public ClassRoom(String classId) {
            this();
            this.classId = classId;
        }

        public ClassRoom(String classId, List<Student> students, double total) {
            this();
            this.classId = classId;
            this.students = students;
            this.total = total;
        }
    }
}