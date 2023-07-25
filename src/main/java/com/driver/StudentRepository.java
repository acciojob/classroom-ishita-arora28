package com.driver;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    Map<String,Student> studentMap=new HashMap<>();
    Map<String,Teacher> teacherMap=new HashMap<>();
    Map<String,List<String>> studentTeacherMap=new HashMap<>();
    
    public void addStudent(Student student) {
         studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
         teacherMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
         List<String> currList=new ArrayList<>();
         
         if(studentTeacherMap.containsKey(teacher)){
            currList=studentTeacherMap.get(teacher);
            
         }
         if(!currList.contains(student)){
            currList.add(student);
            
         }
        
         studentTeacherMap.put(student,currList);
    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
         List<String> studentList=new ArrayList<>();
        if(studentTeacherMap.containsKey(teacher))
            studentList=studentTeacherMap.get(teacher);
        return studentList;

    }

    public List<String> getAllStudents() {
        List<String> studentList=new ArrayList<>();
        for(String id:studentMap.keySet()){
            studentList.add(id);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher) {
            List<String> tempList=new ArrayList<>();
            if(studentTeacherMap.containsKey(teacher)){
                tempList=studentTeacherMap.get(teacher);
                for(String stud:tempList){
                    studentMap.remove(stud);
                }
                studentTeacherMap.remove(teacher);
            }
            teacherMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        // List<String> listOfTeachers=new ArrayList<>();
        for(String id: studentTeacherMap.keySet()){
          List<String> studentList=studentTeacherMap.get(id);
          for(String stud: studentList){
            if(studentMap.containsKey(stud))
                studentMap.remove(stud);
          }
        
        }
        teacherMap.clear();
        studentTeacherMap.clear();

    }



    
}
