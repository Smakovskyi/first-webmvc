package ua.kpi.firstwebmvc.services;


//import org.apache.logging.log4j.util.PropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kpi.firstwebmvc.entities.Student;

/**
 * Created by anastasiastepanova on 1/11/19.
 */
@Component
public class StudentService {
    List<Student> students = new ArrayList<>(Arrays.asList(
            Student.builder().name("first").rate(10.).id(1).build(),
            Student.builder().name("second").rate(12.).id(2).build()
    ));

    public List<Student> getAll(){
        Comparator<Student> comp = Comparator.comparing(Student::getName)
                .thenComparing(Student::getRate);
        return students.stream().sorted(
                //(a,b)->a.getName().compareTo(b.getName())
                comp ).collect(Collectors.toList());
    }

    public Optional<Student> findByName(String name){
        return students.stream().filter( stud-> Objects.equals( stud.getName() , name)).findFirst();
    }
    public Optional<Student> getStudentById (int id) {
        return students.stream().filter(stud -> stud.getId() == id).findFirst();
    }

    public synchronized Student create(Student newStudent) {
        int id = students.size()+1;
        newStudent.setId(id);
        students.add(newStudent);
        return newStudent;
    }

    public boolean updateStudent(Student student) {
        int index = -1;
        for (int i = 0; i<students.size(); i++){
            if (students.get(i).getId() == student.getId()){
                index = i;
                break;
            }
        }
        if (index != -1){
            students.set(index, student);
            return true;
        }
        else
            return false;
    }

    public boolean deleteStudent(int id) {
        int index = -1;
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        if(index == -1) return false;
        students.remove(index);
        return true;

    }

}